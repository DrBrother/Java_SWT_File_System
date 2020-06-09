package model;

public class Teacher extends Person {
	private String faculty;
	private String departmentsName;
	private Integer stage;
	private String academicRank;
	private String academicDegree;

	public Teacher() {
	}

	public Teacher(Person person, String faculty, String departmentsName, Integer stage, String academicRank,
				   String academicDegree) {
		super(person);
		if (stage < 0)
			stage = 0;
		this.faculty = faculty;
		this.departmentsName = departmentsName;
		this.stage = stage;
		this.academicRank = academicRank;
		this.academicDegree = academicDegree;
	}

	public String getFaculty() {
		return this.faculty;
	}

	public String getDepartmentsName() {
		return this.departmentsName;
	}

	public Integer getTitlesQuantity() {
		return this.stage;
	}

	public String getAcademicRank() {
		return this.academicRank;
	}

	public String getAcademicDegree() {
		return this.academicDegree;
	}
}
