package hello.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.MemberRepository;
//import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {
	
//	//데이터소스
//	private DataSource dataSource;
//	@Autowired
//	public SpringConfig(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	
	
//	//JPA를 이용하기 위한 인티티메니져
//	private EntityManager em;
//	@Autowired
//	public SpringConfig(EntityManager em) {
//		this.em = em;
//	}
	
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
//	@Bean
//	public MemberRepository memberRepository() {
//		//return new MemoryMemberRepository(); //메모리저장
//		//return new JdbcMemberRepository(dataSource); //jdbc에 저장
//		//return new JdbcTemplateMemberRepository(dataSource); //jdbcTemplate에 저장
//		return new JpaMemberRepository(em); //jdbcTemplate에 저장
//	}
	/**
	 * 이렇게 직접 의존관계성을 설정해두면 나중에 인터페이스에 붙어있는 레파지토리를
	 * 바꿔야 하는 기회가 있을 경우 바로 바꿀 수 있다.
	 * 모든코드를 손볼필요 없이 레파지토리 빈즈파일에 레파지토리를 변경하면 된다.
	 * (예)
	 * MemoryMemberRepository -> DBMemberRepository
	 * */
	
	
//	//AOP
//	@Bean
//	public TimeTraceAop timeTraceAop() {
//		return new TimeTraceAop();
//	}
}
