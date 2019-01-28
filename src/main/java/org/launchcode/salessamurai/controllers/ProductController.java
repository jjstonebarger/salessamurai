package org.launchcode.salessamurai.controllers;

import org.launchcode.salessamurai.models.Product;
import org.launchcode.salessamurai.models.Users;
import org.launchcode.salessamurai.models.Wholesalers;
import org.launchcode.salessamurai.models.data.ProductDAO;
import org.launchcode.salessamurai.models.data.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "")
    public String landing(Model model){
        model.addAttribute("title", "Welcome to SalesSamurai!");
        return "product/landing";
    }


    @RequestMapping(value = "home")
    public String index(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")){
            return "redirect:/users/login";
        }
        Users u = userDAO.findByUsername(username).get(0);
        model.addAttribute("products", u.getProducts());
        model.addAttribute("title", "My Products");

        return "product/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddProductForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")){
            return "redirect:/users/login";
        }

        model.addAttribute("title", "Add Product");
        model.addAttribute(new Product());
        model.addAttribute("wholesalers", Wholesalers.values());

        return "product/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddProductForm(@ModelAttribute @Valid Product newProduct, Errors errors, Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")){
            return "redirect:/users/login";
        }
        Users u = userDAO.findByUsername(username).get(0);
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Product");
            model.addAttribute("times", Wholesalers.values());
            return "product/add";
        }
        newProduct.setUser(u);
        productDAO.save(newProduct);


        return "redirect:" + newProduct.getId();
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveProductForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")){
            return "redirect:/users/login";
        }
        Users u = userDAO.findByUsername(username).get(0);
        model.addAttribute("products", u.getProducts());
        model.addAttribute("title", "Remove Product");
        return "product/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveProductForm(@RequestParam int[] productIds) {
        for (int productId : productIds) {
            productDAO.delete(productId);

        }
        return "redirect:";
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public String displaySingleProduct(Model model, @PathVariable int productId, @CookieValue(value = "user", defaultValue = "none") String username){
        if(username.equals("none")){
            return "redirect:/users/login";
        }
        Users u = userDAO.findByUsername(username).get(0);
        model.addAttribute("product", productDAO.findOne(productId));
        return "Product/product";
    }

   /* @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editProductForm(Model model,  @CookieValue(value = "user", defaultValue = "none") String username, ){

        if(username.equals("none")){
            return "redirect:/users/login";
        }

        Users u = userDAO.findByUsername(username).get(0);
        Product userProduct = u.getProducts(productDAO.equals(productId));
        model.addAttribute("title", "Edit Product");
        model.addAttribute("product", userProduct);
        model.addAttribute("times", Time.values());
    }*/
}