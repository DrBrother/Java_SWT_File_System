package model;

import java.util.ArrayList;
import java.util.List;

import util.xml.Generator;

public class DataHandler {
	private static List<Teacher> teachers;
	private static Integer teachersCount = 50;
	static {
		teachers = Generator.generateRecords(teachersCount);
	}

	public static List<Teacher> getTeacher() {
		return teachers;
	}

	public static void setTeacherList(List<Teacher> teachersList) {
		teachers = teachersList;
	}

	public static List<Teacher> configPage(Integer page, Integer pageCount, Integer pageSize) {
		teachers = DataHandler.getTeacher();
		if (teachers.isEmpty() == true)
			return null;

		List<List<Teacher>> teachersPage = new ArrayList<>();
		for (int i = 0; i < pageCount; i++) {
			teachersPage.add(new ArrayList<Teacher>());
			for (int j = 0; j < pageSize; j++) {
				if (i * pageSize + j < teachers.size())
					teachersPage.get(i).add(teachers.get(i * pageSize + j));
			}
		}
		if (teachersPage.get(page).isEmpty())
			return null;
		return teachersPage.get(page);
	}

	public static Integer getTeachersCount() {
		return DataHandler.teachersCount;
	}

	public static Integer deleteTeacherProcessing(Integer mod, String parameter1, String parameter2) {
		Integer deleteCount = 0;
		if (mod == 0) {
			for (int i = teachers.size() - 1; i >= 0; i--) {
				if (teachers.get(i).getFullName().equals(parameter1)
						| teachers.get(i).getAcademicRank().equals(parameter2)) {
					teachers.remove(i);
					deleteCount++;
				}
			}
		} else if (mod == 1) {
			for (int i = teachers.size() - 1; i >= 0; i--) {
				try {
					if (Integer.valueOf(parameter1) >= teachers.get(i).getTitlesQuantity()
							& teachers.get(i).getTitlesQuantity() >= Integer.valueOf(parameter2)) {
						teachers.remove(i);
						deleteCount++;
					}
				} catch (NumberFormatException e) {
					System.err.println("Неверный формат строки!");
				}
			}
		} else if (mod == 2) {
			for (int i = teachers.size() - 1; i >= 0; i--) {
				if (teachers.get(i).getFullName().equals(parameter1)
						| teachers.get(i).getAcademicDegree().equals(parameter2)) {
					teachers.remove(i);
					deleteCount++;
				}
			}
		}
		teachersCount = teachers.size();
		return deleteCount;
	}

	public static List<Teacher> searchTeacherProcessing(Integer mod, String parameter1, String parameter2) {
		List<Teacher> chosenTeachers = new ArrayList<>();
		if (mod == 0) {
			for (int i = 0; i < teachers.size(); i++) {
				if (teachers.get(i).getFullName().equals(parameter1)
						| teachers.get(i).getAcademicRank().equals(parameter2)) {
					chosenTeachers.add(teachers.get(i));
				}
			}
		} else if (mod == 1) {
			for (int i = 0; i < teachers.size(); i++) {
				try {
					if (Integer.valueOf(parameter1) >= teachers.get(i).getTitlesQuantity()
							& teachers.get(i).getTitlesQuantity() >= Integer.valueOf(parameter2)) {
						chosenTeachers.add(teachers.get(i));
					}
				} catch (NumberFormatException e) {
					System.err.println("Неверный формат строки!");
				}
			}
		} else if (mod == 2) {
			for (int i = 0; i < teachers.size(); i++) {
				if (teachers.get(i).getFullName().equals(parameter1)
						| teachers.get(i).getAcademicDegree().equals(parameter2)) {
					chosenTeachers.add(teachers.get(i));
				}
			}
		}
		return chosenTeachers;
	}

	public static Boolean addTeacherProcessing(Teacher man) {
		Boolean check = true;
		for (int i = 0; i < teachers.size(); i++) {
			if (teachers.get(i).getFullName().equals(man.getFullName()))
				check = false;
		}
		if (check == true) {
			teachers.add(man);
			teachersCount++;
		}
		return check;
	}
}
