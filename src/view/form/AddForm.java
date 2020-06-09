package view.form;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import model.Person;
import model.Teacher;
import model.DataHandler;
import view.table.MainContainer;

public class AddForm {
	public static void addTeacher() {
		Shell shell = new Shell(MainContainer.getDisplay());
		shell.setText("Add");
		shell.setSize(300, 500);

		MessageBox errorMessege = new MessageBox(shell);
		errorMessege.setText("Informatoins");

		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 50;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;
		shell.setLayout(rowLayout);

		Group groupe = new Group(shell, SWT.NONE);
		groupe.setLayout(new RowLayout(SWT.VERTICAL));

		Button button = new Button(groupe, SWT.PUSH);
		button.setText("Save");
		button.pack();

		List<Text> texts = new ArrayList<>();
		List<Label> labels = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			Group groupe1 = new Group(groupe, SWT.NONE);
			groupe1.setLayout(new RowLayout(SWT.HORIZONTAL));
			
			Text text1 = new Text(groupe1, SWT.BORDER);
			RowData layoutData1 = new RowData();
			layoutData1.width = 150;
			text1.setLayoutData(layoutData1);
			text1.setText("");		
			texts.add(text1);
			text1.pack();
			
			Label label1 = new Label(groupe1, SWT.NONE);
			labels.add(label1);
		}
		labels.get(0).setText("Surname");
		labels.get(1).setText("Name");
		labels.get(2).setText("Middle Name");
		labels.get(3).setText("Faculty");
		labels.get(4).setText("DepartmentsName");
		labels.get(5).setText("AcademicRank");
		labels.get(6).setText("Stage");
		labels.get(7).setText("AcademicDegree");

		button.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				Boolean check = true;
				for (int i = 0; i < 8; i++) {
					if (texts.get(i).getText().equals("")) {
					check = false;
					errorMessege.setMessage("Неверный ввод параметра № " + (i + 1));
					errorMessege.open();
					break;
					}
				}
				if (check == true) {
					Person people = new Person(texts.get(0).getText(), texts.get(1).getText(), texts.get(2).getText());
					Teacher man = new Teacher(people, texts.get(3).getText(), texts.get(4).getText(),
							Integer.valueOf(texts.get(5).getText()), texts.get(6).getText(), texts.get(7).getText());
					if (DataHandler.addTeacherProcessing(man) == false) {
						errorMessege.setMessage("Человек с таким ФИО в списке уже присутствует");
						errorMessege.open();
					}
					MainContainer.tableConfig(DataHandler.configPage(TablePanelManage.getPage(), TablePanelManage.getPageCount(), TablePanelManage.getPageSize()), MainContainer.getTable());
				}
			}
		});
		shell.open();
	}
}
