package com.stacksimplify.restservices.dtos;

public class UserMsDto {
	
	
	public UserMsDto() {
		 
	}

	public UserMsDto(Long userid, String username, String emailaddress, String rolename) {
		this.userid = userid;
		this.username = username;
		this.emailaddress = emailaddress;
		this.rolename = rolename;
	}

	private Long userid;
	private String username;
	private String emailaddress;

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	private String rolename;

	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	
	

}
