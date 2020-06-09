package com.model;

import java.io.File;
import java.io.FilenameFilter;

public class RecordFilenameFilter implements FilenameFilter {
    private String filter;

    public RecordFilenameFilter(String filter){
        this.filter = filter;
    }

    @Override
    public boolean accept(File dir, String name) {
        if (filter.equals("*.*"))  return true;
        if (name.endsWith(filter)) {
            return true;
        }
        return false;
    }

}
