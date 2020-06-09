package com.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FolderFactory {
    private static ArrayList<Record> records;
    private static boolean mode = true;

    private void fetchChild(File file, String filter) {
        if (file.isDirectory()) {
            File[] files = file.listFiles(new RecordFilenameFilter(filter));

            if (files != null)
                if (files.length > 0)
                    for (int i = 0; i < files.length; i++)
                        if ((!files[i].isDirectory() && mode == true)|| (files[i].isDirectory() && mode == false))
                            records.add(new Record(files[i].getPath(), files[i].getName(), new Date(files[i].lastModified()), getFileExtension(files[i]), (long) files[i].length()));
        }
    }

    public static ArrayList<Record> getFolderList(String startFolder, String filter, boolean modeDirOrFile) {
        mode = modeDirOrFile;
        if (filter == "") filter = "*.*";
        filter = modeFileExtension(filter);
        records = new ArrayList<Record>();

        FolderFactory example = new FolderFactory();
        File dir = new File(startFolder);
        if (dir.isDirectory())
            example.fetchChild(dir, filter);

        return records;
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf("."));
        else return "";
    }

    private static String modeFileExtension(String filter) {
        if (filter.length() > 0) {
            if (filter.substring(0, 2).equals("*.")) {
                if ((filter.length() == 3 && filter.endsWith("*")))
                    return "*.*";
                else {
                    String str = filter.substring(filter.lastIndexOf("."));
                    return str;
                }
            } else return filter;
        }
        return "";
    }
}
