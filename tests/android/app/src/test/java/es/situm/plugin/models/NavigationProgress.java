import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.directions.Indication;
import es.situm.sdk.model.directions.RouteStep;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Location;
import es.situm.sdk.model.navigation.NavigationProgress;

public class NavigationProgressCreator {

    public NavigationProgress createNavigationProgressOutdoor() {
        Point point = new Point("101", new Coordinate(2,4));
        Location location = new Location.Builder(1242142142,"TEST_PROVIDER",
                point,3.4f).build();
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
        Indication nextIndication = new Indication.Builder()
                .setDistance(8.6)
                .setDistanceToNextLevel(24)
                .setInstructionType(Indication.Action.GO_AHEAD)
                .setNextLevel(null)
                .setOrientation(3.5)
                .setOrientationType(Indication.Orientation.STRAIGHT)
                .setStepIdxDestination(5)
                .setStepIdxOrigin(4)
                .build();
        Point from = new Point("101", new Coordinate(2,5));
        Point to = new Point("101", new Coordinate(3,4));
        RouteStep routeStep = new RouteStep.Builder()
                .distance(23.4)
                .distanceToEnd(27)
                .distanceToFloorChange(13.6)
                .from(from)
                .id(1)
                .isLast(false)
                .to(to)
                .build();

        return new NavigationProgress.Builder()
                .closestLocationInRoute(location)
                .closestRoutePoint(point)
                .distanceToClosestRoutePoint(12)
                .distanceToEndStep(16)
                .distanceToGoal(24)
                .indication(indication)
                .nextIndication(nextIndication)
                .routeStep(routeStep)
                .speed(2.5)
                .build();
    }

    public NavigationProgress createNavigationProgressIndoor() {
        Point point = new Point("101","12", new Coordinate(2,4), new CartesianCoordinate(2,5));
        Location location = new Location.Builder(1242142142,"TEST_PROVIDER",
                point,3.4f).build();
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
        Indication nextIndication = new Indication.Builder()
                .setDistance(8.6)
                .setDistanceToNextLevel(24)
                .setInstructionType(Indication.Action.GO_AHEAD)
                .setNextLevel(null)
                .setOrientation(3.5)
                .setOrientationType(Indication.Orientation.STRAIGHT)
                .setStepIdxDestination(5)
                .setStepIdxOrigin(4)
                .build();
        Point from = new Point("101","12", Coordinate.EMPTY, new CartesianCoordinate(5,6));
        Point to = new Point("101","12", Coordinate.EMPTY, new CartesianCoordinate(3,5));
        RouteStep routeStep = new RouteStep.Builder()
                .distance(23.4)
                .distanceToEnd(27)
                .distanceToFloorChange(13.6)
                .from(from)
                .id(1)
                .isLast(false)
                .to(to)
                .build();

        return new NavigationProgress.Builder()
                .closestLocationInRoute(location)
                .closestRoutePoint(point)
                .distanceToClosestRoutePoint(12)
                .distanceToEndStep(16)
                .distanceToGoal(24)
                .indication(indication)
                .nextIndication(nextIndication)
                .routeStep(routeStep)
                .speed(1.5)
                .build();
    }
}
