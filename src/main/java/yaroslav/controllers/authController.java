package yaroslav.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class authController {

    @GetMapping(value = "/")
    public ModelAndView auth(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:admin");
        return modelAndView;
    }
}
