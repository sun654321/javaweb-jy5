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
                listdo(request,response);
                //rs = listdo(request);
                break;
            case "login":
               //rs=logindo(request);
                logindo(request,response);
                break;
            case  "disableuser":
                rs = disableuserdo(request);
                break;
            case "schu":
               rs= schudo(request);
               break;
            case "amend":
                rs=amenddo(request);
                break;
            case "notdisableuser":
                rs=notdisableuserdo(request);
                break;
        }

        //返回响应的数据
      response.getWriter().write(rs.toString());
    }


    //获取所有用户列表的请求
    private void listdo(HttpServletRequest request,HttpServletResponse response) {
        ResponseCode rs = new ResponseCode();
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
//        if (user == null) {
//            rs.setStatus(3);
//            rs.setMsg("请登录后此操作！");
//            //return rs;
//        }
//        if (user.getType() != 1) {
//            rs.setStatus(3);
//            rs.setMsg("没有操作权限！");
//            //return rs;
//        }
        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
       rs=uc.selectAll(pageSize, pageNum);
        //return rs;
        request.setAttribute("uil",rs);
        try {
            request.getRequestDispatcher("/WEB-INF/user/userlist.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //用户登录
    private void logindo(HttpServletRequest request ,HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ResponseCode rs = uc.selectone(username, password);

        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("user", rs.getData());
        //调用业务层处理业务
        try {
            //请求转发
            request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//重定向不能使用
//        try {
//            response.sendRedirect("/home.jsp");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // return rs;
    }

    //禁用用户的请求
    private ResponseCode disableuserdo(HttpServletRequest request) {
    //获取参数
        String uid = request.getParameter("uid");
     //调用业务层处理业务
        ResponseCode rs=uc.selectone(uid);

        return rs;
    }

    //解除禁用
    private ResponseCode notdisableuserdo(HttpServletRequest request){
        String uid = request.getParameter("uid");
         ResponseCode rs=uc.selectone4(uid);
     return  rs;

    }


   //删除用户信息
    private ResponseCode schudo(HttpServletRequest request) {
    //获取参数
    String uid = request.getParameter("uid");
    //调用业务层处理业务
    ResponseCode rs=uc.selectone1(uid);
    return rs;
}

    //根据用户id查看用户详情
    private ResponseCode selectdo(HttpServletRequest request) {
        //获取参数
        String uid = request.getParameter("uid");
        //调用业务层处理业务
        ResponseCode rs=uc.selectone2(uid);
        return rs;
    }



    //根据用户id修改用户信息
private ResponseCode amenddo(HttpServletRequest request){
    String uid = request.getParameter("uid");
    String uname=request.getParameter("uname");
    String psd=request.getParameter("psd");
    String tel=request.getParameter("tel");
    String type=request.getParameter("type");
    String stats=request.getParameter("stats");
     ResponseCode rs= uc.selectone3(uid,uname,psd,tel,type,stats);
  return  rs;

}



}


