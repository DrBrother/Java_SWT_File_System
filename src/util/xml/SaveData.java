package util.xml;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.widgets.MessageBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.view.DialogContainer;

import model.DataHandler;
import model.Teacher;
import view.table.MainContainer;

public class SaveData {
	private final static String DATA_XML = "teachers.xml";
	private static Document doc;

	public static void saveTeachers() {
		List<Teacher> teachers = DataHandler.getTeacher();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			Element rootElement = doc.createElementNS("", "Teachers");
			doc.appendChild(rootElement);

			for (int i = 0; i < teachers.size(); i++) {
				rootElement.appendChild(getTeacher(teachers.get(i)));
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			
			DialogContainer container = new DialogContainer(MainContainer.getDisplay());
			String path = container.open() + "\\" + DATA_XML;
			
			StreamResult file = new StreamResult(path);
			transformer.transform(source, file);

			MessageBox errorMessege = new MessageBox(MainContainer.getTable().getShell());
			errorMessege.setText("Сохранение");
			errorMessege.setMessage("Сохранение базы данных в " + path + " завершено успешно!");		
			errorMessege.open();
		} catch (Exception e) {
			MessageBox errorMessege = new MessageBox(MainContainer.getTable().getShell());
			errorMessege.setText("Сохранение");
			errorMessege.setMessage("Неверно выбран путь сохранения!");		
			errorMessege.open();
		}
	}

	private static Node getTeacher(Teacher teacher) {
		Element man = doc.createElement("teacher");

		man.appendChild(getElements(man, "Surname", teacher.getSurname()));
		man.appendChild(getElements(man, "Name", teacher.getName()));
		man.appendChild(getElements(man, "MiddleName", teacher.getMiddleName()));
		man.appendChild(getElements(man, "Faculty", teacher.getFaculty()));
		man.appendChild(getElements(man, "DepartmentsName", teacher.getDepartmentsName()));
		man.appendChild(getElements(man, "Stage", Integer.toString(teacher.getTitlesQuantity())));
		man.appendChild(getElements(man, "AcademicRank", teacher.getAcademicRank()));
		man.appendChild(getElements(man, "AcademicDegree", teacher.getAcademicDegree()));
		return man;
	}

	private static Node getElements(Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

}
