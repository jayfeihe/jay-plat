package com.jay.vo.base.page;

import org.apache.ibatis.session.RowBounds;

/**
 * 分页类
 * Created by hetiewei on 2017/3/2.
 */
public class Pagination extends RowBounds {

    private int currentPage = 1;
    private int pageSize = 15;
    private int count = 0;
    // 是否需要统计开关
    private boolean needCount;

    public Pagination() {
        this(true);
    }

    public Pagination(boolean needCount) {
        this.needCount = needCount;
    }

    public static Pagination getPagination(RowBounds rowBounds) {
        return getPagination(rowBounds, false);
    }

    public static Pagination getPagination(RowBounds rowBounds, boolean needCount) {
        Pagination pagination = new Pagination(needCount);
        pagination.setPageSize(rowBounds.getLimit());
        pagination.setCurrentPage(rowBounds.getOffset() / rowBounds.getLimit() + 1);
        return pagination;
    }

    public static boolean noRow(RowBounds rowBounds) {
        return (rowBounds instanceof Pagination) ? false : (rowBounds.getLimit() == RowBounds.NO_ROW_LIMIT && rowBounds
                .getOffset() == RowBounds.NO_ROW_OFFSET);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Pagination setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Pagination setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getCount() {
        return count;
    }

    public Pagination setCount(int count) {
        this.count = count;
        return this;
    }

    /**
     * 得到起始位置，与getOffset()区别开， mybatis会根据getOffset偏移ResultSet
     *
     * @return
     */
    public int getStartIndex() {
        return pageSize * (currentPage - 1);
    }

    public int getLimit() {
        return pageSize;
    }

    public boolean isNeedCount() {
        return needCount && this.count <= 0;
    }

    public boolean hasNext() {
        return pageSize * currentPage < count;
    }

    public Pagination next() {
        this.currentPage++;
        return this;
    }
}
