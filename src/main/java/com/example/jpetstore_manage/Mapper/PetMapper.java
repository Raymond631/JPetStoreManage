package com.example.jpetstore_manage.Mapper;

import com.example.jpetstore_manage.POJO.DataObject.PetItemDO;
import com.example.jpetstore_manage.POJO.DataObject.PetProductDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:34
 * @description
 */
@Mapper
public interface PetMapper {
    /**
     * 查询列表
     * 只需要查询productId、category、productNameChinese、productNameEnglish、productImage这五个字段
     */
    public List<PetProductDO> selectProductBySupplier(int supplier);

    /**
     * 查询详情
     */
    public PetProductDO selectProductByProductId(int productId);


    /**
     * 将petProductDO插入pet_product表（主键为自增主键，插入时注意跳过主键字段）
     * 利用<selectKey></selectKey>返回自增主键
     */
    public int insertPetProduct(PetProductDO petProductDO);

    /**
     * 将petItemDO插入pet_item表（主键为自增主键，插入时注意跳过主键字段）
     */
    public int insertPetItem(PetItemDO petItemDO);


    public int remove(int productId, int supplier);

    /**
     * 更新除了product_id、product_supplier外的其他字段
     */
    public int updatePetProduct(PetProductDO petProductDO);

    /**
     * 更新除了item_id、product_id外的其他字段
     */
    public int updatePetItem(PetItemDO petItemDO);

}
