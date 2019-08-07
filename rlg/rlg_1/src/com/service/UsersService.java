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
        Integer pageSize = Integer.parseInt(pageSize1);
        Integer pageNum = Integer.parseInt(pageNum1);
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
            rs.setMsg("参数为空");
            return rs;
        }
        //查找是否有这样一个用户
        Users u = ud.selectone(username, password);
        if (u == null) {
            rs.setStatus(1);
            rs.setMsg("请确定密码和账户,重新登录");
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
            uid = Integer.parseInt(uids);

        } catch (Exception e) {
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
        }else{
            rs.setStatus(0);
            rs.setMsg("用户禁用成功");
            return rs;
        }
    }

    //解除禁用
    public ResponseCode  selectone4(String uid1){
        ResponseCode rs=new ResponseCode();
        if(uid1==null){
            rs.setStatus(1);
            rs.setMsg("输入的值为空");
            return  rs;
        }

        Integer uid=Integer.parseInt(uid1);
     int row = ud.selectone5(uid);
        if(row<=0){
            rs.setStatus(1);
            rs.setMsg("修改失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setMsg("修改成功");
        return  rs;
    }

    //删除用户信息
    public ResponseCode selectone1(String uid1) {
        ResponseCode rs = new ResponseCode();
        //输入的参数为空
        if (uid1 == null || uid1.equals("")) {
            rs.setStatus(Const.bcs);
            rs.setMsg(Const.bcsmsg);
            return rs;
        }
        //字符串转数值
        Integer uid = Integer.parseInt(uid1);
        //查找是否有这样一个用户
        int row = ud.selectone2(uid);
        //如果用户不存在
        if (row <= 0) {
            rs.setStatus(1);
            rs.setMsg("删除失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setMsg("删除成功");
        return rs;

    }


    //根据用户id查看用户详情
    public ResponseCode selectone2(String uid1) {
        ResponseCode rs = new ResponseCode();
        //输入的参数为空
        if (uid1 == null) {
            rs.setStatus(Const.bcs);
            rs.setMsg(Const.bcsmsg);
            return rs;
        }
        Integer uid = Integer.parseInt(uid1);
        //查找是否有这样一个用户
        Users u = ud.selectone3(uid);
        //如果用户不存在
        if (u == null) {
            rs.setStatus(1);
            rs.setMsg("请确认信息的正确性");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(u);
        return rs;
    }


    //根据用户id修改用户信息
    public ResponseCode selectone3(String uid1, String uname, String psd, String tel, String type, String stats) {
        ResponseCode rs = new ResponseCode();
        if (uid1 == null || uname == null || psd == null || tel == null || type == null || stats == null) {
            rs.setStatus(1);
            rs.setMsg("输入的值为空");
            return rs;
        }
        Integer uid = Integer.parseInt(uid1);
        int row = ud.selectone4(uid, uname, psd, tel, type, stats);
        if (row <= 0) {
            rs.setStatus(1);
            rs.setMsg("修改失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setMsg("修改成功");
        return rs;

    }
}