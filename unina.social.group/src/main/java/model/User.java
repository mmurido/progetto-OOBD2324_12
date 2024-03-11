package model;

import java.time.LocalDate;

public class User {

    private String email;
    private String username;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDate;
    
	public User(
			String email, String username, String password, 
			String name, String surname, LocalDate birthDate) {
		
		this.email = email;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
	}

	public String getEmail() { return email; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public String getName() { return name; }
	public String getSurname() { return surname; }
	public LocalDate getBirthDate() { return birthDate;	}

	public void setEmail(String email) { this.email = email; }
	public void setUsername(String username) { this.username = username; }
	public void setPassword(String password) { this.password = password; }
	public void setName(String name) { this.name = name; }
	public void setSurname(String surname) { this.surname = surname; }
	public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate;	}
    
 }