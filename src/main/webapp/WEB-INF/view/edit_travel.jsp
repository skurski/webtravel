<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>

<div class="container col-md-12">
	<div class="col-md-12">
		<h2 class="text-muted">Detail information about your trip</h2>
	</div>

	<div class="col-md-8">
		<div id="map-canvas" style="height: 400px; width: 100%;"></div>
	</div>
	
	<div class="col-md-4">
		<form:form id="editTravelForm" modelAttribute="travel" method="post"
			action="edit-tour" class="form-signin form-horizontal">
			<div class="form-group">
				<label class="sr-only control-label" for="name">Title</label>
				<div class="col-md-12">
					<form:input path="name" id="name" class="form-control"
						type="text" autofocus="" required="" 
						placeholder="${!empty travel.name ? travel.name : 'Name'}" />
					<form:errors path="name" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="sr-only control-label" for="location">Location</label>
				<div class="col-md-12">
					<form:input path="location" id="location" class="form-control"
						type="text" autofocus="" required="" 
						placeholder="${!empty travel.location ? travel.location : 'Location'}" />
					<form:errors path="location" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="sr-only control-label" for="latitude">Latitude</label>
				<div class="col-md-12">
					<form:input path="latitude" id="latitude" class="form-control"
						type="text" autofocus="" required="" 
						placeholder="${!empty travel.latitude ? travel.latitude : 'Latitude example: 48.87146'}" />
					<form:errors path="latitude" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="sr-only control-label" for="longitude">Location</label>
				<div class="col-md-12">
					<form:input path="longitude" id="longitude" class="form-control"
						type="text" autofocus="" required="" 
						placeholder="${!empty travel.longitude ? travel.longitude : 'Longitude example: 48.87146'}" />
					<form:errors path="longitude" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="sr-only control-label" for="description">Description</label>
				<div class="col-md-12">
					<form:textarea path="description" id="description" class="form-control" rows="6" cols="10" 
					placeholder="${!empty travel.description ? travel.description : 'Description'}"/>
					<form:errors path="longitude" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<button class="btn btn-lg btn-primary btn-block" type="submit">Submit changes
						»</button>
				</div>
			</div>
		</form:form>
	</div>
</div>
	
<div class="container">
	<div class="col-md-8">
		<h2 class="text-muted">Your pictures</h2>
		<div id="carousel-example-generic2" class="carousel slide">
			<c:if test="${!empty gallerySet}">
			  <div class="carousel-inner">
					<c:forEach items="${gallerySet}" var="gallery">
						<div class="item active">
					      <img src="${!empty gallery.path ? gallery.path : 'http://placehold.it/1280x500'}" alt="">
					      <!-- Opis slajdu -->
					      <div class="carousel-caption">
					        <h3>${!empty gallery.title ? gallery.title : 'No details on image'}</h3>
					      </div>
					    </div>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</div>
	
	<div class="col-md-4">
		<form id="uploadFileForm" method="post" enctype="multipart/form-data"
			action="uploadFile" class="form-signin form-horizontal">
			<h2 class="text-muted">Upload picture</h2>

			<div class="col-md-12">
				<input name="file" class="" type="file" />
			</div>
			
			<br />

			<div class="form-group">
				<label class="sr-only control-label" for="name">Title</label>
				<div class="col-md-12">
					<input name="name" class="form-control" type="text" autofocus="" required="" placeholder="Name" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<button class="btn btn-lg btn-primary btn-block" type="submit">Add Picture
						»</button>
				</div>
			</div>
		</form>
	</div>
</div>


<div class="row"></div>



<%@include file="template/footer.jsp"%>


<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDC3f0xPLRA5meF2z7Ze3HCBWTjG-St1r4&sensor=false"></script>
<script>
    
$(document).ready(function() {
    function initialize() {
        
        var myLat = ${!empty travel.latitude ? travel.latitude : "48.87146"},
            myLng = ${!empty travel.longitude ? travel.longitude : "2.35500"},
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
    google.maps.event.addDomListener(window, 'load', initialize);
});
</script>