package spring_mvc_hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring_mvc_hibernate.model.User;
import spring_mvc_hibernate.service.UserService;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getTable(Model model) {
        List<User> table = userService.getUsers();
        model.addAttribute("table", table);
        return "table";
    }

    @PostMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("userData", new User());
        return "add_user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("userData") User user) {
            userService.addUser(user);
        return "redirect:/";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("userData", userService.getUser(id));
        return "update_user";
    }

    @PostMapping("/updateUserData")
    public String updateUserData(@ModelAttribute("userData") User user) {
            userService.updateUser(user);
        return "redirect:/";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
