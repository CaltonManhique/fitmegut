package com.fitmegut.dciwarehousefinalproject.model;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "members", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "birthdate")
	private String birthdate; // Date

	@Column(name = "gender")
	private String gender; // radio button

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "country")
	private String country;

	@Column(name = "city")
	private String city;

	@Column(name = "address")
	private String address;

	@Column(name = "member_type")
	private String userType; // Private or company

	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "members_roles",
			joinColumns = @JoinColumn(
					name = "member_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	public Member(String firstName, String lastName, String nickname, String birthdate, String gender, String email,
			String phoneNumber, String country, String city, String address, String userType, String password,
			Collection<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickname = nickname;
		this.birthdate = birthdate;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.city = city;
		this.address = address;
		this.userType = userType;
		this.password = password;
		this.roles = roles;
	}

	public Member() {
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickname() {
		return nickname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public String getUserType() {
		return userType;
	}

	public String getPassword() {
		return password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}
