package com.example.jpetstore_manage.Controller;

import com.example.jpetstore_manage.POJO.DataObject.PetProductDO;
import com.example.jpetstore_manage.POJO.DataObject.UserAuthDO;
import com.example.jpetstore_manage.POJO.MapStruct.PetMapping;
import com.example.jpetstore_manage.POJO.ViewObject.CommonResponse;
import com.example.jpetstore_manage.POJO.ViewObject.PetDetailVO;
import com.example.jpetstore_manage.POJO.ViewObject.PetListVO;
import com.example.jpetstore_manage.Service.PetService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:31
 * @description 宠物数据接口，前端通过AJAX请求数据，页面跳转请调用AllPageController中的接口
 * 查询“我”上架的宠物列表
 * 查看某个宠物的详情
 * 下架宠物（修改库存为-1）
 * 上架新宠物
 * 修改宠物信息
 */
@Slf4j
@RestController
public class PetController {
    /**
     * 业务层接口
     */
    @Autowired
    private PetService petService;

    /**
     * 对象转换器
     */
    @Autowired
    private PetMapping petMapping;

    /**
     * 调用service层，查找供应商为“当前用户”的宠物列表(userMainDO代表当前用户)
     */
    @GetMapping("/pets/list")
    public List<PetListVO> getPetList(@SessionAttribute("loginUser") UserAuthDO userAuthDO) {
        List<PetProductDO> petProductDOS = petService.getPetList(userAuthDO.getUserId());
        return petMapping.toPetListVOList(petProductDOS);
    }

    /**
     * 调用service层，根据productId查询宠物详情
     */
    @GetMapping("/pets/{productId}")
    public PetDetailVO getPetDetail(@PathVariable("productId") int productId) {
        PetProductDO petProductDO = petService.getPetDetail(productId);
        return petMapping.toPetDetailVO(petProductDO);
    }

    /**
     * 下架（为了方便，这里修改库存为零即可）
     */
    @DeleteMapping("/pets/{productId}")
    public CommonResponse remove(@PathVariable("productId") int productId) {
        return petService.remove(productId);
    }

    /**
     * 对象转换
     * 调用service新增宠物，供应商为当前用户（userMainDO）
     */
    @PostMapping("/pets")
    public CommonResponse newPet(@RequestBody PetDetailVO petDetailVO, @SessionAttribute("loginUser") UserAuthDO userAuthDO) {
        return petService.newPet(petMapping.toPetProductDO(petDetailVO, userAuthDO));
    }

    /**
     * 修改宠物信息（productId和ItemId不可修改）
     * 上传图片请调用ImageController中的接口
     */
    @PutMapping("/pets")
    public CommonResponse updatePet(@RequestBody PetDetailVO petDetailVO, @SessionAttribute("loginUser") UserAuthDO userAuthDO) {
        PetProductDO petProductDO = petMapping.toPetProductDO(petDetailVO, userAuthDO);
        return petService.updatePet(petProductDO);
    }

    /**
     * 图片上传接口
     * TODO 开发中，有bug
     */
    @PostMapping("/uploadImage")
    public void uploadImageAjax(HttpServletRequest request) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multiRequest.getFile("file");
        try {
            if (!multipartFile.isEmpty()) {
                // 根路径，在 resources/static/pet
                String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/image/pet/";
                // 获取文件的名称
                String fileName = multipartFile.getOriginalFilename();
                // 获取文件对象
                File file = new File(basePath, fileName);
                // 完成文件的上传
                multipartFile.transferTo(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File test = new File("src/main/resources/static/image/pet");
        System.out.println("相对路径:" + test.getPath());
        try {
            String testPath = test.getCanonicalPath();
            System.out.println("映射路径:" + testPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
