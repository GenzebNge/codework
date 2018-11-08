package codework.codework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String homePage(){
        return "homepage";
    }


    @RequestMapping("/login")
    public String login(){
        return "loginform";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registrationform";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "registrationform";
        }
        else {
            userService.saveUser(user);
            model.addAttribute("message", "User account created");
        }
        return "redirect:/";
    }

//    @RequestMapping("/message")
//    public String login(){
//        return "list";
//    }
//
//    @RequestMapping("/process")
//    public String processMessage(){
//
//        return "redirect:/";
//
//    }

}
