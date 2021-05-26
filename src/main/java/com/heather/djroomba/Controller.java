package com.heather.djroomba;

import com.heather.djroomba.data.CleanUpRequest;
import com.heather.djroomba.data.CleanUpResponse;
import com.heather.djroomba.exceptions.BadInputException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

import static java.util.Objects.isNull;

@RestController
public class Controller {

    /**
     * Create and deploy a roomba to clean up the room, if given proper instructions
     */
    @RequestMapping(method = RequestMethod.POST, value = "/clean")
    public CleanUpResponse instructCleanUp(@RequestBody CleanUpRequest request) {
        verifyRequestFieldsNonNull(request);
        //TODO create roomba + instruct to clean
        return null;
    }

    private void verifyRequestFieldsNonNull(CleanUpRequest cleanUpRequest) {
        Field[] fields = cleanUpRequest.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (isNull(field)) {
                throw new BadInputException("Cannot deploy roomba with incomplete instructions");
            }
        }
    }
}
