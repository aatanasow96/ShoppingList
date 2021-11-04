package com.example.shoppinglist.web;

import com.example.shoppinglist.model.binding.ProductAddBindModel;
import com.example.shoppinglist.entity.service.ProductServiceModel;
import com.example.shoppinglist.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper mapper;

    public ProductController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("productAddBindModel")) {
            model.addAttribute("productAddBindModel", new ProductAddBindModel());
        }

        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ProductAddBindModel productAddBindModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindModel", productAddBindModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.productAddBindModel",
                            bindingResult);

            return "redirect:add";
        }

        productService.add(mapper.map(productAddBindModel, ProductServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buyById(@PathVariable Long id) {
        productService.buyById(id);

        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAll(){
        productService.buyAll();

        return "redirect:/";
    }
}

