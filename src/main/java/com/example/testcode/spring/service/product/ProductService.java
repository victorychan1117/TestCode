package com.example.testcode.spring.service.product;

import com.example.testcode.spring.domain.product.Product;
import com.example.testcode.spring.domain.product.ProductRepository;
import com.example.testcode.spring.service.product.response.ProductResponse;
import com.example.testcode.spring.domain.product.ProductSellingStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

  private final ProductRepository productRepository;

  public List<ProductResponse> getSellingProducts(){
    List<Product> products = productRepository.findAllBySellingStatusIn(
        ProductSellingStatus.forDisplay());

    return products.stream()
        .map(ProductResponse::of)
        .toList();
  }

}
