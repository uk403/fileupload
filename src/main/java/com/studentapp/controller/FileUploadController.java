package com.studentapp.controller;

import com.studentapp.dal.FileProperty;
import com.studentapp.dal.Navigation;
import com.studentapp.util.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;


@Controller
public class FileUploadController {

    @RequestMapping(value = "/data")
    public String index(Model model, String path)
    {
        String pathChild = "file";
        if(path!=null)
        {
            String directory[] = path.split(" ");
            for(String str:directory) {
//                pathChild = "file";
                pathChild +=  "/"+str;
            }
            FileUtils.changePath(pathChild);
        }

        else {
//            pathChild = "file";
            FileUtils.changePath(pathChild);
        }

        System.out.println("pathchild:  "+pathChild);

        model.addAttribute("pathchild",pathChild);

        ArrayList<FileProperty> fileList = new ArrayList<>();
        ArrayList<FileProperty> directoryList = new ArrayList<>();
        FileUtils.fileObtain(fileList,directoryList);

        model.addAttribute("fileList",fileList);
        model.addAttribute("directoryList",directoryList);
        FileProperty.visitorVolume+=1;
        model.addAttribute("visitorVolume",FileProperty.visitorVolume);

        //导航
//        System.out.println(path);
        if(path!=null) {
            System.out.println(FileProperty.NAVIGATIONPATH);
            ArrayList<Navigation> navigationList = new ArrayList<>();

            String navigation[] = FileProperty.NAVIGATIONPATH.split(" ");
            String url = "data?path=";

            for (String s : navigation) {
                Navigation navi = new Navigation();
                if (navigationList.size() != 0) {
                    String last = navigationList.get(navigationList.size() - 1).getNavigationPath();
                    navi.setNavigationPath(last + s+" ");
                } else
                    navi.setNavigationPath(url + s + " ");
                navi.setNavigationName(s);
                navigationList.add(navi);
            }

            model.addAttribute("navigation",navigationList);
            for (Navigation s:navigationList)
            {
                System.out.println(s.getNavigationName()+" "+s.getNavigationPath());
            }
        }
        return "upload";
    }


    @ResponseBody
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file)
    {

        System.out.println("上传请求到");
        return FileUtils.fileUpload(file);

    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam("path") String path)
    {
//        System.out.println("访问到");
        path = FileProperty.DELETEPATH +path;
        System.out.println("dele   "+FileProperty.DELETEPATH);

        System.out.println("path...  "+path);
        File file = new File(path);
        return FileUtils.fileDelete(file);
    }

    @ResponseBody
    @RequestMapping("/createdirectory")
    public String createFile(@RequestParam("directory_name")String name)
    {
        System.out.println(name);
        return FileUtils.createDirectory(name);
    }
}
