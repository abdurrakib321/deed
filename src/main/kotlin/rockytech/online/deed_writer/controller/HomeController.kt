package rockytech.online.deed_writer.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import rockytech.online.deed_writer.common.AuthHelperDetails
import rockytech.online.deed_writer.users.service.UserService

@Controller
class HomeController(private  val userService: UserService):AuthHelperDetails {
    @RequestMapping(value = ["{_:^(?!index\\.html|deed).*\$}"])
    fun redirect(): String = "forward:/"
    @GetMapping("/status")
    fun getStatus():String{

        return "server is running"
    }
    @PostMapping("/test")
    fun getServer():String{
        val phone=getUserId()
        println("phone number is $phone")
        return "test success"
    }
    @GetMapping("/api/dashboard")
    fun getDashboard():ModelAndView{
        return ModelAndView("dashboard")
    }
}