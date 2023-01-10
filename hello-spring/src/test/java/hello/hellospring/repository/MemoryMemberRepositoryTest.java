package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

/* 테스트 주도 개발(TTD)
 * 테스트를 우선적으로 만든 후 그 틀에 맞게 구현부를 만든다.
 * 그후 구현부를 테스트에 돌려서 결과를 확인한다.
 * 
 * (이코드는 테스트 주도 개발이 아니다.)
 */

class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	//테스트간의 순서는 정해지지 않은채로 돌아가게 된다.(의존성이 있어선 안된다.)
	//한번의 테스트를 완료할 시 객체의 정보를 없에는 함수를 실행해야 한다.
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		//확인하는 방법
		
		//프린트 해보기
		System.out.println("result = " + (result == member));
		
		//Assertions(jUnit제공)객체 사용하여 결과확인
//		Assertions.assertEquals(member, result);
		
		//Assertions(assertj제공)객체 사용하여 결과확인
		//이를 static import 하면 편하게 사용할 수 있다.
		assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		
		Member result = repository.findByName("spring1").get();
		
		assertThat(member1).isEqualTo(result);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
	}
}
