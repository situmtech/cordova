package es.situm.plugin.creators;

import es.situm.sdk.v1.Point2f;
import es.situm.sdk.v1.SitumConversionArea;

public class SitumConversionAreaCreator {

    public SitumConversionArea createSitumConversionArea() {
        Point2f topRight = new Point2f(5,6);
        Point2f topLeft = new Point2f(5,4);
        Point2f bottomRight = new Point2f(3,6);
        Point2f bottomLeft = new Point2f(3,4);
        return new SitumConversionArea(topRight, topLeft, bottomLeft, bottomRight, 12);
    }
}
