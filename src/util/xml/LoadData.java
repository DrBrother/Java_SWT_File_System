package util.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.widgets.MessageBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.view.DialogContainer;

import model.DataHandler;
import model.Person;
import model.Teacher;
import view.form.TablePanelManage;
import view.table.MainContainer;

public class LoadData {
	private final static String DATA_XML = "teachers.xml";

	public static void loadTeachers() {
		try {
		DialogContainer container = new DialogContainer(MainContainer.getDisplay());
		String path = container.open();
		File xmlFile = new File(path);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("teachers");
			List<Teacher> sportsmans = new ArrayList<Teacher>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				sportsmans.add(getTeacher(nodeList.item(i)));
			}

			DataHandler.setTeacherList(sportsmans);
			MainContainer.tableConfig(DataHandler.configPage(TablePanelManage.getPage(), TablePanelManage.getPageCount(), TablePanelManage.getPageSize()), MainContainer.getTable());
		} catch (Exception exc) {
			MessageBox errorMessege = new MessageBox(MainContainer.getTable().getShell());
			errorMessege.setText("Загрузка");
			errorMessege.setMessage("Неверно выбран файл!");		
			errorMessege.open();
		}

	}

	private static Teacher getTeacher(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			Person people = new Person(getTagValue("Фамилия", element), getTagValue("Имя", element),
					getTagValue("Отчество", element));
			Teacher man = new Teacher(people, getTagValue("Faculty", element), getTagValue("DepartmentName", element),
					Integer.valueOf(getTagValue("Stage", element)), getTagValue("AcademicRank", element),
					getTagValue("AcademicDegree", element));
			return man;
		}

		return null;
	}

	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}
}