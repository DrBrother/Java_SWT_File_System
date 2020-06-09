package controller;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import model.DataHandler;
import model.Teacher;
import view.form.GeneralSearchForm;
import view.table.MainContainer;

public class ApplicationSearchController {
	private GeneralSearchForm generalForm;
	public ApplicationSearchController(GeneralSearchForm generalForm){
		this.generalForm = generalForm;
		this.searchProcessing();
	}
	public void searchProcessing() {
		List<Button> buttons = generalForm.getButtons();
		generalForm.getStartButton().addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				Integer mod = 0;
				for (int i = 0; i < 3; i++) {
					if (buttons.get(i).getSelection() == true)
						mod = i;
				}
				List<Teacher> rezult = DataHandler.searchTeacherProcessing(mod, generalForm.getText1(), generalForm.getText2());
				if (rezult.size() == 0) {
					generalForm.getErrorMessege().setMessage("Подходящих элементов не найдено");
					generalForm.getErrorMessege().open();
				} else
					MainContainer.tableConfig(rezult, generalForm.getTable());
				generalForm.getTable().setVisible(true);
			}
		});
	}
}
