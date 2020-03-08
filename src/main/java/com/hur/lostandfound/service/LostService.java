package com.hur.lostandfound.service;

import com.hur.lostandfound.bean.Lost;

import java.util.List;

public interface LostService {
    public List<Lost> selectAllLost();

    public List<Lost> selectLostByMsg(String msg);

    public List<Lost> selectAllLost1();

    public void insertLost(Lost lost);

    public void deleteLostById(int id);
}
