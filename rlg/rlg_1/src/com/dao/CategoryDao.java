package com.dao;



import com.pojo.Getcategory;
import com.pojo.Product;
import com.util.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class CategoryDao {
    //获取品类子节点
    public Getcategory selectone(Integer categoryId) {
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from  get_category  where cid= ?  ";
        Getcategory g = null;
        try {
            g = qr.query(sql, new BeanHandler<Getcategory>(Getcategory.class), categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }
//添加
    public int selectone(Integer categoryId,String categoryName) {
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "insert into  category(cid,cname) values(?,?)  ";

        int row= 0;
        try {
            row = qr.update(sql,categoryId,categoryName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
//修改名字
    public int selectone1(Integer categoryId,String categoryName){
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "update category set cname= ? where  cid=? ";
        int row= 0;
        try {
            row = qr.update(sql,categoryName,categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }



    //获取当前分类id及递归子节点categoryId



}


