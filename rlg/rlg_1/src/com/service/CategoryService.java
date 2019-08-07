package com.service;

import com.common.ResponseCode;
import com.dao.CategoryDao;
import com.pojo.Getcategory;

public class CategoryService {

   CategoryDao  cd=new CategoryDao();
   //获取品类子节点
    public ResponseCode selectone(String categoryId1) {
        ResponseCode rs = new ResponseCode();
        if(categoryId1==null){
        rs.setStatus(1);
        rs.setMsg("输入的id为空");
            return  rs;
        }
//转为int
         Integer  categoryId=Integer.parseInt(categoryId1);

        Getcategory g = cd.selectone(categoryId);
        if (g == null) {
            rs.setStatus(1);
            rs.setMsg("没有该产品");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(g);
        return rs;
    }

//增加节点
    public ResponseCode selectone1(String categoryId1,String categoryName) {
        ResponseCode rs = new ResponseCode();
        if(categoryId1==null||categoryName==null){
            rs.setStatus(1);
            rs.setMsg("输入的id为空");
            return  rs;
        }
//转为int
        Integer  categoryId=Integer.parseInt(categoryId1);

        int row = cd.selectone(categoryId,categoryName);
        if (row>0) {
            rs.setStatus(0);
            rs.setMsg("添加成功");
            return rs;
        }else{
            rs.setStatus(1);
            rs.setMsg("添加失败");
            return rs;

        }
    }

//修改品类的名字
public ResponseCode selectone2(String categoryId2,String categoryName) {
    ResponseCode rs = new ResponseCode();
    if(categoryId2==null||categoryName==null){
        rs.setStatus(1);
        rs.setMsg("输入的id为空");
        return  rs;
    }
//转为int
    Integer  categoryId=Integer.parseInt(categoryId2);

    int row = cd.selectone1(categoryId,categoryName);
    if (row>0) {
        rs.setStatus(0);
        rs.setMsg("修改成功");
        return rs;
    }else{
        rs.setStatus(1);
        rs.setMsg("修改失败");
        return rs;

    }
}

    //获取当前分类id及递归子节点categoryId
    //没有完成
    public ResponseCode selectone3(String categoryId1) {
        ResponseCode rs=new ResponseCode();
        if(categoryId1==null){
            rs.setStatus(1);
            rs.setMsg("输入的值为空");
            return rs;
        }

        Integer categoryId=Integer.parseInt(categoryId1);
    //    cd.selectone(categoryId);
        return rs;


    }

}

