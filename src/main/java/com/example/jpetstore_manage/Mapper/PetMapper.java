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
     * 只需要查询productId、category、productNameChinese、productNameEnglish、productImage这五个字段
     */
    public List<PetProductDO> selectProductBySupplier(int supplier);

    /**
     * pet_product x pet_item联表查询(一对多，用collection标签)
     * 根据productId查pet_product,再用productId对pet_item表进行联表查询
     */
    public PetProductDO selectProductByProductId(int productId);

    /**
     * 此方法供 上面的selectProductByProductId方法 进行联表查询使用
     * 根据productId查询该product所包含的item
     */
    public List<PetItemDO> selectItemByProductId(int productId);

    /**
     * 根据productId修改pet_item表的item_stock字段(productId会匹配多个item，都改为零)
     */
    public int updateStock(int productId);

    /**
     * 将petProductDO插入pet_product表（主键为自增主键，插入时注意跳过主键字段）
     * 利用<selectKey></selectKey>返回自增主键
     */
    public int insertPetProduct(PetProductDO petProductDO);

    /**
     * 将petItemDO插入pet_item表（主键为自增主键，插入时注意跳过主键字段）
     */
    public int insertPetItem(PetItemDO petItemDO);

    /**
     * 更新除了product_id、product_supplier外的其他字段
     */
    public int updatePetProduct(PetProductDO petProductDO);

    /**
     * 更新除了item_id、product_id外的其他字段
     */
    public int updatePetItem(PetItemDO petItemDO);
}
