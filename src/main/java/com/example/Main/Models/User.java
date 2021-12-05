package com.example.Main.Models;

import org.json.JSONObject;

import javax.persistence.*;

@Table(name = "users")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Lob
	@Column(name = "LOGIN", nullable = false)
	private String login;

	@Lob
	@Column(name = "PASSWORD", nullable = false)
	private String password;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JSONObject toJSON(){
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("login", login);
		json.put("password", password);
		System.out.println("Hey I got: " + json.toString());
		return json;
	}
}
