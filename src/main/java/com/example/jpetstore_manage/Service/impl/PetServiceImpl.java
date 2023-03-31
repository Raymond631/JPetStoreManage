package com.example.jpetstore_manage.Service.impl;

import com.example.jpetstore_manage.Common.CommonResponse;
import com.example.jpetstore_manage.Mapper.PetMapper;
import com.example.jpetstore_manage.POJO.DataObject.PetItemDO;
import com.example.jpetstore_manage.POJO.DataObject.PetProductDO;
import com.example.jpetstore_manage.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
