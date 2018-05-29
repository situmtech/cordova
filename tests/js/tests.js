const expect = require("expect.js");
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
    expect(buildings).to.be.ok();
    expect(buildings instanceof Array).to.be(true);
  });
  it('Check building', () => {
    expect(building = buildings[0]);
    expect(typeof building).to.be('object');
    expect(typeof building.address).to.be('string');
    expect(typeof building.name).to.be('string');
    expect(typeof building.buildingIdentifier).to.be('string');
    expect(typeof building.userIdentifier).to.be('string');
    expect(typeof building.infoHtml).to.be('string');
    expect(typeof building.center).to.be('object');
    expect(typeof building.rotation).to.be('number');
    expect(typeof building.dimensions).to.be('object');
    expect(typeof building.bounds).to.be('object');
    expect(typeof building.boundsRotated).to.be('object');
  });
  it('Check bounds', () => {
    expect(typeof building.bounds.northEast).to.be('object');
    expect(typeof building.bounds.northEast.latitude).to.be('number');
    expect(typeof building.bounds.northEast.longitude).to.be('number');
    expect(typeof building.bounds.northWest).to.be('object');
    expect(typeof building.bounds.northWest.latitude).to.be('number');
    expect(typeof building.bounds.northWest.longitude).to.be('number');
    expect(typeof building.bounds.southEast).to.be('object');
    expect(typeof building.bounds.southEast.latitude).to.be('number');
    expect(typeof building.bounds.southEast.longitude).to.be('number');
    expect(typeof building.bounds.southWest).to.be('object');
    expect(typeof building.bounds.southWest.latitude).to.be('number');
    expect(typeof building.bounds.southWest.longitude).to.be('number');
  });
  it('Check bounds rotated', () => {
    expect(typeof building.boundsRotated.northEast).to.be('object');
    expect(typeof building.boundsRotated.northEast.latitude).to.be('number');
    expect(typeof building.boundsRotated.northEast.longitude).to.be('number');
    expect(typeof building.boundsRotated.northWest).to.be('object');
    expect(typeof building.boundsRotated.northWest.latitude).to.be('number');
    expect(typeof building.boundsRotated.northWest.longitude).to.be('number');
    expect(typeof building.boundsRotated.southEast).to.be('object');
    expect(typeof building.boundsRotated.southEast.latitude).to.be('number');
    expect(typeof building.boundsRotated.southEast.longitude).to.be('number');
    expect(typeof building.boundsRotated.southWest).to.be('object');
    expect(typeof building.boundsRotated.southWest.latitude).to.be('number');
    expect(typeof building.boundsRotated.southWest.longitude).to.be('number');
  });
  it('Check center property', () => {
    expect(typeof building.center.latitude).to.be('number');
    expect(typeof building.center.longitude).to.be('number');
  });
  it('Check dimensions property', () => {
    expect(typeof building.dimensions.width).to.be('number');
    expect(typeof building.dimensions.height).to.be('number');
  });
});

describe('Test floor ->', () => {
  it('Check returned value', () => {
    expect(floors).to.be.ok();
    expect(floors instanceof Array).to.be(true);
  });
  it('Check floor object', () => {
    expect(floor = floors[0]);
    expect(typeof floor).to.be('object');
    expect(typeof floor.altitude).to.be('number');
    expect(typeof floor.buildingIdentifier).to.be('string');
    expect(typeof floor.floorIdentifier).to.be('string');
    expect(typeof floor.level).to.be('number');
    expect(typeof floor.mapUrl).to.be('string');
    expect(typeof floor.scale).to.be('number');
  });
});

describe('Test method fetchIndoorPOIsFromBuilding ->', () => {
  it('Check returned value', () => {
    expect(indoorPois).to.be.ok();
    expect(indoorPois instanceof Array).to.be(true);
  });
  it('Check poi object', () => {
    expect(indoorPoi = indoorPois[0]);
    expect(typeof indoorPoi).to.be('object');
    expect(typeof indoorPoi.buildingIdentifier).to.be('string');
    expect(typeof indoorPoi.cartesianCoordinate).to.be('object');
    expect(typeof indoorPoi.category).to.be('string');
    expect(typeof indoorPoi.coordinate).to.be('object');
    expect(typeof indoorPoi.customFields).to.be('object');
    expect(typeof indoorPoi.floorIdentifier).to.be('string');
    expect(typeof indoorPoi.identifier).to.be('string');
    expect(typeof indoorPoi.infoHtml).to.be('string');
    expect(typeof indoorPoi.isIndoor).to.be('boolean');
    expect(typeof indoorPoi.isOutdoor).to.be('boolean');
    expect(typeof indoorPoi.poiName).to.be('string');
    expect(typeof indoorPoi.position).to.be('object');
    expect(typeof indoorPoi.customFields).to.be('object');
  });
  it('Check cartesiansCoordinate', () => {
    expect(typeof indoorPoi.cartesianCoordinate.x).to.be('number');
    expect(typeof indoorPoi.cartesianCoordinate.y).to.be('number');
  });
  it('Check coordinate', () => {
    expect(typeof indoorPoi.coordinate.latitude).to.be('number');
    expect(typeof indoorPoi.coordinate.longitude).to.be('number');
  });
  it('Check position', () => {
    expect(typeof indoorPoi.position.buildingIdentifier).to.be('string');
    expect(typeof indoorPoi.position.cartesianCoordinate).to.be('object');
    expect(typeof indoorPoi.position.coordinate).to.be('object');
    expect(typeof indoorPoi.position.floorIdentifier).to.be('string');
    expect(typeof indoorPoi.position.isIndoor).to.be('boolean');
    expect(typeof indoorPoi.position.isOutdoor).to.be('boolean');
  });
  it('Check cartesiansCoordinate of position property', () => {
    expect(typeof indoorPoi.position.cartesianCoordinate.x).to.be('number');
    expect(typeof indoorPoi.position.cartesianCoordinate.y).to.be('number');
  });
  it('Check coordinate of position property', () => {
    expect(typeof indoorPoi.position.coordinate.latitude).to.be('number');
    expect(typeof indoorPoi.position.coordinate.longitude).to.be('number');
  });
});
describe('Test method fetchOutdoorPOIsFromBuilding ->', () => {
  it('Check returned value', () => {
    expect(outdoorPois).to.be.ok();
    expect(outdoorPois instanceof Array).to.be(true);
  });
  it('Check poi object', () => {
    expect(outdoorPoi = outdoorPois[0]);
    expect(typeof outdoorPoi).to.be('object');
    expect(typeof outdoorPoi.buildingIdentifier).to.be('string');
    expect(typeof outdoorPoi.cartesianCoordinate).to.be('object');
    expect(typeof outdoorPoi.category).to.be('string');
    expect(typeof outdoorPoi.coordinate).to.be('object');
    expect(typeof outdoorPoi.customFields).to.be('object');
    expect(typeof outdoorPoi.floorIdentifier).to.be('string');
    expect(typeof outdoorPoi.identifier).to.be('string');
    expect(typeof outdoorPoi.infoHtml).to.be('string');
    expect(typeof outdoorPoi.isIndoor).to.be('boolean');
    expect(typeof outdoorPoi.isOutdoor).to.be('boolean');
    expect(typeof outdoorPoi.poiName).to.be('string');
    expect(typeof outdoorPoi.position).to.be('object');
    expect(typeof outdoorPoi.customFields).to.be('object');
  });
  it('Check cartesiansCoordinate', () => {
    expect(typeof outdoorPoi.cartesianCoordinate.x).to.be('number');
    expect(typeof outdoorPoi.cartesianCoordinate.y).to.be('number');
  });
  it('Check coordinate', () => {
    expect(typeof outdoorPoi.coordinate.latitude).to.be('number');
    expect(typeof outdoorPoi.coordinate.longitude).to.be('number');
  });
  it('Check position', () => {
    expect(typeof outdoorPoi.position.buildingIdentifier).to.be('string');
    expect(typeof outdoorPoi.position.cartesianCoordinate).to.be('object');
    expect(typeof outdoorPoi.position.coordinate).to.be('object');
    expect(typeof outdoorPoi.position.floorIdentifier).to.be('string');
    expect(typeof outdoorPoi.position.isIndoor).to.be('boolean');
    expect(typeof outdoorPoi.position.isOutdoor).to.be('boolean');
  });
  it('Check cartesiansCoordinate of position property', () => {
    expect(typeof outdoorPoi.position.cartesianCoordinate.x).to.be('number');
    expect(typeof outdoorPoi.position.cartesianCoordinate.y).to.be('number');
  });
  it('Check coordinate of position property', () => {
    expect(typeof outdoorPoi.position.coordinate.latitude).to.be('number');
    expect(typeof outdoorPoi.position.coordinate.longitude).to.be('number');
  });
});
describe('Test method fetchEventsFromBuilding ->', () => {
  it('Check response', () => {
    expect(events).to.be.ok();
    expect(events instanceof Array).to.be(true);
  });
  it('Check event object', () => {
    expect(event = events[0]);
    expect(typeof event).to.be('object');
    expect(typeof event.buildingIdentifier).to.be('number');
    expect(typeof event.conversionArea).to.be('object');
    expect(typeof event.customFields).to.be('object');
    expect(typeof event.floorIdentifier).to.be('number');
    expect(typeof event.identifier).to.be('number');
    expect(typeof event.infoHtml).to.be('string');
    expect(typeof event.radius).to.be('number');
  });
  it('Check conversion area', () => {
    expect(typeof event.conversionArea.floorIdentifier).to.be('number');
    expect(typeof event.conversionArea.bottomLeft).to.be('string');
    expect(typeof event.conversionArea.bottomRight).to.be('string');
    expect(typeof event.conversionArea.topLeft).to.be('string');
    expect(typeof event.conversionArea.topRight).to.be('string');
  });
});
describe('Test method fetchPoiCategories ->', () => {
  it('Check POICategories', () => {
    expect(poiCategories).to.be.ok();
    expect(poiCategories instanceof Array).to.be(true);
  });
  it('Check POICategory object', () => {
    expect(poiCategory = poiCategories[0]);
    expect(typeof poiCategory).to.be('object');
    expect(typeof poiCategory.icon_selected).to.be('string');
    expect(typeof poiCategory.icon_unselected).to.be('string');
    expect(typeof poiCategory.poiCategoryCode).to.be('string');
    expect(typeof poiCategory.poiCategoryName).to.be('string');
    expect(typeof poiCategory.public).to.be('boolean');
  });
});
describe('Test method fetchMapFromFloor ->', () => {
  it('Check map object', () => {
    expect(map).to.be.ok();
    expect(typeof map.data).to.be('string');
  });
});
describe('Test fetchPoiCategoryIconNormal', () => {
  it('Check icon object', () => {
    expect(iconNormal).to.be.ok();
    expect(typeof iconNormal.data).to.be('string')
  });
});
describe('Has fetchPoiCategoryIconSelected method', () => {
  it('Chek icon object', () => {
    expect(iconSelected).to.be.ok();
    expect(typeof iconSelected.data).to.be('string');
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
