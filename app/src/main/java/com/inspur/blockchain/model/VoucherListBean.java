package com.inspur.blockchain.model;

import java.util.List;

/**
 * @author lichun
 */
public class VoucherListBean {
    private int total;
    private int pages;
    private int page_num;
    private List<VoucherListItemBean> list;
    private int page_size;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public List<VoucherListItemBean> getList() {
        return list;
    }

    public void setList(List<VoucherListItemBean> list) {
        this.list = list;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }
}
