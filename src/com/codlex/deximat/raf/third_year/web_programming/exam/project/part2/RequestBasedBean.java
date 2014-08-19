package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class RequestBasedBean {
	String nothing = "";
	
	public String getNothing() {
		return nothing;
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("Executed!");
		User user = (User) User.getManagedBean("user");
		user.showMessage(null);
	}
}
