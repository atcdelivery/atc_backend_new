package com.atcdilivery.spring.jwt.mongodb.serviceImpl;

import com.atcdilivery.spring.jwt.mongodb.entity.ProductCategory;
import com.atcdilivery.spring.jwt.mongodb.entity.Products;
import com.atcdilivery.spring.jwt.mongodb.payload.request.ProductRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;
import com.atcdilivery.spring.jwt.mongodb.repository.ProductCategoryRepository;
import com.atcdilivery.spring.jwt.mongodb.repository.ProductRepository;
import com.atcdilivery.spring.jwt.mongodb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public Response addProduct(ProductRequest productRequest){
        Response response = new Response();
        if (!ObjectUtils.isEmpty(productRequest)){
            Products products = new Products();
            products.setProductName(productRequest.getProductName());
            products.setProductPrice(products.getProductPrice());
            products.setProductQuantity(products.getProductQuantity());
            products.setCategoryId(productRequest.getCategoryId());
            products.setProductPrice(products.getProductPrice());
            products.setCategoryName(productRequest.getCategoryName());
            products.setUserId(productRequest.getUserId());
            productRepository.save(products);

            response.setStatusCode(200);
            response.setMessage("Product Save Successfully");
            return response;
        }
        response.setStatusCode(409);
        response.setMessage("Some fields are empty");
        return response;
    }
    @Override
    public Response editProduct(ProductRequest productRequest, String id){
        Products product = productRepository.findById(id).get();
        Response response = new Response();
        if (!ObjectUtils.isEmpty(product)){
            product.setProductPrice(productRequest.getProductPrice());
            product.setCategoryId(productRequest.getCategoryId());
            product.setCategoryName(productRequest.getCategoryName());
            product.setProductName(productRequest.getProductName());
            product.setProductCode(productRequest.getProductCode());
            product.setProductQuantity(productRequest.getProductQuantity());
            productRepository.save(product);

            response.setStatusCode(200);
            response.setMessage("Product Edited Successfully");
            return response;
        }

        response.setStatusCode(409);
        response.setMessage("Product Not Found");
        return response;
    }

    @Override
    public Products getProductById(String id){
        return productRepository.findById(id).get();
    }

    @Override
    public List<Products> getAllProducts(String userId){
        return productRepository.findByUserId(userId);
    }

    @Override
    public Response addCategory(String categoryName){
        ProductCategory productCategory = new ProductCategory();
        Response response = new Response();

        if (!categoryName.equals(null) && !categoryName.equals(" ")){
            productCategory.setCategoryName(categoryName);
            productCategory.setCreateDate(new Date());
            productCategory.setModifyDate(new Date());
            productCategoryRepository.save(productCategory);

            response.setStatusCode(200);
            response.setMessage("Category Added Successfully");
            return response;
        }

        response.setStatusCode(409);
        response.setMessage("Some Values are Empty");
        return response;
    }

    @Override
    public Response editCategory(String categoryName, String id){
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
        Response response = new Response();
        if (!ObjectUtils.isEmpty(productCategory)){
            productCategory.get().setCategoryName(categoryName);
            productCategory.get().setModifyDate(new Date());
            productCategoryRepository.save(productCategory.get());

            response.setStatusCode(200);
            response.setMessage("Category Edited Successfully");
            return response;
        }

        response.setStatusCode(409);
        response.setMessage("Category Not Found");
        return response;
    }

    @Override
    public List<ProductCategory> getAllCategory(String name){
        return productCategoryRepository.findByCategoryName(name);
    }

    @Override
    public List<ProductCategory> getAllCategories(){
        return productCategoryRepository.findAll();
    }
}
