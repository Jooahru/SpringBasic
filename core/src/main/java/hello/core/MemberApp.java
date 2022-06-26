package hello.core;

import ch.qos.logback.core.net.SyslogOutputStream;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new memeber = " + member.getName());
        System.out.println("nfind Member = " + findMember.getName());

    }
}
