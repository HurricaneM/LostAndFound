package com.hur.lostandfound.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Msg {
    int status;
    String msg;
    Map<String,Object> extend=new HashMap<String,Object>();

    public static Msg success(){
        Msg msg = new Msg();
        msg.setStatus(200).setMsg("SUCCESS");
        return msg;
    }

    public static Msg fail(){
        Msg msg = new Msg();
        msg.setStatus(400).setMsg("FAIL");
        return msg;
    }

    public Msg add(String s,Object o){
        this.getExtend().put(s,o);
        return this;
    }
}
