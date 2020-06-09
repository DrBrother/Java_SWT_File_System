package com.view;

import com.model.FolderFactory;
import com.model.Record;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.*;

import java.io.File;

import java.util.ArrayList;

public class DialogContainer {
    private File homeFolderMain;
    private File sellectedFolderMain;
    private String sellected = "";
    private Shell shell;
    private Display display;
    private Table table;
    private List list;
    private Tree tree;
    private Button updateButton;
    private Button exitButton;
    private Combo namePanel;
    private boolean listMode = true;
    private String[] filtersSetItems = {"*.docx", "*.xlsx", "*.pdf", "*.xml", "*.*", "*.scs"};

    public DialogContainer(Display display) {
        this.display = display;
        initMainElements();
    }

    public DialogContainer(Display display, String homeDirectory) {
        this.display = display;
        searchHomeFolder(homeDirectory);
        initMainElements();
    }

    public DialogContainer(Display display, String homeDirectory, String[] filtersSet) {
        this.display = display;
        searchHomeFolder(homeDirectory);
        this.filtersSetItems = filtersSet;
        initMainElements();
    }

    private void searchHomeFolder(String homeFolder) {
        sellectedFolderMain = homeFolderMain;
        File dir = new File(homeFolder);
        if (dir != null)
            if (dir.isDirectory())
                homeFolderMain = dir;
            else homeFolderMain = null;
        else homeFolderMain = null;
    }

    private void initMainElements() {
        shell = new Shell(display, SWT.DIALOG_TRIM);
        shell.setText("SWT");
        shell.setSize(800, 500);

        RowLayout rowLayout = new RowLayout();
        rowLayout.spacing = 20;
        rowLayout.marginLeft = 10;
        rowLayout.marginTop = 10;
        shell.setLayout(rowLayout);

        initMenu();

        Composite groupAll = new Composite(shell, SWT.BORDER);
        groupAll.setLayout(new RowLayout(SWT.VERTICAL));

        updateButton = new Button(groupAll, SWT.PUSH);
        updateButton.setText("Update page");
        updateButton.pack();

        namePanel = new Combo(groupAll, SWT.DROP_DOWN);
        namePanel.setItems(filtersSetItems);
        RowData layoutData = new RowData();
        layoutData.width = 140;
        namePanel.setLayoutData(layoutData);
        namePanel.pack();

        tree = new Tree(groupAll, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        tree.setLayoutData(new RowData(250, 300));
        treeConfig();

        exitButton = new Button(groupAll, SWT.PUSH);
        exitButton.setText("Choose this");
        exitButton.pack();

        table = new Table(shell, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL);
        table.setHeaderVisible(true);
        table.setLayoutData(new RowData(400, 400));
        tableConfig(homeFolderMain.getPath());
    }

    public String open() {
        Display display = shell.getDisplay();
        shell.open();

        updateButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                updatePage();
            }
        });

        exitButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                shell.dispose();
            }
        });

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        if(sellected != "")
            return sellected;
        else return sellectedFolderMain.getPath();
    }

    public void setFilters(String[] setItems) {
        filtersSetItems = setItems;
        if (namePanel != null)
            namePanel.setItems(filtersSetItems);
        shell.layout();
    }

    private void tableConfig(String catalog) {
        table.removeAll();
        ArrayList<Record> records = FolderFactory.getFolderList(catalog, namePanel.getText(), true);

        String[] titles = {"Name file  ", "File extension", "File size", "Date of the last change"};
        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            TableColumn column = new TableColumn(table, SWT.CENTER);
            column.setAlignment(SWT.CENTER);
            column.setText(titles[loopIndex]);
        }

        if (records.size() > 0) {
            for (int loopIndex = 0; loopIndex < records.size(); loopIndex++) {
                TableItem item = new TableItem(table, SWT.LEFT);
                item.setText(0, records.get(loopIndex).getName());
                item.setText(1, records.get(loopIndex).getExtension());
                item.setText(2, Long.toString(records.get(loopIndex).getSize()));
                item.setText(3, records.get(loopIndex).getModified().toString());
            }
        }


        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++)
            table.getColumn(loopIndex).pack();
        shell.layout();

        if (table != null) {
            table.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent event) {
                    for (int i = 0; i < records.size(); i++)
                        if (table.getItem(table.getSelectionIndex()).getText().equals(records.get(i).getName())) {
                            sellected = records.get(i).getPath();
                            break;
                        }
                }
            });
        }
    }

    private void listConfig(String catalog) {
        list.removeAll();

        ArrayList<Record> records1 = FolderFactory.getFolderList(catalog, namePanel.getText(), true);
        if (listMode) {
            if (records1.size() > 0)
                for (int i = 0; i < records1.size(); i++)
                    list.add(records1.get(i).getName());
        }
        ArrayList<Record> records2 = FolderFactory.getFolderList(catalog, namePanel.getText(), false);
        if (records2.size() > 0)
            for (int i = 0; i < records2.size(); i++)
                list.add(records2.get(i).getName());

        shell.layout();

        if (list != null) {
            list.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent event) {
                    for (int i = 0; i < records2.size(); i++)
                        if (list.getItem(list.getSelectionIndex()).equals(records2.get(i).getName())) {
                            sellected = records2.get(i).getPath();
                            break;
                        }
                    if (listMode) {
                        for (int i = 0; i < records1.size(); i++)
                            if (list.getItem(list.getSelectionIndex()).equals(records1.get(i).getName())) {
                                sellected = records1.get(i).getPath();
                                break;
                            }
                    }
                }
            });
        }
    }

    private void treeConfig() {
        File[] roots = File.listRoots();

        for (int i = 0; i < roots.length; i++) {
            File file = roots[i];
            TreeItem treeItem = new TreeItem(tree, SWT.NONE);
            treeItem.setText(file.getAbsolutePath());
            treeItem.setData(file);
            new TreeItem(treeItem, SWT.NONE);
        }
        if (homeFolderMain == null)
            homeFolderMain = roots[0];

        tree.addListener(SWT.Expand, new Listener() {
            public void handleEvent(Event e) {
                TreeItem item = (TreeItem) e.item;
                if (item == null)
                    return;
                if (item.getItemCount() == 1) {
                    TreeItem firstItem = item.getItems()[0];
                    if (firstItem.getData() != null)
                        return;
                    firstItem.dispose();
                } else {
                    return;
                }
                File root = (File) item.getData();
                File[] files = root.listFiles();
                if (files == null)
                    return;
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    if (file.isDirectory()) {
                        TreeItem treeItem = new TreeItem(item, SWT.NONE);
                        treeItem.setText(file.getName());
                        treeItem.setData(file);
                        new TreeItem(treeItem, SWT.NONE);
                    }
                }
            }
        });

        tree.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                TreeItem item = (TreeItem) e.item;
                if (item == null)
                    return;
                sellectedFolderMain = (File) item.getData();
                if(sellectedFolderMain != null) {
                    if (table != null) {
                        if (!table.isDisposed())
                            tableConfig(sellectedFolderMain.getPath());
                    }
                    if (list != null) {
                        if (!list.isDisposed())
                            listConfig(sellectedFolderMain.getPath());
                    }
                }
            }
        });
    }

    private void updatePage() {
        if (sellectedFolderMain != null) {
            if (list != null)
                if (!list.isDisposed())
                    listConfig(sellectedFolderMain.getPath());

            if (table != null)
                if (!table.isDisposed())
                    tableConfig(sellectedFolderMain.getPath());
        }
    }

    private void initMenu() {
        Menu menuBar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menuBar);

        MenuItem MenuFileItems = new MenuItem(menuBar, SWT.CASCADE);
        MenuFileItems.setText("File");
        MenuItem MenuEditItems = new MenuItem(menuBar, SWT.CASCADE);
        MenuEditItems.setText("Mode");

        Menu fileSubMenu = new Menu(menuBar.getShell(), SWT.DROP_DOWN);
        MenuFileItems.setMenu(fileSubMenu);
        Menu editSubMenu = new Menu(menuBar.getShell(), SWT.DROP_DOWN);
        MenuEditItems.setMenu(editSubMenu);

        MenuItem menuExitItem = new MenuItem(fileSubMenu, SWT.PUSH);
        menuExitItem.setText("Exit");
        menuExitItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                shell.dispose();
            }
        });

        MenuItem menuHomeItem = new MenuItem(fileSubMenu, SWT.PUSH);
        menuHomeItem.setText("Home");
        menuHomeItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                sellectedFolderMain = homeFolderMain;
                if (list != null)
                    if (!list.isDisposed())
                        listConfig(homeFolderMain.getPath());
                if (table != null)
                    if (!table.isDisposed())
                        tableConfig(homeFolderMain.getPath());
            }
        });

        MenuItem menuTableItem = new MenuItem(editSubMenu, SWT.PUSH);
        menuTableItem.setText("Table");
        menuTableItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if (!list.isDisposed()) {
                    list.dispose();
                    iniTable();
                }
            }
        });

        MenuItem menuListItem = new MenuItem(editSubMenu, SWT.PUSH);
        menuListItem.setText("List");
        menuListItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if (!table.isDisposed()) {
                    table.dispose();
                    listMode = true;
                    iniList();
                } else if (!listMode) {
                    listMode = true;
                    if(sellectedFolderMain != null)
                        listConfig(sellectedFolderMain.getPath());
                    else
                        listConfig(homeFolderMain.getPath());
                }
            }
        });

        MenuItem menuFolderItem = new MenuItem(editSubMenu, SWT.PUSH);
        menuFolderItem.setText("Folder");
        menuFolderItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if (!table.isDisposed()) {
                    table.dispose();
                    listMode = false;
                    iniList();
                } else if (listMode) {
                    listMode = false;
                    if(sellectedFolderMain != null)
                        listConfig(sellectedFolderMain.getPath());
                    else
                        listConfig(homeFolderMain.getPath());
                }
            }
        });

    }

    private void iniTable() {
        table = new Table(shell, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL);
        table.setHeaderVisible(true);
        table.setLayoutData(new RowData(400, 400));
        if(sellectedFolderMain != null)
            tableConfig(sellectedFolderMain.getPath());
        else
            tableConfig(homeFolderMain.getPath());
        shell.layout();
    }

    private void iniList() {
        list = new List(shell, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
        list.setLayoutData(new RowData(349, 400));
        if(sellectedFolderMain != null)
            listConfig(sellectedFolderMain.getPath());
        else
            listConfig(homeFolderMain.getPath());
        shell.layout();
    }
}
