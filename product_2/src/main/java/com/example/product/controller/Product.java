package com.example.product.controller;

import com.example.product.enumm.Provider;
import com.example.product.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class Product {
    private final ProductService productService;
    @Autowired
    public Product(ProductService productService) {
        this.productService = productService;
    }

    // Данный метод позволяет получить get параметры и добавить товар в ArrayList из DAO класса
//    @GetMapping("/product/add")
//    public void addProduct(@RequestParam(value = "name", required = false) String name,
//                           @RequestParam(value = "price", required = false) float price,
//                           @RequestParam(value = "weight", required = false) String weight,
//                           @RequestParam(value = "provider", required = false) Provider provider){
//        daoProduct.addProduct(name, price, weight, provider);
//        // http://localhost:8080/product/add?name=Хлеб&price=50&weight=300&provider=LadyLux
//        // http://localhost:8080/product/add?name=Молоко&price=100&weight=1000&provider=OBi
//    }

    // метод позволяет получить список всех продуктов и вернуть html страницу
    @GetMapping("/product")
    public String index(Model model){
        model.addAttribute("product",productService.getAllProduct());
        return "product";
    }
    // Метод позволяет получить объект из листа по id
    @GetMapping("/product/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductId(id));
        return "product_info";
    }
    // Метод позволяет отобразить представление с формой добавления товара
    @GetMapping("/product/add")
    public String newProduct(Model model){
        model.addAttribute("product", new com.example.product.models.Product());
        return "add_product";
    }
    // метод добавляет новый продукт из формы в лист
    @PostMapping("/product/add")
    public String newProduct(@ModelAttribute("product") @Valid com.example.product.models.Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "add_product";
        }
        productService.addProduct(product);
        return "redirect:/product";
    }
    // получение редактируемого продукта по id и возврат его формы
    @GetMapping("product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("edit_product", productService.getProductId(id));
        return "edit_product";
    }
    // метод принимает редактируемы объект с формы и обновляет информацию о нем
    @PostMapping("product/edit/{id}")
    public String edit_Product(@ModelAttribute("edit_product") @Valid com.example.product.models.Product product, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "edit_product";
        }
        productService.editProduct(id,product);
        return "redirect:/product/" + id;
    }
    @GetMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/product";
    }
}