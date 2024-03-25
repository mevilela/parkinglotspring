package zely.parkinglotspring.dto;

import java.util.ArrayList;
import java.util.List;

public class DisplayBoardDto {
    private List<DisplayBoardItemDto> displayBoardItemList;

    public DisplayBoardDto() {
        this.displayBoardItemList = new ArrayList<>();
    }

    public List<DisplayBoardItemDto> getDisplayBoardItemList() {
        return displayBoardItemList;
    }

    public void setDisplayBoardItemList(List<DisplayBoardItemDto> displayBoardItemList) {
        this.displayBoardItemList = displayBoardItemList;
    }
}