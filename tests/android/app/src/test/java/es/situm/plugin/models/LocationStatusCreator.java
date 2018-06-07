package es.situm.plugin.models;

import es.situm.sdk.location.LocationStatus;

public class LocationStatusCreator {

    public LocationStatus createLocationStatusStarting() {
        return LocationStatus.STARTING;
    }

    public LocationStatus createLocationStatusBLENotAvailable() {
        return LocationStatus.BLE_NOT_AVAILABLE;
    }

    public LocationStatus createLocationStatusCalculating() {
        return LocationStatus.CALCULATING;
    }

    public LocationStatus createLocationStatusCompassCalibrationNeeded() {
        return LocationStatus.COMPASS_CALIBRATION_NEEDED;
    }

    public LocationStatus createLocationStatusCompassCalibrationNotNeeded() {
        return LocationStatus.COMPASS_CALIBRATION_NOT_NEEDED;
    }

    public LocationStatus createLocationStatusNoConnection() {
        return LocationStatus.NO_CONNECTION;
    }

    public LocationStatus createLocationStatusPreparingPositioningModel() {
        return LocationStatus.PREPARING_POSITIONING_MODEL;
    }

    public LocationStatus createLocationStatusProcessingPositioningModel() {
        return LocationStatus.PROCESSING_POSITIONING_MODEL;
    }

    public LocationStatus createLocationStatusRetryDownloadPositioningModel() {
        return LocationStatus.RETRY_DOWNLOAD_POSITIONING_MODEL;
    }

    public LocationStatus createLocationStatusStartDownloadPositioningModel() {
        return LocationStatus.START_DOWNLOADING_POSITIONING_MODEL;
    }

    public LocationStatus createLocationStatusStartingPositioning() {
        return LocationStatus.STARTING_POSITIONING;
    }

    public LocationStatus createLocationStatusTimeSettingsManual() {
        return LocationStatus.TIME_SETTINGS_MANUAL;
    }

    public LocationStatus createLocationStatusUserNotInBuilding() {
        return LocationStatus.USER_NOT_IN_BUILDING;
    }
}
