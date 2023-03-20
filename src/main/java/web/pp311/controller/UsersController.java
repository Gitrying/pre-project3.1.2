package web.pp311.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.pp311.model.User;
import web.pp311.service.UserService;


@Controller
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    //Starting page/
    @GetMapping("/")
    public String usersPage(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "users";
    }

    //Add user method GET
    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    //Add user method Post
    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    //User show method
    @GetMapping("/{id}")
    public String userById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "info";
    }


    //Edit Method, show edit page
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id){
        userService.edit(user, id);
        return "redirect:/";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}