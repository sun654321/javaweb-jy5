package com.controller;

import com.common.ResponseCode;
import com.pojo.Users;
import com.service.UsersService;
import com.util.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/manage/user/*")

public class UsersController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private UsersService uc = new UsersService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //处理乱码
       //request.setCharacterEncoding("UTF-8");
       //response.setContentType("text/html;charset=UTF-8");

        //获取请求路径信息
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        ResponseCode rs = null;
        //判断是什么请求
        switch (path) {
            case "list":
                rs = listdo(request);
                break;
            case "login":
                rs = logindo(request);
                break;
            case  "disableuser":
                rs = disableuserdo(request);
                break;
        }

        //返回响应的数据
        response.getWriter().write(rs.toString());
    }


    //获取所有用户列表的请求
    private ResponseCode listdo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            rs.setStatus(3);
            rs.setMsg("请登录后此操作！");
            return rs;
        }
        if (user.getType() != 1) {
            rs.setStatus(3);
            rs.setMsg("没有操作权限！");
            return rs;
        }
        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
       rs=uc.selectAll(pageSize, pageNum);
        return rs;
    }
    //用户登录
    private ResponseCode logindo(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ResponseCode rs= uc.selectone(username, password);

        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("user", rs.getData());
        //调用业务层处理业务
        return rs;
    }

    //禁用用户的请求
    private ResponseCode disableuserdo(HttpServletRequest request) {
    //获取参数
        String uid = request.getParameter("uid");
     //调用业务层处理业务
        ResponseCode rs=uc.selectone(uid);
        return rs;
    }


    //根据用户id查看用户详情
    //根据用户id修改用户信息
}


