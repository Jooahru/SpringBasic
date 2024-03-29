package hello.core.order;

import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import org.springframework.stereotype.Component;

@Component

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired 생성자 하나일 때는 생략 가능
// @RequiredArgsConstructor를 붙여서 자동 생성
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
