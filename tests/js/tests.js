const expect = require("expect.js");
const buildings = require('./resources/fetchBuildings.json');
const floors = require('./resources/fetchFloorsFromBuilding.json');
const indoorPois = require('./resources/fetchIndoorPOIsFromBuilding.json');
const outdoorPois = require('./resources/fetchOutdoorPOIsFromBuilding.json');
const events = require('./resources/fetchEventsFromBuilding.json');
const poiCategories = require('./resources/fetchPoiCategories.json');
const map = require('./resources/fetchMapFromFloor.json');
const iconNormal = require('./resources/fetchPoiCategoryIconNormal.json');
const iconSelected = require('./resources/fetchPoiCategoryIconSelected.json');
const route = require('./resources/requestDirections.json');
let building, floor, indoorPoi, outdoorPoi, event, poiCategory, edge, indication, node, point, step;

describe('Test fetchBuildings -> ', () => {
  it('Check reutrned value', () => {
    expect(buildings).to.be.ok();
    expect(buildings instanceof Array).to.be(true);
  });
  it('Check building', () => {
    expect(building = buildings[0]);
    expect(typeof building).to.be('object');
    expect(typeof building.address).to.be('string');
    expect(typeof building.bounds).to.be('object');
    expect(typeof building.boundsRotated).to.be('object');
    expect(typeof building.center).to.be('object');
    expect(typeof building.dimensions).to.be('object');
    expect(typeof building.infoHtml).to.be('string');
    expect(typeof building.name).to.be('string');
    expect(typeof building.pictureThumbUrl).to.be('string');
    expect(typeof building.pictureUrl).to.be('string');
    expect(typeof building.rotation).to.be('number');
    expect(typeof building.userIdentifier).to.be('string');
    expect(typeof building.buildingIdentifier).to.be('string');
    expect(typeof building.customFields).to.be('object');
  });
  it('Check building bounds', () => {
    testBounds(building.bounds);
  });
  it('Check building boundsRotated', () => {
    testBounds(building.boundsRotated);
  });
  it('Check building center', () => {
    testCoordinate(building.center);
  });
  it('Check building dimensions', () => {
    testDimension(building.dimensions);
  });
});

describe('Test fetchFloorsFromBuilding ->', () => {
  it('Check returned value', () => {
    expect(floors).to.be.ok();
    expect(floors instanceof Array).to.be(true);
  });
  it('Check floor object', () => {
    expect(floor = floors[0]);
    expect(typeof floor).to.be('object');
    expect(typeof floor.altitude).to.be('number');
    expect(typeof floor.buildingIdentifier).to.be('string');
    expect(typeof floor.level).to.be('number');
    expect(typeof floor.mapUrl).to.be('string');
    expect(typeof floor.scale).to.be('number');
    expect(typeof floor.floorIdentifier).to.be('string');
  });
});

describe('Test fetchIndoorPOIsFromBuilding ->', () => {
  it('Check returned value', () => {
    expect(indoorPois).to.be.ok();
    expect(indoorPois instanceof Array).to.be(true);
  });
  it('Check indoorPOI object', () => {
    expect(indoorPoi = indoorPois[0]);
    expect(typeof indoorPoi).to.be('object');
    expect(typeof indoorPoi.identifier).to.be('string');
    expect(typeof indoorPoi.buildingIdentifier).to.be('string');
    expect(typeof indoorPoi.cartesianCoordinate).to.be('object');
    expect(typeof indoorPoi.coordinate).to.be('object');
    expect(typeof indoorPoi.floorIdentifier).to.be('string');
    expect(typeof indoorPoi.poiName).to.be('string');
    expect(typeof indoorPoi.position).to.be('object');
    expect(typeof indoorPoi.isIndoor).to.be('boolean');
    expect(typeof indoorPoi.isOutdoor).to.be('boolean');
    expect(typeof indoorPoi.category).to.be('string');
    expect(typeof indoorPoi.infoHtml).to.be('string');
    expect(typeof indoorPoi.customFields).to.be('object');
  });
  it('Check indoorPOI cartesiansCoordinate', () => {
    testCartesianCoordinate(indoorPoi.cartesianCoordinate);
  });
  it('Check indoorPOI coordinate', () => {
    testCoordinate(indoorPoi.coordinate);
  });
  it('Check indoorPOI position', () => {
    testPoint(indoorPoi.position)
  });
});
describe('Test fetchOutdoorPOIsFromBuilding ->', () => {
  it('Check returned value', () => {
    expect(outdoorPois).to.be.ok();
    expect(outdoorPois instanceof Array).to.be(true);
  });
  it('Check outdoorPOI object', () => {
    expect(outdoorPoi = outdoorPois[0]);
    expect(typeof outdoorPoi).to.be('object');
    expect(typeof outdoorPoi.identifier).to.be('string');
    expect(typeof outdoorPoi.buildingIdentifier).to.be('string');
    expect(typeof outdoorPoi.cartesianCoordinate).to.be('object');
    expect(typeof outdoorPoi.coordinate).to.be('object');
    expect(typeof outdoorPoi.floorIdentifier).to.be('string');
    expect(typeof outdoorPoi.poiName).to.be('string');
    expect(typeof outdoorPoi.position).to.be('object');
    expect(typeof outdoorPoi.isIndoor).to.be('boolean');
    expect(typeof outdoorPoi.isOutdoor).to.be('boolean');
    expect(typeof outdoorPoi.category).to.be('string');
    expect(typeof outdoorPoi.infoHtml).to.be('string');
    expect(typeof outdoorPoi.customFields).to.be('object');
  });
  it('Check outdoorPOI cartesiansCoordinate', () => {
    testCartesianCoordinate(outdoorPoi.cartesianCoordinate);
  });
  it('Check outdoorPOI coordinate', () => {
    testCoordinate(outdoorPoi.coordinate);
  });
  it('Check outdoorPOI position', () => {
    testPoint(outdoorPoi.position);
  });
});
describe('Test fetchEventsFromBuilding ->', () => {
  it('Check response', () => {
    expect(events).to.be.ok();
    expect(events instanceof Array).to.be(true);
  });
  it('Check event object', () => {
    expect(event = events[0]);
    expect(typeof event).to.be('object');
    expect(typeof event.buildingIdentifier).to.be('number');
    expect(typeof event.identifier).to.be('number');
    expect(typeof event.floorIdentifier).to.be('number');
    expect(typeof event.infoHtml).to.be('string');
    expect(typeof event.conversionArea).to.be('object');
    expect(typeof event.customFields).to.be('object');
    expect(typeof event.radius).to.be('number');
  });
  it('Check event conversionArea', () => {
    testConversionArea(event.conversionArea);
  });
});
describe('Test fetchPoiCategories ->', () => {
  it('Check POICategories', () => {
    expect(poiCategories).to.be.ok();
    expect(poiCategories instanceof Array).to.be(true);
  });
  it('Check POICategory object', () => {
    expect(poiCategory = poiCategories[0]);
    expect(typeof poiCategory).to.be('object');
    expect(typeof poiCategory.poiCategoryCode).to.be('string');
    expect(typeof poiCategory.poiCategoryName).to.be('string');
    expect(typeof poiCategory.icon_selected).to.be('string');
    expect(typeof poiCategory.icon_unselected).to.be('string');
    expect(typeof poiCategory.public).to.be('boolean');
  });
});
describe('Test fetchMapFromFloor ->', () => {
  it('Check map object', () => {
    expect(map).to.be.ok();
    expect(typeof map.data).to.be('string');
  });
});
describe('Test fetchPoiCategoryIconNormal ->', () => {
  it('Check icon object', () => {
    expect(iconNormal).to.be.ok();
    expect(typeof iconNormal.data).to.be('string')
  });
});
describe('Test fetchPoiCategoryIconSelected ->', () => {
  it('Chek icon object', () => {
    expect(iconSelected).to.be.ok();
    expect(typeof iconSelected.data).to.be('string');
  });
});
describe('Test requestDirections ->', () => {
  it('Check route object', () => {
    expect(route).to.be.ok();
    expect(typeof route).to.be('object');
    expect(route.edges instanceof Array).to.be(true);
    expect(typeof route.firstStep).to.be('object');
    expect(typeof route.from).to.be('object');
    expect(route.indications instanceof Array).to.be(true);
    expect(typeof route.lastStep).to.be('object');
    expect(route.nodes instanceof Array).to.be(true);
    expect(route.points instanceof Array).to.be(true);
    expect(typeof route.TO).to.be('object');
    expect(route.steps instanceof Array).to.be(true);
  });
  it('Check edge route', () => {
    expect(edge = route.edges[0]);
    expect(typeof edge).to.be('object');
    testRouteStep(edge);
  });
  it('Check firstStep route', () => {
    testRouteStep(route.firstStep);
  });
  it('Check from route', () => {
    testPoint(route.from);
  });
  it('Check indication route', () => {
    expect(indication = route.indications[0]);
    expect(typeof indication).to.be('object');
    testIndication(indication);
  });
  it('Check lastStep route', () => {
    testRouteStep(route.lastStep);
  });
  it('Check node route', () => {
    expect(node = route.nodes[0]);
    expect(typeof node).to.be('object');
    testPoint(node);
  });
  it('Check point route', () => {
    expect(point = route.points[0]);
    expect(typeof point).to.be('object');
    testPoint(point);
  });
  it('Check TO route', () => {
    testPoint(route.TO);
  });
  it('Check step route', () => {
    expect(step = route.steps[0]);
    expect(typeof step).to.be('object');
    testRouteStep(step);
  });
});

const testBounds = bounds => {
  expect(typeof bounds.northEast).to.be('object');
  expect(typeof bounds.northWest).to.be('object');
  expect(typeof bounds.southEast).to.be('object');
  expect(typeof bounds.southWest).to.be('object');
  testCoordinate(bounds.northEast);
  testCoordinate(bounds.northWest);
  testCoordinate(bounds.southEast);
  testCoordinate(bounds.southWest);
}

const testCoordinate = coordinate => {
  expect(typeof coordinate.latitude).to.be('number');
  expect(typeof coordinate.longitude).to.be('number');
}

const testDimension = dimension => {
  expect(typeof dimension.width).to.be('number');
  expect(typeof dimension.height).to.be('number');
}

const testCartesianCoordinate = cartesianCoordinate => {
  expect(typeof cartesianCoordinate.x).to.be('number');
  expect(typeof cartesianCoordinate.y).to.be('number');
}

const testPoint = point => {
  expect(typeof point.buildingIdentifier).to.be('string');
  expect(typeof point.cartesianCoordinate).to.be('object');
  expect(typeof point.coordinate).to.be('object');
  expect(typeof point.floorIdentifier).to.be('string');
  expect(typeof point.isIndoor).to.be('boolean');
  expect(typeof point.isOutdoor).to.be('boolean');
  testCartesianCoordinate(point.cartesianCoordinate);
  testCoordinate(point.coordinate);
}

const testConversionArea = conversionArea => {
  expect(typeof event.conversionArea.floorIdentifier).to.be('number');
  expect(typeof event.conversionArea.topLeft).to.be('string');
  expect(typeof event.conversionArea.topRight).to.be('string');
  expect(typeof event.conversionArea.bottomLeft).to.be('string');
  expect(typeof event.conversionArea.bottomRight).to.be('string');
}

const testRouteStep = routeStep => {
  expect(typeof routeStep.distance).to.be('number');
  expect(typeof routeStep.distanceToGoal).to.be('number');
  expect(typeof routeStep.from).to.be('object');
  expect(typeof routeStep.id).to.be('number');
  expect(typeof routeStep.TO).to.be('object');
  expect(typeof routeStep.isFirst).to.be('boolean');
  expect(typeof routeStep.isLast).to.be('boolean');
  testPoint(routeStep.from);
  testPoint(routeStep.TO);
}

const testIndication = indication => {
  expect(typeof indication.distance).to.be('number');
  expect(typeof indication.distanceToNextLevel).to.be('number');
  expect(typeof indication.indicationType).to.be('string');
  expect(typeof indication.orientation).to.be('number');
  expect(typeof indication.orientationType).to.be('string');
  expect(typeof indication.stepIdxDestination).to.be('number');
  expect(typeof indication.stepIdxOrigin).to.be('number');
  expect(typeof indication.neededLevelChange).to.be('boolean');
  expect(typeof indication.humanReadableMessage).to.be('string');
}