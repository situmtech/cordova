import es.situm.sdk.model.location.Angle;

public class AngleCreator {

    public Angle createAngleFromDegrees() {
        return  Angle.fromDegrees(47);
    }

    public Angle createAngleFromRadians() {
        return Angle.fromRadians(1.4);
    }
}
