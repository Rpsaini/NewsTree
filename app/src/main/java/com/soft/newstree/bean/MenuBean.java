package com.soft.newstree.bean;

public class MenuBean
{
   private String itemName;
   private String itemImage;
   private String itemLink;
   private boolean lineSepration;



    public MenuBean()
    {

    }

    public String getItemName() {
        return itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public boolean isLineSepration() {
        return lineSepration;
    }

    public void setLineSepration(boolean lineSepration) {
        this.lineSepration = lineSepration;
    }
}
