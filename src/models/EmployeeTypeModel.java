/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author kavindu
 */
public class EmployeeTypeModel {
    private int id;
    private int leavesForMonth;
    private String type;
    private Double basicSallary;

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
     * @return the leavesForMonth
     */
    public int getLeavesForMonth() {
        return leavesForMonth;
    }

    /**
     * @param leavesForMonth the leavesForMonth to set
     */
    public void setLeavesForMonth(int leavesForMonth) {
        this.leavesForMonth = leavesForMonth;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the basicSallary
     */
    public Double getBasicSallary() {
        return basicSallary;
    }

    /**
     * @param basicSallary the basicSallary to set
     */
    public void setBasicSallary(Double basicSallary) {
        this.basicSallary = basicSallary;
    }
    
}
