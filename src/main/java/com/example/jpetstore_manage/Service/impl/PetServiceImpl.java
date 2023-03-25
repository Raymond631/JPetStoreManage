package com.example.jpetstore_manage.Service.impl;

import com.example.jpetstore_manage.Mapper.PetMapper;
import com.example.jpetstore_manage.POJO.DataObject.PetItemDO;
import com.example.jpetstore_manage.POJO.DataObject.PetProductDO;
import com.example.jpetstore_manage.POJO.ViewObject.Message;
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
    public List<PetProductDO> getPetList(String supplier) {
        List<PetProductDO> petProductDOList = petMapper.selectProductBySupplier(supplier);
        return petProductDOList;
    }

    @Override
    public PetProductDO getPetDetail(int productId) {
        PetProductDO petProductDO = petMapper.selectProductByProductId(productId);
         return petProductDO;
    }

    @Override
    public Message remove(int productId) {
        int number = petMapper.updateStock(productId);
        return new Message(number,"You have moved "+ number+" row.");
    }

    @Override
    public Message newPet(PetProductDO petProductDO) {
        List<PetItemDO> petItemDOList = petProductDO.getPetItemList();
        for(PetItemDO petItemDO:petItemDOList){
            petMapper.insertPetItem(petItemDO);
        }
        int number = petMapper.insertPetProduct(petProductDO);
        return new Message(number,"You have inserted "+ number+" row.");
    }

    @Override
    public Message updatePet(PetProductDO petProductDO) {
        List<PetItemDO> petItemDOList = petProductDO.getPetItemList();
        for(PetItemDO petItemDO:petItemDOList){
            petMapper.updatePetItem(petItemDO);
        }
        int number = petMapper.updatePetProduct(petProductDO);
        return new Message(number,"You have inserted "+ number+" row.");
    }
}
