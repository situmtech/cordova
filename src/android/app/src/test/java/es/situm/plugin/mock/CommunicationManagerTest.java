package es.situm.plugin.mock;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import es.situm.sdk.communication.CommunicationManager;
import es.situm.sdk.communication.HttpRequestExecutor;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.BuildingInfo;
import es.situm.sdk.model.cartography.CalibrationArea;
import es.situm.sdk.model.cartography.Floor;
import es.situm.sdk.model.cartography.Poi;
import es.situm.sdk.model.cartography.PoiCategory;
import es.situm.sdk.model.cartography.calibration.Scans;
import es.situm.sdk.model.geofencing.EventOccurrence;
import es.situm.sdk.model.organization.OrganizationTheme;
import es.situm.sdk.utils.Handler;
import es.situm.sdk.v1.SitumEvent;

public class CommunicationManagerTest implements CommunicationManager {

    @Override
    public boolean validateUserCredentials(@NonNull Handler<Object> handler) {
        return false;
    }

    @Override
    public boolean logout(Handler<Object> handler) {
        return false;
    }

    @Override
    public boolean fetchBuildings(@NonNull Handler<Collection<Building>> handler) {
        Collection<Building>  buildings = new ArrayList<Building>();
        buildings.add(new Building.Builder().address("camiño de adran").build());
        handler.onSuccess(buildings);
        return true;
    }

    @Override
    public boolean fetchBuildings(Map<String, Object> map, @NonNull Handler<Collection<Building>> handler) {
        return false;
    }

    @Override
    public boolean fetchBuildingInfo(Building building, @NonNull Handler<BuildingInfo> handler) {
        return false;
    }

    @Override
    public boolean fetchBuildingInfo(@NonNull Building building, Map<String, Object> map, @NonNull Handler<BuildingInfo> handler) {
        return false;
    }

    @Override
    public boolean fetchFloorsFromBuilding(@NonNull Building building, @NonNull Handler<Collection<Floor>> handler) {
        return false;
    }

    @Override
    public boolean fetchFloorsFromBuilding(@NonNull Building building, Map<String, Object> map, @NonNull Handler<Collection<Floor>> handler) {
        return false;
    }

    @Override
    public boolean fetchMapFromFloor(@NonNull Floor floor, @NonNull Handler<Bitmap> handler) {
        return false;
    }

    @Override
    public boolean fetchMapFromFloor(@NonNull Floor floor, Map<String, Object> map, @NonNull Handler<Bitmap> handler) {
        return false;
    }

    @Override
    public boolean fetchIndoorPOIsFromBuilding(@NonNull Building building, @NonNull Handler<Collection<Poi>> handler) {
        return false;
    }

    @Override
    public boolean fetchIndoorPOIsFromBuilding(@NonNull Building building, Map<String, Object> map, @NonNull Handler<Collection<Poi>> handler) {
        return false;
    }

    @Override
    public boolean fetchPoiCategories(@NonNull Handler<Collection<PoiCategory>> handler) {
        return false;
    }

    @Override
    public boolean fetchPoiCategories(Map<String, Object> map, @NonNull Handler<Collection<PoiCategory>> handler) {
        return false;
    }

    @Override
    public boolean fetchPoiCategoryIconNormal(@NonNull PoiCategory poiCategory, @NonNull Handler<Bitmap> handler) {
        return false;
    }

    @Override
    public boolean fetchPoiCategoryIconNormal(@NonNull PoiCategory poiCategory, Map<String, Object> map, @NonNull Handler<Bitmap> handler) {
        return false;
    }

    @Override
    public boolean fetchPoiCategoryIconSelected(@NonNull PoiCategory poiCategory, @NonNull Handler<Bitmap> handler) {
        return false;
    }

    @Override
    public boolean fetchPoiCategoryIconSelected(@NonNull PoiCategory poiCategory, Map<String, Object> map, @NonNull Handler<Bitmap> handler) {
        return false;
    }

    @Override
    public boolean fetchOutdoorPOIsFromBuilding(@NonNull Building building, @NonNull Handler<Collection<Poi>> handler) {
        return false;
    }

    @Override
    public boolean fetchOutdoorPOIsFromBuilding(@NonNull Building building, Map<String, Object> map, @NonNull Handler<Collection<Poi>> handler) {
        return false;
    }

    @Override
    public boolean fetchEventsFromBuilding(@NonNull Building building, @NonNull Handler<Collection<SitumEvent>> handler) {
        return false;
    }

    @Override
    public boolean fetchEventsFromBuilding(@NonNull Building building, Map<String, Object> map, @NonNull Handler<Collection<SitumEvent>> handler) {
        return false;
    }

    @Override
    public boolean eventSeen(@NonNull SitumEvent situmEvent, @NonNull Handler<EventOccurrence> handler) {
        return false;
    }

    @Override
    public boolean eventSeen(@NonNull SitumEvent situmEvent, Map<String, Object> map, @NonNull Handler<EventOccurrence> handler) {
        return false;
    }

    @Override
    public boolean eventClicked(@NonNull EventOccurrence eventOccurrence, @NonNull Handler<EventOccurrence> handler) {
        return false;
    }

    @Override
    public boolean eventClicked(@NonNull EventOccurrence eventOccurrence, Map<String, Object> map, @NonNull Handler<EventOccurrence> handler) {
        return false;
    }

    @Override
    public boolean eventConverted(@NonNull EventOccurrence eventOccurrence, @NonNull Handler<EventOccurrence> handler) {
        return false;
    }

    @Override
    public boolean eventConverted(@NonNull EventOccurrence eventOccurrence, Map<String, Object> map, @NonNull Handler<EventOccurrence> handler) {
        return false;
    }

    @Override
    public boolean fetchCalibratedArea(@NonNull Building building, @NonNull Floor floor, @NonNull Handler<Collection<CalibrationArea>> handler) {
        return false;
    }

    @Override
    public boolean fetchCalibrationWifiScans(@NonNull Building building, @NonNull Floor floor, @NonNull Handler<Scans> handler) {
        return false;
    }

    @Override
    public boolean fetchCalibrationBleScans(@NonNull Building building, @NonNull Floor floor, @NonNull Handler<Scans> handler) {
        return false;
    }

    @Override
    public boolean fetchRailWidth(@NonNull Building building, @NonNull Handler<Float> handler) {
        return false;
    }

    @Override
    public boolean updateRailWidth(@NonNull Building building, float v, @NonNull Handler<Object> handler) {
        return false;
    }

    @Override
    public void invalidateCache() {

    }

    @NonNull
    @Override
    public HttpRequestExecutor getHttpRequestExecutor() {
        return null;
    }

    @Override
    public boolean fetchOrganizationTheme(@NonNull Handler<OrganizationTheme> handler) {
        return false;
    }
}

