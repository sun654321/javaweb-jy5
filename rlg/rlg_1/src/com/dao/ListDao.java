package com.dao;

import com.pojo.Lists;
import com.util.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ListDao {
    //订单列表
    public List<Lists> selectAll(Integer pageSize, Integer  pageNum) {
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from list limit ? , ?";
        List<Lists> li = null;

        try {
            li = qr.query(sql, new BeanListHandler<Lists>(Lists.class),pageNum,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }
    //根据订单号进行查询信息
    public List<Lists> selectone(Integer orderNo) {

        QueryRunner qr=new QueryRunner(PoolUtil.getcom());
        String sql= "select * from list where orderNo=? ";
        List<Lists>  li=null;
        try {
           li= qr.query(sql,new BeanListHandler<Lists>(Lists.class),orderNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  li ;
    }
    //订单详情
    public List<Lists> selectone1(Integer orderNo) {
        QueryRunner qr=new QueryRunner(PoolUtil.getcom());
        String sql= "select * from list where orderNo=? ";
        List<Lists>  li=null;
        try {
            li= qr.query(sql,new BeanListHandler<Lists>(Lists.class),orderNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  li ;
    }
//订单发货
    public int  selectone2(Integer orderNo) {
        QueryRunner qr=new QueryRunner(PoolUtil.getcom());
        String sql="update list set status=1 where orderNo=? ";
       int row=0;
        try {
          row= qr.update(sql,orderNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return row;
    }
}
