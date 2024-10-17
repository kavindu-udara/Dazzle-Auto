/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author kavindu
 */
public class AppointmentModel {

    private String id;
    private int serviceId;
    private String serviceName;
    private String vehicleNumber;
    private String vehicleModel;
    private String vehicleType;
    private String date;
    private String note;
    private int appointmentStatusId;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the serviceId
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
     * @return the vehicleModel
     */
    public String getVehicleModel() {
        return vehicleModel;
    }

    /**
     * @param vehicleModel the vehicleModel to set
     */
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    /**
     * @return the vehicleType
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * @param vehicleType the vehicleType to set
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the appointmentStatusId
     */
    public int getAppointmentStatusId() {
        return appointmentStatusId;
    }

    /**
     * @param appointmentStatusId the appointmentStatusId to set
     */
    public void setAppointmentStatusId(int appointmentStatusId) {
        this.appointmentStatusId = appointmentStatusId;
    }

}