package controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.sun.beans.introspect.PropertyInfo.Name.required;

@Controller
public class ContactController {
    @RequestMapping(path="contact",method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "contact";
    }

    @RequestMapping(path="processForm",method = RequestMethod.POST)
    public String formProcess (@RequestParam(name = "email", required=true) String email,
                               @RequestParam(name ="userName", required=false) String userName,
                               @RequestParam("password") String password , Model model) {
        System.out.println(email);
        System.out.println(userName);
        System.out.println(password);
        model.addAttribute("email",email);
        model.addAttribute("userName",userName);
        model.addAttribute("password",password);

        return "success";
    }


}
