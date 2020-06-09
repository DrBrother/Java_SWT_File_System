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
		add("Оксана");
		add("Жанна");
		add("Нона");
		add("Эдуард");
		add("Измаил");
		add("Дина");
		add("Ольга");
		add("Илья");
		add("Фридрих");
		add("Евдоким");
		add("Давид");
		add("Татьяна");
		add("Модест");
		add("Ника");
		add("Герман");
		add("Никон");
		add("Анастасия");
		add("Всеволод");
		add("Владимир");
		add("Игнис");
		add("Вух");
		add("Уй");
		add("Сергей");
		add("Ким");
		add("Роберт");
		add("Матильда");
		add("Изабелла");
		add("Модест");
	}};
	}

	static {
		surnames = new ArrayList<String>(){{
		add("Науменко");
		add("Болокан(-а)");
		add("Чадов(-а)");
		add("Мишин(-а)");
		add("Гринин(-а)");
		add("Кузинков(-а)");
		add("Николаев(-а)");
		add("Богоносцев(-а)");
		add("Соломин(-а)");
		add("Палюлин(-а)");
		}};
	}

	static {
		middleNames = new ArrayList<String>(){{
		add("Игнатиевич(-на)");
		add("Феоктистович(-на)");
		add("Ипатович(-на)");
		add("Валерьянович(-на)");
		add("Андрианович(-на)");
		add("Валерьянович(-на)");
		add("Моисеевич(-на)");
		add("Фролович(-на)");
		add("Ипполитович(-на)");
		add("Валерьянович(-на)");
		}};
	}

	static {
		faculty = new ArrayList<String>(){{
		add("ИЭФ");
		add("КСИС");
		add("ФИТУ");
		}};
	}

	static {
		departmentsName = new ArrayList<String>(){{
			add("Математики");
			add("Физики");
			add("ИИ");
			add("Английского языка");
		}};
	}
	static {
		academicRank = new ArrayList<String>(){{
			add("Бакалавр");
			add("Профессор");
			add("Доцент");
			add("Кандидат наук");
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
		add("Доктор математических наук");
		add("Доктор физических наук");
		add("Аспирант");
		add("Профессор информационных наук");
		add("Магистрант");
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
