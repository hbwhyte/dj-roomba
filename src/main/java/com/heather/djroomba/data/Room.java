package com.heather.djroomba.data;

public class Room {

    private int[][] floorPlan;
    private int dirtPatchesCleaned;

    public Room(int[][] floorPlan) {
        this.floorPlan = floorPlan;
        this.dirtPatchesCleaned = 0;
    }

    public Room() {
    }

    public int[][] getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(int[][] floorPlan) {
        this.floorPlan = floorPlan;
    }

    public int getDirtPatchesCleaned() {
        return dirtPatchesCleaned;
    }

    public void setDirtPatchesCleaned(int dirtPatchesCleaned) {
        this.dirtPatchesCleaned = dirtPatchesCleaned;
    }

    public void incrementCleaned() {
        this.dirtPatchesCleaned++;
    }
}
