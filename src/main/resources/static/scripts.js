const mymap = L.map('mapid').setView([-16.990307933934773, -50.87270736694335], 10);
const request = new XMLHttpRequest();

const polys = [];

function loadMap() {

    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        maxZoom: 12,
        id: 'mapbox.streets',
        accessToken: 'API_KEY'
    }).addTo(mymap);
}

function getJson(url, callback) {

    request.open('GET', url, true);

    request.onload = function() {
    if (request.status >= 200 && request.status < 400) {
        callback(JSON.parse(request.responseText));
    } else {
        console.error("Connection error: " + request.status)
    }
    };

    request.onerror = function() {
        console.error("Error connecting to the API")
    };

    request.send();
}

function drawPolygon(json) {

    for(let i = 0; i < json.length; i++){
        const polygonObj = L.geoJson(json[i].geoJSON).addTo(mymap);
        polys.push( { // creating a object for easy distinction and info gathering
            quality: 0, // Soil quality analysis
            bugs: 0, // Pest Counting
            geoJSON: json[i].geoJSON, // GeoJSON for analysis point testing + area sum
            polygon: polygonObj // leaflet object for map functions
        } )
    }
}

function drawPoints(json) {

    for (let i = 0; i < json.length; i++) {
        isMarkerInsidePolygon(json[i].geoJSON, json[i]); 
    }
}

// this function will test in which polygon the point is
// will add to that polygon object what service that marker was pointing as so it can be counted later
function isMarkerInsidePolygon(marker, json) {

    for(let p = 0; p < polys.length; p++){
        var poly = polys[p].geoJSON;

        if (turf.booleanWithin(marker, poly)) {
            if(json.id === "1"){
                polys[p].quality += 1;
            }else{
                polys[p].bugs += 1;
            }
            break;
        }
    }

};

function calculatePrice(){

    for(let p = 0; p < polys.length; p++) {
        var sum = 0;

        // calculating the service fee discounts
        var qualityMultiplyer;
        if (polys[p].quality > 10){
            qualityMultiplyer = 249.90;
        }else{
            qualityMultiplyer = 279.90;
        }
        var bugMultiplier
        if (polys[p].bugs > 5){
            bugMultiplier = 379.90;
        }else{
            bugMultiplier = 399.90;
        }
        sum += (polys[p].quality * qualityMultiplyer) + (polys[p].bugs * bugMultiplier); // service fees price

        var areaHa = (turf.area(polys[p].geoJSON) / 10000) // area sum (turf.area returns m^2) 1ha == 10.000 m^2

        // calculating per area fee/discount
        var areaMultiplier;
        var size; // getting the "size" for displaying in the map
        console.log(areaHa + "," + (areaHa < 1000));
        if (areaHa < 50) {
            areaMultiplier = 1.25;
            size = "Fazenda Pequena";
        } else if(areaHa < 1000) {
            areaMultiplier = 1.15;
            size = "Fazenda Media";
        }else{
            areaMultiplier = 1.02;
            size = "Fazenda Grande";
        }
        sum += (areaHa * areaMultiplier); // per ha fee

        polys[p].polygon.bindPopup(`${size}<br/>R$${sum.toFixed(2)}`); // uses html syntax
        // return example: 
        // Fazenda Grande
        // R$ 7362.73
    }
}

loadMap();

// this call was done this way because of the "ajax" function's await/async system
getJson('/fazendas', (res) => { // gets the farms json, loading the polygons
    drawPolygon(res);

    this.getJson('/servicos', (res) => { // then it calls the services json for loading the points
        drawPoints(res);

        calculatePrice(); // then it calculates the price for each farm and puts it on a price tag
    })
})