package com.sspu.domain.commodity;

import com.sspu.domain.commodity.Commodity;

import java.util.List;

public class SearchResult {
    private String intent;
    private String type;
    private int page;
    private int pageSize;
    private int totalPages;
    private int total;
    private String sortField;
    private String sortOrder;
    private List<Commodity> commodities;

    public String getIntent() {
        return intent;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "intent='" + intent + '\'' +
                ", type='" + type + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", totalPages=" + totalPages +
                ", total=" + total +
                ", sortField='" + sortField + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                ", commodities=" + commodities +
                '}';
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }
}
