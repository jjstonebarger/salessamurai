package org.launchcode.salessamurai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping(value="search")
public class SearchController {

    @Autowired
    public ProductDAO productDAO;

    @Autowired
    public UserDAO userDAO;

    @RequestMapping(value="")
    public String searchProducts(Model model) {
        model.addAttribute("title", "Search Products");
        model.addAttribute(new SearchForm());
        return "search/index";
    }

    @RequestMapping(value = "results")
    public String searchProducts(Model model, @ModelAttribute SearchForm searchForm, @CookieValue(value = "user", defaultValue = "none") String username){
        if(username.equals("none")){
            return "redirect:/users/login";
        }
        model.addAttribute("title", "Search Products");
        Users u = userDAO.findByUsername(username).get(0);
        ArrayList<Product> products = (ArrayList<Product>) productDAO.findByUserId(u.getId()); //access to all products for user

        ArrayList<Product> matchingProducts = new ArrayList<>();

        for (Product product : products) {

            if (product.getName().toLowerCase().contains(searchForm.getKeyword())) {
                matchingProducts.add(product);
            }
        }
        model.addAttribute("products", matchingProducts);
        return "search/index";
    }



}
