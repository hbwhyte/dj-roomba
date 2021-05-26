package com.heather.djroomba.impl.util;

import com.heather.djroomba.data.Room;

import java.util.List;

public class RoomBuilder extends Builder<Room> {

    public RoomBuilder getLayout() {
        List<int[]> dirtPatches = List.of(new int[]{1, 0}, new int[]{2, 2}, new int[]{2, 3});
        int[][] cleaningGrid = new int[5][5];
        dirtPatches.forEach(dirtPatch -> cleaningGrid[dirtPatch[0]][dirtPatch[1]] = 1);
        target = new Room(cleaningGrid);
        return this;
    }

    public RoomBuilder getLayout(List<int[]> dirtPatches, int x, int y) {
        target = new Room();
        int[][] cleaningGrid = new int[x][y];
        dirtPatches.forEach(dirtPatch -> cleaningGrid[dirtPatch[0]][dirtPatch[1]] = 1);
        target = new Room(cleaningGrid);
        return this;
    }
}
