package com.studentapp.dal;


public class Navigation {
    private String navigationPath;
    private String navigationName;

    @Override
    public String toString() {
        return "Navigation{" +
                "navigationPath='" + navigationPath + '\'' +
                ", navigationName='" + navigationName + '\'' +
                '}';
    }

    public String getNavigationPath() {
        return navigationPath;
    }

    public void setNavigationPath(String navigationPath) {
        this.navigationPath = navigationPath;
    }

    public String getNavigationName() {
        return navigationName;
    }

    public void setNavigationName(String navigationName) {
        this.navigationName = navigationName;
    }
}
