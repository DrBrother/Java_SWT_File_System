package util.xml;

import model.Teacher;
import model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
	public static final Integer ZERO = 0;

	public static List<String> names;
	public static List<String> surnames;
	public static List<String> middleNames;
	public static List<String> faculty;
	public static List<String> departmentsName;
	public static List<Integer> stage;
	public static List<String> academicRank;
	public static List<String> academicDegree;

	static {
		names = new ArrayList<String>(){{
		add("������");
		add("�����");
		add("����");
		add("������");
		add("������");
		add("����");
		add("�����");
		add("����");
		add("�������");
		add("�������");
		add("�����");
		add("�������");
		add("������");
		add("����");
		add("������");
		add("�����");
		add("���������");
		add("��������");
		add("��������");
		add("�����");
		add("���");
		add("��");
		add("������");
		add("���");
		add("������");
		add("��������");
		add("��������");
		add("������");
	}};
	}

	static {
		surnames = new ArrayList<String>(){{
		add("��������");
		add("�������(-�)");
		add("�����(-�)");
		add("�����(-�)");
		add("������(-�)");
		add("��������(-�)");
		add("��������(-�)");
		add("����������(-�)");
		add("�������(-�)");
		add("�������(-�)");
		}};
	}

	static {
		middleNames = new ArrayList<String>(){{
		add("����������(-��)");
		add("������������(-��)");
		add("��������(-��)");
		add("������������(-��)");
		add("�����������(-��)");
		add("������������(-��)");
		add("���������(-��)");
		add("��������(-��)");
		add("�����������(-��)");
		add("������������(-��)");
		}};
	}

	static {
		faculty = new ArrayList<String>(){{
		add("���");
		add("����");
		add("����");
		}};
	}

	static {
		departmentsName = new ArrayList<String>(){{
			add("����������");
			add("������");
			add("��");
			add("����������� �����");
		}};
	}
	static {
		academicRank = new ArrayList<String>(){{
			add("��������");
			add("���������");
			add("������");
			add("�������� ����");
		}};
	}

	static {
		stage = new ArrayList<Integer>(){{
		for (int i = 0; i < 600; i=i+50) {
			add(i);
		}
		}};
	}

	static {
		academicDegree = new ArrayList<String>(){{
		add("������ �������������� ����");
		add("������ ���������� ����");
		add("��������");
		add("��������� �������������� ����");
		add("����������");
		}};
	}

	public static <T> T getRandomData(List<T> elements) {
		return elements.get(ThreadLocalRandom.current().nextInt(0, elements.size()));
	}

	public static Person generatePerson() {
		return new Person(getRandomData(surnames), getRandomData(names), getRandomData(middleNames));
	}

	public static Teacher generateSportsman() {
		return new Teacher(generatePerson(), getRandomData(faculty), getRandomData(departmentsName),
				getRandomData(stage),getRandomData(academicRank) ,getRandomData(academicDegree));
	}

	public static List<Teacher> generateRecords(int amount) {
		List<Teacher> teachers = new ArrayList<>();
		while (amount != 0) {
			amount--;
			teachers.add(generateSportsman());
		}
		return teachers;
	}
}
