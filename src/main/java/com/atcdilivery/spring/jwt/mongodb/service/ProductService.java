package com.atcdilivery.spring.jwt.mongodb.service;

import com.atcdilivery.spring.jwt.mongodb.entity.ProductCategory;
import com.atcdilivery.spring.jwt.mongodb.entity.Products;
import com.atcdilivery.spring.jwt.mongodb.payload.request.ProductRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;

import java.util.List;

public interface ProductService {

    Response addProduct(ProductRequest productRequest);

    Response editProduct(ProductRequest productRequest, String id);

    Products getProductById(String id);

    List<Products> getAllProducts(String userId);

    Response addCategory(String categoryName);

    Response editCategory(String categoryName, String id);

    List<ProductCategory> getAllCategory(String userId);

    List<ProductCategory> getAllCategories();
}
