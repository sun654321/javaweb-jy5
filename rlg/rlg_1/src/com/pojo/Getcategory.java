package com.pojo;

import java.sql.Date;


public class Getcategory {
    private  int id;
    private int cid;
    private  String name;
    private  int status;
    private  int sortorder;
    private Date createtime;
    private  Date updatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSortorder() {
        return sortorder;
    }

    public void setSortorder(int sortorder) {
        this.sortorder = sortorder;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Getcategory{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", sortorder=" + sortorder +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }
}
