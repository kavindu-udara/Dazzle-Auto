/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author kavindu
 */
public class ServiceHistoryModel {
    private int id;
    private String serviceInvoiceId;

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
     * @return the serviceInvoiceId
     */
    public String getServiceInvoiceId() {
        return serviceInvoiceId;
    }

    /**
     * @param serviceInvoiceId the serviceInvoiceId to set
     */
    public void setServiceInvoiceId(String serviceInvoiceId) {
        this.serviceInvoiceId = serviceInvoiceId;
    }
    
}
