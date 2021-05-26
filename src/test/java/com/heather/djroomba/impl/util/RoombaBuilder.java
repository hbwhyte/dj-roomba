package com.heather.djroomba.impl.util;

import com.heather.djroomba.data.Room;
import com.heather.djroomba.data.Roomba;

public class RoombaBuilder extends Builder<Roomba> {

    public RoombaBuilder buildRoomba(int[] start, String instructions, Room room) {
        target = new Roomba();
        target.setStartingPosition(start);
        target.setInstructions(instructions);
        target.setRoom(room);
        return this;
    }
}
