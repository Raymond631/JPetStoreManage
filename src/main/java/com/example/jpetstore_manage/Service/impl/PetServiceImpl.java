package com.example.jpetstore_manage.Service.impl;

import com.example.jpetstore_manage.Mapper.PetMapper;
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
        return null;
    }

    @Override
    public PetProductDO getPetDetail(int productId) {
        return null;
    }

    @Override
    public Message remove(int productId) {
        return null;
    }

    @Override
    public Message newPet(PetProductDO petProductDO) {
        return null;
    }

    @Override
    public Message updatePet(PetProductDO petProductDO) {
        return null;
    }
}
