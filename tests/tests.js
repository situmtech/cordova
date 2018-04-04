exports.defineAutoTests = () => {
  // Basic tests to check if is correctly defined in cordova plugins
  describe('Situm object basic tests', () => {
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
}