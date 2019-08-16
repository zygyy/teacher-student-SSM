package com.zy.entity;

/**
 * @author 周宇
 * @university ycit.com
 * @creat 2019/6/8 9:33
 *
 * 分页
 */
public class PageBean {
    private int page;//第几页
    private int pagesize;//每页记录数
    private int start;//起始页

    public PageBean(int page, int pagesize) {
        super();
        this.page = page;
        this.pagesize = pagesize;

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getStart() {
        return (page-1)*pagesize;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
