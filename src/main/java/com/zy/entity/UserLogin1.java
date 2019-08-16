package com.zy.entity;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/8/12 8:37
 * 创建userLogin是为了解决属性名相同，后面一个属性不能映射上值的问题
 */
public class UserLogin1 {
    private int userid;
    private String name1;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    @Override
    public String toString() {
        return "UserLogin1{" +
                "userid=" + userid +
                ", name1='" + name1 + '\'' +
                '}';
    }
}
