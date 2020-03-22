package yaroslav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import yaroslav.model.User;
import yaroslav.model.role.Role;
import yaroslav.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        modelAndView.addObject("userEditing", service.getById(id));
        modelAndView.addObject("rolelist", service.getAllRoles());
        return modelAndView;
    }

    @PostMapping(value = "/admin/edit")
    public ModelAndView editUser(
            @ModelAttribute("id") Long id,
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            @RequestParam("roles") String[] roles
    ) {
        User user = service.getById(id);
        user.setUsername(username);
        user.setPassword(password);

        Set<Role> rolesNew = new HashSet<>();

        for (String s : roles) {
            if (s.equals("ROLE_ADMIN")) {
                rolesNew.add(new Role(2L, "ROLE_ADMIN"));
            }
            if (s.equals("ROLE_USER")) {
                rolesNew.add(new Role(1L, "ROLE_USER"));
            }
        }

        user.setRoles(rolesNew);

        ModelAndView modelAndView = new ModelAndView();
        if (!service.getByName(username).getId().equals(id) && service.userIsExist(user)) {
            modelAndView.addObject("message", "Имя занято");
            modelAndView.setViewName("/admin/editUser");
            return modelAndView;
        }

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

        if (!service.userIsExist(user)) {
            modelAndView.setViewName("redirect:/admin");
            service.add(user);
            return modelAndView;
        }

        modelAndView.addObject("message", "Имя занято");
        modelAndView.setViewName("redirect:/admin/addUser");
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