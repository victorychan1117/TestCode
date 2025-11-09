package com.example.testcode.unit.order;

import com.example.testcode.unit.beverage.Beverage;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {

  private final LocalDateTime orderDateTime;
  private final List<Beverage> beverages;

}
