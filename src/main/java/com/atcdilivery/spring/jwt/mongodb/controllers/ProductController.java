package com.atcdilivery.spring.jwt.mongodb.controllers;

import com.atcdilivery.spring.jwt.mongodb.entity.ProductCategory;
import com.atcdilivery.spring.jwt.mongodb.entity.Products;
import com.atcdilivery.spring.jwt.mongodb.payload.request.ProductRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;
import com.atcdilivery.spring.jwt.mongodb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     *  Add Category By USER ID
     * @param categoryName
     * @return
     */

    @GetMapping("/add-category/{categoryName}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response addCategory(@PathVariable String categoryName){
        return productService.addCategory(categoryName);
    }

    /**
     *  Edit Category By CategoryID
     * @param categoryName
     * @param id
     * @return
     */

    @GetMapping("/edit-category/{categoryName}/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response editCategory(@PathVariable String categoryName,@PathVariable String id){
        return productService.editCategory(categoryName,id);
    }

    @GetMapping("/get-all-category")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<ProductCategory> getALlActegory(){
        return productService.getAllCategories();
    }


    /**
     * Get Category By UserID
     * @param name
     * @return
     */

    @GetMapping("/get-category/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<ProductCategory> getCategories(@PathVariable String name){
        return productService.getAllCategory(name);
    }



    /**
     * Add Product By UserID
     * @param productRequest
     * @return
     */

    @PostMapping("/add-product")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response addProduct(@RequestBody ProductRequest productRequest){
        return productService.addProduct(productRequest);
    }

    /**
     * Edit Product By ProductId
     * @param productRequest
     * @param id
     * @return
     */

    @PostMapping("/edit-product/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response editProduct(@RequestBody ProductRequest productRequest, @PathVariable String id){
       return productService.editProduct(productRequest,id);
    }

    /**
     * Get Product By ID
     * @param id
     * @return
     */

    @GetMapping("/get-by-productId/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Products getProductById(@PathVariable String id){
        return productService.getProductById(id);
    }

    /**
     * Get ALl Products
     * @param userId
     * @return
     */

    @GetMapping("/get-all-products/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Products> getAllProducts(@PathVariable String userId){
        return productService.getAllProducts(userId);
    }

}
