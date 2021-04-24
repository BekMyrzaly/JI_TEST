package com.codecademy.ent_test.ui.home;

public class Subject_models {
    String icon;
    String subjectName;

    Subject_models(){}

    public Subject_models(String icon, String subjectName) {
        this.icon = icon;
        this.subjectName = subjectName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
