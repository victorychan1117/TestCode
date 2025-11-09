package com.example.testcode.spring.api.service.order;

import com.example.testcode.spring.api.controller.order.request.OrderCreateRequest;
import com.example.testcode.spring.api.service.order.response.OrderResponse;
import com.example.testcode.spring.domain.order.Order;
import com.example.testcode.spring.domain.order.OrderRepository;
import com.example.testcode.spring.domain.product.Product;
import com.example.testcode.spring.domain.product.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
    List<String> productNumbers = request.getProductNumbers();
    List<Product> duplicateProducts = findProductsBy(
        productNumbers);

    Order order = Order.create(duplicateProducts, registeredDateTime);
    Order saveOrder = orderRepository.save(order);

    return OrderResponse.of(saveOrder);
  }

  private List<Product> findProductsBy(List<String> productNumbers) {
    List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
    Map<String, Product> productMap = products.stream()
        .collect(Collectors.toMap(Product::getProductNumber, p -> p));

    return productNumbers.stream()
        .map(productMap::get)
        .toList();
  }
}
