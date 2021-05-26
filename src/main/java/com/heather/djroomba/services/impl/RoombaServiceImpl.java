package com.heather.djroomba.services.impl;

import com.heather.djroomba.data.CleanUpRequest;
import com.heather.djroomba.data.CleanUpResponse;
import com.heather.djroomba.data.Room;
import com.heather.djroomba.data.Roomba;
import com.heather.djroomba.exceptions.BadInputException;
import com.heather.djroomba.services.LayoutService;
import com.heather.djroomba.services.RoombaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoombaServiceImpl implements RoombaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoombaServiceImpl.class);

    @Autowired
    LayoutService layoutService;

    @Override
    public CleanUpResponse cleanUpDirt(Roomba roomba) {
        int[][] grid = roomba.getRoom().getFloorPlan();
        int X = roomba.getStartingPosition()[0];
        int Y = roomba.getStartingPosition()[1];
        LOGGER.info("{} successfully arrived at starting position <{},{}>", roomba.getName(), X, Y);

        LOGGER.info("Beginning clean-up...");
        roomba.tryCleaning(X, Y);
        for (Character c : roomba.getInstructions().toCharArray()) {
            switch (c) {
                case 'N':
                    Y = Math.min(Y + 1, grid[X].length - 1); // Spin out safely at walls
                    break;
                case 'S':
                    Y = Math.max(Y - 1, 0);
                    break;
                case 'E':
                    X = Math.min(X + 1, grid.length - 1);
                    break;
                case 'W':
                    X = Math.max(X - 1, 0);
                    roomba.tryCleaning(X, Y);
                    break;
                default:
                    throw new BadInputException("Err what? That's not a direction.");
            }
            roomba.tryCleaning(X, Y);
        }
        LOGGER.info("... Cleanup complete! Successfully cleaned {} patches.", roomba.getRoom().getDirtPatchesCleaned());
        LOGGER.info("{} is now having a cleaning party at <{},{}>", roomba.getName(), X, Y);

        return new CleanUpResponse(new int[]{X, Y}, roomba.getRoom().getDirtPatchesCleaned());
    }

    @Override
    public Roomba generateRoomba(CleanUpRequest cleanUpRequest) {
        Roomba roomba = new Roomba();
        roomba.setStartingPosition(cleanUpRequest.getCoords());
        roomba.setInstructions(cleanUpRequest.getInstructions().toUpperCase());

        Room room = layoutService.mapRoom(cleanUpRequest.getRoomSize(), cleanUpRequest.getPatches());
        roomba.setRoom(room);

        verifyStart(roomba);

        return roomba;
    }

    private void verifyStart(Roomba roomba) {
        if (roomba.getStartingPosition()[0] > roomba.getRoom().getFloorPlan().length ||
                roomba.getStartingPosition()[1] > roomba.getRoom().getFloorPlan()[0].length) {
            throw new BadInputException(String.format("%s can't start there, they don't even know where that is!", roomba.getName()));
        }
    }
}
