package com.heather.djroomba.impl;

import com.heather.djroomba.data.Room;
import com.heather.djroomba.exceptions.BadInputException;
import com.heather.djroomba.services.impl.LayoutServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class LayoutServiceImplTest {

    @InjectMocks
    private LayoutServiceImpl underTest;

    private static int[] AREA_GRID_5x5 = new int[]{5, 5};
    private static int[] AREA_GRID_1x1 = new int[]{1, 1};
    private static List<int[]> DIRT_PATCHES = List.of(new int[]{1, 0}, new int[]{2, 2}, new int[]{2, 3});

    @Test
    void mapRoomTest() {
        Room room = underTest.mapRoom(AREA_GRID_5x5, DIRT_PATCHES);
        int[][] dirtPatch = room.getFloorPlan();
        assertThat(dirtPatch[1][0]).isEqualTo(1);
        assertThat(dirtPatch[2][2]).isEqualTo(1);
        assertThat(dirtPatch[2][3]).isEqualTo(1);
        assertThat(dirtPatch[0][1]).isEqualTo(0);
    }

    @Test
    void createMap_outOfBoundOilPatches() {
        assertThatThrownBy(() -> underTest.mapRoom(AREA_GRID_1x1, DIRT_PATCHES))
                .isInstanceOf(BadInputException.class)
                .hasMessage("Sorry, we can't clean that! Index 1 out of bounds for length 1");
    }

}
