package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        System.out.println("Inside home method of controller");
        System.out.println("This returns default view of index.jsp");
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        System.out.println("Inside about method of controller");
        return "about";
    }
}
