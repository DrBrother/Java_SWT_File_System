package controller;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;

import util.xml.LoadData;
import util.xml.SaveData;
import view.form.AddForm;
import view.form.GeneralSearchForm;

public class ApplicationContainerController {

	public static void listenerProcessin(List<MenuItem> listeners) {

		listeners.get(0).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				LoadData.loadTeachers();
			}
		});

		listeners.get(1).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				SaveData.saveTeachers();
			}
		});

		listeners.get(2).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				AddForm.addTeacher();
			}
		});

		listeners.get(3).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				GeneralSearchForm form = new GeneralSearchForm();
				form.generalSearchForm(true);
			}
		});

		listeners.get(4).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				GeneralSearchForm form = new GeneralSearchForm();
				form.generalSearchForm(false);
			}
		});
	}
}
