package codework.codework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping("/")
    public String homePage(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "homepage";
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

    @RequestMapping("/login")
    public String login(){
        return "loginform";
    }


    @RequestMapping("/addmessage")
    public String addMessage(Model model){
        model.addAttribute("model", new Message());
        return "messageform";
    }

    @PostMapping("/processmessage")
        public String processMessage(@Valid @ModelAttribute("message") Message message, BindingResult result){
         if (result.hasErrors()){
             return "messageform";
         }
        messageRepository.save(message);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showmessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("meassage", messageRepository.findById(id).get());
        return "messageform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMessage(@PathVariable("id") long id){
        messageRepository.deleteById(id);
        return "redirect:/";
    }

}
