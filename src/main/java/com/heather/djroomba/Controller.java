package com.heather.djroomba;

import com.heather.djroomba.data.CleanUpRequest;
import com.heather.djroomba.data.CleanUpResponse;
import com.heather.djroomba.exceptions.BadInputException;
import com.heather.djroomba.services.RoombaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@RestController
public class Controller {

    @Autowired
    private RoombaService roombaService;

    /**
     * Create and deploy a roomba to clean up the room, if given proper instructions
     */
    @RequestMapping(method = RequestMethod.POST, value = "/clean")
    public CleanUpResponse instructCleanUp(@RequestBody CleanUpRequest request) {
        verifyRequestFieldsNonNull(request);
        return roombaService.cleanUpDirt(roombaService.generateRoomba(request));
    }

    private void verifyRequestFieldsNonNull(CleanUpRequest c) {
        if (isEmpty(c.getCoords()) || c.getCoords().length != 2 || c.getPatches().isEmpty() ||
                isEmpty(c.getRoomSize()) || c.getRoomSize().length != 2 || isEmpty(c.getInstructions())) {
            throw new BadInputException("Cannot deploy roomba with incomplete instructions");
        }

    }
}
