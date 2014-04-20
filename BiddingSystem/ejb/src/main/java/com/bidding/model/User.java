package com.bidding.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(/* uniqueConstraints = @UniqueConstraint(columnNames = "email") */)
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE, CascadeType.MERGE })
	private Seller seller;

	@OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE, CascadeType.MERGE })
	private Customer customer;

	@NotNull
	@Size(min = 4, max = 512)
	private String password;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE, CascadeType.MERGE })
	private List<UserRole> userRoles = new ArrayList<UserRole>();

	@NotNull
	@Size(min = 4, max = 250)
	private String address;

	@Lob
	@Column(name = "profile_picture", columnDefinition = "mediumblob")
	private byte[] profilePicture;

	private boolean enabled;

	@NotNull
	@NotEmpty
	@Email
	private String email;

	@NotNull
	private String phoneNumber;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}
}
