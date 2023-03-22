package com.example.jpetstore_manage.Mapper;

import com.example.jpetstore_manage.POJO.DataObject.PetItemDO;
import com.example.jpetstore_manage.POJO.DataObject.PetProductDO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-21 22:23
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class PetMapperTest {

    @Autowired
    PetMapper petMapper;

    @Test
    void selectProductBySupplier() {
        System.out.println("\n\n\n测试开始..........");
        List<PetProductDO> petProductDOList = petMapper.selectProductBySupplier("csu001");
        System.out.println(petProductDOList);
        System.out.println(petProductDOList.size());
        System.out.println(petProductDOList.get(0).getPetItemList());
        System.out.println("测试结束..........\n\n\n");
    }

    @Test
    void selectProductByProductId() {
        System.out.println("\n\n\n测试开始..........");
        PetProductDO petProductDO = petMapper.selectProductByProductId(1);
        System.out.println(petProductDO);
        System.out.println("测试结束..........\n\n\n");
    }

    @Test
    void selectItemByProductId() {
        System.out.println("\n\n\n测试开始..........");
        List<PetItemDO> petItemDOList = petMapper.selectItemByProductId(1);
        System.out.println(petItemDOList);
        System.out.println(petItemDOList.size());
        System.out.println("测试结束..........\n\n\n");
    }

    @Test
    void updateStock() {
        System.out.println("\n\n\n测试开始..........");
        System.out.println(petMapper.updateStock(1));
        List<PetItemDO> petItemDOList = petMapper.selectItemByProductId(1);
        System.out.println(petItemDOList.get(0).getItemStock());
        System.out.println("测试结束..........\n\n\n");

    }

    @Test
    void insertPetProduct() {
        System.out.println("\n\n\n测试开始..........");
        PetProductDO testProduct = new PetProductDO(999, "1", "test", "test",
                "test", "test", "test", "test", "test",
                "test","test", new ArrayList<>());
        System.out.println(petMapper.insertPetProduct(testProduct));
        System.out.println("测试结束..........\n\n\n");
    }

    @Test
    void insertPetItem() {
        System.out.println("\n\n\n测试开始..........");
        PetItemDO testItem = new PetItemDO(888,888,"test",BigDecimal.valueOf(888),888);
        System.out.println(petMapper.insertPetItem(testItem));
        System.out.println("测试结束..........\n\n\n");
    }

    @Test
    void updatePetProduct() {
        System.out.println("\n\n\n测试开始..........");
        PetProductDO petProductDO = petMapper.selectProductByProductId(1);
        System.out.println(petProductDO);
        petProductDO.setProductSupplier("test");
        System.out.println(petMapper.updatePetProduct(petProductDO));
        System.out.println("测试结束..........\n\n\n");
    }

    @Test
    void updatePetItem() {
        System.out.println("\n\n\n测试开始..........");
        List<PetItemDO> petItemDOList = petMapper.selectItemByProductId(1);
        PetItemDO testItem = petItemDOList.get(0);
        testItem.setItemPrice(BigDecimal.valueOf(888));
        System.out.println(petMapper.updatePetItem(testItem));
        System.out.println("测试结束..........\n\n\n");
    }
}