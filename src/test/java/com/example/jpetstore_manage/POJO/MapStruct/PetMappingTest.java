package com.example.jpetstore_manage.POJO.MapStruct;

import com.example.jpetstore_manage.POJO.DataObject.PetItemDO;
import com.example.jpetstore_manage.POJO.DataObject.PetProductDO;
import com.example.jpetstore_manage.POJO.DataObject.UserMainDO;
import com.example.jpetstore_manage.POJO.ViewObject.PetDetailVO;
import com.example.jpetstore_manage.POJO.ViewObject.PetItemVO;
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
 * @create 2023-03-21 20:32
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class PetMappingTest {
    @Autowired
    private PetMapping petMapping;

    @Test
    void toPetListVOList() {
        PetProductDO petProductDO = new PetProductDO();
        petProductDO.setProductId(1);
        petProductDO.setCategory("狗狗");
        petProductDO.setProductNameChinese("哈士奇");
        petProductDO.setProductNameEnglish("Siberian Huskiy");
        petProductDO.setProductImage("/image/1.jpg");

        PetProductDO petProductDO2 = new PetProductDO();
        petProductDO2.setProductId(1);
        petProductDO2.setCategory("狗狗");
        petProductDO2.setProductNameChinese("哈士奇");
        petProductDO2.setProductNameEnglish("Siberian Huskiy");
        petProductDO2.setProductImage("/image/1.jpg");

        List<PetProductDO> petProductDOList = new ArrayList<>();
        petProductDOList.add(petProductDO);
        petProductDOList.add(petProductDO2);
        System.out.println(petMapping.toPetListVOList(petProductDOList));
    }

    @Test
    void toPetDetailVOList() {
        PetProductDO petProductDO = new PetProductDO();
        petProductDO.setProductId(1);
        petProductDO.setCategory("狗狗");
        petProductDO.setProductNameChinese("哈士奇");
        petProductDO.setProductNameEnglish("Siberian Huskiy");
        petProductDO.setProductImage("/image/1.jpg");
        PetItemDO petItemDO = new PetItemDO(1, 1, "成年雄性", new BigDecimal("10.58"), 23);
        PetItemDO petItemDO2 = new PetItemDO(1, 1, "成年雄性", new BigDecimal("10.58"), 23);
        List<PetItemDO> petItemDOList = new ArrayList<>();
        petItemDOList.add(petItemDO);
        petItemDOList.add(petItemDO2);
        petProductDO.setPetItemList(petItemDOList);

        System.out.println(petMapping.toPetDetailVO(petProductDO));
    }

    @Test
    void toPetProductDO() {
        PetDetailVO petDetailVO = new PetDetailVO();
        petDetailVO.setProductId(1);
        petDetailVO.setCategory("狗狗");
        petDetailVO.setProductNameChinese("哈士奇");
        petDetailVO.setProductNameEnglish("Siberian Huskiy");
        petDetailVO.setProductImage("/image/1.jpg");
        PetItemVO petItemVO = new PetItemVO(1, "成年雄性", new BigDecimal("10.58"), 23);
        PetItemVO petItemVO2 = new PetItemVO(1, "成年雄性", new BigDecimal("10.58"), 23);
        List<PetItemVO> petItemVOList = new ArrayList<>();
        petItemVOList.add(petItemVO);
        petItemVOList.add(petItemVO2);
        petDetailVO.setPetItemList(petItemVOList);

        System.out.println(petMapping.toPetProductDO(petDetailVO, new UserMainDO("张三", "123")));
    }
}