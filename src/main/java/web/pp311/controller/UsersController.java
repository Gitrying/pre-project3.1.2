package web.pp311.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.pp311.model.User;
import web.pp311.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


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
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "add";
        }
        userService.add(user);
        return "redirect:/";
    }

    //User show method
    @GetMapping("/{id}")
    public String userById(@PathVariable("id") int id, Model model) {
        if (userService.getById(id)!=null){
            model.addAttribute("user", userService.getById(id));
            return "info";
        }else{
            List<String> messages = new ArrayList<>();
            messages.add("Ошибка");
            messages.add("Такого пользователя не существует (*μ_μ)");
            model.addAttribute("messages", messages);
            return "unknown_user";
        }
    }


    //Edit Method, show edit page
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user")@Valid User user,BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "edit";
        }
        userService.edit(user, id);
        return "redirect:/";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}