package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 만들어 주는 값을 IDENTITY라고 한다.
	private Long id;
	
	// @Column(name = "username") //객체의 필드명과 DB의 칼럼명이 다를 때 사용하는 어노테이션
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
