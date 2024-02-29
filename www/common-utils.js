module.exports = {
  /**
   * Takes the different input types from requestLocationUpdates (and startPositioning) and standarizes the response.
   * This function aims to maintain compatibility with older versions of this plugin.
   * @param {any} input 
   * @returns A single item array containing the LocationRequest, ready to be sent to native platforms.
   */
  standarizeRequest: function(input) {
    let response;
    if (!input) {
      // If it is null or undefined, pass an empty location request.
      response = {};
    } else if (Array.isArray(input) && input.length > 0) {
      // If it is an array:
      if (input.every(item => typeof item === 'object')){
        // It may contain objects (legacy API: Building + LocationOptions).
        // Then merge all of them into a single object.
        response = Object.assign({}, ...input);
      } else if (typeof input[0] === 'string' || typeof input[0] === 'number') {
        // It may contain a building identifier + a set of LocationOptions.
        // Assign the building identifier and merge.
        input[0] = {buildingIdentifier: input[0]};
        response = Object.assign({}, ...input);
      }
    } else if (typeof input === 'string' || typeof input === 'number') {
      // The input itself may be a single building identifier.
      response = {buildingIdentifier: input};
    } else {
      // If the input is an object (or any other case), return the object itself. Delegate the
      // error checks to the platform's mapper.
      response = input;
    }
    // Cordova platforms wait for a JSONArray:
    return [response];
  }
}