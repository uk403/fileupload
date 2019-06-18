package com.studentapp.dal;



public class FileProperty  {
    //修改路径
    public static final String MAINPATH = "/home/ukyu";
    public static String FILEPATH = "";
    public static String FILECHildPATH = "static/file";
    public static String PROJECTAbsolutePath = "";
    public static String DELETEPATH = "";
    public static String  NAVIGATIONPATH = "";
    public static int visitorVolume = 0;
    private String fileName;
    private String fileDate;
    private String fileSize;
    private String filePath;
    private boolean isFile;

    @Override
    public String toString() {
        return "FileProperty{" +
                "fileName='" + fileName + '\'' +
                ", fileDate='" + fileDate + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", filePath='" + filePath + '\'' +
                ", isFile=" + isFile +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }
}