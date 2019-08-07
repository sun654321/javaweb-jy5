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

@WebServlet(name = "ProductController", value = "/manage/product/*")
public class ProductController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private ProductService ps = new ProductService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求路径信息
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        ResponseCode rs = null;
        //判断是什么请求
        switch (path) {
            case "list":
                //rs = listdo(request);
                listdo(request, response);
                break;
            case "search": //根据姓名或id进行查询
                rs = searchdo(request);
                break;
            case "searchdo"://查询详细信息
                rs = searchdo2(request);
                break;
            case "set_sale_status"://上架
                rs = statusdo(request);
                break;
            case "notstatus"://下架
                rs = notstatusdo(request);
                break;
            case "save": //更新产品
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
    private void listdo(HttpServletRequest request, HttpServletResponse response) {
        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        ResponseCode rs = ps.selectAll(pageSize, pageNum);
        request.setAttribute("uil2", rs);
        try {
            request.getRequestDispatcher("/WEB-INF/product/shoplist.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return rs;
    }

    //根据姓名或id进行查询
    private ResponseCode searchdo(HttpServletRequest request) {
        String productName = request.getParameter("productName");
        String productId = request.getParameter("productId");
        ResponseCode rs = null;
        if (productName == null) {
            rs = ps.selectone1(productId);

        } else {
            rs = ps.selectone(productName);
        }

        //调用业务层处理业务
        return rs;

    }


    //上传照片


    //产品详情
    private ResponseCode searchdo2(HttpServletRequest request) {
        String productId = request.getParameter("productcId");

        ResponseCode rs3 = ps.selectone2(productId);

        //调用业务层处理业务
        return rs3;
    }


    //商品下架
    private ResponseCode statusdo(HttpServletRequest request) {
        String productId = request.getParameter("productId");
        ResponseCode rs = ps.selectone3(productId);
        //调用业务层处理业务
        return rs;

    }

    //商品上架
    private ResponseCode notstatusdo(HttpServletRequest request) {
        String productId = request.getParameter("productId");
        ResponseCode rs = ps.selectone3_1(productId);


        //调用业务层处理业务
        return rs;

    }

    //更新产品
    private ResponseCode savedo(HttpServletRequest request) {
        String categoryId = request.getParameter("categoryId");
        String name = request.getParameter("name");
        String subtitle = request.getParameter("subtitle");
        String mainImage = request.getParameter("mainImage");
        String price = request.getParameter("price");
        String status = request.getParameter("status");
        String id = request.getParameter("id");
        ResponseCode rs = null;
        if (id == null) {
            rs = ps.selectone4(categoryId, name, subtitle, mainImage, price, status);
        } else {
            rs = ps.selectone5(categoryId, name, subtitle, mainImage, price, status, id);
        }
        //调用业务层处理业务
        return rs;

    }
}




