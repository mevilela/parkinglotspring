//package model.vehicle;
//
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import model.ParkingTicket;
//
//@Entity
//public abstract class Vehicle {
//
//
//    @Id
//    private Integer id;
//
//    private String licenseNo;
//
//    public abstract void assignTicket(ParkingTicket ticket);
//
//
//    public Vehicle() {
//    }
//
//    public Vehicle(Integer id, String licenseNo) {
//        this.id = id;
//        this.licenseNo = licenseNo;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getLicenseNo() {
//        return licenseNo;
//    }
//
//    public void setLicenseNo(String licenseNo) {
//        this.licenseNo = licenseNo;
//    }
//
//
//}
