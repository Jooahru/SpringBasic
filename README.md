# SpringBasic

## 좋은 객체 지향 설계의 5가지 원칙

### SRP 단일 책임 원칙 (Single Responsibility Principle)
 * 한 클래스는 하나의 책임만 가져야 한다.

### DIP 의존관계 역전 원칙 (Dependency Inversion Principle)
 * 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다." 의존성 주입은 이원칙을 따르는 방법중 하나다.

### OCP (Open Closed Principle)
 * 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.

### ISP 인터페이스 분리 원칙 (Interface Segregation Principle)

### LSP 리스코프 치환 원칙 (Liskov Substitution Principle)

## 의존관계 주입 DI(Dependency Injection)

 * 의존관계는 '정적인 클래스 의존관계와, 실행시점에 결정되는 동적인 객체(인스턴스) 의존 관계를 분리해서 생각하자
   * 정적인 클래스 의존관계 - 클래스가 사용하는 import코드만 보고 의존 관계 쉽게 판단 가능
   * 동적인 클래스 의존관계 - 어플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존 관계

    
## IoC컨테이너, DI 컨테이너
* Appconfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결 해주는 것
* 어샘블러, 오브젝트 팩토리등으로 불리기도함


## 스프링 컨테이너
 * ApplicationContext를 스프링 컨테이너라고 한다
 * 기존에는 개발자가 'AppConfig'를 사용해서 직접 객체를 생성하고 DI를 했지만 이제는 스프링 컨테이너를 통해서 사용한다.
 * 스프링 컨테이너는 @Configuration이 붙은 'AppConfig'를 설정 정보로 사용 여기서 @Bean이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록하고 이 객체를 스프링 빈이라고한다.
 * 스프링 빈은 @Bean이 붙은 메서드의 명을 스프링 빈의 이름으로 사용(name으로 이름 재지정은 가능)

## 싱글톤 패턴
 * 스프링 없는 순수한 DI 컨테이너로 만들면 요청할 때 마다 새로운 객체를 생성한다. 이는 메모리 낭비가 심하다 그래서 나온 해결책 객체가 1개만 생성되고 공유하도록 설계 하는 패턴이 싱글톤 패턴이다
 * 싱글톤 패턴의 단점
   * 구현할 때 코드가 많이 들어간다
   * 내부 속성을 변경하거나 초기화 하기 어렵다
   * 테스트 하기 어렵다

## 싱글톤 컨테이너 (스프링 컨테이너)
 * 싱글톤 패턴의 단점을 보완해서 객체 인스턴스를 싱글톤으로 관리
   * 싱글톤 패턴을 위한 지저분한 코드 들어가지 않아도 된다
   * DIP,OCP,테스트,private 생성자로 부터 자유롭게 싱글톤 사용가능

## 싱글톤 방식의 주의점
 * 싱글톤 패턴,스프링 같은 싱글톤 컨테이너를 사용하든 객체를 하나만 만들어 공유 하기 때문에 싱글톤 객체는 상태를 유지(staeful)하게 설계하면 안된다.
 * 무상태(stateless)로 설계해야 한다!
   * 특정 클라이언트에 의존적인 필드가 있으면 안된다.
   * 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
   * 가급적 읽기만 가능해야 한다.
   * 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터,ThreadLocal등을 사용해야 한다.

## Configuration
 * @Configuration이 붙은 AppConfig class의 경우 단순한 클래스가 아닌 싱글톤이 보장되도록 스프링에서 자동으로 변경된 클래스가 등록된다.

## @ComponentScan
 * @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다.
 * 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다.
 * @Autowired를 지정하면 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
 * basePackages로 탐색위치(패키지) 지정 가능 지정하지 않으면 Default 해당 클래스 패키지가 시작 위치
 * @SpringBootApplication을 프로젝트 시작 루트에 두는 것이 관례적
 * @Component,@Controller,@Service,@Repository,@Configuration 모두 컴포넌트 스캔 대상이다.
 * @Component 이름 중복 등록과 충돌
   * 자동빈 등록일 때 이름이 중복되면 BeanDefinitionStoreException발생
   * 수동빈 vs 자동빈 등록일 때 이름이 중복이면 수동빈 등록이 우선권을 가진다. 수동빈이 자동빈을 오버라이딩 한다.
   * (그러나 현재는 디폴트 값으로 이름이 같으면 수동이든 자동이든 오류 발생)
   * spring.main.allow-bean-definition-overriding=true 설정시 가능

## 의존관계 주입(@Autowired)
 * 생성자 주입 
   * 생성자를 통해서 의존 관계를 주입 받는 방식
   * 생성자 호출시점에 딱 1번만 호출
   * '불변,필수' 의존관계에 사용
   * 생성자가 딱 1개면 @Autowired를 붙이지 않아도 자동주입된다.
 * 수정자 주입(setter주입)
   * setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해 주입
   * 선택,변경 가능성이 있는 의존성 관계에 사용
   * @Autowired는 주입할 대상이 없으면 오류 발생
   * @Autowired(required= false)로 지정시 오류해결 가능
 * 필드 주입
   * 코드가 간결하지만 외부에서 변경이 불가능해 테스트하기 힘들다
   * DI 프레임워크가 없으면 아무것도 할 수 없다.
   * 최대한 사용지양
     * 애플리케이션의 실제 코드와 관계없는 테스트코드
     * 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로사용
 * 일반 메서드 주입
   * 일반 메서드를 통해서 주입 받는다
   * 한번에 여러 필드를 주입 받을 수 있다 public void init(MemberRepository memberRepo, DiscountPolicy discountPoli);위에 @Autowired작성
   * 일반적으로 잘 사용 하지 않음
 *@Autowired 옵션
   * required = false 주입할 대상 없으면 호출 되지 않음
   * @Nullable 주입할 대상 없으면 null로 지정
   * Optional<>: 자동 주입할 대상이 없으면 'Optional.empty'가 된다.

### 생성자 주입방식을 사용하자
 * 프레임 워크에 의존하지 않고 순수한 자바 언어의 특징을 잘 살리는 방법
 * final과 함께사용시 테스트시 생성자 주입 하지 않으면 컴파일 오류 발생해서 바로 알 수있음

## @Autowired 매칭
 1. 타입매칭
 2. 타입 매칭의 결과가 2개 이상일 때 필드 명, 파라미터 명으로 빈 이름 매칭(@Autowired 필드 명)
 3. @Qualifier - @Qualifier끼리 매칭, 빈이름매칭,'NosuchBeanDefinitionException 예외발생
 4. @Primary - @Autowired 여러개 매칭시 우선순위를 가지게 된다.
 * 우선순위 @Primary보다 @Qualifier가 우선순위가 더 높다

## 빈 생명주기 및 콜백시작
 * 인터페이스 InitializingBean, DisposableBean
   * 생성자 생성 후 InitializingBean(afterPropertiesSet()) 실행 //스프링종료전 DisposableBean(Destroy()) 실행
   * 지금은 잘 사용하지 않음 
   
 * 빈 등록 초기화, 소멸 메서드
   * @Bean(initMethod = "init(메서드이름)", destoryMethod = "close(메서드이름)")
   * 메서드 이름을 자유롭게 줄 수 있다.
   * 스프링 빈이 스프링 코드에 의존하지 않는다
   * 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부라이브러리에도 적용 가능
   * destoryMethod의 경우 close,shutdown이라는 이름의 메서드를 자동 호출해준다.