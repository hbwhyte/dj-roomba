package com.heather.djroomba.impl;

import com.heather.djroomba.data.CleanUpRequest;
import com.heather.djroomba.data.CleanUpResponse;
import com.heather.djroomba.data.Room;
import com.heather.djroomba.data.Roomba;
import com.heather.djroomba.impl.util.CleanUpRequestBuilder;
import com.heather.djroomba.impl.util.RoomBuilder;
import com.heather.djroomba.impl.util.RoombaBuilder;
import com.heather.djroomba.services.LayoutService;
import com.heather.djroomba.services.impl.RoombaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoombaServiceImplTest {

    @InjectMocks
    private RoombaServiceImpl underTest;

    @Mock
    private LayoutService layoutService;

    @Test
    void cleanUpDirtTest_Basic() {
        Roomba roomba = new RoombaBuilder()
                .buildRoomba(new int[]{1, 2}, "NNESEESWNWW", new RoomBuilder().getLayout().build())
                .build();
        CleanUpResponse result = underTest.cleanUpDirt(roomba);
        assertThat(result).extracting(CleanUpResponse::getCoords, CleanUpResponse::getPatches)
                .contains(new int[]{1, 3}, 1);
    }

    @Test
    void cleanUpDirtTest_StartOnDirtySquare() {
        Room room = new RoomBuilder()
                .getLayout(List.of(new int[]{1, 0}, new int[]{3, 0}, new int[]{4, 3}, new int[]{4, 2}), 5, 5).build();
        Roomba roomba = new RoombaBuilder()
                .buildRoomba(new int[]{1, 0}, "EENNENWW", room)
                .build();
        CleanUpResponse result = underTest.cleanUpDirt(roomba);
        assertThat(result).extracting(CleanUpResponse::getCoords, CleanUpResponse::getPatches)
                .contains(new int[]{2, 3}, 4);
    }

    @Test
    void cleanUpDirtTest_EndOnDirtySquare() {
        Room room = new RoomBuilder()
                .getLayout(List.of(new int[]{1, 0}, new int[]{3, 0}, new int[]{4, 3}, new int[]{4, 2}), 5, 5).build();
        Roomba roomba = new RoombaBuilder()
                .buildRoomba(new int[]{0, 0}, "EEENNEN", room)
                .build();
        CleanUpResponse result = underTest.cleanUpDirt(roomba);
        assertThat(result).extracting(CleanUpResponse::getCoords, CleanUpResponse::getPatches)
                .contains(new int[]{4, 3}, 4);
    }

    @Test
    void cleanUpDirtTest_HitTheWall() {
        Roomba roomba = new RoombaBuilder()
                .buildRoomba(new int[]{1, 2}, "NNESEESWNWW", new RoomBuilder().getLayout().build())
                .build();
        CleanUpResponse result = underTest.cleanUpDirt(roomba);
        assertThat(result).extracting(CleanUpResponse::getCoords, CleanUpResponse::getPatches)
                .contains(new int[]{1, 3}, 1);
    }

    // TODO Test case -> clean on first/last square; hit the wall

    @Test
    void generateRoombaTest() {
        CleanUpRequest request = new CleanUpRequestBuilder().createRequest().build();
        when(layoutService.mapRoom(any(), any())).thenReturn(new RoomBuilder().getLayout().build());
        Roomba robbie = underTest.generateRoomba(request);
        assertThat(robbie)
                .extracting(Roomba::getStartingPosition, Roomba::getInstructions, Roomba::getName)
                .contains(request.getCoords(), request.getInstructions(), "DJ Roomba");
        assertThat(robbie.getRoom()).isNotNull();
    }

    private Integer[][] dirtPatchBuilder() {
        return new Integer[][]{
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
    }
}
