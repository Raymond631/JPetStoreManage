package com.example.jpetstore_manage.Service;

import com.example.jpetstore_manage.POJO.DataObject.PetProductDO;
import com.example.jpetstore_manage.POJO.ViewObject.CommonResponse;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:32
 * @description
 */
public interface PetService {
    /**
     * 根据supplier查询宠物列表（简略信息）
     */
    public List<PetProductDO> getPetList(int supplier);

    /**
     * 根据productId查询某个product的详细信息
     */
    public PetProductDO getPetDetail(int productId);

    /**
     * 调用Mapper层接口，将库存修改为零
     */
    public CommonResponse remove(int productId);

    /**
     * 将PetProduct的常规属性插入pet_product表（插入后，自增主键会被封装在PetProductDo的productId中返回）
     * 遍历PetProduct中的petItemList：将PetProductDO中的productId封装到PetItemDO的productId中去，再将petItemDO插入pet_item表
     */
    public CommonResponse newPet(PetProductDO petProductDO);

    /**
     * 根据productId更新pet_product表中相应的信息
     * 遍历PetProduct中的petItemList：根据itemId更新pet_item表中相应的信息
     */
    public CommonResponse updatePet(PetProductDO petProductDO);
}
