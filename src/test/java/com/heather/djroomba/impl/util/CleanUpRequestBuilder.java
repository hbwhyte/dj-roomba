package com.heather.djroomba.impl.util;

import com.heather.djroomba.data.CleanUpRequest;

import java.util.List;

public class CleanUpRequestBuilder extends Builder<CleanUpRequest> {

    public CleanUpRequestBuilder createRequest(int[] roomSize, String instructions, List<int[]> patches, int[] coords) {
        target = new CleanUpRequest();
        target.setRoomSize(roomSize);
        target.setInstructions(instructions);
        target.setPatches(patches);
        target.setCoords(coords);
        return this;
    }

    public CleanUpRequestBuilder createRequest() {
        target = new CleanUpRequest();
        target.setRoomSize(new int[]{5, 5});
        target.setInstructions("NNNNESEEEEEESSSSSSSSSSSNWWWWWWWWWWW");
        target.setPatches(List.of(new int[]{1, 0}, new int[]{2, 2}, new int[]{2, 3}));
        target.setCoords(new int[]{1, 2});
        return this;
    }
}
