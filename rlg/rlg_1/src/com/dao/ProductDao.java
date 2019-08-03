package com.dao;

import com.pojo.Product;
import com.util.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

//查看商品列表
    public List<Product> selectAll(String pageSize, String pageNum) {

        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from product ";
        List<Product> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;

    }
//根据商品的姓名进行模糊查询
    public Product selectone(String  productName) {
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
//查出的是一条数据
        String sql = "select * from product where 1=1 ";
        List<String> list=new ArrayList<String>();
        sql=sql+"and name like ? group by id";
        list.add("%"+productName+"%");
        Product p = null;
        try {

            p = qr.query(sql, new BeanHandler<Product>(Product.class),list.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

//根据商品的id进行查询
    public Product selectone1(Integer productId) {
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from product where id=?";
        Product p = null;
        try {
            p = qr.query(sql, new BeanHandler<Product>(Product.class), productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;

    }

    //根据商品的id查询详细信息
    public Product selectone3(Integer productId) {
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from product where id=?";
        Product p = null;
        try {
            p = qr.query(sql, new BeanHandler<Product>(Product.class), productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;



    }

//根据商品的id和状态进行查询
        public Product selectone4(Integer productId, Integer status) {
            QueryRunner qr = new QueryRunner(PoolUtil.getcom());
            String sql = "select * from product where id=? and status=? ";
            Product p = null;
            try {
                p = qr.query(sql, new BeanHandler<Product>(Product.class), productId,status);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return p;

            }
        public int updateByUid(Integer productId, Integer status) {
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "update product set status= ? where id = ?";
        int row = 0;
        try {
            row = qr.update(sql,new BeanHandler<Product>(Product.class), productId,status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }



}
