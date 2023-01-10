package hello.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {

	
	//테스트 중복을 없애기 위해 clear메소드를 추가해야한다.
	//그러나 MemberService상위의 클래스가 없다.
	//MemoryMemberRepository를 불러 매번 초기화 시킨다.
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	//@BeforeEach는 테스트(jUnit)가 실행되기 전에 실행되는 메소드이다.
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	
	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("어떻게 사람 이름이");
		
		
		//when
		Long saveId = memberService.join(member);
		
		
		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복회원예외() {
		//given
		Member member1 = new Member();
		member1.setName("어떻게 사람 이름이");
		Member member2 = new Member();
		member2.setName("어떻게 사람 이름이");
		
		
		//when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
		
		//try-catch 방법
//		try {
//			memberService.join(member2);
//			fail();
//		} catch (IllegalStateException e) {
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
		
		
		//then
		
		
	}
	
	@Test
	void findMembers() {
		
	}
	
	@Test
	void findOne() {
		
	}
}
