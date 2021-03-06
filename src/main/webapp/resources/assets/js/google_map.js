 function initialize(latitude, longitude) {
        
        var myLat = latitude,
            myLng = longitude,
            myCenter=new google.maps.LatLng(myLat,myLng),
            myPosition=new google.maps.LatLng(myLat, myLng);
    
var mapOptions = {
center: myCenter,
zoom: 6,
panControl: false,
navigationControl: false,
mapTypeControl: false,
scaleControl: false,
draggable: true,
scrollwheel: false,
zoomControl:true,
streetViewControl:false,
overviewMapControl:false,
rotateControl:false,
styles: [

{
                                    featureType: "landscape",
                                    elementType: "geometry",
                                    stylers: [
                                        {hue: "#ffffff"},
                                        {saturation: -100},
{lightness: 50},
{visibility: 'on'}
                                    ]
},
{
featureType: "water",
elementType: "geometry.fill",
stylers: [
                                            { hue: "#00d4ff" },
                                            { gamma: 2.38 },
                                            { saturation: -4 },
                                            { lightness: 26 }
]
},
{
                                        featureType: "landscape.man_made",
stylers: [
                                            { hue: "#00ffa2" },
                                            { lightness: 24 },
                                            { gamma: 3.84 }
]
},
{
featureType: "road.local",
stylers: [
                                            { gamma: 1.22 },
                                            { lightness: -7 },
                                            { hue: "#00ffff" },
                                            { saturation: 8 }
]
},
{
featureType: "poi",
stylers: [
                                            { hue: "#00ffbb" },
                                            { lightness: 6 }
                                        ]
},
{
featureType: "transit.station",
stylers: [
                                            { "hue": "#00ddff" },
                                            { "gamma": 0.92 },
                                            { "lightness": -4 },
                                            { "saturation": -27 }
]
},
{
featureType: "road.highway",
elementType: "geometry.fill",
stylers: [
                                            { hue: "#00ff91" },
                                            { lightness: 37 },
                                            { saturation: -67 },
                                            { gamma: 1.71 }
]
},
{
featureType: "landscape.natural",
stylers: [
                                            { hue: "#00ffa2" },
                                            { lightness: 62 },
                                            { gamma: 1.37 }
]
},
{
featureType: "road.arterial",
stylers: [
                                            { hue: "#00ffff" },
                                            { lightness: 32 },
                                            { saturation: -35 }
]
},
{
featureType: "road.highway"  
                                },
                                {
featureType: "road.arterial",
stylers: [
                                            { hue: "#00ff4d" }
]
},
{
featureType: "administrative.country",
elementType: "geometry.stroke",
stylers: [
                                            { hue: "#ff005d" }
]
},
                                {
featureType: "water",
elementType: "geometry.stroke",
stylers: [
                                            { hue: "#ff005d" }
]
},
                                {
featureType: "road.local"  
                                },
                                {
featureType: "road.highway",
elementType: "geometry.stroke",
stylers: [
                                            { hue: "#00ffbb" },
                                            { visibility: "on" },
                                            { saturation: -39 },
                                            { lightness: 28 }
]
                                }

]
};

        var map = new google.maps.Map(document.getElementById("map-canvas"),mapOptions);
        var marker=new google.maps.Marker({
			position:myCenter,
		});
        var marker=new google.maps.Marker({
			position:myPosition,
			animation:google.maps.Animation.DROP
		});

        marker.setMap(map);

        var infowindow = new google.maps.InfoWindow({
                content: "Your travel location"
        });

        google.maps.event.addListener(marker, 'click', function() {
                infowindow.open(map,marker);
        });
    }