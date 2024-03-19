package zely.parkinglotspring.dto;

public class CreateParkingTicketDTO {

    private Integer vehicleId;

    private String vehicleLicenseNo;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public String getVehicleLicenseNo() {
        return vehicleLicenseNo;
    }

    public void setVehicleLicenseNo(String vehicleLicenseNo) {
        this.vehicleLicenseNo = vehicleLicenseNo;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
}
