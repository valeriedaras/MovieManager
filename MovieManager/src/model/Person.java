package model;

public class Person {

	protected String firstName ;
	
	protected String lastName ;
	
	protected Gender gender ;
	
	enum Gender {
		FEMALE,
		MALE
	}
	
	public Person(String f, String l, int g) {
		firstName = f ;
		lastName = l ;
		this.setGendre(g);
	}
	
	public Person(String l, int g) {
		lastName = l ;
		this.setGendre(g);
	}
	
	public Person(String f, String l) {
		firstName = f ;
		lastName = l ;
	}
	
	public Person(String l) {
		lastName = l ;
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
	
	public String toString() {
		if (gender != null) {
			return firstName + " " + lastName + "(" + this.gender.name() +")";
		}
		return firstName + " " + lastName;
	}
	
	public String getGender() {
		return gender.name();
	}
	
	public void setGendre(int g) {
		switch(g){
		case 0:
			break;
		case 1:
			this.gender = Gender.FEMALE ;
		case 2:
			this.gender = Gender.MALE ;
		}
	}
}
