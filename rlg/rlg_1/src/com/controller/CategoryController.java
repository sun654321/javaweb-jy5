package com.controller;

import com.common.ResponseCode;
import com.service.CategoryService;
import com.util.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CategoryController",value = "/manage/category/*")
public class CategoryController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private CategoryService cs = new CategoryService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        ResponseCode rs = null;
        //判断是什么请求
        switch (path) {
            case "get_category":
                rs = get_categorydo(request);
                break;
            case "add_category":
                rs=add_categorydo(request);
             case "set_category_name":
                rs=set_category_namedo(request);
            case "get_deep_category":
               rs=get_deep_categorydo(request);
        }

        //返回响应的数据
        response.getWriter().write(rs.toString());
    }

  


    //获取品类子节点
    private ResponseCode get_categorydo(HttpServletRequest request) {
       //传入的值为null
        String  categoryId = request.getParameter("categoryId");

        ResponseCode rs=cs.selectone(categoryId); //调用业务层处理业务

        return rs;
    }
//增加节点
    private ResponseCode add_categorydo(HttpServletRequest request) {
        String parentId=request.getParameter("parentId");
        String categoryName=request.getParameter("categoryName");
        ResponseCode rs = cs.selectone1(parentId, categoryName);

        return  rs;
    }
//修改品类姓名
    private ResponseCode set_category_namedo(HttpServletRequest request) {
        String categoryId=request.getParameter("categoryId");
        String categoryName=request.getParameter("categoryName");

        ResponseCode rs = cs.selectone2(categoryId, categoryName);

        return  rs;
    }

   //获取当前分类id及递归子节点categoryId
private  ResponseCode get_deep_categorydo(HttpServletRequest request){
    String categoryId = request.getParameter("categoryId");
     ResponseCode   rs=cs.selectone3(categoryId);
return  rs;
}





}
