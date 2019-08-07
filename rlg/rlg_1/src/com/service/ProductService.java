package com.service;

import com.common.ResponseCode;
import com.dao.ProductDao;
import com.pojo.Product;

import java.util.List;

public class ProductService {
    private ProductDao pd = new ProductDao();

    //商品列表
    public ResponseCode selectAll(String pageSize1, String pageNum1) {
        if (pageSize1 == null || pageSize1.equals("")) {
            pageSize1 = "10";
        }
        if (pageNum1 == null || pageNum1.equals("")) {
            pageNum1 = "1";
        }
        Integer pageSize =Integer.parseInt(pageSize1)  ;
        Integer  pageNum=Integer.parseInt(pageNum1)  ;
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
            rs.setMsg("商品信息输入错误");
            return rs;
        }
        //查找是否有这样一个商品
        Product p = pd.selectone(productName);
        if (p == null) {
            rs.setStatus(1);
            rs.setMsg("该商品不存在");
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

        } catch (Exception e) {
            rs.setStatus(0);
            rs.setMsg("输入的id为空");
            return rs;
        }

        //查找是否有这样一个商品
        Product p = pd.selectone1(productId);
        if (p == null) {
            rs.setStatus(1);
            rs.setMsg("该商品不存在");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }


    //上传照片


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

        } catch (Exception e) {
            rs.setStatus(0);
            rs.setMsg("输入的id为空");
            return rs;
        }

        //查找是否有这样一个商品
        Product p = pd.selectone3(productId);
        if (p == null) {
            rs.setStatus(1);
            rs.setMsg("该商品不存在");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;


    }

    //商品的下架
    public ResponseCode selectone3(String productId1) {
        ResponseCode rs = new ResponseCode();
        //输入的参数为空
        if (productId1 == null) {
            rs.setStatus(1);
            rs.setMsg("输入的值为空");
            return rs;
        }
        //字符串转数值
        Integer  productId = Integer.parseInt(productId1);
        //查找是否有这样一个商品

        Product  p = pd.selectone4(productId);
            //如果商品不存在
            if (p == null) {
                rs.setStatus(1);
                rs.setMsg("该商品不存在或状态码不正确");
                return rs;
            }
            //商品禁用情况
            if (p.getStatus() == 1) {
                rs.setStatus(1);
               rs.setMsg("该商品已经被禁用");
               return rs;
            }
            //禁用
            int row = pd.updateByUid1(productId);
            if (row>0) {
                rs.setStatus(0);
                rs.setMsg("修改产品状态成功");
                return rs;
            }
                rs.setStatus(1);
                rs.setMsg("修改产品状态失败");
                return rs;
            }

      //商品上架
    public ResponseCode selectone3_1(String productId1) {
        ResponseCode rs = new ResponseCode();
        //输入的参数为空
        if (productId1 == null) {
            rs.setStatus(1);
            rs.setMsg("输入的值为空");
            return rs;
        }
        //字符串转数值
        Integer  productId = Integer.parseInt(productId1);
        //查找是否有这样一个商品

        Product  p = pd.selectone4(productId);
        //如果商品不存在
        if (p == null) {
            rs.setStatus(1);
            rs.setMsg("该商品不存在或状态码不正确");
            return rs;
        }
        int row = pd.updateByUid2(productId);
        if (row>0) {
            rs.setStatus(0);
            rs.setMsg("修改产品状态成功");
            return rs;
        }else{
            rs.setStatus(1);
            rs.setMsg("修改产品状态失败");
            return rs;
        }

    }



    //更新产品
    public ResponseCode selectone4(String categoryId, String name, String subtitle, String mainImage, String price, String status) {
        ResponseCode rs = new ResponseCode();
        //输入的参数为空
        if (categoryId == null || name == null || subtitle == null || mainImage == null || price == null || status == null) {
            rs.setStatus(1);
            rs.setMsg("输入的值为空");
            return rs;
        }
        //添加商品
        int   row = pd.selectone(categoryId, name, subtitle, mainImage, price, status);

        //商品是否添加成功情况
        if (row<=0) {
            rs.setStatus(1);
            rs.setMsg("添加产品失败");
            return rs;
        }else{
            rs.setStatus(0);
            rs.setMsg("添加产品成功");
            return rs;

        }

    }

    //修改产品信息
    public ResponseCode selectone5(String categoryId, String name, String subtitle, String mainImage, String price, String status, String id1) {
        ResponseCode rs = new ResponseCode();
        //输入的参数为空
        if (categoryId == null || name == null || subtitle == null || mainImage == null || price == null || status == null||id1==null) {
            rs.setStatus(1);
            rs.setMsg("输入的值为空");
            return rs;
        }
        Integer  id=Integer.parseInt(id1);
        //添加商品
        int   row = pd.selectone(categoryId, name, subtitle, mainImage, price, status,id);

        //商品是否添加成功情况
        if (row<=0) {
            rs.setStatus(1);
            rs.setMsg("修改产品失败");
            return rs;
        }else{
            rs.setStatus(0);
            rs.setMsg("修改产品成功");
            return rs;

        }

    }


}


