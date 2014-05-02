<#-- @ftlvariable name="" type="com.testupstream.timetogo.views.ArrivalsView" -->
<html>
<head>
    <title>Local bus departures</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/time-to-go.css">
</head>
<body>
<table class="table table-striped">
    <tr><th>ETA</th><th>Stop Name</th><th>Route</th><th>Destination</th></tr>
    <#list arrivals as arrival>
    <tr><td>${arrival.eta}</td><td>${arrival.stopName}</td><td>${arrival.route}</td><td>${arrival.destination}</td></tr>
    </#list>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>