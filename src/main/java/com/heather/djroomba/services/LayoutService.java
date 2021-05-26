package com.heather.djroomba.services;

import com.heather.djroomba.data.Room;

import java.util.List;

/***
 * Room service creates a layout of the room that the Roomba can use, once given the size of area
 * to be cleaned, and the location of the dirt.
 */
public interface LayoutService {

    Room mapRoom(int[] areaSize, List<int[]> dirtPatches);
}
