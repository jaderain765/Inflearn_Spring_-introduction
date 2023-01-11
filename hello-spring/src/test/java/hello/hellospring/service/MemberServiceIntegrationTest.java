package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

@SpringBootTest
// 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional
// @Transactional은 DB작업 후 원상태로 롤백시키는 작업을 해준다.
// test 코드에는 꼭 추가해서 실제DB에 변경이 반영되지 않게된다.
// 해당 테스트메소드에 @Commit을 붙이면 DB에 반영이 된다.(절대 사용할 일 없을 듯)
public class MemberServiceIntegrationTest {
	
	//테스트 중복을 없애기 위해 clear메소드를 추가해야한다.
	//그러나 MemberService상위의 클래스가 없다.
	//MemoryMemberRepository를 불러 매번 초기화 시킨다.
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@BeforeEach
	//@BeforeEach는 테스트(jUnit)가 실행되기 전에 실행되는 메소드이다.
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@Test
	@Commit
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("사람이름");
		
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
		member1.setName("어떻게 사람 이름이2");
		Member member2 = new Member();
		member2.setName("어떻게 사람 이름이2");
		
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
