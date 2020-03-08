package com.hur.lostandfound.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hur.lostandfound.bean.Lost;
import com.hur.lostandfound.bean.Msg;
import com.hur.lostandfound.service.LostService;
import com.hur.lostandfound.service.serviceImpl.LostServiceImpl;
import org.jcp.xml.dsig.internal.dom.ApacheOctetStreamData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@CrossOrigin(origins = {"*","null"})
public class LostController {

    @Autowired
    LostServiceImpl lostService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LostController.class) ;


//    @GetMapping("/lost")
//    public Msg getAllLost(String msg){
//        System.out.println(msg);
//        if (msg==null){
//            System.out.println("no msg");
//            List<Lost> losts = lostService.selectAllLost();
//            return Msg.success().add("losts",losts);
//        }else {
//            System.out.println("have msg");
//            List<Lost> losts1 = lostService.selectLostByMsg(msg);
//            for (Lost lost:losts1)
//                lost.getName();
//            return Msg.success().add("losts",losts1);
//        }
//    }

    @GetMapping("/lost/page/{pn}")
    public Msg getAllLost(@PathVariable Integer pn){

        PageHelper.startPage(pn,9);
        System.out.println(pn);
        List<Lost> losts = lostService.selectAllLost1();
        PageInfo pageInfo = new PageInfo(losts,5);
        System.out.println(pageInfo.getSize());
        return Msg.success().add("pageInfo",pageInfo);

    }

    @GetMapping("/lost/{searchMsg}")
    public Msg getAllLost(@PathVariable String searchMsg){

        System.out.println("/lost/{searchMsg}/{str}");
        System.out.println("searchMsg"+searchMsg);
        System.out.println("have msg");

        PageHelper.startPage(1,9);
        List<Lost> losts1 = lostService.selectLostByMsg(searchMsg);
        PageInfo pageInfo = new PageInfo(losts1,5);
        for (Lost lost:losts1)
            System.out.println(lost.getName());
        return Msg.success().add("pageInfo",pageInfo);
    }


    @PostMapping("/lost")
    public Msg addLost(@RequestParam("picture") MultipartFile picture,String name,String nowAddress
            ,String lostDate,String lostPlace,String otherMsg) throws IOException, ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(lostDate);


        Lost lost = new Lost();
        lost.setName(name).setNowAddress(nowAddress).setLostDate(date).setLostPlace(lostPlace).setOtherMsg(otherMsg);

        if (!picture.isEmpty()){
            LOGGER.info("Name == >>{}",picture.getName());
            LOGGER.info("OriginalFilename == >>{}",picture.getOriginalFilename());
            LOGGER.info("ContentType == >>{}",picture.getContentType());
            LOGGER.info("Size == >>{}",picture.getSize());
            String filePath = "E:/IDEAproject/lostandfound/src/main/resources/static/picture" ;
            new File(filePath).mkdirs();
            File writeFile = new File(filePath,picture.getOriginalFilename());
            picture.transferTo(writeFile);
            lost.setPicture("Http://localhost:8080/picture/"+picture.getOriginalFilename());
        }

        lostService.insertLost(lost);

        return Msg.success();
    }



    @RequestMapping("addProduct")
    @ResponseBody
    public Boolean addProduct(HttpServletRequest request, String pName, Integer pStock,
                              BigDecimal pCost, BigDecimal pPrice, String pDescription, @RequestParam("pPicture") MultipartFile pPicture) throws IOException {
//        String fileName = UUID.randomUUID()+ pPicture.getOriginalFilename();
        System.out.println(pName+" "+pStock+"  "+pCost);
        if(!pPicture.isEmpty()){
            LOGGER.info("Name == >>{}",pPicture.getName());
            LOGGER.info("OriginalFilename == >>{}",pPicture.getOriginalFilename());
            LOGGER.info("ContentType == >>{}",pPicture.getContentType());
            LOGGER.info("Size == >>{}",pPicture.getSize());
            // 文件输出地址
            String filePath = "E:/IDEAproject/lostandfound/src/main/resources/static/picture" ;
            new File(filePath).mkdirs();
            File writeFile = new File(filePath, pPicture.getOriginalFilename());
            pPicture.transferTo(writeFile);

        }
        return true;
    }

    @DeleteMapping("/lost")
    public Msg deleteLostById(int id){
        System.out.println("deleteLost");
        System.out.println(id);
        Lost lost = new Lost();
        lost.setId(id);
        lostService.deleteLostById(id);
        return Msg.success();
    }
}
