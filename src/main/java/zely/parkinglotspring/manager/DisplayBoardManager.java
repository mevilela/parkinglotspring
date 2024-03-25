package zely.parkinglotspring.manager;

import org.springframework.stereotype.Service;
import zely.parkinglotspring.dto.DisplayBoardDto;
import zely.parkinglotspring.dto.DisplayBoardItemDto;
import zely.parkinglotspring.model.parkingspot.*;
import zely.parkinglotspring.service.displayboard.DisplayBoardService;
import zely.parkinglotspring.service.parkingspot.ParkingSpotService;

@Service
public class DisplayBoardManager {
    private final DisplayBoardService displayBoardService;
    private final ParkingSpotService parkingSpotService;

    public DisplayBoardManager(DisplayBoardService displayBoardService, ParkingSpotService parkingSpotService) {
        this.displayBoardService = displayBoardService;
        this.parkingSpotService = parkingSpotService;
    }

    public DisplayBoardDto showSpotsOnDisplayBoard(DisplayBoardItemDto displayBoardItemDto) {

        String spotType = displayBoardItemDto.getSpotType();


        long compactTotalSpots = parkingSpotService.countTotalSpotsByType(Compact.class);
        long largeTotalSpots = parkingSpotService.countTotalSpotsByType(Large.class);
        long handicappedTotalSpots = parkingSpotService.countTotalSpotsByType(Handicapped.class);
        long motorcycleTotalSpots = parkingSpotService.countTotalSpotsByType(Motorcycle.class);

        long compactFreeSpots = parkingSpotService.countFreeSpotTypes(Compact.class);
        long largeFreeSpots = parkingSpotService.countFreeSpotTypes(Large.class);
        long handicappedFreeSpots = parkingSpotService.countFreeSpotTypes(Handicapped.class);
        long motorcycleFreeSpots = parkingSpotService.countFreeSpotTypes(Motorcycle.class);

        long compactOccupiedSpots = parkingSpotService.countOccupiedSpotTypes(Compact.class);
        long largeOccupiedSpots = parkingSpotService.countOccupiedSpotTypes(Large.class);
        long handicappedOccupiedSpots = parkingSpotService.countOccupiedSpotTypes(Handicapped.class);
        long motorcycleOccupiedSpots = parkingSpotService.countOccupiedSpotTypes(Motorcycle.class);


        DisplayBoardItemDto displayBoardCompact = new DisplayBoardItemDto("Compact", compactFreeSpots, compactOccupiedSpots, compactTotalSpots);
        DisplayBoardItemDto displayBoardLarge = new DisplayBoardItemDto("Large", largeFreeSpots, largeOccupiedSpots, largeTotalSpots);
        DisplayBoardItemDto displayBoardHandicapped = new DisplayBoardItemDto("Handicapped", handicappedFreeSpots, handicappedOccupiedSpots, handicappedTotalSpots);
        DisplayBoardItemDto displayBoardMotorcycle = new DisplayBoardItemDto("Motorcycle", motorcycleFreeSpots, motorcycleOccupiedSpots, motorcycleTotalSpots);


        DisplayBoardDto displayBoardDto = new DisplayBoardDto();
        displayBoardDto.getDisplayBoardItemList().add(displayBoardCompact);
        displayBoardDto.getDisplayBoardItemList().add(displayBoardLarge);
        displayBoardDto.getDisplayBoardItemList().add(displayBoardHandicapped);
        displayBoardDto.getDisplayBoardItemList().add(displayBoardMotorcycle);


        return displayBoardDto;

    }

}