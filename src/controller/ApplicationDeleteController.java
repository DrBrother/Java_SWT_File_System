package controller;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import model.DataHandler;
import view.form.GeneralSearchForm;
import view.form.TablePanelManage;
import view.table.MainContainer;

public class ApplicationDeleteController {
	private GeneralSearchForm generalForm;
	
	public ApplicationDeleteController(GeneralSearchForm generalForm){
		this.generalForm = generalForm;
		this.deleteProcessing();
	}
	
	public void deleteProcessing() {
		generalForm.getStartButton().addListener(SWT.Selection, new Listener() {
		@Override
		public void handleEvent(Event arg0) {
			List<Button> buttons = generalForm.getButtons();
			Integer mod = 0;
			for (int i = 0; i < 3; i++) {
				if (buttons.get(i).getSelection() == true)
					mod = i;
			}
			Integer rezult = DataHandler.deleteTeacherProcessing(mod, generalForm.getText1(), generalForm.getText2());
			if (rezult == 0)
				generalForm.getErrorMessege().setMessage("Подходящих элементов не найдено");
			else
				generalForm.getErrorMessege().setMessage("Из списка удалено " + rezult + " элемента");
			TablePanelManage.setPage(0);
			MainContainer.tableConfig(DataHandler.configPage(TablePanelManage.getPage(), TablePanelManage.getPageCount(), TablePanelManage.getPageSize()), MainContainer.getTable());
			generalForm.getErrorMessege().open();
		}
	});
	}
}
