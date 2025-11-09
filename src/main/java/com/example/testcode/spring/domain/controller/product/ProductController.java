package com.example.testcode.spring.domain.controller.product;

import com.example.testcode.spring.service.product.ProductService;
import com.example.testcode.spring.service.product.response.ProductResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {


  private final ProductService productService;

  @GetMapping("/api/v1/products/selling")
  public List<ProductResponse> getSellingProducts(){
    return productService.getSellingProducts();
  }

}
