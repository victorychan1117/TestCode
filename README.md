## 💡 TDD

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
  → `@Test`, `@BeforeEach`, `@AfterEach` 등의 어노테이션을 제공  
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
  // 정상적으로 2잔이 추가되어야 함
  assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
  assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
}

@Test
void addZeroBeverages() {
  // ⚠️ 예외 케이스 (비정상 흐름)
  // given
  CafeKiosk cafeKiosk = new CafeKiosk();
  Americano americano = new Americano();

  // when & then
  // count가 0이면 예외가 발생해야 한다.
  assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
}

@Test
void addBeveragesBoundaryTest() {
  // ⚙️ 경계값 테스트 (Boundary Test)
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
