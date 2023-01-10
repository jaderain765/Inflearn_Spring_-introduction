package hello.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
	/**
	 * 이렇게 직접 의존관계성을 설정해두면 나중에 인터페이스에 붙어있는 레파지토리를
	 * 바꿔야 하는 기회가 있을 경우 바로 바꿀 수 있다.
	 * 모든코드를 손볼필요 없이 레파지토리 빈즈파일에 레파지토리를 변경하면 된다.
	 * (예)
	 * MemoryMemberRepository -> DBMemberRepository
	 * */
}
