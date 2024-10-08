package com.product.controller;

import com.product.model.ProductRequest;
import com.product.model.ProductResponse;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    Environment environment;

    @GetMapping("/active-profiles")
    public Optional<String> ping(){
       String[] activeProfiles = environment.getActiveProfiles();
        return Arrays.stream(activeProfiles).findFirst();
    }
    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        long productId = productService.addProduct(productRequest);

        return  new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){

        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") long id){
            return new ResponseEntity<>(productService.getProduct(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId,
                                               @RequestParam long quantity){
        productService.reduceQuantity(productId, quantity);
       return new ResponseEntity<>(HttpStatus.OK);
    }

}
