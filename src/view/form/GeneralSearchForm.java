package view.form;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import controller.ApplicationDeleteController;
import controller.ApplicationSearchController;
import model.DataHandler;
import model.Teacher;
import view.table.MainContainer;

public class GeneralSearchForm {
	private boolean mod;
	private List<Button> buttons;
	private Button startButton;
	private Text text1;
	private MessageBox errorMessege;
	private Combo text2;
	private Text text3;
	private Table table;

	public Table getTable() {
		if (this.mod == false)
			return this.table;
		else return null;
	}
	
	public String getText2() {
		if (this.mod == true)
			return this.text3.getText();
		else if (this.mod == false)
			return this.text2.getText();
		else return "";
	}

	public MessageBox getErrorMessege() {
		return this.errorMessege;
	}

	public String getText1() {
		return this.text1.getText();
	}

	public Button getStartButton() {
		return this.startButton;
	}

	public List<Button> getButtons() {
		return this.buttons;
	}

	public void generalSearchForm(boolean startMod) {
		this.mod = startMod;
		Shell shell = new Shell(MainContainer.getDisplay());

		this.errorMessege = new MessageBox(shell);
		this.errorMessege.setText("Informatoins");

		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 50;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;
		shell.setLayout(rowLayout);

		Group groupe = new Group(shell, SWT.NONE);
		groupe.setLayout(new RowLayout(SWT.VERTICAL));
		List<Group> groups = new ArrayList<>();
		for(int i=0;i<3;i++) {
			groups.add(new Group(groupe, SWT.NONE));
			groups.get(i).setLayout(new RowLayout(SWT.VERTICAL));
		}
	
		this.buttons = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Button button1 = new Button(groups.get(0), SWT.RADIO);
			buttons.add(button1);
		}
		this.buttons.get(0).setText("по фио или наименованию кафедры");
		this.buttons.get(1).setText("по стажу работы (макс и мин)");
		this.buttons.get(2).setText("по ученому званию и факультету");
		this.startButton = new Button(groupe, SWT.PUSH);
		
		Label label1 = new Label(groups.get(1), SWT.NONE);
		label1.setText("Критерий №1");
		this.text1 = new Text(groups.get(1), SWT.BORDER);
		RowData layoutData = new RowData();
		layoutData.width = 150;
		this.text1.setLayoutData(layoutData);
		this.text1.setText("");
		this.text1.pack();

		Label label2 = new Label(groups.get(2), SWT.NONE);
		label2.setText("Критерий №2");
		
		if (this.mod == true) {
			shell.setText("Delete");
			shell.setSize(350, 400);
			this.startButton.setText("Delete");

			this.text3 = new Text(groups.get(2), SWT.BORDER);
			this.text3.setLayoutData(layoutData);
			this.text3.setText("");
			this.text3.pack();
			
			new ApplicationDeleteController(this);
		}
		else if (this.mod == false) {
			shell.setText("Search");
			shell.setSize(800, 800);
			this.startButton.setText("Search");

			this.text2 = new Combo(groups.get(2), SWT.DROP_DOWN);
			this.updateListCombobox();
			this.text2.pack();
			
			this.table = new Table(groupe.getShell(), SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
			this.table.setHeaderVisible(true);
			this.table.setVisible(false);
			new ApplicationSearchController(this);
		}
		this.startButton.pack();
		shell.layout();
		shell.open();
	}

	private void updateListCombobox() {
		List<String> updateCombobox = new ArrayList<>();
		List<Teacher> teachers = DataHandler.getTeacher();

		updateCombobox.add(teachers.get(0).getAcademicRank());
		updateCombobox.add(teachers.get(0).getAcademicDegree());

		for (int i = 1; i < teachers.size(); i++) {
			Boolean checkAcademicRank = false;
			Boolean checkAcademicDegree = false;
			for (int j = 0; j < updateCombobox.size(); j++) {
				if (updateCombobox.get(j).equals(teachers.get(i).getAcademicRank()))
					checkAcademicRank = true;
				if (updateCombobox.get(j).equals(teachers.get(i).getAcademicDegree()))
					checkAcademicDegree = true;
			}
			if (checkAcademicRank == false)
				updateCombobox.add(teachers.get(i).getAcademicRank());
			if (checkAcademicDegree == false)
				updateCombobox.add(teachers.get(i).getAcademicDegree());
		}

		String[] items = updateCombobox.toArray(new String[0]);
		this.text2.setItems(items);
	}
}
