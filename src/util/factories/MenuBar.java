package util.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import controller.ApplicationContainerController;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MenuBar {
	public static final String FILE = "File";
	public static final String LOAD = "Load";
	public static final String SAVE = "Save";

	public static final String EDIT = "Edit";
	public static final String ADD = "Add";
	public static final String DELETE = "Delete";
	public static final String SEARCH = "Search";

	public static Menu getInstance(Shell shell) {
		Menu menuBar = new Menu(shell, SWT.BAR);
		generateButtons(menuBar);
		shell.setMenuBar(menuBar);

		return menuBar;
	}

	public static void generateButtons(Menu menuBar) {
		MenuItem fileItem = new MenuItem(menuBar, SWT.CASCADE);
		fileItem.setText(FILE);
		MenuItem editItem = new MenuItem(menuBar, SWT.CASCADE);
		editItem.setText(EDIT);

		Menu fileSubMenu = new Menu(menuBar.getShell(), SWT.DROP_DOWN);
		fileItem.setMenu(fileSubMenu);
		Menu editSubMenu = new Menu(menuBar.getShell(), SWT.DROP_DOWN);
		editItem.setMenu(editSubMenu);

		MenuItem load = new MenuItem(fileSubMenu, SWT.PUSH);
		load.setText(LOAD);
		MenuItem save = new MenuItem(fileSubMenu, SWT.PUSH);
		save.setText(SAVE);

		MenuItem add = new MenuItem(editSubMenu, SWT.PUSH);
		add.setText(ADD);
		MenuItem delete = new MenuItem(editSubMenu, SWT.PUSH);
		delete.setText(DELETE);
		MenuItem search = new MenuItem(editSubMenu, SWT.PUSH);
		search.setText(SEARCH);
		
		List<MenuItem> listeners = new ArrayList<>();
		listeners.add(load);
		listeners.add(save);
		listeners.add(add);
		listeners.add(delete);
		listeners.add(search);
		ApplicationContainerController.listenerProcessin(listeners);
	}
	
}
