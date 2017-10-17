package by.vasilevsky.leasing.domain.user;

import java.io.Serializable;

public class UserDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String firstName;
	private String lastName;
	private int age;

	public UserDetails() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		UserDetails other = (UserDetails) obj;
		if (age != other.age) {
			return false;
		}

		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}

		} else if (!firstName.equals(other.firstName)) {
			return false;
		}

		if (id != other.id) {
			return false;
		}

		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}

		} else if (!lastName.equals(other.lastName)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
	}
}
