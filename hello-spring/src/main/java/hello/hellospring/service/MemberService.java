package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

public class MemberService {
	
	//private final MemberRepository memberRepository = new MemoryMemberRepository();
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	/**
	 * 회원 가입
	 */
	public Long join(Member member) {
		validateDuplicateMember(member); //중복 회원 검증
		memberRepository.save(member);
		//출력문
		System.out.println("(" + member.getId() + ")" + member.getName() + "회원 생성됨");
		
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		// 같은 이름이 있는 중복 회원X
		Optional<Member> result = memberRepository.findByName(member.getName());
		
		//Optional객체의 값이 있을 경우(과거에는 !=null등의 조건문을 사용했으나, null의 가능성이 있을 경우 Optional객체를 사용하것이 좋다.)
		result.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
		/*
		 * Optional이란?
		 * NPE(NullPointException)를 방어하는 수단 중 하나이며,
		 * 객체에 null이 들어가지 않게 초깃값을 사용하는 방법도 있으나,
		 * Optional<T>은 null이 들어올경우 오류를 반환하는 Wrapper클래스로 NPE가 발생하지 않도록 해준다.
		 * */
	}
	
	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
