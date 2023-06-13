package com.shabbir.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Contect")
public class ContectBean {

	@Id
	@Column(name = "Phone_Number")
	private long phoneNum;
	@Column(name = "First_Name")
	private String firstName;
	@Column(name = "Last_Name")
	private String lastName;
	@Column(name = "Contact_groups")
	private String groups;

	public long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
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

	public String getGroups() {
		return groups;
	}

	public void setGroup(String group) {
		this.groups = group;
	}

	public ContectBean() {
		super();
	}

	@Override
	public String toString() {
		return "First Name : "+ firstName +"\rLast Name : "+lastName +"\rPhone Number : "+phoneNum +"\rGroup : "+groups;
	}

	public ContectBean(long phoneNum, String firstName, String lastName, String group) {
		super();
		this.phoneNum = phoneNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.groups = group;
	}

}
