package com.service;

import com.common.Const;
import com.common.ResponseCode;
import com.dao.UsersDao;
import com.pojo.Users;

import java.util.List;

public class UsersService {
    private UsersDao ud = new UsersDao();

    //用户列表
    public ResponseCode selectAll(String pageSize1, String pageNum1) {
        if (pageSize1 == null || pageSize1.equals("")) {
            pageSize1 = "10";
        }
        if (pageNum1 == null || pageNum1.equals("")) {
            pageNum1 = "1";
        }

        Integer pageSize =Integer.parseInt(pageSize1)  ;
        Integer  pageNum=Integer.parseInt(pageNum1)  ;
        List<Users> li = ud.selectAll(pageSize, pageNum);

        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);

        return rs;
    }

    //用户登录
    public ResponseCode selectone(String username, String password) {
        ResponseCode rs = new ResponseCode();
        if (username == null || username.equals("") || password == null || password.equals("")) {
            rs.setStatus(1);
            rs.setMsg("账号或密码错误");
            return rs;
        }
        //查找是否有这样一个用户
        Users u = ud.selectone(username, password);
        if (u == null) {
            rs.setStatus(1);
            rs.setMsg("没有改用户");
            return rs;
        }
        if (u.getType() != 1) {
            rs.setStatus(2);
            rs.setMsg("没有权限");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(u);

        return rs;
    }

    //禁用用户
    public ResponseCode selectone(String uids) {
        ResponseCode rs = new ResponseCode();
        //输入的参数为空
        if (uids == null || uids.equals("")) {
            rs.setStatus(Const.bcs);
            rs.setMsg(Const.bcsmsg);
            return rs;
        }

        //字符串转数值
        Integer uid = null;
        try {
            uid = Integer.parseInt(uids); //字符串转换不了int，uid为空

        }catch (Exception e){
            rs.setStatus(Const.bcs);
            rs.setMsg(Const.bcsmsg);
            return rs;
        }

        //查找是否有这样一个用户
        Users u = ud.selectone(uid);

        //如果用户不存在
        if (u == null) {
            rs.setStatus(Const.bcz);
            rs.setMsg(Const.bczmsg);
            return rs;
        }
        //用户禁用情况
        if (u.getStats() == 1) {
            rs.setStatus(Const.yhjy);
            rs.setMsg(Const.yhjymsg);
            return rs;
        }
        //禁用用户
        int row = ud.updateByUid(uid);
        if (row <= 0) {
            rs.setStatus(Const.yhjy1);
            rs.setMsg(Const.yhjymsg1);
            return rs;
        }
        rs.setStatus(0);
        rs.setData(row);
        return rs;

    }



}
