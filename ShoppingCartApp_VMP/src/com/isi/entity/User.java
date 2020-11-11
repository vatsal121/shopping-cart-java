package com.isi.entity;

import java.sql.Date;

public class User {

	int Id;
	String UserName, PasswordSalt, PasswordHash, Role;
	Date DateCreated, DateModified;

	public User(int id, String userName, String passwordSalt, String passwordHash, String role, Date dateCreated,
			Date dateModified) {
		Id = id;
		UserName = userName;
		PasswordSalt = passwordSalt;
		PasswordHash = passwordHash;
		Role = role;
		DateCreated = dateCreated;
		DateModified = dateModified;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPasswordSalt() {
		return PasswordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		PasswordSalt = passwordSalt;
	}

	public String getPasswordHash() {
		return PasswordHash;
	}

	public void setPasswordHash(String passwordHash) {
		PasswordHash = passwordHash;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public Date getDateCreated() {
		return DateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		DateCreated = dateCreated;
	}

	public Date getDateModified() {
		return DateModified;
	}

	public void setDateModified(Date dateModified) {
		DateModified = dateModified;
	}

}
