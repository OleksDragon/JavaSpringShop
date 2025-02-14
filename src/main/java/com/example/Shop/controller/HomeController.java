package com.example.Shop.controller;

import com.example.Shop.model.Product;
import com.example.Shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/read")
    public String read(Model model) {
        model.addAttribute("products", productService.findAll());
        return "crud/read";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product(0, "", "", 0.0, 0));
        return "crud/save";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/read";
    }

    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());
            return "crud/update";
        }
        return "redirect:/read"; // Перенаправление, если продукт не найден
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable long id, @ModelAttribute Product product) {
        productService.update(id, product);
        return "redirect:/read";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.findById(id).ifPresent(productService::delete);
        return "redirect:/read";
    }
}