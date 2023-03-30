package com.example.jpetstore_manage.Common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//对jpetstore/uploadedImage/的资源访问均映射到在本地磁盘d盘根目录中的test文件夹
public class FilePathMapConfig implements WebMvcConfigurer {

    //@Value("${image.uploadedImagePath}")
    String uploadedImagePath = "D:/test/";

    public void addResourceHandlers(ResourceHandlerRegistry registry){

        //上传的图片默认在项目根同级文件下内

        //访问路径
        registry.addResourceHandler("/uploadedImage/**")
                //访问后物理路径
                .addResourceLocations("file:"+uploadedImagePath);
    }
}
