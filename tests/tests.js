exports.defineAutoTests = () => {
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

  describe('Testing method fetchBuildings -> ', () => {
    let buildings;
    let building;
    beforeAll((done) => {
      cordova.plugins.Situm.setApiKey("oscar.fuentes@cocodin.com", "98a83630ce6667627f0e231cbde0f5052512290c9d52e7965f53486587cf7b22");
      cordova.plugins.Situm.fetchBuildings((response) => {
        buildings = response
        done();
      });
    });
    it('Check reutrned value', (done) => {
      expect(buildings).toBeDefined();
      expect(buildings instanceof Array).toBeTruthy();
      done();
    });
    it('Check building', () => {
      expect(building = buildings[0]);
      expect(building).toBeDefined();
      expect(typeof building === 'object').toBeTruthy();
      expect(typeof building.address === 'string').toBeTruthy();
      expect(typeof building.name === 'string').toBeTruthy();
      expect(typeof building.buildingIdentifier === 'string').toBeTruthy();
      expect(typeof building.userIdentifier === 'string').toBeTruthy();
      expect(typeof building.infoHtml === 'string').toBeTruthy();
      expect(typeof building.center === 'object').toBeTruthy();
      expect(typeof building.rotation === 'number').toBeTruthy();
      expect(typeof building.bounds === 'object').toBeTruthy();
      expect(typeof building.boundsRotated === 'object').toBeTruthy();
    });
    it('Check center property', () => {
      expect(typeof building.center.latitude === 'number').toBeTruthy();
      expect(typeof building.center.longitude === 'number').toBeTruthy();
    });
    it('Check bounds property', () => {
      expect(typeof building.bounds.northEast === 'object').toBeTruthy();
      expect(typeof building.bounds.northEast.latitude === 'number').toBeTruthy();
      expect(typeof building.bounds.northEast.longitude === 'number').toBeTruthy();
      expect(typeof building.bounds.northWest === 'object').toBeTruthy();
      expect(typeof building.bounds.northWest.latitude === 'number').toBeTruthy();
      expect(typeof building.bounds.northWest.longitude === 'number').toBeTruthy();
      expect(typeof building.bounds.southEast === 'object').toBeTruthy();
      expect(typeof building.bounds.southEast.latitude === 'number').toBeTruthy();
      expect(typeof building.bounds.southEast.longitude === 'number').toBeTruthy();
      expect(typeof building.bounds.southWest === 'object').toBeTruthy();
      expect(typeof building.bounds.southWest.latitude === 'number').toBeTruthy();
      expect(typeof building.bounds.southWest.longitude === 'number').toBeTruthy();
    });
    it('Check boundsRotated property', () => {
      expect(typeof building.boundsRotated.northEast === 'object').toBeTruthy();
      expect(typeof building.boundsRotated.northEast.latitude === 'number').toBeTruthy();
      expect(typeof building.boundsRotated.northEast.longitude === 'number').toBeTruthy();
      expect(typeof building.boundsRotated.northWest === 'object').toBeTruthy();
      expect(typeof building.boundsRotated.northWest.latitude === 'number').toBeTruthy();
      expect(typeof building.boundsRotated.northWest.longitude === 'number').toBeTruthy();
      expect(typeof building.boundsRotated.southEast === 'object').toBeTruthy();
      expect(typeof building.boundsRotated.southEast.latitude === 'number').toBeTruthy();
      expect(typeof building.boundsRotated.southEast.longitude === 'number').toBeTruthy();
      expect(typeof building.boundsRotated.southWest === 'object').toBeTruthy();
      expect(typeof building.boundsRotated.southWest.latitude === 'number').toBeTruthy();
      expect(typeof building.boundsRotated.southWest.longitude === 'number').toBeTruthy();
    });
  });
}
