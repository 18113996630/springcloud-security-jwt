package org.hrong.springcloudsecurityjwt.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.omg.CORBA.UserException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
//@JsonIgnoreProperties(value = {"password"})
public class User implements Serializable{
	private static final long serialVersionUID = -5748428329513631710L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "userName")
	private String userName;

	@Column(name = "password")
	private String password;

	public static void main(String[] args) {
		User user = new User();
		user.setPassword("ll");
		user.setUserName("name");

		System.out.println(user);
	}
}
