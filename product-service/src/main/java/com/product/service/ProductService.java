package com.product.service;

import com.product.model.ProductRequest;
import com.product.model.ProductResponse;

import java.util.List;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProduct(long productId);
}
