package com.heather.djroomba.services;

import com.heather.djroomba.data.CleanUpRequest;
import com.heather.djroomba.data.CleanUpResponse;
import com.heather.djroomba.data.Roomba;

/***
 * RoombaService handles the roomba's creation and actions
 */
public interface RoombaService {

    CleanUpResponse cleanUpDirt(Roomba roomba);

    Roomba generateRoomba(CleanUpRequest cleanUpRequest);
}
