package com.example.jpetstore_manage.Controller;

import com.example.jpetstore_manage.POJO.DataObject.PetProductDO;
import com.example.jpetstore_manage.POJO.DataObject.UserMainDO;
import com.example.jpetstore_manage.POJO.MapStruct.PetMapping;
import com.example.jpetstore_manage.POJO.ViewObject.Message;
import com.example.jpetstore_manage.POJO.ViewObject.PetDetailVO;
import com.example.jpetstore_manage.POJO.ViewObject.PetListVO;
import com.example.jpetstore_manage.Service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/pet")
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
    @GetMapping("/list")
    public List<PetListVO> getPetList(@SessionAttribute("loginUser") UserMainDO userMainDO) {
        List<PetProductDO> petProductDOS = petService.getPetList(userMainDO.getUserId());
        return petMapping.toPetListVOList(petProductDOS);
    }

    /**
     * 调用service层，根据productId查询宠物详情
     */
    @GetMapping("/detail")
    public PetDetailVO getPetDetail(int productId) {
        PetProductDO petProductDO = petService.getPetDetail(productId);
        return petMapping.toPetDetailVO(petProductDO);
    }

    /**
     * 下架（为了方便，这里修改库存为零即可）
     */
    @PutMapping("/remove")
    public Message remove(int productId) {
        return petService.remove(productId);
    }

    /**
     * 对象转换
     * 调用service新增宠物，供应商为当前用户（userMainDO）
     */
    @PostMapping("/newPet")
    public Message newPet(@RequestBody PetDetailVO petDetailVO, @SessionAttribute("loginUser") UserMainDO userMainDO) {
        return petService.newPet(petMapping.toPetProductDO(petDetailVO, userMainDO));
    }

    /**
     * 修改宠物信息（productId和ItemId不可修改）
     * 上传图片请调用ImageController中的接口
     */
    @PutMapping("/updatePet")
    public Message updatePet(@RequestBody PetDetailVO petDetailVO, @SessionAttribute("loginUser") UserMainDO userMainDO) {
        PetProductDO petProductDO = petMapping.toPetProductDO(petDetailVO, userMainDO);
        return petService.updatePet(petProductDO);
    }
}
