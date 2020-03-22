package yaroslav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import yaroslav.model.User;
import yaroslav.service.UserService;

@Controller
public class AllPermisController {

    private UserService userService;

    @Autowired
    public void setUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @PostMapping(value = "/login")
    public ModelAndView loginFine(@ModelAttribute("username") String name, @ModelAttribute("password") String pass) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        modelAndView.addObject("user", userService.get(name, pass));
        return modelAndView;
    }

    @GetMapping(value = "/")
    public ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView reg() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView addNewUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            modelAndView.setViewName("registration");
            modelAndView.addObject("passwordError", "Пароли не совпадают");
            return modelAndView;
        }
        if (!userService.add(user)) {
            modelAndView.setViewName("registration");
            modelAndView.addObject("usernameError", "Пользователь с таким именем уже существует");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
