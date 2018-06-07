import java.util.ArrayList;
import java.util.List;

import es.situm.sdk.directions.DirectionsRequest;
import es.situm.sdk.model.URL;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.Floor;
import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.directions.Indication;
import es.situm.sdk.model.directions.Route;
import es.situm.sdk.model.directions.RouteStep;
import es.situm.sdk.model.location.Angle;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Dimensions;

public class RouteCreator {

    public Route createRouteBuildingWithDegreesPointWithCoordinates() {
        Coordinate coordinate = new Coordinate(12.3,14.0);
        Dimensions dimensions = new Dimensions( 40.5,60.2);
        Building building = new Building.Builder()
                .address("TEST_BUILDING")
                .center(coordinate)
                .dimensions(dimensions)
                .infoHtml("TEST_INFO")
                .name("TEST_NAME")
                .pictureThumbUrl(new URL("TEST_URL"))
                .pictureUrl(new URL("TEST_URL"))
                .rotation(Angle.fromDegrees(45))
                .userIdentifier("TEST_USER")
                .build();
        Point from = new Point(building, "12",new CartesianCoordinate(4,7));
        Point to = new Point(building, "12",new CartesianCoordinate(2,5));
        DirectionsRequest request = new DirectionsRequest.Builder()
                .from(from, Angle.fromDegrees(30))
                .isAccessible(true)
                .to(to)
                .build();
        to = new Point(building, "12",new CartesianCoordinate(6,6));
        RouteStep step1 = new RouteStep.Builder()
                .distance(11.4)
                .distanceToEnd(27)
                .distanceToFloorChange(15.0)
                .from(from)
                .id(1)
                .isLast(false)
                .to(to)
                .build();
        from = new Point(building, "12",new CartesianCoordinate(6,6));
        to = new Point(building, "12",new CartesianCoordinate(3,5));
        RouteStep step2 = new RouteStep.Builder()
                .distance(8.6)
                .distanceToEnd(15.6)
                .distanceToFloorChange(24.5)
                .from(from)
                .id(2)
                .isLast(false)
                .to(to)
                .build();
        from = new Point(building, "12",new CartesianCoordinate(3,5));
        to = new Point("101", new Coordinate(2,5));
        RouteStep step3 = new RouteStep.Builder()
                .distance(7)
                .distanceToEnd(7)
                .distanceToFloorChange(30.0)
                .from(from)
                .id(3)
                .isLast(true)
                .to(to)
                .build();
        Indication indication = new Indication.Builder()
                .setDistance(11.4)
                .setDistanceToNextLevel(15)
                .setInstructionType(Indication.Action.TURN)
                .setNextLevel(null)
                .setOrientation(14.5)
                .setOrientationType(Indication.Orientation.BACKWARD)
                .setStepIdxDestination(5)
                .setStepIdxOrigin(4)
                .build();
        Indication indication2 = new Indication.Builder()
                .setDistance(8.6)
                .setDistanceToNextLevel(24)
                .setInstructionType(Indication.Action.GO_AHEAD)
                .setNextLevel(null)
                .setOrientation(3.5)
                .setOrientationType(Indication.Orientation.STRAIGHT)
                .setStepIdxDestination(5)
                .setStepIdxOrigin(4)
                .build();
        Indication indication3 = new Indication.Builder()
                .setDistance(7)
                .setDistanceToNextLevel(30)
                .setInstructionType(Indication.Action.END)
                .setNextLevel(null)
                .setOrientation(3.5)
                .setOrientationType(Indication.Orientation.LEFT)
                .setStepIdxDestination(5)
                .setStepIdxOrigin(4)
                .build();
        Floor floor = new Floor.Builder()
                .buildingIdentifier("101")
                .level(1)
                .mapUrl(new URL("TEST_URL"))
                .scale(10.2)
                .build();
        List<RouteStep> steps = new ArrayList<>();
        List<Indication> indications = new ArrayList<>();
        List<Floor> floors = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        indications.add(indication);
        indications.add(indication2);
        indications.add(indication3);
        floors.add(floor);
        return new Route(request, steps, indications, building, floors);

    }
}
