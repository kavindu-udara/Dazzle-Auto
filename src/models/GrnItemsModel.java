/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author kavindu
 */
public class GrnItemsModel {

    private int id;
    private int stockId;
    private String grnId;
    private Double qty;
    private Double price;

    private String ProductId;
    private String BrandName;
    private int BrandId;
    private String ProductName;
    private Double BuyingPrice;
    private Double SellingPrice;
    private String SupplierId;
    private String EmployeeId;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the stockId
     */
    public int getStockId() {
        return stockId;
    }

    /**
     * @param stockId the stockId to set
     */
    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    /**
     * @return the grnId
     */
    public String getGrnId() {
        return grnId;
    }

    /**
     * @param grnId the grnId to set
     */
    public void setGrnId(String grnId) {
        this.grnId = grnId;
    }

    /**
     * @return the qty
     */
    public Double getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(Double qty) {
        this.qty = qty;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the ProductId
     */
    public String getProductId() {
        return ProductId;
    }

    /**
     * @param ProductId the ProductId to set
     */
    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    /**
     * @return the BrandName
     */
    public String getBrandName() {
        return BrandName;
    }

    /**
     * @param BrandName the BrandName to set
     */
    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    /**
     * @return the BrandId
     */
    public int getBrandId() {
        return BrandId;
    }

    /**
     * @param BrandId the BrandId to set
     */
    public void setBrandId(int BrandId) {
        this.BrandId = BrandId;
    }

    /**
     * @return the ProductName
     */
    public String getProductName() {
        return ProductName;
    }

    /**
     * @param ProductName the ProductName to set
     */
    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    /**
     * @return the BuyingPrice
     */
    public Double getBuyingPrice() {
        return BuyingPrice;
    }

    /**
     * @param BuyingPrice the BuyingPrice to set
     */
    public void setBuyingPrice(Double BuyingPrice) {
        this.BuyingPrice = BuyingPrice;
    }

    /**
     * @return the SellingPrice
     */
    public Double getSellingPrice() {
        return SellingPrice;
    }

    /**
     * @param SellingPrice the SellingPrice to set
     */
    public void setSellingPrice(Double SellingPrice) {
        this.SellingPrice = SellingPrice;
    }

    /**
     * @return the SupplierId
     */
    public String getSupplierId() {
        return SupplierId;
    }

    /**
     * @param SupplierId the SupplierId to set
     */
    public void setSupplierId(String SupplierId) {
        this.SupplierId = SupplierId;
    }

    /**
     * @return the EmployeeId
     */
    public String getEmployeeId() {
        return EmployeeId;
    }

    /**
     * @param EmployeeId the EmployeeId to set
     */
    public void setEmployeeId(String EmployeeId) {
        this.EmployeeId = EmployeeId;
    }
}
