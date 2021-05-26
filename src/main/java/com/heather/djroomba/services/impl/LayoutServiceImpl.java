package com.heather.djroomba.services.impl;

import com.heather.djroomba.data.Room;
import com.heather.djroomba.exceptions.BadInputException;
import com.heather.djroomba.services.LayoutService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutServiceImpl implements LayoutService {

    @Override
    public Room mapRoom(int[] areaSize, List<int[]> dirtPatches) {

        // Generate empty grid
        int[][] cleaningGrid = new int[areaSize[0]][areaSize[1]];

        // Add dirt
        try {
            dirtPatches.forEach(dirtPatch -> cleaningGrid[dirtPatch[0]][dirtPatch[1]] = 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BadInputException("Sorry, we can't clean that! " + e.getMessage());
        }

        return new Room(cleaningGrid);
    }
}
