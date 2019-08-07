package com.service;

import com.common.ResponseCode;
import com.dao.ListDao;
import com.pojo.Lists;

import java.util.List;

public class ListService {

    ListDao ld = new ListDao();

    //订单列表
    public ResponseCode selectAll(String pageSize1, String pageNum1) {
        if (pageSize1 == null || pageSize1.equals("")) {
            pageSize1 = "10";
        }
        if (pageNum1 == null || pageNum1.equals("")) {
            pageNum1 = "1";
        }
        Integer pageSize=Integer.parseInt(pageSize1);
        Integer pageNum=Integer.parseInt(pageNum1);

        List<Lists> li = ld.selectAll(pageSize, pageNum);

        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);

        return rs;


    }

    //根据订单号进行查询信息
    public ResponseCode selectone(String orderNo1) {
        ResponseCode rs = new ResponseCode();
        if (orderNo1 == null) {
            rs.setStatus(1);
            rs.setMsg("输入为空");
            return rs;
        }
        Integer orderNo = Integer.parseInt(orderNo1);
        List<Lists> li = ld.selectone(orderNo);
        if (li == null) {
            rs.setStatus(1);
            rs.setMsg("没有该商品");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    //订单详情
    public ResponseCode selectone1(String orderNo1) {
        ResponseCode rs = new ResponseCode();
        if (orderNo1 == null) {
            rs.setStatus(1);
            rs.setMsg("输入为空");
            return rs;
        }
        Integer orderNo = Integer.parseInt(orderNo1);
        List<Lists> li = ld.selectone1(orderNo);
        if (li == null) {
            rs.setStatus(1);
            rs.setMsg("没有该商品");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    //订单发货
    public ResponseCode selectone2(String orderNo1) {
        ResponseCode rs = new ResponseCode();
        if (orderNo1 == null) {
            rs.setStatus(1);
            rs.setMsg("输入为空");
            return rs;
        }

        Integer orderNo = Integer.parseInt(orderNo1);
        int row = ld.selectone2(orderNo);
        if (row > 0) {
            rs.setStatus(0);
            rs.setMsg("发货成功");
            return rs;
        }
        rs.setStatus(1);
        rs.setMsg("发货失败");
        return rs;
    }
}
