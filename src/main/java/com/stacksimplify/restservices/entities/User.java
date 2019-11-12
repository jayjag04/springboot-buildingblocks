package com.stacksimplify.restservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


// entity
@Entity
@Table(name="user")
@JsonIgnoreProperties({"firstname", "lastname"})
public class User extends ResourceSupport {
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	@Id
	@GeneratedValue()
	private Long userid;
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Column(name="USER_NAME", length=50, nullable=false, unique=true)
	@NotEmpty(message="User name is mandatory. Please provide a value")
	private String username;
	
	@Column(name="first_name", length=50, nullable=false)
	@Size(min=2, message="FistName should have atleast 2 characters")
	private String firstname;
	
	@Column(name="last_name", length=50, nullable=false)
	private String lastname;
	
	@Column(name="email_address", length=50, nullable=false)
	private String email;
	
	@Column(name="Role", length=50, nullable=false)
	private String role;
	
	@Column(name="SSN", length=50, nullable=false, unique=true)
    @JsonIgnore
	private String ssn;
	
	@OneToMany(mappedBy="user")
	private List<Order> orders;
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	// no argument constructor
	public User() {
		super();		
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + "]";
	}
	public User(Long userid, @NotEmpty(message = "User name is mandatory. Please provide a value") String username,
			@Size(min = 2, message = "FistName should have atleast 2 characters") String firstname, String lastname,
			String email, String role, String ssn, List<Order> orders) {
		super();
		this.userid = userid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
	} 
}
