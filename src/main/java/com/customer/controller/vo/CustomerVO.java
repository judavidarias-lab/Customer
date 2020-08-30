package com.customer.controller.vo;

public class CustomerVO {

	private Long id;
	private String name;
	private String identificator;
	private int age;
	private boolean active;

	public CustomerVO() {

	}

	public CustomerVO(String name, String identificator, int age, boolean active) {

		this.name = name;
		this.identificator = identificator;
		this.age = age;
		this.active = active;
	}

	public CustomerVO(Long id, String name, String identificator, int age, boolean active) {
		this.id = id;
		this.name = name;
		this.identificator = identificator;
		this.age = age;
		this.active = active;
	}

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

	public String getIdentificator() {
		return identificator;
	}

	public void setIdentificator(String identificator) {
		this.identificator = identificator;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
