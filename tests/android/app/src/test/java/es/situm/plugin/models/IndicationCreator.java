package es.situm.plugin.models;

import es.situm.sdk.model.directions.Indication;

public class IndicationCreator {

    public Indication createIndication() {
        return new Indication.Builder()
                .setDistance(5)
                .setDistanceToNextLevel(16)
                .setInstructionType(Indication.Action.GO_AHEAD)
                .setNextLevel(null)
                .setOrientation(14.5)
                .setOrientationType(Indication.Orientation.STRAIGHT)
                .setStepIdxDestination(5)
                .setStepIdxOrigin(4)
                .build();
    }
}
