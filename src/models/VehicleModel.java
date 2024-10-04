/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author kavindu
 */
public class VehicleModel {

    private int customerId;
    private int vehicleBrandId;
    private int vehicleTypeId;
    private String vehicleNumber;
    private String model;

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the vehicleBrandId
     */
    public int getVehicleBrandId() {
        return vehicleBrandId;
    }

    /**
     * @param vehicleBrandId the vehicleBrandId to set
     */
    public void setVehicleBrandId(int vehicleBrandId) {
        this.vehicleBrandId = vehicleBrandId;
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
     * @return the vehicleNumber
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * @param vehicleNumber the vehicleNumber to set
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }
    
    
}
