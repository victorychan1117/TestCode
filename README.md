## 💡 테스트 코드

<details>
  <summary>Why Do We Need Tests?</summary>

### 🧩 테스트가 없을 때 발생하는 문제
- 커버할 수 없는 영역 발생
- 경험과 감에 의존
- 늦은 피드백
- 유지보수 어려움
- 소프트웨어 신뢰 하락

### 🎯 우리가 얻고자 하는 것
- 빠른 피드백
- 자동화
- 안정감

### ⚠️ 테스트 코드를 작성하지 않는다면
- 변화가 생길 때마다 모든 경우의 수를 고려해야 한다.
- 변화가 생길 때마다 모든 팀원이 동일한 고민을 반복해야 한다.
- 빠르게 변화하는 소프트웨어의 안정성을 보장할 수 없다.

### 🧱 테스트 코드가 병목이 된다면
- 프로덕션 코드의 안정성을 보장하기 어렵다.
- 테스트 코드 자체가 유지보수하기 어려운 짐이 된다.
- 잘못된 검증이 이루어질 가능성이 생긴다.

### ✅ 올바른 테스트 코드는
- 자동화 테스트로 빠르게 버그를 발견하고, 수동 테스트 비용을 절약한다.
- 소프트웨어의 변화를 유연하게 지원한다.
- 팀원들의 집단 지성을 팀 차원의 이익으로 승격시킨다.
- 가까이 보면 느리지만, 멀리 보면 가장 빠르다.

</details>

---

<details>
  <summary>Manual Testing vs Automated Testing</summary>

### ⚙️ 테스트 방식의 차이

| 구분 | 수동 테스트 (Manual Test) | 자동 테스트 (Automated Test) |
|------|----------------------------|-------------------------------|
| **수행 주체** | 사람이 직접 실행 | 기계(코드)가 자동으로 검증 |
| **목적** | 사용자의 입장에서 기능이 잘 동작하는지 확인 | 코드의 동작이 의도대로 수행되는지 검증 |
| **속도** | 느림 — 사람이 직접 클릭, 확인 | 빠름 — 코드 실행만으로 즉시 결과 확인 |
| **정확성** | 사람의 실수 가능성 존재 | 일관되고 정확한 결과 |
| **비용** | 초기 비용은 낮지만 반복 시 높음 | 초기 작성 비용은 높지만 반복 시 거의 0원 |
| **적합한 영역** | UI, UX, 시각적 검증 | 로직, API, 비즈니스 규칙 검증 |

💬 **요약**
> 수동 테스트는 사람이 “믿음을 얻기 위한 과정”이고,  
> 자동 테스트는 시스템이 “그 믿음을 지키기 위한 장치”이다.

---

### 🧩 테스트 기법

- **단위 테스트 (Unit Test)**  
  → 작은 코드 단위를 독립적으로 검증하는 테스트 (클래스, 메서드 단위)  
  → 검증 속도가 빠르고, 안정적이다.  
  → 작성하기 쉽고, 자동화하기 좋다.

- **통합 테스트 (Integration Test)**  
  → 여러 모듈이 올바르게 협력하는지 검증  
  → DB, 외부 API, 서비스 간 연동 확인에 사용

- **인수 테스트 (Acceptance Test)**  
  → 실제 사용자 시나리오 기준으로 시스템 전체 동작 검증  
  → “요구사항이 잘 구현되었는가?”에 초점

---

### 🧱 단위 테스트 핵심 도구

- **JUnit5**  
  → Java에서 단위 테스트를 위한 표준 프레임워크  
  → `@Test`, `@BeforeEach`, `@AfterEach` 등의 어노테이션 제공  
  → XUnit 계열 (Kent Beck이 만든 SUnit → JUnit → NUnit)

- **AssertJ**  
  → 테스트 검증(Assertion)을 더 읽기 쉽게 만들어주는 라이브러리  
  → 풍부한 API와 **메서드 체이닝** 지원  
  → 예시:
    ```java
    assertThat(result)
        .isNotNull()
        .contains("success")
        .startsWith("OK");
    ```

---

### ✅ 정리

- 수동 테스트는 **사람이 직접 확인**하며 신뢰를 쌓는 과정
- 자동 테스트는 **기계가 반복적으로 검증**하며 신뢰를 유지하는 과정
- 단위 테스트는 그 자동화의 핵심이며, **JUnit5 + AssertJ**로 쉽게 구현할 수 있다.

</details>

---

<details>
  <summary>테스트 케이스 세분화 (Happy / Exception / Boundary)</summary>

테스트를 설계할 때는 하나의 기능을 다양한 시나리오로 나누어 검증해야 한다.  
대표적으로 아래 세 가지 유형으로 구분한다.

| 구분 | 설명 | 목적 |
|------|------|------|
| ✅ **해피 케이스 (Happy Case)** | 정상 입력 시, 예상한 대로 동작하는 경우 | “정상적인 흐름이 잘 작동하는가?” |
| ⚠️ **예외 케이스 (Exception Case)** | 잘못된 입력이나 예외 상황을 처리하는 경우 | “에러 상황을 올바르게 처리하는가?” |
| ⚙️ **경계값 테스트 (Boundary Test)** | 값의 경계(이상/이하/초과/미만)를 검증하는 경우 | “한계선에서 동작이 안정적인가?” |

---

### 🧪 테스트 코드 예시

```java
@Test
void addSeveralBeverages() {
  // ✅ 해피 케이스 (정상 흐름)
  // given
  CafeKiosk cafeKiosk = new CafeKiosk();
  Americano americano = new Americano();

  // when
  cafeKiosk.add(americano, 2);

  // then
  assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
  assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
}

@Test
void addZeroBeverages() {
  // ⚠️ 예외 케이스
  CafeKiosk cafeKiosk = new CafeKiosk();
  Americano americano = new Americano();

  assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
}

@Test
void addBeveragesBoundaryTest() {
  // ⚙️ 경계값 테스트
  CafeKiosk cafeKiosk = new CafeKiosk();
  Americano americano = new Americano();

  // count = 1 → 허용되는 최소값
  cafeKiosk.add(americano, 1);
  assertThat(cafeKiosk.getBeverages()).hasSize(1);

  // count = 0 → 허용되지 않는 경계선 값
  assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");

  // count = -1 → 유효 범위 미만
  assertThatThrownBy(() -> cafeKiosk.add(americano, -1))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
}
```
</details>

---

<details>
  <summary>테스트하기 어려운 코드 vs 테스트하기 쉬운 코드</summary>

### ⚠️ 테스트하기 어려운 코드

테스트하기 어려운 코드는 **외부 환경이나 실행 시점에 의존하는 코드**입니다.  
즉, 실행할 때마다 결과가 달라지거나, 외부 시스템에 영향을 주는 코드입니다.

#### 💥 대표적인 유형

| 유형 | 설명 |
|------|------|
| ⏰ **현재 시간/날짜 의존** | `LocalDateTime.now()` 등 실행 시점마다 다른 값을 생성 |
| 🎲 **랜덤 값 사용** | `Math.random()`처럼 실행할 때마다 결과가 달라짐 |
| 🌍 **전역 변수/상태 의존** | 다른 테스트나 실행 환경의 영향을 받음 |
| 💬 **외부 입출력 의존** | 콘솔 출력, 메시지 전송, DB 저장 등 외부 리소스에 의존 |

---

### 💡 예시 1: 테스트하기 어려운 코드

```java
public class CafeKiosk {

    private static final LocalTime SHOP_OPEN_TIME = LocalTime.of(9, 0);  // 9시
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22, 0); // 22시
    private List<Beverage> beverages = new ArrayList<>();

    public Order createOrder() {
        LocalDateTime currentDateTime = LocalDateTime.now(); // ⚠️ 실행 시점마다 달라짐
        LocalTime currentTime = currentDateTime.toLocalTime();

        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.");
        }

        return new Order(LocalDateTime.now(), beverages);
    }
}

public class CafeKiosk {

    private static final LocalTime SHOP_OPEN_TIME = LocalTime.of(9, 0);
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22, 0);
    private List<Beverage> beverages = new ArrayList<>();

    // 💡 외부에서 시간을 주입받아 테스트 가능하게 개선
    public Order createOrder(LocalDateTime currentDateTime) {
        LocalTime currentTime = currentDateTime.toLocalTime();

        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.");
        }

        return new Order(currentDateTime, beverages);
    }
}

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class CafeKioskTest {

    @Test
    void createOrderWithinBusinessHours() {
        CafeKiosk kiosk = new CafeKiosk();
        LocalDateTime testTime = LocalDateTime.of(2024, 11, 9, 10, 0); // 오전 10시 (영업시간 내)

        Order order = kiosk.createOrder(testTime);

        assertThat(order).isNotNull();
    }

    @Test
    void createOrderOutsideBusinessHours() {
        CafeKiosk kiosk = new CafeKiosk();
        LocalDateTime testTime = LocalDateTime.of(2024, 11, 9, 23, 0); // 오후 11시 (영업시간 외)

        assertThatThrownBy(() -> kiosk.createOrder(testTime))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요.");
    }
}
```
</details>

---
<details>
  <summary>정리</summary>

### 🧩 수동 테스트 vs 자동 테스트
- **가장 큰 차이점**: 테스트 결과를 검증하는 **주체**이다.  
  → 수동 테스트는 **사람의 눈으로**,  
  → 자동 테스트는 **기계(코드)**가 검증한다.

---

### ⚙️ 유닛 테스트(Unit Test)의 대상과 특징
- **검증 대상**: 코드의 작은 단위 (클래스 / 메서드)
- **특징**: 다른 코드와 **독립적으로 동작**해야 하며, 빠르고 반복 가능한 검증이 가능하다.  
  → 즉, 시스템의 일부분을 **분리하여 독립적으로 검증**하는 테스트이다.

---

### 🧱 AssertJ의 주요 장점
- **풍부한 검증(Assertion) API 제공**
- **가독성 높은 문법**을 통해 다양한 조건을 명확하게 표현할 수 있다.  
  예:
  ```java
  assertThat(result)
      .isNotNull()
      .startsWith("OK")
      .contains("success");
  ```
---

### ⚙️ 경계값 테스트 (Boundary Test)
- 조건이 **“정수 값이 3 이상일 때”**라면,  
  → **경계값은 3** (조건이 막 시작되는 값)  
  → **바로 아래 값은 2** (조건이 막 깨지는 값)  
  두 값을 우선적으로 검증해야 한다.  
  즉, “조건의 경계 지점과 그 주변 값”을 확인하는 것이 핵심이다.

---

### 💡 테스트하기 어려운 코드 분리 이유
- **목적**: 테스트의 **예측 가능성**과 **안정성 확보**
- **예시**: `LocalDateTime.now()`처럼 실행 시마다 결과가 달라지는 코드  
  → 외부 환경에 의존하므로 테스트가 불안정해짐  
  → 이를 **테스트 가능한 영역(의존 주입 등)**으로 분리하면  
  **결과가 일정하고 재현 가능한 테스트**를 작성할 수 있다.
</details>

---

<details>
  <summary>테스트의 의미</summary>

테스트 코드는 단순히 검증 도구가 아니라,  
**프로덕션 코드의 의도를 설명하는 살아있는 문서**이다.

#### 🧩 테스트 코드의 가치
- **문서적 역할**  
  → 프로덕션 코드가 어떻게 동작해야 하는지를 명확히 보여주는 **실행 가능한 문서**이다.
- **이해의 보완**  
  → 다양한 테스트 케이스를 통해 프로덕션 코드를 **다른 시각과 관점에서 이해**할 수 있다.
- **지식의 공유**  
  → 한 개발자가 과거에 겪은 문제 해결의 흔적을  
  **팀 전체의 자산으로 승격**시켜 공유할 수 있다.

💬 **요약**
> 테스트 코드는 “검증 도구”를 넘어,  
> “이해하고 공유하는 문서이자 학습 도구”이다.
</details>

---

<details>
  <summary>@DisplayName을 섬세하게 작성하기</summary>

### 🎯 핵심 개념
`@DisplayName`은 테스트의 **행위 자체가 아니라, 그 행위의 결과와 의도**를 표현해야 한다.  
즉, **“무엇을 테스트했는가”보다 “왜, 어떤 규칙에 따라 그렇게 되어야 하는가”**를 드러내는 문장으로 작성한다.

---

### ⚙️ 예시 비교

| 구분 | 잘못된 예시 ❌ | 올바른 예시 ✅ | 설명 |
|------|----------------|----------------|------|
| 테스트 행위만 서술 | `음료를 1개 추가할 수 있다.` | `음료를 1개 추가하면 주문 목록에 담긴다.` | 단순한 기능 설명이 아닌 **행위의 결과까지 표현** |
| 현상 중심 표현 | `특정 시간 이전에 주문을 생성하면 실패한다.` | `영업 시작 시간 이전에는 주문을 생성할 수 없다.` | **도메인 용어**를 사용해 규칙 중심으로 서술 |
| 메서드 관점 서술 | `addOrder 메서드는 성공한다.` | `주문은 영업 시간 내에만 생성할 수 있다.` | 코드보다는 **도메인 정책**을 기준으로 작성 |

---

### ⚙️ 예시 코드

```java
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
```

### 💡 작성 원칙
1. **행위 + 결과**를 함께 기술한다.  
   → 단순히 "할 수 있다"보다는, "하면 ~된다" 형태로 작성  
   예: `상품을 장바구니에 추가하면 총 금액이 증가한다.`

2. **도메인 용어로 표현한다.**  
   → "성공한다 / 실패한다" 같은 기술적 표현보다  
   → "주문할 수 있다 / 불가능하다"처럼 **비즈니스 용어** 사용

3. **구현보다 정책 중심으로 쓴다.**  
   → 메서드 이름이나 내부 로직이 아닌, **업무 규칙의 관점**으로 설명

---

### ✅ 정리
> `@DisplayName`은 단순히 테스트 이름이 아니라,  
> **테스트의 의도와 도메인 규칙을 설명하는 문장형 문서**이다.
>
> “무엇을 테스트했는가?”보다  
> “왜, 어떤 상황에서 어떤 결과가 나와야 하는가?”를 표현하라.

</details>

---
<details>
  <summary>BDD (Behavior Driven Development)</summary>

### 💡 BDD란?
- **TDD(Test Driven Development)**에서 파생된 개발 방법론
- 함수 단위의 테스트보다 **시나리오(행동)** 중심으로 테스트를 작성
- **개발자가 아닌 사람도 읽고 이해할 수 있는 수준의 추상화**를 권장

즉,
> “어떤 상황(Given)에서, 어떤 행동(When)을 하면, 어떤 결과(Then)가 발생한다.”
>
> 라는 **행동 기반 시나리오(Behavior Scenario)**를 중심으로 테스트를 기술한다.

---

### ⚙️ 시나리오 구조 (Given / When / Then)

| 단계 | 의미 | 예시 |
|------|------|------|
| **Given** | 시나리오 진행에 필요한 준비 과정 (객체, 값, 상태 등) | 주문 목록에 아메리카노와 라떼를 담는다. |
| **When** | 시나리오에서 발생하는 행동 | 총 주문 금액을 계산한다. |
| **Then** | 시나리오의 기대 결과 | 총 금액이 8,500원이 되어야 한다. |

---

### 🧪 테스트 코드 예시

```java
@DisplayName("주문 목록에 담긴 상품들의 총 금액을 계산할 수 있다.")
@Test
void calculateTotalPrice() {
    // 🧩 Given (준비)
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();
    Latte latte = new Latte();

    cafeKiosk.add(americano);
    cafeKiosk.add(latte);

    // 🧩 When (행동)
    int totalPrice = cafeKiosk.calculateTotalPrice();

    // 🧩 Then (결과 검증)
    assertThat(totalPrice).isEqualTo(8500);
}
```

</details>

---

<details>
  <summary>레이어드 아키텍처(Layered Architecture)와 테스트</summary>

### 💡 레이어드 아키텍처란?
- 애플리케이션을 **역할과 책임에 따라 계층(Layer)** 으로 구분하는 구조이다.
- 각 계층은 **자신의 역할에만 집중**하고,  
  **상위 계층이 하위 계층을 의존하는 방향(one-way)** 으로 설계된다.

#### 🎯 각 계층의 역할

| 계층 | 주요 역할 | 설명 |
|------|------------|------|
| **Client** | 요청 발신자 | 사용자의 요청을 서버로 전달 |
| **Presentation Layer** | 표현 계층 (Controller) | 클라이언트 요청을 수신하고 응답 반환. 비즈니스 로직 호출 담당 |
| **Business Layer** | 비즈니스 계층 (Service) | 도메인 로직과 트랜잭션 관리. 실제 “업무 규칙”을 구현 |
| **Persistence Layer** | 영속성 계층 (Repository) | DB 접근, CRUD 수행. 비즈니스 로직에서 데이터 접근을 추상화 |
| **Database** | 데이터 저장소 | 실제 데이터가 저장되는 물리적 계층 |


### 🧩 통합 테스트(Integration Test)란?
- **여러 모듈이 협력하는 기능을 통합적으로 검증하는 테스트**이다.  
  예를 들어, Controller → Service → Repository → DB 로직이 올바르게 연결되어 동작하는지 확인한다.

---

### ⚙️ 통합 테스트의 필요성
- 단위 테스트(Unit Test)는 작은 코드 단위를 검증하는 데 집중하지만,  
  **기능 전체의 신뢰성은 보장하지 못한다.**
- 실제 서비스는 여러 계층(Controller, Service, Repository 등)이 상호 작용하므로,  
  이 협력 관계를 검증하는 통합 테스트가 필수적이다.

---

### 🧩 테스트의 균형
- **단위 테스트(Unit Test)** → 작은 기능을 빠르고 정확하게 검증
- **통합 테스트(Integration Test)** → 계층 간 협력을 실제 환경처럼 검증

> ✅ **핵심:**  
> “풍부한 단위 테스트”로 개별 기능을 견고하게 만들고,  
> “적절한 통합 테스트”로 시스템 전체의 신뢰성을 확보하라.

</details>

---

<details>
  <summary>라이브러리 vs 프레임워크 / Spring / JPA / ORM</summary>

### ⚖️ 라이브러리 vs 프레임워크

| 구분 | 라이브러리 (Library) | 프레임워크 (Framework) |
|------|------------------------|--------------------------|
| **제어의 흐름** | 개발자가 직접 제어 | 프레임워크가 제어 (IoC, 제어의 역전) |
| **호출 주체** | 개발자가 라이브러리를 호출 | 프레임워크가 개발자의 코드를 호출 |
| **자유도** | 필요할 때 호출하여 사용 | 정해진 구조와 규칙에 따라 동작 |
| **예시** | Lombok, Gson, Apache Commons | Spring, Django, Angular |

> 💡 정리:  
> “라이브러리는 내가 가져다 쓰는 도구이고,  
> 프레임워크는 나를 불러서 실행하는 틀이다.”

---

### 🌱 Spring Framework

**Spring**은 Java 진영의 대표적인 애플리케이션 프레임워크로,  
IoC / DI / AOP를 중심으로 구성되어 있다.

#### 🧩 주요 개념

| 개념 | 의미 | 설명 |
|------|------|------|
| **IoC (Inversion of Control)** | 제어의 역전 | 객체의 생명주기와 의존성 관리를 개발자가 아닌 **Spring Container**가 담당 |
| **DI (Dependency Injection)** | 의존성 주입 | 필요한 객체를 직접 생성하지 않고 **외부에서 주입받는 방식** |
| **AOP (Aspect Oriented Programming)** | 관점 지향 프로그래밍 | 공통 로직(로깅, 보안, 트랜잭션 등)을 분리하여 **핵심 로직과 횡단 관심사를 분리** |

> 💬 Spring은 “**객체 지향 원칙을 실제로 구현할 수 있게 해주는 틀**”이다.

---

### 🧱 JPA (Java Persistence API)

- Java 진영의 **ORM(Object Relational Mapping) 기술 표준 인터페이스**
- 대표 구현체로 **Hibernate**가 있다.
- 반복적인 **CRUD SQL을 자동으로 생성 및 실행**해주며, 다양한 부가 기능을 제공한다.

#### 🧩 주요 특징
- **생산성 향상**: SQL 작성 없이 CRUD 처리 가능
- **객체 중심 개발**: 엔티티와 테이블을 매핑
- **추상화된 데이터 접근**: 구현체 교체 용이

#### ⚙️ 주요 애노테이션
| 애노테이션 | 설명 |
|-------------|------|
| `@Entity` | JPA 엔티티 클래스임을 명시 |
| `@Id` | 기본 키(PK) 지정 |
| `@Column` | 컬럼 속성 정의 |
| `@ManyToOne`, `@OneToMany` | 다대일 / 일대다 관계 매핑 |
| `@OneToOne`, `@ManyToMany` | 일대일 / 다대다 관계 매핑 *(단, 다대다는 중간 테이블로 풀어 사용하는 것이 일반적)* |

#### 🧩 Spring Data JPA
- Spring이 **JPA를 한 번 더 추상화**한 기술
- `JpaRepository`를 통해 CRUD를 간단히 처리 가능
- **QueryDSL**과 함께 사용 시  
  → **타입 안정성 + 동적 쿼리 작성** 가능

> 💡 단, 편리함에 의존해 내부 동작(쿼리 생성 과정)을 모르면 성능 이슈로 이어질 수 있으므로  
> “자동화된 쿼리가 **어떻게 동작하는지 이해하는 것**”이 중요하다.

---

### 🧩 ORM (Object Relational Mapping)

- **객체 지향 패러다임**과 **관계형 데이터베이스 패러다임** 간의 불일치를 해결하는 기술
- 과거에는 개발자가 객체를 직접 SQL로 매핑해야 했지만,  
  ORM은 이 과정을 **자동화**하여 **비즈니스 로직에 집중**할 수 있게 해준다.

#### ⚙️ ORM의 장점
| 항목 | 설명 |
|------|------|
| **생산성** | CRUD SQL 작성 생략으로 개발 속도 향상 |
| **유지보수성** | 객체 중심 구조로 코드 일관성 유지 |
| **이식성** | 데이터베이스 교체 시 코드 변경 최소화 |
| **집중력** | 개발자가 비즈니스 로직에만 집중 가능 |

---

### ✅ 정리

| 구분 | 핵심 개념 | 요약 |
|------|-------------|------|
| **Library** | 개발자가 호출하는 도구 | “내가 사용한다.” |
| **Framework** | 나를 호출하는 틀 | “나를 사용한다.” |
| **Spring** | IoC / DI / AOP 기반 프레임워크 | 객체 제어를 프레임워크에 위임 |
| **JPA** | ORM 표준 인터페이스 | SQL 대신 객체로 DB 접근 |
| **ORM** | 객체-테이블 매핑 기술 | 반복적 SQL을 자동화, 비즈니스에 집중 |

> 🎯 **핵심 요약:**  
> Spring은 **제어의 역전(IoC)** 을 통해 객체의 생명주기를 관리하고,  
> JPA는 **ORM 기술 표준**으로 객체 중심의 데이터 접근을 가능하게 한다.
>
> 즉, “**Spring + JPA = 객체 중심의 선언적 프로그래밍 기반 아키텍처**”이다.

</details>

---

<details>
  <summary>Repository 테스트 예시 — ProductRepositoryTest</summary>

### 🎯 테스트 목적
> **특정 판매 상태(SELLING, HOLD)에 해당하는 상품만 DB에서 조회되는지 검증한다.**

이 테스트는 `@DataJpaTest`를 활용하여  
`ProductRepository`의 쿼리 메서드(`findAllBySellingStatusIn`)가  
의도한 대로 동작하는지를 검증한다.

---

### 🧱 테스트 구조

```java
package com.example.testcode.spring.domain.product;

import static com.example.testcode.spring.domain.product.ProductSellingStatus.*;
import static com.example.testcode.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest // ✅ Repository 계층 테스트용 어노테이션
class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
  @Test
  void findAllBySellingStatusIn() {
    // given — 테스트용 데이터 준비
    Product product1 = Product.builder()
        .productNumber("001")
        .type(HANDMADE)
        .sellingStatus(SELLING)
        .name("아메리카노")
        .price(4000)
        .build();

    Product product2 = Product.builder()
        .productNumber("002")
        .type(HANDMADE)
        .sellingStatus(HOLD)
        .name("카페라떼")
        .price(4500)
        .build();

    Product product3 = Product.builder()
        .productNumber("003")
        .type(HANDMADE)
        .sellingStatus(STOP_SELLING)
        .name("팥빙수")
        .price(7000)
        .build();

    productRepository.saveAll(List.of(product1, product2, product3));

    // when — 특정 판매 상태(SELLING, HOLD)로 조회
    List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

    // then — 검증
    assertThat(products).hasSize(2)
        .extracting("productNumber", "name", "sellingStatus")
        .containsExactly(
            tuple("001", "아메리카노", SELLING),
            tuple("002", "카페라떼", HOLD)
        );
  }
}
```
</details>