package com.ags.poc.ingestion.staging;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",") // , skipFirstLine = true
public class SalesRecord {

    @DataField(pos = 1)
    private String region;
    @DataField(pos = 2)
    private String country;
    @DataField(pos = 3)
    private String itemType;
    @DataField(pos = 4)
    private String salesChannel;
    @DataField(pos = 5)
    private String orderPriority;
    @DataField(pos = 6)
    private String orderDate;
    @DataField(pos = 7)
    private String orderld;
    @DataField(pos = 8)
    private String shipDate;
    @DataField(pos = 9)
    private Integer unitSold;
    @DataField(pos = 10)
    private Float unitPrice;
    @DataField(pos = 11)
    private Float unitCost;
    @DataField(pos = 12)
    private Double totalRevenue;
    @DataField(pos = 13)
    private Double totalCost;
    @DataField(pos = 14)
    private Double totalProfit;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public String getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(String orderPriority) {
        this.orderPriority = orderPriority;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderld() {
        return orderld;
    }

    public void setOrderld(String orderld) {
        this.orderld = orderld;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public Integer getUnitSold() {
        return unitSold;
    }

    public void setUnitSold(Integer unitSold) {
        this.unitSold = unitSold;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Float unitCost) {
        this.unitCost = unitCost;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    @Override
    public String toString() {
        return "SalesRecord [country=" + country + ", itemType=" + itemType + ", orderDate=" + orderDate
                + ", orderPriority=" + orderPriority + ", orderld=" + orderld + ", region=" + region + ", salesChannel="
                + salesChannel + ", shipDate=" + shipDate + ", totalCost=" + totalCost + ", totalProfit=" + totalProfit
                + ", totalRevenue=" + totalRevenue + ", unitCost=" + unitCost + ", unitPrice=" + unitPrice
                + ", unitSold=" + unitSold + "]";
    }

}