package zely.parkinglotspring.dto;

public class DisplayBoardItemDto {

    String spotType;

    long freeSpots;

    long occupiedSpots;

    long totalSpots;


    public DisplayBoardItemDto(String spotType, long freeSpots, long occupiedSpots, long totalSpots) {
        this.spotType = spotType;
        this.freeSpots = freeSpots;
        this.occupiedSpots = occupiedSpots;
        this.totalSpots = totalSpots;
    }

    public long getOccupiedSpots() {
        return occupiedSpots;
    }

    public void setOccupiedSpots(long occupiedSpots) {
        this.occupiedSpots = occupiedSpots;
    }

    public String getSpotType() {
        return spotType;
    }

    public void setSpotType(String spotType) {
        this.spotType = spotType;
    }

    public long getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(long totalSpots) {
        this.totalSpots = totalSpots;
    }

    public long getFreeSpots() {
        return freeSpots;
    }

    public void setFreeSpots(long freeSpots) {
        this.freeSpots = freeSpots;
    }
}
