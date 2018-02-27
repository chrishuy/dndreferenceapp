package com.dnd.android.dndreferenceapp;

import java.io.Serializable;

/**
 * Created by chris on 2/26/2018.
 */

public class Classes implements Serializable {

    private String className;
    private int classLevel;

    public Classes(String n)
    {
        className = n;
        classLevel = 0;
    }

    //Getters
    public String getClassName() {
        return className;
    }
    public int getClassLevel() {
        return classLevel;
    }

    //Setters
    public void setClassName(String className) {
        this.className = className;
    }
    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }
}
