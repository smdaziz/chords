<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			#form-center {
				position: absolute;
				left: 5%;
				top: 7%;
				right: 5%;
			}
			#table-center {
				position: absolute;
				left: 5%;
				/*top: 15%;*/
				top: 75%;
				right: 5%;
			}
			#map {
				height: 50%;
				width: 50%;
			}
			.map-center {
				border: solid 1px black;
				position: absolute;
				left: 50%;
				/*top: 75%;*/
				top: 50%;
				background-color: white;
				z-index: 100;
	
				height: 400px;
				margin-top: -200px;
	
				width: 600px;
				margin-left: -300px;
			}
			#customerSource {
				width: 500px;
				height: 25px;
			}
			#customerDestination {
				width: 500px;
				height: 25px;
			}
			.topcorner {
				position: absolute;
				top:0;
				right:0;
			}
			.topcenter {
				position: relative;
				margin: 0 auto;
				clear: left;
				height: auto;
				z-index: 0;
				text-align: center;
			}
		</style>
	</head>
	<body>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script src="https://maps.googleapis.com/maps/api/js?sensor=false&libraries=places,geometry"></script>
		<script src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/routeboxer/src/RouteBoxer.js"></script>
		<script>
			var sourceLat, sourceLng;
			var destinationLat, destinationLng;
			var rideBy;

			var directionsDisplay = new google.maps.DirectionsRenderer();
			var customerDirectionsDisplay = new google.maps.DirectionsRenderer({
				polylineOptions: {
					strokeColor: "red"
				}
			});
			var directionsService = new google.maps.DirectionsService();
			var map;
			var routeBoxer = new RouteBoxer();
			var distance = 0.01;
			var cascadiaFault;
			var routeBounds = [];
			var map;
			var mapOptions = {
				center: new google.maps.LatLng(37.7831,-122.4039),
				zoom: 12,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};

			$(document).ready(function() {
				$('tbody').on(
						'change',
						':radio',
						function() {
							var $row = $(this).parent().parent();
							var valueOfRadio = $('input[name=rideOption]:checked')
									.val();
							var idOfRow = $row.attr('id');
							$('#checkvalue').html(
									$('input[name=rideOption]:checked').val());
//		                    alert($('input[name=rideOption]:checked').val());
//							alert(idOfRow);
							rideBy = document.getElementById("rideTable").rows[idOfRow].cells[1].innerHTML;
							sourceLat = document.getElementById("rideTable").rows[idOfRow].cells[8].innerHTML;
							sourceLng = document.getElementById("rideTable").rows[idOfRow].cells[9].innerHTML;
							destinationLat = document.getElementById("rideTable").rows[idOfRow].cells[10].innerHTML;
							destinationLng = document.getElementById("rideTable").rows[idOfRow].cells[11].innerHTML;
//							alert(document.getElementById("rideTable").rows[idOfRow].cells[8].innerHTML + ", " + document.getElementById("rideTable").rows[idOfRow].cells[9].innerHTML);

//							map = new google.maps.Map(document.getElementById('map'), mapOptions);
		
							directionsDisplay.setMap(map);
		
						    var start = new google.maps.LatLng(sourceLat, sourceLng);
							var end = new google.maps.LatLng(destinationLat, destinationLng);
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
			});
			
			function initialize() {
				map = new google.maps.Map(document.getElementById('map'), mapOptions);
				directionsDisplay.setMap(map);
				customerDirectionsDisplay.setMap(map);

				var customerSource = new google.maps.places.Autocomplete(document.getElementById('customerSource'));
				var infoWindow = new google.maps.InfoWindow();
				var customerSourceMarker = new google.maps.Marker({
				  map: map
				});

				google.maps.event.addListener(customerSource, 'place_changed', function() {
				  infoWindow.close();
				  var place = customerSource.getPlace();
				  customerSourceMarker.setPosition(place.geometry.location);
				  customerSourceLat = customerSourceMarker.getPosition().lat();
				  customerSourceLng = customerSourceMarker.getPosition().lng();
				  infoWindow.setContent('<div><strong>' + place.name + '</strong><br>');
				});
				
				var customerDestination = new google.maps.places.Autocomplete(document.getElementById('customerDestination'));
				var infoWindow = new google.maps.InfoWindow();
				var customerDestinationMarker = new google.maps.Marker({
				  map: map
				});

				google.maps.event.addListener(customerDestination, 'place_changed', function() {
				  infoWindow.close();
				  var place = customerDestination.getPlace();
				  customerDestinationMarker.setPosition(place.geometry.location);
				  customerDestinationLat = customerDestinationMarker.getPosition().lat();
				  customerDestinationLng = customerDestinationMarker.getPosition().lng();
				  infoWindow.setContent('<div><strong>' + place.name + '</strong><br>');
				
					//Same event, draw route
				    var start = new google.maps.LatLng(customerSourceLat, customerSourceLng);
					var end = new google.maps.LatLng(customerDestinationLat, customerDestinationLng);
					var request = {
						origin: start,
						destination: end,
						travelMode: google.maps.TravelMode.DRIVING
					};
					directionsService.route(request, function(response, status) {
						if (status == google.maps.DirectionsStatus.OK) {
							customerDirectionsDisplay.setDirections(response);
							customerDirectionsDisplay.setMap(map);
						} else {
							alert("Directions Request from " + start.toUrlValue(6) + " to " + end.toUrlValue(6) + " failed: " + status);
						}
					});
				});
				
				google.maps.event.addDomListener(document.getElementById('confirm'), 'click', function searchLocation() {
				  var sourceInRoute = false, destinationInRoute = false;
				  for (var i = 0; i < routeBounds.length; i++) {
					//alert(routeBounds[i].getCenter().lat().toFixed(4)+" "+routeBounds[i].getCenter().lng().toFixed(4));
					if((routeBounds[i].getCenter().lat().toFixed(4)-customerSource.getPlace().geometry.location.lat().toFixed(4) < 0.001 &&
						routeBounds[i].getCenter().lng().toFixed(4)-customerSource.getPlace().geometry.location.lng().toFixed(4) < 0.001)/* ||
						(routeBounds[i].getNorthEast().lat().toFixed(4)-customerSource.getPlace().geometry.location.lat().toFixed(4) < 0.001 &&
						routeBounds[i].getNorthEast().lng().toFixed(4)-customerSource.getPlace().geometry.location.lng().toFixed(4) < 0.001) || (routeBounds[i].getSouthWest().lat().toFixed(4)-customerSource.getPlace().geometry.location.lat().toFixed(4) < 0.001 &&
						routeBounds[i].getSouthWest().lng().toFixed(4)-customerSource.getPlace().geometry.location.lng().toFixed(4) < 0.001)*/) {
							sourceInRoute = true;
							break;
						}
					/*else
						alert(routeBounds[i].getCenter().lat().toFixed(4)+" "+routeBounds[i].getCenter().lng().toFixed(4)+" "+customerSource.getPlace().geometry.location.lat().toFixed(4)+" "+customerSource.getPlace().geometry.location.lng().toFixed(4));*/
				  }
				  if(sourceInRoute) {
					for(var i = 0; i < routeBounds.length; i++) {
					//alert(routeBounds[i].getCenter().lat().toFixed(4)+" "+routeBounds[i].getCenter().lng().toFixed(4));
					if((routeBounds[i].getCenter().lat().toFixed(4)-customerDestination.getPlace().geometry.location.lat().toFixed(4) < 0.001 &&
						routeBounds[i].getCenter().lng().toFixed(4)-customerDestination.getPlace().geometry.location.lng().toFixed(4) < 0.001)/* ||
						(routeBounds[i].getNorthEast().lat().toFixed(4)-customerDestination.getPlace().geometry.location.lat().toFixed(4) < 0.001 &&
						routeBounds[i].getNorthEast().lng().toFixed(4)-customerDestination.getPlace().geometry.location.lng().toFixed(4) < 0.001) || (routeBounds[i].getSouthWest().lat().toFixed(4)-customerDestination.getPlace().geometry.location.lat().toFixed(4) < 0.001 &&
						routeBounds[i].getSouthWest().lng().toFixed(4)-customerDestination.getPlace().geometry.location.lng().toFixed(4) < 0.001)*/) {
							destinationInRoute = true;
							break;
						}
					}
				  }
				  if(sourceInRoute && destinationInRoute) {
					  //alert("Ride Possible..!!");
					  document.getElementById("ridePossible").value = "true";
					  document.getElementById("rideBy").value = rideBy;
				  } else {
					  document.getElementById("ridePossible").value = "false";
				  }
				  /*alert(routeBounds);
				  alert(customerSource.getPlace().geometry.location);*/
				});

			}

			google.maps.event.addDomListener(window, "load", initialize);

		</script>
		<div class="topcenter">${message}</div>
		<div class="topcorner"><a href="ride.jsp">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Welcome&nbsp;&nbsp;&nbsp;${userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">logout</a>&nbsp;&nbsp;&nbsp;</div>
		<div id="form-center">
			<form method="POST" action="requestRide">
				<table>
					<tr>
						<td>From&nbsp;<input type="text" id="customerSource"></td>
						<td>To&nbsp;<input type="text" id="customerDestination"></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" id="confirm" value="Request Ride"/></td>
					</tr>
				</table>
				<input type="hidden" id="ridePossible" name="ridePossible"/>
				<input type="hidden" id="rideBy" name="rideBy"/>
			</form>
		</div>
		<div id="table-center">
			<table id="rideTable" border="1">
				<thead>
					<tr>
						<th>Select</th>
						<th>Driver Name</th>
						<th>From</th>
						<th>To</th>
						<th>Start Time</th>
						<th>Passengers</th>
						<th>Mobile</th>
						<th>Email</th>
						<th style="display:none;">From Lat</th>
						<th style="display:none;">From Lng</th>
						<th style="display:none;">To Lat</th>
						<th style="display:none;">To Lng</th>
					</tr>
				</thead>
				<% int i = 1; %>
				<tbody>
					<c:forEach items="${availableRides}" var="availableRide">
						<tr id="<%=i++ %>">
							<td><input type="radio" name="rideOption" id="rideOption"/></td>
							<td>${availableRide.driverName}</td>
							<td>${availableRide.source}</td>
							<td>${availableRide.dest}</td>
							<td>${availableRide.startTime}</td>
							<td>${availableRide.passengers}</td>
							<td>${availableRide.mobile}</td>
							<td>${availableRide.email}</td>
							<td style="display:none;">${availableRide.srcLat}</td>
							<td style="display:none;">${availableRide.srcLng}</td>
							<td style="display:none;">${availableRide.destLat}</td>
							<td style="display:none;">${availableRide.destLng}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="map" class="map-center"></div>
	</body>
</html>