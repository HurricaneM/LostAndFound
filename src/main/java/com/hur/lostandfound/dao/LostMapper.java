package com.hur.lostandfound.dao;

import com.hur.lostandfound.bean.Lost;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

//@org.apache.ibatis.annotations.Mapper
public interface LostMapper extends Mapper<Lost> {

    public List<Lost> selectLostByMsg(@Param("msg") String msg);

    public List<Lost> selectAllLost();

    public void deleteById(@Param("id") int id);
}
