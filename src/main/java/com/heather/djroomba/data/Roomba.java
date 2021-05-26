package com.heather.djroomba.data;

public class Roomba {

    private String instructions;
    private int[] startingPosition;
    private Room room;
    private String name = "DJ Roomba";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int[] getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(int[] startingPosition) {
        this.startingPosition = startingPosition;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void tryCleaning(int X, int Y) {
        if (this.room.getFloorPlan()[X][Y] == 1) {
            this.room.incrementCleaned();
            this.room.getFloorPlan()[X][Y] = 0;
        }
    }
}
