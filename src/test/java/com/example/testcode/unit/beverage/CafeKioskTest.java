package com.example.testcode.unit.beverage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.testcode.unit.Latte;
import com.example.testcode.unit.order.Order;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CafeKioskTest {

  @Test
  void add_manual_test() {
    // 👇 수동 테스트 (사람이 콘솔 출력으로 직접 확인)
    // given
    CafeKiosk cafeKiosk = new CafeKiosk();

    // when
    cafeKiosk.add(new Americano());

    // then
    // 사람이 직접 콘솔 출력 결과를 보고 판단해야 함
    System.out.println(">>> 담긴 음료 수 : " + cafeKiosk.getBeverages().size());
    System.out.println(">>> 담긴 음료 : " + cafeKiosk.getBeverages().get(0).getName());
  }

  @DisplayName("음료 1개를 추가하면 주문 목록에 담긴다.")
  @Test
  void add_auto_test() {
    // ✅ 자동 테스트 (JUnit이 직접 검증)
    // given
    CafeKiosk cafeKiosk = new CafeKiosk();

    // when
    cafeKiosk.add(new Americano());

    // then
    // 코드가 스스로 결과를 검증 (사람이 콘솔을 볼 필요 없음)
    assertThat(cafeKiosk.getBeverages()).hasSize(1); // 기대값: 1개
    assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노"); // 기대값: "아메리카노"
  }

  @Test
  void addSeveralBeverages() {
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();

    cafeKiosk.add(americano, 2);

    assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
    assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
  }

  @Test
  void addZeroBeverages() {
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();


    assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
  }

  @Test
  void remove_auto_test() {
    // ✅ 자동 테스트 (삭제 동작 검증)
    // given
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();

    // when
    cafeKiosk.add(americano);
    assertThat(cafeKiosk.getBeverages()).hasSize(1); // 추가된 상태 확인

    // then
    cafeKiosk.remove(americano);
    assertThat(cafeKiosk.getBeverages()).isEmpty(); // 기대값: 비어 있음
  }

  @Test
  void clear_auto_test() {
    // ✅ 자동 테스트 (전체 초기화 검증)
    // given
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();
    Latte latte = new Latte();

    // when
    cafeKiosk.add(americano);
    cafeKiosk.add(latte);
    assertThat(cafeKiosk.getBeverages()).hasSize(2); // 추가된 상태 확인

    // then
    cafeKiosk.clear();
    assertThat(cafeKiosk.getBeverages()).isEmpty(); // 기대값: 비어 있음
  }

  @Test
  void createOrderWithCurrentTime(){
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();
    cafeKiosk.add(americano);

    Order order = cafeKiosk.createOrder(LocalDateTime.of(2025, 1, 17, 14, 0));

    assertThat(order.getBeverages()).hasSize(1);
    assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
  }

  @Test
  void createOrderOutsideOpenTime(){
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();
    cafeKiosk.add(americano);


    assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2025, 1, 17, 9, 59)))
        .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요.");
  }


  @DisplayName("주문 목록에 담긴 상품들의 총 그맹긍ㄹ 계산할 수 있다.")
  @Test
  void calculateTotalPrice(){
      // given
      CafeKiosk cafeKiosk = new CafeKiosk();
      Americano americano = new Americano();
      Latte latte = new Latte();

      cafeKiosk.add(americano);
      cafeKiosk.add(latte);

      // when
      int totalPrice = cafeKiosk.calculateTotalPrice();

      // then
    assertThat(totalPrice).isEqualTo(8500);
  }
}
