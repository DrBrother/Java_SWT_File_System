package model;

public class Person {
	private String surname;
	private String name;
	private String middleName;

	public Person(String surname, String name, String middleName) {
		this.surname = surname;
		this.name = name;
		this.middleName = middleName;
	}
	public Person() {
	}
	public Person(Person person) {
		this.surname = person.getSurname();
		this.name = person.getName();
		this.middleName = person.getMiddleName();
	}

	public String getSurname() {
		return this.surname;
	}

	public String getName() {
		return this.name;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public String getFullName() {
		return surname + " " + name + " " + middleName;
	}

}
