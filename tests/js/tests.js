const buildings = require('./resources/fetchBuildings.json');
let building;
const floors = require('./resources/fetchFloorsFromBuilding.json');
let floor;
const indoorPois = require('./resources/fetchIndoorPOIsFromBuilding.json');
let indoorPoi;
const outdoorPois = require('./resources/fetchOutdoorPOIsFromBuilding.json');
let outdoorPoi;
const events = require('./resources/fetchEventsFromBuilding.json');
let event;
const poiCategories = require('./resources/fetchPoiCategories.json');
let poiCategory;
const map = require('./resources/fetchMapFromFloor.json');
const iconNormal = require('./resources/fetchPoiCategoryIconNormal.json');
const iconSelected = require('./resources/fetchPoiCategoryIconSelected.json');

describe('Test building -> ', () => {
  it('Check reutrned value', () => {
    expect(buildings).toBeDefined();
    expect(buildings instanceof Array).toBeTruthy();
  });
  console.info("Buildings are correct");
  console.info("Start test building");
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
  console.info("Building test success");
  console.info("Test building bounds field");
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
  console.info("Bounds test success");
  console.info("Test building boundsRotated field");
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
  console.info("BoundsRotated test success");
  console.info("Test building center field")
  it('Check center property', () => {
    expect(typeof building.center.latitude).toBe('number');
    expect(typeof building.center.longitude).toBe('number');
  });
  console.info("Center test success");
  console.info("Test dimension building field")
  it('Check dimensions property', () => {
    expect(typeof building.dimensions.width).toBe('number');
    expect(typeof building.dimensions.height).toBe('number');
  });
  console.info("Dimension test success");
});

describe('Test floor ->', () => {
  it('Check returned value', () => {
    expect(floors).toBeDefined();
    expect(floors instanceof Array).toBeTruthy();
  });
  console.info("Floors are correct");
  console.info("Test floor");
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
  console.info("Floor test success");
});

describe('Test method fetchIndoorPOIsFromBuilding ->', () => {
  it('Check returned value', () => {
    expect(indoorPois).toBeDefined();
    expect(indoorPois instanceof Array).toBeTruthy();
  });
  console.info("IndoorPois are correct");
  console.info("Test indoorPOI");
  it('Check poi object', () => {
    expect(indoorPoi = indoorPois[0]);
    expect(typeof indoorPoi).toBe('object');
    expect(typeof indoorPoi.buildingIdentifier).toBe('string');
    expect(typeof indoorPoi.cartesianCoordinate).toBe('object');
    expect(typeof indoorPoi.category).toBe('string');
    expect(typeof indoorPoi.coordinate).toBe('object');
    expect(typeof indoorPoi.customFields).toBe('object');
    expect(typeof indoorPoi.floorIdentifier).toBe('string');
    expect(typeof indoorPoi.identifier).toBe('string');
    expect(typeof indoorPoi.infoHtml).toBe('string');
    expect(typeof indoorPoi.isIndoor).toBe('boolean');
    expect(typeof indoorPoi.isOutdoor).toBe('boolean');
    expect(typeof indoorPoi.poiName).toBe('string');
    expect(typeof indoorPoi.position).toBe('object');
    expect(typeof indoorPoi.createdAt).toBe('string');
    expect(typeof indoorPoi.updatedAt).toBe('string');
    expect(typeof indoorPoi.customFields).toBe('object');
  });
  console.info("IndoorPOI test success");
  console.info("Test indoorPOI cartesianCoordinate field");
  it('Check cartesiansCoordinate', () => {
    expect(typeof indoorPoi.cartesianCoordinate.x).toBe('number');
    expect(typeof indoorPoi.cartesianCoordinate.y).toBe('number');
  });
  console.info("CartesianCoordinate test success");
  console.info("Test indoorPOI coordinate field");
  it('Check coordinate', () => {
    expect(typeof indoorPoi.coordinate.latitude).toBe('number');
    expect(typeof indoorPoi.coordinate.longitude).toBe('number');
  });
  console.info("Coordinate test success");
  console.info("Test indoorPOI position field");
  it('Check position', () => {
    expect(typeof indoorPoi.position.buildingIdentifier).toBe('string');
    expect(typeof indoorPoi.position.cartesianCoordinate).toBe('object');
    expect(typeof indoorPoi.position.coordinate).toBe('object');
    expect(typeof indoorPoi.position.floorIdentifier).toBe('string');
    expect(typeof indoorPoi.position.isIndoor).toBe('boolean');
    expect(typeof indoorPoi.position.isOutdoor).toBe('boolean');
  });
  console.info("Position test success");
  console.info("Test position cartesianCoordinate field");
  it('Check cartesiansCoordinate of position property', () => {
    expect(typeof indoorPoi.position.cartesianCoordinate.x).toBe('number');
    expect(typeof indoorPoi.position.cartesianCoordinate.y).toBe('number');
  });
  console.info("CatesianCoordinate test success");
  console.info("Test position coordinate field");
  it('Check coordinate of position property', () => {
    expect(typeof indoorPoi.position.coordinate.latitude).toBe('number');
    expect(typeof indoorPoi.position.coordinate.longitude).toBe('number');
  });
  console.info("Coordinate test success");
});
describe('Test method fetchOutdoorPOIsFromBuilding ->', () => {
  it('Check returned value', () => {
    expect(outdoorPois).toBeDefined();
    expect(outdoorPois instanceof Array).toBeTruthy();
  });
  it('Check poi object', () => {
    expect(outdoorPoi = outdoorPois[0]);
    expect(typeof outdoorPoi).toBe('object');
    expect(typeof outdoorPoi.buildingIdentifier).toBe('string');
    expect(typeof outdoorPoi.cartesianCoordinate).toBe('object');
    expect(typeof outdoorPoi.category).toBe('string');
    expect(typeof outdoorPoi.coordinate).toBe('object');
    expect(typeof outdoorPoi.customFields).toBe('object');
    expect(typeof outdoorPoi.floorIdentifier).toBe('string');
    expect(typeof outdoorPoi.identifier).toBe('string');
    expect(typeof outdoorPoi.infoHtml).toBe('string');
    expect(typeof outdoorPoi.isIndoor).toBe('boolean');
    expect(typeof outdoorPoi.isOutdoor).toBe('boolean');
    expect(typeof outdoorPoi.poiName).toBe('string');
    expect(typeof outdoorPoi.position).toBe('object');
    expect(typeof outdoorPoi.createdAt).toBe('string');
    expect(typeof outdoorPoi.updatedAt).toBe('string');
    expect(typeof outdoorPoi.customFields).toBe('object');
  });
  it('Check cartesiansCoordinate', () => {
    expect(typeof outdoorPoi.cartesianCoordinate.x).toBe('number');
    expect(typeof outdoorPoi.cartesianCoordinate.y).toBe('number');
  });
  it('Check coordinate', () => {
    expect(typeof outdoorPoi.coordinate.latitude).toBe('number');
    expect(typeof outdoorPoi.coordinate.longitude).toBe('number');
  });
  it('Check position', () => {
    expect(typeof outdoorPoi.position.buildingIdentifier).toBe('string');
    expect(typeof outdoorPoi.position.cartesianCoordinate).toBe('object');
    expect(typeof outdoorPoi.position.coordinate).toBe('object');
    expect(typeof outdoorPoi.position.floorIdentifier).toBe('string');
    expect(typeof outdoorPoi.position.isIndoor).toBe('boolean');
    expect(typeof outdoorPoi.position.isOutdoor).toBe('boolean');
  });
  it('Check cartesiansCoordinate of position property', () => {
    expect(typeof outdoorPoi.position.cartesianCoordinate.x).toBe('number');
    expect(typeof outdoorPoi.position.cartesianCoordinate.y).toBe('number');
  });
  it('Check coordinate of position property', () => {
    expect(typeof outdoorPoi.position.coordinate.latitude).toBe('number');
    expect(typeof outdoorPoi.position.coordinate.longitude).toBe('number');
  });
});
describe('Test method fetchEventsFromBuilding ->', () => {
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
  it('Check map object', () => {
    expect(map).toBeDefined();
    expect(typeof map.data).toBe('string');
  });
});
describe('Test fetchPoiCategoryIconNormal', () => {
  it('Check icon object', () => {
    expect(iconNormal).toBeDefined();
    expect(typeof iconNormal.data).toBe('string')
  });
});
describe('Has fetchPoiCategoryIconSelected method', () => {
  it('Chek icon object', () => {
    expect(iconSelected).toBeDefined();
    expect(typeof iconSelected.data).toBe('string');
  });
});
describe('Has requestDirections method', () => {

});
describe('Has requestNavigationUpdates method', () => {

});
describe('Has updateNavigationWithLocation method', () => {

});
describe('Has removeNavigationUpdates method', () => {

});
