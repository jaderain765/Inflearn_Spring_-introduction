package hello.hellospring.controller;

import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//Controller 어노테이션을 꼭 추가 해줘야한다.
@Controller
public class HelloController {
	
	/**
	 * hello가 요청이 되면 스프링 컨테이너가 컨트롤러에서 찾아서 페이지를 요청해준다.
	 * 
	 * @param model
	 * @return html페이지를 리턴한다.
	 */
	@GetMapping("hello") //도메인 주소가 된다.
	public String hello(Model model) { //MVC의 모델(m)이다.
		model.addAttribute("data", "spring!!"); //data를 찾아서 spring!!으로 출력한다.
		return "hello"; //동명의 html파일을 불러온다.
	}
	
	//파라미터가 추가된 모습
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}
	
	@GetMapping("hello-string")
	@ResponseBody
	public String helloString(@RequestParam("name") String name) {
		return "hello " + name;
	}
	
	
	//API 타입 전송
	@GetMapping("hello-api")
	@ResponseBody
	/*
		보통은 기본적으로 템플릿(html)을 호출하는 viewResolver에게 호출하나,
		ResponseBody는 이과정을 거치지 않고, 바로 데이터를 호출하게 된다.
		변수 타입은 기본적인 포멧에 관해 셋팅이 되어있으나(변경 가능은 하나, 안함),
		객체데이터의 형식은 기본적으로 json으로 전송된다.
		xml 형식으로 바꿀 수 도있다.
	*/
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}
	
	static class Hello {
		private String name;

		public String getName() { return name; }

		public void setName(String name) { this.name = name; }
		
	}
}
