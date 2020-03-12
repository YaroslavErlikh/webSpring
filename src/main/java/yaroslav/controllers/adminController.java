package yaroslav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import yaroslav.model.User;
import yaroslav.service.UserService;

import java.util.List;

@Controller
public class adminController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/admin")
    public ModelAndView allUsers() {
        List<User> users = service.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/adminUsers");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/admin/editUser/{id}")
    public ModelAndView editPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/editUser");
        modelAndView.addObject("user", service.getById(id));
        return modelAndView;
    }

    @PostMapping(value = "/admin/edit")
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        service.edit(user);
        return modelAndView;
    }

    @GetMapping(value = "/admin/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/addUser");
        return modelAndView;
    }

    @PostMapping(value = "/admin/add")
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        service.add(user);
        return modelAndView;
    }

    @PostMapping(value = "/admin/deleteUser/{id}")
    public ModelAndView deletePage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/deleteUser");
        modelAndView.addObject("user", service.getById(id));
        return modelAndView;
    }

    @GetMapping(value = "/admin/deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        service.delete(id);
        return modelAndView;
    }
}