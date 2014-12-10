package com.epam.jmp.hibernate.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.epam.pmc.exception.DaoException;

@Entity
@Table(name = "employee")
public class Employee {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    
    @Column(name = "first_name")
	private String firstName;
    
    @Column(name = "last_name")
	private String lastName;
    
    @ManyToOne
    @JoinColumn(name="position_id")
	private Position position;
	
    @Column(name = "login")
	private String login;
    
    @Column(name = "password")
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws DaoException{
		if ("".equals(firstName))
			throw new DaoException("Field First Name is empty");
		if (firstName.length() > 20)
			throw new DaoException("Field First Name is too long");
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws DaoException{
		if ("".equals(lastName))
			throw new DaoException("Field Last Name is empty");
		if (lastName.length() > 20)
			throw new DaoException("Field Last Name is too long");
		this.lastName = lastName;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login)throws DaoException {
		if ("".equals(login))
			throw new DaoException("Field Login is empty");
		if (login.length() > 40)
			throw new DaoException("Field Login is too long");
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws DaoException{
		this.password = password;
		if ("".equals(password))
			throw new DaoException("Field Password is empty");
		if (password.length() > 50)
			throw new DaoException("Field Password is too long");
	}

	
	
	
}
