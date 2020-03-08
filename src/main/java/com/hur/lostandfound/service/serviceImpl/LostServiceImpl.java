package com.hur.lostandfound.service.serviceImpl;

import com.hur.lostandfound.bean.Lost;
import com.hur.lostandfound.dao.LostMapper;
import com.hur.lostandfound.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostServiceImpl implements LostService {

    @Autowired
    LostMapper lostMapper;

    @Override
    public List<Lost> selectAllLost() {
        lostMapper.selectAll();
        return lostMapper.selectAll();
    }

    @Override
    public List<Lost> selectLostByMsg(String msg) {
        System.out.println("service selectByMsg");
        return lostMapper.selectLostByMsg(msg);
    }

    @Override
    public List<Lost> selectAllLost1() {
        return lostMapper.selectAllLost();
    }

    @Override
    public void insertLost(Lost lost) {
        lostMapper.insert(lost);
    }

    @Override
    public void deleteLostById(int id) {
        lostMapper.deleteById(id);
    }


}
