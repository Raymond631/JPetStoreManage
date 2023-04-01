package com.example.jpetstore_manage.Service.impl;

import com.example.jpetstore_manage.Common.CommonResponse;
import com.example.jpetstore_manage.Mapper.PetMapper;
import com.example.jpetstore_manage.POJO.DataObject.PetItemDO;
import com.example.jpetstore_manage.POJO.DataObject.PetProductDO;
import com.example.jpetstore_manage.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:34
 * @description
 */
@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetMapper petMapper;


    @Override
    public List<PetProductDO> getPetList(int supplier) {
        return petMapper.selectProductBySupplier(supplier);
    }

    @Override
    public PetProductDO getPetDetail(int productId) {
        return petMapper.selectProductByProductId(productId);
    }

    @Override
    public CommonResponse newPet(PetProductDO petProductDO, int supplier) {
        petProductDO.setSupplierId(supplier);
        petMapper.insertPetProduct(petProductDO);

        List<PetItemDO> petItemDOList = petProductDO.getPetItemList();
        for (PetItemDO petItemDO : petItemDOList) {
            petItemDO.setProductId(petProductDO.getProductId());
            petMapper.insertPetItem(petItemDO);
        }
        return CommonResponse.success("添加成功");
    }

    @Override
    public CommonResponse remove(int productId, int supplier) {
        int number = petMapper.remove(productId, supplier);
        if (number == 1) {
            return CommonResponse.success("删除成功");
        } else {
            return CommonResponse.error("删除失败");
        }
    }

    @Override
    public CommonResponse updatePet(PetProductDO petProductDO, int supplier) {
        petProductDO.setSupplierId(supplier);
        petMapper.updatePetProduct(petProductDO);

        List<PetItemDO> petItemDOList = petProductDO.getPetItemList();
        for (PetItemDO petItemDO : petItemDOList) {
            petMapper.updatePetItem(petItemDO);
        }
        return CommonResponse.success("修改成功");
    }

    @Override
    public String saveImage(MultipartFile multipartFile) throws IOException {
        String filePath = "D:/jpetstoreImage/";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 文件名称
        String realFileName = multipartFile.getOriginalFilename();
        // 文件处理
        String newFileName = UUID.randomUUID() + "-" + realFileName;
        String newFilePath = filePath + newFileName;
        // 新文件的路径
        multipartFile.transferTo(new File(newFilePath));

        return newFileName;
    }

    @Override
    public void updateImage(int productId, String newFileName) {
        // 删除老文件
        String oldImageName = petMapper.selectProductImageById(productId);
        try {
            File file = new File("D:/jpetstoreImage/" + oldImageName);
            if (file.delete()) {
                System.out.println(file.getName() + " 文件已被删除！");
            } else {
                System.out.println("文件删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 删除新文件
        petMapper.updateImage(productId, newFileName);
    }
}
