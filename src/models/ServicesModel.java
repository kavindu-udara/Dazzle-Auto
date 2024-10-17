/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author kavindu
 */
public class ServicesModel {

    private int id;
    private int vehicleTypeId;
    private double charge;
    private String name;
    private String vehicleTypeName;

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
     * @return the vehicleTypeId
     */
    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    /**
     * @param vehicleTypeId the vehicleTypeId to set
     */
    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    /**
     * @return the charge
     */
    public double getCharge() {
        return charge;
    }

    /**
     * @param charge the charge to set
     */
    public void setCharge(double charge) {
        this.charge = charge;
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

    /**
     * @return the vehicleTypeName
     */
    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    /**
     * @param vehicleTypeName the vehicleTypeName to set
     */
    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

}
