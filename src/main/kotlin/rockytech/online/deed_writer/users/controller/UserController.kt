package rockytech.online.deed_writer.users.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import rockytech.online.deed_writer.common.AuthHelperDetails
import rockytech.online.deed_writer.users.model.User
import rockytech.online.deed_writer.users.service.UserService

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) : AuthHelperDetails {
    @PatchMapping()
    fun addAdminDetails(@RequestBody user: User):User{
       val phoneNo=getUserId()
        return userService.addAdminDetails(phoneNo!!,user)
    }

    @GetMapping("/signup")
    fun showSignupPage(): ModelAndView {
        // Serve the HTML page for signup
        return ModelAndView("signup")
    }
    @GetMapping("/login")
    fun showSignInPage(): ModelAndView {
        // Serve the HTML page for signup
        return ModelAndView("login")
    }

    @GetMapping()
    fun getAllUsers():List<User>{
        return userService.getAllUsers();
    }

}

