package com.farukyilmaz.spring.boot.thymeleaf.crud.controllers;

import com.farukyilmaz.spring.boot.thymeleaf.crud.models.Image;
import com.farukyilmaz.spring.boot.thymeleaf.crud.models.Mail;
import com.farukyilmaz.spring.boot.thymeleaf.crud.models.User;
import com.farukyilmaz.spring.boot.thymeleaf.crud.services.SecurityService;
import com.farukyilmaz.spring.boot.thymeleaf.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class HomeController {

    private final UserService userService;
    private final SecurityService securityService;
    private final MailController mailController;
    @Autowired
    public HomeController(UserService userService, SecurityService securityService, MailController mailController) {
        this.userService = userService;
        this.securityService = securityService;
        this.mailController = mailController;
    }

    @GetMapping("/index")
    public String showIndex(Model model) {
        return "index";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getList());
        return "users";
    }

    @GetMapping("/adduser")
    public String showAddUserForm(User user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model,
                            @RequestParam("imagefile") MultipartFile file) throws IOException
    {
        if (result.hasErrors()) {
            return "add-user";
        }
        user.setImage(file.getBytes());
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String showUpdateUserForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model,
                                @RequestParam("imagefile") MultipartFile file) throws IOException
    {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        user.setImage(file.getBytes());
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        userService.delete(user);
        return "redirect:/users";
    }

    @GetMapping("/error")
    public String showError(Model model) {
        return "error";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/sendmail/{id}")
    public String showEmailForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        Mail mail = new Mail();
        mail.setMail(user.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("mail", mail);
        return "send-mail";
    }

    @PostMapping("/sendmail/{id}")
    public String sendEmailToUser(@PathVariable("id") long id,@Valid Mail mail,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "error";
        }
        mailController.sendMail(mail.getMail(),mail.getText(),mail.getSubject());
        return "redirect:/users";
    }
}
