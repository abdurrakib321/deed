package rockytech.online.deed_writer.common

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import rockytech.online.deed_writer.users.model.User

interface AuthHelperDetails {
    fun getAuthentication(): Authentication {
        return SecurityContextHolder.getContext().authentication
    }

    fun getUser(): User? {
        val auth = getAuthentication()
        return  if (auth.principal is User) auth.principal as User else null
    }

    fun getUserId(): String? {
        return getUser()?.phoneNo
    }
}