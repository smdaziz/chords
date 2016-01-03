<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<style>
			html,body {
				height: 100%;
				margin: 0;
				background-color: #F3F3F3;
			}
			#map {
				height: 50%;
				width: 50%;
				left: 25%;
				top: 45%;
				border: solid 1px black;
			}
			#table-center {
				position: absolute;
				left: 30%;
				top: 20%;
			}
			#from {
				width: 500px;
			}
			#to {
				width: 500px;
			}
			#passengers {
				width: 30px;
			}
			.topcorner{
				position:absolute;
				top:0;
				right:0;
			}
		</style>
	</head>
	<body>
		<script src="https://maps.googleapis.com/maps/api/js?sensor=false&libraries=places,geometry"></script>
		<script src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/routeboxer/src/RouteBoxer.js"></script>
	    <script>
			var fromLat, fromLng;
			var toLat, toLng;
			function initialize() {
	
				var directionsDisplay = new google.maps.DirectionsRenderer();
				var directionsService = new google.maps.DirectionsService();
				var map;
				var routeBoxer = new RouteBoxer();
				var distance = 0.01;
				var cascadiaFault;
				var routeBounds = [];
	
				var mapOptions = {
					center: new google.maps.LatLng(37.7831,-122.4039),
					zoom: 12,
					mapTypeId: google.maps.MapTypeId.ROADMAP
				};
	
				var map = new google.maps.Map(document.getElementById('map'), mapOptions);
	
				directionsDisplay.setMap(map);
	
				var acOptions = {
				  types: ['address']
				};
				var from = new google.maps.places.Autocomplete(document.getElementById('from'));
				var infoWindow = new google.maps.InfoWindow();
				var marker = new google.maps.Marker({
				  map: map
				});
	
				google.maps.event.addListener(from, 'place_changed', function() {
				  infoWindow.close();
				  var place = from.getPlace();
				  marker.setPosition(place.geometry.location);
				  fromLat = marker.getPosition().lat();
				  fromLng = marker.getPosition().lng();
				  document.getElementById("fromLat").value = fromLat;
				  document.getElementById("fromLng").value = fromLng;
				  infoWindow.setContent('<div><strong>' + place.name + '</strong><br>');
				});
	
				var to = new google.maps.places.Autocomplete(document.getElementById('to'));
				var infoWindow = new google.maps.InfoWindow();
				var marker = new google.maps.Marker({
				  map: map
				});
	
				google.maps.event.addListener(to, 'place_changed', function() {
				  infoWindow.close();
				  var place = to.getPlace();
				  marker.setPosition(place.geometry.location);
				  toLat = marker.getPosition().lat();
				  toLng = marker.getPosition().lng();
				  document.getElementById("toLat").value = toLat;
				  document.getElementById("toLng").value = toLng;
				  infoWindow.setContent('<div><strong>' + place.name + '</strong><br>');
	
					//Same event, draw route
				    var start = new google.maps.LatLng(fromLat, fromLng);
					var end = new google.maps.LatLng(toLat, toLng);
					var request = {
						origin: start,
						destination: end,
						travelMode: google.maps.TravelMode.DRIVING
					};
					directionsService.route(request, function(response, status) {
						if (status == google.maps.DirectionsStatus.OK) {
							directionsDisplay.setDirections(response);
							directionsDisplay.setMap(map);
							
							// Box around the overview path of the first route
						    var path = response.routes[0].overview_path;
						    var boxes = routeBoxer.box(path, distance);
							var pathsTemp = [];
							for (var i = 0; i < boxes.length; i++) {
								var bounds = boxes[i];
								// Perform search over this bounds
								pathsTemp.push(bounds.getCenter());
								routeBounds.push(bounds);
							}
							var temp = {}
							cascadiaFault = new google.maps.Polyline({
								paths: pathsTemp
							 });
							//alert(pathsTemp);
							//alert(cascadiaFault.getPath());
						} else {
							alert("Directions Request from " + start.toUrlValue(6) + " to " + end.toUrlValue(6) + " failed: " + status);
						}
					});
				});
	
			}
			
			google.maps.event.addDomListener(window, "load", initialize);
	
	    </script>
		<div class="topcorner"><a href="ride.jsp">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Welcome&nbsp;&nbsp;&nbsp;${userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">logout</a>&nbsp;&nbsp;&nbsp;</div>
		<div id="table-center">
			<form method="POST" action="selfride">
				<table>
					<tr colspan="2">
						<td rowspan="2">Time</td>
						<td>&nbsp;HH&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MM</td>
					</tr>
					<tr>
						<td>
							<select name="hh">
								<option value="0">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
							</select>
							<select name="mm">
								<option value="0">0</option>
								<option value="15">15</option>
								<option value="30">30</option>
								<option value="45">45</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>From</td>
						<td><input type="text" id="from" name="from"></td>
					</tr>
					<tr>
						<td>To</td>
						<td><input type="text" id="to" name="to"></td>
					</tr>
					<tr>
						<td>Passengers</td>
						<td><input type="text" id="passengers" name="passengers"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" id="confirm" value="Confirm" /></td>
					</tr>
				</table>
				<input type="hidden" id="fromLat" name="fromLat"/>
				<input type="hidden" id="fromLng" name="fromLng"/>
				<input type="hidden" id="toLat" name="toLat"/>
				<input type="hidden" id="toLng" name="toLng"/>
			</form>
		</div>
		<div id="map"></div>
	</body>
</html>