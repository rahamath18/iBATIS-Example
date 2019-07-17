package com.test.iBATIS;

public class User {

	public int id;
	public String first_name;
	public String last_name;
	public String email;

	public User() {
	}

	public User(String fname, String lname, String email) {
		this.first_name = fname;
		this.last_name = lname;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + last_name + ", lastName=" + last_name + ", email=" + email + "]";
    }

}
