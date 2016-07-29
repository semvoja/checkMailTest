package com.company;

/**
 * Created by operator6 on 28.07.16.
 */
public class BrokenHost {
    private String dateBroken;
    private String ipAddress;
    private String address;
    private String nameService;

    public BrokenHost(String dateBroken, String ipAddress, String address, String nameService) {
        this.dateBroken = dateBroken;
        this.ipAddress = ipAddress;
        this.address = address;
        this.nameService = nameService;
    }

    public String getDateBroken() {
        return dateBroken;
    }

    public void setDateBroken(String dateBroken) {
        this.dateBroken = dateBroken;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }
}
