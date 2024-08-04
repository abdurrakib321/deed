package rockytech.online.deed_writer.users.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import rockytech.online.deed_writer.common.exception.CustomException
import rockytech.online.deed_writer.users.model.User
import rockytech.online.deed_writer.users.model.UserDto
import rockytech.online.deed_writer.users.repository.UserRepository
import java.io.Console
import java.util.*

@Service
class UserService(
    private  val userRepository: UserRepository,
    @Value("\${default.password}") private val defaultPassword: String,
) :UserDetailsService{
    fun getUserById(id: String): User {
        return userRepository.findById(id).orElseThrow {
            NoSuchElementException("User not found with id: $id")
        }
    }

    fun updateUser(id: String, newUser: User): User {
        val existingUser = getUserById(id)
        return userRepository.save(existingUser)
    }

    fun deleteUser(id: String) {
        userRepository.deleteById(id)
    }


    override fun loadUserByUsername(phoneNo: String?): User {
        if (phoneNo == null) {
            throw UsernameNotFoundException("Username is null")
        }

        val userObj: Optional<User> = userRepository.findByPhoneNo(phoneNo)
        val authUser: User = userObj.orElseThrow {
            UsernameNotFoundException("User not found with username: $phoneNo")
        }

        return authUser
    }

    fun addAdminDetails( phoneNo: String,user: User):User{
        val userResponse = userRepository.findByPhoneNo(phoneNo).get()
        println("Before update:")
        println("email: ${userResponse.email}")
        println("userDto: ${userResponse.userDto}")

        userResponse.apply {
            name=user.name
            email = user.email
            userDto = user.userDto
        }

        println("After update:")
        println("email: ${userResponse.email}")
        println("userDto: ${userResponse.userDto?.postOffice}")

        return userRepository.save(userResponse)

    }

    fun signup(user: User): User {
       // user.passwordhash=passwordEncoder.encode(user.passwordhash)
        val existingUser = userRepository.findByPhoneNo(user.phoneNo!!)
        if (existingUser.isPresent) {
            throw CustomException("User with phone number ${user.phoneNo} already exists.")
        }
        if (!user.authoritiess.contains("USER")) {
            user.authoritiess = user.authoritiess + "USER"
        }
      //  user.passwordhash = passwordEncoder.encode(user.passwordhash) // Encode the password

        val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
        val context: SecurityContext = SecurityContextHolder.createEmptyContext()
        context.authentication = authentication
        SecurityContextHolder.setContext(context)
        return userRepository.save(user)
    }

    fun getAllUsers():List<User>{
        return userRepository.findAll()
    }

}