package com.controller;

import com.common.ResponseCode;
import com.pojo.Product;
import com.service.ProductService;
import com.util.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ProductController",value = "/manage/product/*")
public class ProductController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request,response);
    }

    private ProductService ps = new ProductService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求路径信息
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        ResponseCode rs = null;
        ResponseCode rs1 = null;
        ResponseCode rs2 = null;
        //判断是什么请求
        switch (path) {
            case "list":
                rs = listdo(request);
                break;
             case "search":
                //在这应该加一个判断，是否输入的是什么
                        //根据name或id进行查询
                        rs = searchdo(request);
                        break;
                 case "set_sale_status":

                     rs = statusdo(request);
                     break;
            case "save":
                rs = savedo(request);
                break;
                }



        // break;
//            case "upload":
//                rs = uploaddo(request);
//                break;


        //返回响应的数据
        response.getWriter().write(rs.toString());

    }



    //产品列表
    private ResponseCode listdo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();
        HttpSession session = request.getSession();
        Product product = (Product) session.getAttribute("product");
//        if (product == null) {
//            rs.setStatus(3);
//            rs.setMsg("请登录后此操作！");
//            return rs;
//        }
//        if (product.getType() != 1) {
//            rs.setStatus(3);
//            rs.setMsg("没有操作权限！");
//            return rs;

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        return ps.selectAll(pageSize, pageNum);
    }

    //产品搜索根据姓名进行查询
    private ResponseCode searchdo(HttpServletRequest request) {
        String productName = request.getParameter("productName");
        String productId = request.getParameter("productId");
        ResponseCode  rs=null;
        if(productName==null){
           rs= ps.selectone1(productId);

        }else{
            rs= ps.selectone(productName);
        }
        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("product", rs.getData());
        //调用业务层处理业务
        return rs;

    }








   //上传照片




    //产品详情
    private ResponseCode searchdo2(HttpServletRequest request) {
        String productId = request.getParameter("productcId");

        ResponseCode  rs3= ps.selectone2(productId);

        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("product", rs3.getData());
        //调用业务层处理业务
        return rs3;
    }


//商品上下架
private ResponseCode statusdo(HttpServletRequest request) {
     String productId = request.getParameter("productId");
     String status = request.getParameter("status");
     ResponseCode rs= ps.selectone3(productId,status);

    //获取session对象
    HttpSession session = request.getSession();
    session.setAttribute("product", rs.getData());
    //调用业务层处理业务
    return rs;

}


//更新产品
private  ResponseCode savedo (HttpServletRequest request){
    String categoryId = request.getParameter("categoryId");
    String name = request.getParameter("name");
    String subtitle = request.getParameter("subtitle");
    String mainImage = request.getParameter("mainImage");
    String subImages = request.getParameter("subImages");
    String price = request.getParameter("price");
    String status = request.getParameter("status");


    ResponseCode rs = ps.selectAll(categoryId,name,subtitle,mainImage,price,status);
    //获取session对象
    HttpSession session = request.getSession();
    session.setAttribute("product", rs.getData());
    //调用业务层处理业务
    return rs;

}



    }




