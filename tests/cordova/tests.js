exports.defineAutoTests = () => {
  let buildings;
  let building;
  let floors;
  let floor;
  // Basic tests to check if is correctly defined in cordova plugins
  describe('Situm object basic tests -> ', () => {
    it('Is Situm defined', () => {
      expect(cordova.plugins.Situm).toBeDefined();
    });
    it('Has setApiKey method', () => {
      expect(cordova.plugins.Situm.setApiKey).toBeDefined();
    });
    it('Has setUserPass method', () => {
      expect(cordova.plugins.Situm.setUserPass).toBeDefined();
    });
    it('Has setCacheMaxAge method', () => {
      expect(cordova.plugins.Situm.setCacheMaxAge).toBeDefined();
    });
    it('Has startPositioning method', () => {
      expect(cordova.plugins.Situm.startPositioning).toBeDefined();
    });
    it('Has stopPositioning method', () => {
      expect(cordova.plugins.Situm.stopPositioning).toBeDefined();
    });
    it('Has fetchBuildings method', () => {
      expect(cordova.plugins.Situm.fetchBuildings).toBeDefined();
    });
    it('Has fetchFloorsFromBuilding method', () => {
      expect(cordova.plugins.Situm.fetchFloorsFromBuilding).toBeDefined();
    });
    it('Has fetchIndoorPOIsFromBuilding method', () => {
      expect(cordova.plugins.Situm.fetchIndoorPOIsFromBuilding).toBeDefined();
    });
    it('Has fetchOutdoorPOIsFromBuilding method', () => {
      expect(cordova.plugins.Situm.fetchOutdoorPOIsFromBuilding).toBeDefined();
    });
    it('Has fetchEventsFromBuilding method', () => {
      expect(cordova.plugins.Situm.fetchEventsFromBuilding).toBeDefined();
    });
    it('Has fetchPoiCategories method', () => {
      expect(cordova.plugins.Situm.fetchPoiCategories).toBeDefined();
    });
    it('Has fetchMapFromFloor method', () => {
      expect(cordova.plugins.Situm.fetchMapFromFloor).toBeDefined();
    });
    it('Has fetchPoiCategoryIconNormal method', () => {
      expect(cordova.plugins.Situm.fetchPoiCategoryIconNormal).toBeDefined();
    });
    it('Has fetchPoiCategoryIconSelected method', () => {
      expect(cordova.plugins.Situm.fetchPoiCategoryIconSelected).toBeDefined();
    });
    it('Has invalidateCache method', () => {
      expect(cordova.plugins.Situm.invalidateCache).toBeDefined();
    });
    it('Has requestDirections method', () => {
      expect(cordova.plugins.Situm.requestDirections).toBeDefined();
    });
    it('Has requestNavigationUpdates method', () => {
      expect(cordova.plugins.Situm.requestNavigationUpdates).toBeDefined();
    });
    it('Has updateNavigationWithLocation method', () => {
      expect(cordova.plugins.Situm.updateNavigationWithLocation).toBeDefined();
    });
    it('Has removeNavigationUpdates method', () => {
      expect(cordova.plugins.Situm.removeNavigationUpdates).toBeDefined();
    });
  });

  describe('Test method fetchBuildings -> ', () => {
    beforeAll((done) => {
      cordova.plugins.Situm.setApiKey("oscar.fuentes@cocodin.com", "98a83630ce6667627f0e231cbde0f5052512290c9d52e7965f53486587cf7b22");
      cordova.plugins.Situm.fetchBuildings((response) => {
        buildings = response
        done();
      });
    });
    it('Check reutrned value', () => {
      expect(buildings).toBeDefined();
      expect(buildings instanceof Array).toBeTruthy();
    });
    it('Check building', () => {
      expect(building = buildings[0]);
      expect(typeof building).toBe('object');
      expect(typeof building.createdAt).toBe('string');
      expect(typeof building.updatedAt).toBe('string');
      expect(typeof building.address).toBe('string');
      expect(typeof building.name).toBe('string');
      expect(typeof building.buildingIdentifier).toBe('string');
      expect(typeof building.userIdentifier).toBe('string');
      expect(typeof building.infoHtml).toBe('string');
      expect(typeof building.center).toBe('object');
      expect(typeof building.rotation).toBe('number');
      expect(typeof building.dimensions).toBe('object');
      expect(typeof building.bounds).toBe('object');
      expect(typeof building.boundsRotated).toBe('object');
    });
    it('Check bounds', () => {
      expect(typeof building.bounds.northEast).toBe('object');
      expect(typeof building.bounds.northEast.latitude).toBe('number');
      expect(typeof building.bounds.northEast.longitude).toBe('number');
      expect(typeof building.bounds.northWest).toBe('object');
      expect(typeof building.bounds.northWest.latitude).toBe('number');
      expect(typeof building.bounds.northWest.longitude).toBe('number');
      expect(typeof building.bounds.southEast).toBe('object');
      expect(typeof building.bounds.southEast.latitude).toBe('number');
      expect(typeof building.bounds.southEast.longitude).toBe('number');
      expect(typeof building.bounds.southWest).toBe('object');
      expect(typeof building.bounds.southWest.latitude).toBe('number');
      expect(typeof building.bounds.southWest.longitude).toBe('number');
    });
    it('Check bounds rotated', () => {
      expect(typeof building.boundsRotated.northEast).toBe('object');
      expect(typeof building.boundsRotated.northEast.latitude).toBe('number');
      expect(typeof building.boundsRotated.northEast.longitude).toBe('number');
      expect(typeof building.boundsRotated.northWest).toBe('object');
      expect(typeof building.boundsRotated.northWest.latitude).toBe('number');
      expect(typeof building.boundsRotated.northWest.longitude).toBe('number');
      expect(typeof building.boundsRotated.southEast).toBe('object');
      expect(typeof building.boundsRotated.southEast.latitude).toBe('number');
      expect(typeof building.boundsRotated.southEast.longitude).toBe('number');
      expect(typeof building.boundsRotated.southWest).toBe('object');
      expect(typeof building.boundsRotated.southWest.latitude).toBe('number');
      expect(typeof building.boundsRotated.southWest.longitude).toBe('number');
    });
    it('Check center property', () => {
      expect(typeof building.center.latitude).toBe('number');
      expect(typeof building.center.longitude).toBe('number');
    });
    it('Check dimensions property', () => {
      expect(typeof building.dimensions.width).toBe('number');
      expect(typeof building.dimensions.height).toBe('number');
    });
  });

  describe('Test method fetchFloorsFromBuilding ->', () => {
    beforeAll((done) => {
      cordova.plugins.Situm.fetchFloorsFromBuilding(building, (response) => {
        floors = response;
        done();
      });
    });
    it('Check returned value', () => {
      expect(floors).toBeDefined();
      expect(floors instanceof Array).toBeTruthy();
    });
    it('Check floor object', () => {
      expect(floor = floors[0]);
      expect(typeof floor).toBe('object');
      expect(typeof floor.altitude).toBe('number');
      expect(typeof floor.buildingIdentifier).toBe('string');
      expect(typeof floor.floorIdentifier).toBe('string');
      expect(typeof floor.level).toBe('number');
      expect(typeof floor.mapUrl).toBe('string');
      expect(typeof floor.scale).toBe('number');
      expect(typeof floor.createdAt).toBe('string');
      expect(typeof floor.updatedAt).toBe('string');
      expect(typeof floor.customFields).toBe('object');
    });
  });

  describe('Test method fetchIndoorPOIsFromBuilding ->', () => {
    let pois;
    let poi;
    beforeAll((done) => {
      cordova.plugins.Situm.fetchIndoorPOIsFromBuilding(building, (response) => {
        pois = response;
        done();
      });
    });
    it('Check returned value', () => {
      expect(pois).toBeDefined();
      expect(pois instanceof Array).toBeTruthy();
    });
    it('Check poi object', () => {
      expect(poi = pois[0]);
      expect(typeof poi).toBe('object');
      expect(typeof poi.buildingIdentifier).toBe('string');
      expect(typeof poi.cartesianCoordinate).toBe('object');
      expect(typeof poi.category).toBe('string');
      expect(typeof poi.coordinate).toBe('object');
      expect(typeof poi.customFields).toBe('object');
      expect(typeof poi.floorIdentifier).toBe('string');
      expect(typeof poi.identifier).toBe('string');
      expect(typeof poi.infoHtml).toBe('string');
      expect(typeof poi.isIndoor).toBe('boolean');
      expect(typeof poi.isOutdoor).toBe('boolean');
      expect(typeof poi.poiName).toBe('string');
      expect(typeof poi.position).toBe('object');
      expect(typeof poi.createdAt).toBe('string');
      expect(typeof poi.updatedAt).toBe('string');
      expect(typeof poi.customFields).toBe('object');
    });
    it('Check cartesiansCoordinate', () => {
      expect(typeof poi.cartesianCoordinate.x).toBe('number');
      expect(typeof poi.cartesianCoordinate.y).toBe('number');
    });
    it('Check coordinate', () => {
      expect(typeof poi.coordinate.latitude).toBe('number');
      expect(typeof poi.coordinate.longitude).toBe('number');
    });
    it('Check position', () => {
      expect(typeof poi.position.buildingIdentifier).toBe('string');
      expect(typeof poi.position.cartesianCoordinate).toBe('object');
      expect(typeof poi.position.coordinate).toBe('object');
      expect(typeof poi.position.floorIdentifier).toBe('string');
      expect(typeof poi.position.isIndoor).toBe('boolean');
      expect(typeof poi.position.isOutdoor).toBe('boolean');
    });
    it('Check cartesiansCoordinate of position property', () => {
      expect(typeof poi.position.cartesianCoordinate.x).toBe('number');
      expect(typeof poi.position.cartesianCoordinate.y).toBe('number');
    });
    it('Check coordinate of position property', () => {
      expect(typeof poi.position.coordinate.latitude).toBe('number');
      expect(typeof poi.position.coordinate.longitude).toBe('number');
    });
  });
  describe('Test method fetchOutdoorPOIsFromBuilding ->', () => {
    let pois;
    let poi;
    beforeAll((done) => {
      cordova.plugins.Situm.fetchOutdoorPOIsFromBuilding(building, (response) => {
        pois = response;
        done();
      });
    });
    it('Check returned value', () => {
      expect(pois).toBeDefined();
      expect(pois instanceof Array).toBeTruthy();
    });
    it('Check poi object', () => {
      expect(poi = pois[0]);
      expect(typeof poi).toBe('object');
      expect(typeof poi.buildingIdentifier).toBe('string');
      expect(typeof poi.cartesianCoordinate).toBe('object');
      expect(typeof poi.category).toBe('string');
      expect(typeof poi.coordinate).toBe('object');
      expect(typeof poi.customFields).toBe('object');
      expect(typeof poi.floorIdentifier).toBe('string');
      expect(typeof poi.identifier).toBe('string');
      expect(typeof poi.infoHtml).toBe('string');
      expect(typeof poi.isIndoor).toBe('boolean');
      expect(typeof poi.isOutdoor).toBe('boolean');
      expect(typeof poi.poiName).toBe('string');
      expect(typeof poi.position).toBe('object');
      expect(typeof poi.createdAt).toBe('string');
      expect(typeof poi.updatedAt).toBe('string');
      expect(typeof poi.customFields).toBe('object');
    });
    it('Check cartesiansCoordinate', () => {
      expect(typeof poi.cartesianCoordinate.x).toBe('number');
      expect(typeof poi.cartesianCoordinate.y).toBe('number');
    });
    it('Check coordinate', () => {
      expect(typeof poi.coordinate.latitude).toBe('number');
      expect(typeof poi.coordinate.longitude).toBe('number');
    });
    it('Check position', () => {
      expect(typeof poi.position.buildingIdentifier).toBe('string');
      expect(typeof poi.position.cartesianCoordinate).toBe('object');
      expect(typeof poi.position.coordinate).toBe('object');
      expect(typeof poi.position.floorIdentifier).toBe('string');
      expect(typeof poi.position.isIndoor).toBe('boolean');
      expect(typeof poi.position.isOutdoor).toBe('boolean');
    });
    it('Check cartesiansCoordinate of position property', () => {
      expect(typeof poi.position.cartesianCoordinate.x).toBe('number');
      expect(typeof poi.position.cartesianCoordinate.y).toBe('number');
    });
    it('Check coordinate of position property', () => {
      expect(typeof poi.position.coordinate.latitude).toBe('number');
      expect(typeof poi.position.coordinate.longitude).toBe('number');
    });
  });
  describe('Test method fetchEventsFromBuilding ->', () => {
    let evetns;
    let event;
    beforeAll((done) => {
      cordova.plugins.Situm.fetchEventsFromBuilding(building, (response) => {
        events = response;
        done();
      });
    });
    it('Check response', () => {
      expect(events).toBeDefined();
      expect(events instanceof Array).toBeTruthy();
    });
    it('Check event object', () => {
      expect(event = events[0]);
      expect(typeof event).toBe('object');
      expect(typeof event.buildingIdentifier).toBe('number');
      expect(typeof event.conversionArea).toBe('object');
      expect(typeof event.customFields).toBe('object');
      expect(typeof event.floorIdentifier).toBe('number');
      expect(typeof event.identifier).toBe('number');
      expect(typeof event.infoHtml).toBe('string');
      expect(typeof event.radius).toBe('number');
      expect(typeof event.name).toBe('string');
    });
    it('Check conversion area', () => {
      expect(typeof event.conversionArea.floorIdentifier).toBe('number');
      expect(typeof event.conversionArea.bottomLeft).toBe('string');
      expect(typeof event.conversionArea.bottomRight).toBe('string');
      expect(typeof event.conversionArea.topLeft).toBe('string');
      expect(typeof event.conversionArea.topRight).toBe('string');
    });
  });
  describe('Test method fetchPoiCategories ->', () => {
    let poiCategories;
    let poiCategory;
    beforeAll((done) => {
      cordova.plugins.Situm.fetchPoiCategories((response) => {
        poiCategories = response;
        done();
      });
    });
    it('Check POICategories', () => {
      expect(poiCategories).toBeDefined();
      expect(poiCategories instanceof Array).toBeTruthy();
    });
    it('Check POICategory object', () => {
      expect(poiCategory = poiCategories[0]);
      expect(typeof poiCategory).toBe('object');
      expect(typeof poiCategory.icon_selected).toBe('string');
      expect(typeof poiCategory.icon_unselected).toBe('string');
      expect(typeof poiCategory.poiCategoryCode).toBe('string');
      expect(typeof poiCategory.poiCategoryName).toBe('string');
      expect(typeof poiCategory.public).toBe('boolean');
    });
  });
  describe('Test method fetchMapFromFloor ->', () => {
    let map;
    beforeAll((done) => {
      cordova.plugins.Situm.fetchMapFromFloor(floor, (response) => {
        map = response;
        done();
      });
    });
    it('Check map object', () => {
      expect(map).toBeDefined();
      expect(typeof map.data).toBe('string');
    });
  });
}
