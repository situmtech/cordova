// import MapViewController from './map-view-controller.js';

class MapView extends HTMLElement {
  constructor() {
    super();
    console.log("base-domain: ", this.getAttribute("base-domain"));
    console.log("situm-api-key: ", this.getAttribute("situm-api-key"));
    console.log("building-id: ", this.getAttribute("building-id"));
    console.log(
      "whole_msg: ",
      `${this.getAttribute("base-domain")}/?apikey=${this.getAttribute(
        "situm-api-key"
      )}&buildingid=${this.getAttribute("building-id")}`
    );

    this.innerHTML = `\
        <iframe\
            id="map-view-iframe"\
            src="${this.getAttribute("base-domain")}/?apikey=${this.getAttribute("situm-api-key")}&buildingid=${this.getAttribute("building-id")}"\
            width="100%"\
            height="600px"\
        />\
        `;
  }

  connectedCallback() {
    // MapViewController.handleMapViewMessages();
    window.addEventListener("message", function (m) {
      console.log("message data: ", m.data);
      const msg = JSON.parse(m.data);
      console.log("msg.type: ", msg.type);
      // MapViewController.handleMapViewMessagesWithM(m);
      if (msg.type && msg.type == "app.map_is_ready") {
        this.document
          .getElementById("map-view-iframe")
          .contentWindow.postMessage(
            {
              type: "cartography.select_poi",
              payload: { identifier: 122321 },
            },
            "https://map-viewer.situm.com"
          );
        console.log("Sending poi selection");
      }
    });
  }
}

customElements.define("map-view", MapView);