package com.heather.djroomba.data;

public class CleanUpResponse {

    private int[] coords;
    private int patches;

    public CleanUpResponse(int[] coords, int patches) {
        this.coords = coords;
        this.patches = patches;
    }

    public int[] getCoords() {
        return coords;
    }

    public int getPatches() {
        return patches;
    }
}
