package com.product.service;

import com.product.entity.Product;
import com.product.exceptions.ProductNotFoundException;
import com.product.model.ProductRequest;
import com.product.model.ProductResponse;
import com.product.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product...");

        Product product = Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quanity(productRequest.getQuantity())
                .build();

        productRepository.save(product);
        log.info("Product created..");
        return product.getProductId();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        log.info("get all products..");
        return productRepository.findAll()
                 .stream()
                 .map(product ->
                         new ProductResponse(product.getProductId(),
                                 product.getProductName(),
                                 product.getPrice(),
                                 product.getQuanity()))
                 .toList();
    }

    @Override
    public ProductResponse getProduct(long productId) {
           return productRepository
                   .findById(productId)
                   .map(this::mapToProductResponse)
                   .orElseThrow(()-> new ProductNotFoundException("Product not found", "PRODUCT_NOT_FOUND"));

    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getProductId());
        response.setName(product.getProductName());
        response.setPrice(product.getPrice());
        return response;
    }
}
