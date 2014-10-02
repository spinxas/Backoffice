package uaiContacts.vo;

import uaiContacts.model.Technologies;

import java.util.List;

public class TechnologyListVO {
    private int pagesCount;
    private long totalTechnology;

    private String actionMessage;
    private String searchMessage;

    private List<Technologies> technology;

    public TechnologyListVO() {
    }

    public TechnologyListVO(int pages, long totalTechnology, List<Technologies> technology) {
        this.pagesCount = pages;
        this.technology = technology;
        this.totalTechnology = totalTechnology;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public List<Technologies> getTechnology() {
        return technology;
    }

    public void setTechnology(List<Technologies> technology) {
        this.technology = technology;
    }

    public long getTotalTechnology() {
        return totalTechnology;
    }

    public void setTotalTechnology(long totalTechnology) {
        this.totalTechnology = totalTechnology;
    }

    public String getActionMessage() {
        return actionMessage;
    }

    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
    }

    public String getSearchMessage() {
        return searchMessage;
    }

    public void setSearchMessage(String searchMessage) {
        this.searchMessage = searchMessage;
    }
}