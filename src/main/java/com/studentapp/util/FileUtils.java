package com.studentapp.util;

import com.studentapp.dal.FileProperty;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.studentapp.dal.FileProperty.FILECHildPATH;

public class FileUtils {

    public static void fileObtain(ArrayList<FileProperty> fileList,ArrayList<FileProperty> directoryList)
    {
        File file = new File(FileProperty.FILEPATH);
        File []list = file.listFiles();
       // ArrayList<FileProperty> fileProperties = new ArrayList<>();
        String filepath = FileProperty.FILEPATH;
        String projectAbsolutePath = FileProperty.PROJECTAbsolutePath;
        String path = filepath.substring(projectAbsolutePath.length(),filepath.length());
        System.out.println("path\n"+path);
        String directory[] = path.split("/");

        String setpath = "";
        if(directory.length==1) {
            for (String str : directory) {
//                System.out.println(str);
                setpath += str;
            }
        }
        else {
            for (String str : directory) {
                System.out.println(str);
                setpath += str + " ";
            }
        }
        FileProperty.NAVIGATIONPATH = setpath;
        System.out.println("setpath"+setpath);
        for (File f: list) {
            FileProperty property = new FileProperty();

            String fileName = f.getName();

            boolean isFile = f.isFile();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String fileDate = format.format(new Date(f.lastModified()));

            long fileSize = f.length();
//            System.out.println("位置:" + file.getAbsoluteFile());

            property.setFileName(fileName);
            property.setFileSize(FileSizeFormat.sizeFormat(fileSize, 1));
            property.setFileDate(fileDate);
            property.setFilePath(setpath);
            //System.out.println("path"+property.getFilePath());
            property.setFile(isFile);

            if(f.isFile()) {
                fileList.add(property);
            }
            else if(f.isDirectory())
            {
                directoryList.add(property);
            }
            System.out.println("  名称:   " + fileName + " 大小:  " + fileSize);
        }
//        }

        System.out.println("目录  "+list.length);

    }

    public static String fileDelete(File file)
    {

//        File file = new File(path);
        if(file.isFile()) {

            return file.delete()?"True":"False";
        }
        else if(file.isDirectory())
        {
            String str[] = file.list();
            if(str == null)
                return file.delete()?"True":"False";
            else
            {
                for(String s:str)
                {
                    System.out.println("路径:  "+s);
                    fileDelete(new File(file,s));
                }
            }
            return file.delete()?"True":"False";
        }
        return "False";
    }

    public static String changePath(String pathChild)
    {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File mainDirectory = new File(path.getAbsolutePath(),"static/file");
        if(!mainDirectory.exists())
            mainDirectory.mkdirs();

        FileProperty.FILECHildPATH = "static/"+pathChild;

        File upload = new File(path.getAbsolutePath(),FILECHildPATH);
        if(!upload.exists())
            return "False";
        FileProperty.PROJECTAbsolutePath = path.getAbsolutePath()+"/static/file/";
        System.out.println("当前文件路径:  "+FileProperty.PROJECTAbsolutePath);

        File f = new File(FileProperty.PROJECTAbsolutePath);
        if(!f.exists()) {
            f.mkdir();
            System.out.println(FileProperty.PROJECTAbsolutePath);
        }
        FileProperty.DELETEPATH = path.getAbsolutePath()+"/static/";
//        System.out.println("path.getabsolutepath:    "+FileProperty.PROJECTAbsolutePath);
//        System.out.println("此时的子目录   "+FILECHildPATH);
//        System.out.println("upload url:"+upload.getAbsolutePath());
        FileProperty.FILEPATH = upload.getAbsolutePath() +"/";
        return "True";
    }

    public static String fileUpload(MultipartFile file)
    {
        //FileUtils.fileObtain();
        if(file.isEmpty())
        {

            return "文件为空,上传失败！！";
        }

        String filename = file.getOriginalFilename();

//        System.out.println(filename+"   "+file.getSize());
        String filePath = FileProperty.FILEPATH;

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String Path = filePath + filename;

        File path = new File(Path);

        try
        {

            file.transferTo(path);
            System.out.println("ok");
            return "True";
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "False";

        }
    }

    public static String createDirectory(String name)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        File file = new File(FileProperty.FILEPATH+name);
        if(file.mkdir())
        {
            return "True";
        }
        else
            return "False";

    }
}
