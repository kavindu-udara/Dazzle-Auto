/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author kavindu
 */
public class ProductModel {

    private int id;
    private int brandId;
    private String name;
    
    
    private String ItemId;
    private String brandName;
    

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
     * @return the brandId
     */
    public int getBrandId() {
        return brandId;
    }

    /**
     * @param brandId the brandId to set
     */
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    public String getItemId() {
        return ItemId;
    }

    /**
     * @param name the name to set
     */
    public void setItemId(String ItemId) {
        this.ItemId = ItemId;
    }
    
    
     public String getbrandName() {
        return brandName;
    }

    /**
     * @param name the name to set
     */
    public void setbrandName(String brandName) {
        this.brandName = brandName;
    }
}
