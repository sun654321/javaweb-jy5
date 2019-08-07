package com.controller;

import com.common.ResponseCode;
import com.service.ListService;
import com.util.PathUtil;
import com.util.PoolUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ListController", value = "/manage/order/*")
public class ListController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    ListService ls = new ListService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);
        ResponseCode rs = null;
        switch (path) {
            case "list":
                rs = listdo(request);
                break;
            case "search":
                rs = searchdo(request);
                break;
            case "detail":
                rs = detaildo(request);
                break;
            case "send_goods":
                rs=send_goodsdo(request);
                break;
        }
        response.getWriter().write(rs.toString());
    }


    //订单列表
    private ResponseCode listdo(HttpServletRequest request) {
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        ResponseCode rs = ls.selectAll(pageSize, pageNum);
        return rs;
    }

    //根据订单号进行查询信息
    private ResponseCode searchdo(HttpServletRequest request) {
        String orderNo = request.getParameter("orderNo");
        ResponseCode rs = ls.selectone(orderNo);
        return rs;

    }
  //订单详情
    private ResponseCode detaildo(HttpServletRequest request) {
        String  orderNo =request.getParameter("orderNo");
        ResponseCode rs = ls.selectone1(orderNo);
        return rs;
    }

//订单发货

    private ResponseCode send_goodsdo(HttpServletRequest request) {
        String  orderNo =request.getParameter("orderNo");
        ResponseCode rs = ls.selectone2(orderNo);
        return rs;
    }
}
