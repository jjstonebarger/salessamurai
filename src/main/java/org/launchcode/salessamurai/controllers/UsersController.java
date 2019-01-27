package org.launchcode.salessamurai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("title", "Create Account");
        model.addAttribute("user", new Users());
        return "users/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(Model model, @ModelAttribute @Valid Users user,
                          Errors errors, String confirm, HttpServletResponse response) {
        List<Users> sameUsername = userDAO.findByUsername(user.getUsername());

        if (!errors.hasErrors() && user.getPassword().equals(confirm) && sameUsername.isEmpty()) {
            model.addAttribute("user", user);
            userDAO.save(user);

            Cookie c = new Cookie("user", user.getUsername());
            c.setPath("/");
            response.addCookie(c);
            return "redirect:/product/home";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("title", "Create Account");
            if (!user.getPassword().equals(confirm)) {
                model.addAttribute("message", "Passwords must match");
                user.setPassword("");
            }
            if (!sameUsername.isEmpty()) {
                model.addAttribute("message", "Username already in use, please choose again.");
            }
            return "users/add";
        }
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login (Model model){
        model.addAttribute("title", "Login");
        model.addAttribute(new Users());
        return "users/login";
    }

    @RequestMapping(value="login", method = RequestMethod.POST)
    public String loginUser(Model model, @ModelAttribute Users user, HttpServletResponse response){
        List<Users> i = userDAO.findByUsername(user.getUsername());
        if(i.isEmpty()){
            model.addAttribute("message", "Invalid Username");
            model.addAttribute("title", "Login");
            return "users/login";
        }

        Users loggedIn = i.get(0);
        if(loggedIn.getPassword().equals(user.getPassword())){
            Cookie c = new Cookie("user", user.getUsername());
            c.setPath("/");
            response.addCookie(c);
            return "redirect:/product/home";
        }else{
            model.addAttribute("message", "Invalid Password");
            model.addAttribute("title", "Login");
            return "users/login";
        }
    }


    @RequestMapping(value ="logout")
    public String logout(Model model,HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie c : cookies){
                c.setMaxAge(0);
                c.setPath("/");
                response.addCookie(c);
            }
        }
        return "redirect:/users/login";
    }
}


