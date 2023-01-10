package hello.hellospring.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	// 컨트롤러와의 의존관계를 주입해준다.
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	//필드에 직접 Autowired해서 사용하는 방법(변경불가)과
	//set메소드에 해서 하는방법 생성자에 하는 방법(public으로 인한 간섭)등이 있으나,
	//이중 가장 좋은 방법은 생성자에 주입하는것이다.
	
	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		return "redirect:/";
	}
	
	/**
	 * 같은 주소이나, 어노테이션이 다르다.
	 * GetMapping은 해당주소를 찾을(조회)때 주로 사용되며(기본적인 URL방식이다.),
	 * PostMapping은 값을 전달할때 사용한다.
	 */
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
}
