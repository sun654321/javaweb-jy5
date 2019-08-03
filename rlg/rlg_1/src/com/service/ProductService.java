package com.service;

import com.common.ResponseCode;
import com.dao.ProductDao;
import com.pojo.Product;

import java.util.List;

public class ProductService {
    private ProductDao pd = new ProductDao();

    //商品列表
  public ResponseCode selectAll(String pageSize, String pageNum) {
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }
        List<Product> li = pd.selectAll(pageSize, pageNum);

        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);

        return rs;


    }

//根据商品的名称进行查询
    public ResponseCode selectone(String productName) {
        ResponseCode rs = new ResponseCode();
        if (productName == null || productName.equals("")) {
            rs.setStatus(1);
            rs.setMsg("商品输入错误");
            return rs;
        }

        //查找是否有这样一个商品
        Product p = pd.selectone(productName);
        if (p == null) {
            rs.setStatus(1);
            rs.setMsg("商品输入错误,没有查询到该商品");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);

        return rs;

    }

//根据商品的id进行查询
    public ResponseCode selectone1(String productId1) {
        ResponseCode rs = new ResponseCode();
        if (productId1 == null || productId1.equals("")) {
            rs.setStatus(1);
            rs.setMsg("商品的id输入错误");
            return rs;
        }
        //字符串转数值
        Integer productId = null;
        try {
            productId = Integer.parseInt(productId1);

        }catch (Exception e){
            rs.setStatus(0);
            rs.setMsg("输入的id为空");
            return rs;
        }

        //查找是否有这样一个商品
        Product p = pd.selectone1(productId);
        if (p == null) {
            rs.setStatus(1);
            rs.setMsg("商品的id输入错误");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }

//根据id查询详细信息
    public ResponseCode selectone2(String productId1) {
        ResponseCode rs = new ResponseCode();
        if (productId1 == null || productId1.equals("")) {
            rs.setStatus(1);
            rs.setMsg("商品的id输入错误");
            return rs;
        }
        //字符串转数值
        Integer productId = null;
        try {
            productId = Integer.parseInt(productId1);

        }catch (Exception e){
            rs.setStatus(0);
            rs.setMsg("输入的id为空");
            return rs;
        }

        //查找是否有这样一个商品
        Product p = pd.selectone3(productId);
        if (p == null) {
            rs.setStatus(1);
            rs.setMsg("商品的id输入错误");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;


    }

//商品的上下架
    public ResponseCode selectone3(String productId1, String status1) {
        ResponseCode rs = new ResponseCode();
        //输入的参数为空
        if (productId1 == null || productId1.equals("")||status1==null||status1.equals("")) {
            rs.setStatus(2);
            rs.setMsg("输入的值为空");
            return rs;
        }

        //字符串转数值
        Integer productId = null;
        Integer status=null;
        try {
            productId = Integer.parseInt(productId1);
            status=Integer.parseInt(status1);
        }catch (Exception e){
            rs.setStatus(3);
            rs.setMsg("输入的商品信息有误");
            return rs;
        }

        //查找是否有这样一个用户
        Product p = pd.selectone4(productId,status);

        //如果商品不存在
        if (p == null) {
            rs.setStatus(4);
            rs.setMsg("该商品不存在");
            return rs;
        }
        //商品禁用情况
        if (p.getStatus() == 1) {
            rs.setStatus(1);
            rs.setMsg("修改产品状态失败");
            return rs;
        }
        //禁用用户
        int row = pd.updateByUid(productId,status);
        if (row <= 0) {
            rs.setStatus(0);
            rs.setMsg("修改产品状态成功");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(row);
        return rs;



    }
}
