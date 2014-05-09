<#-- @ftlvariable name="" type="com.testupstream.timetogo.views.ArrivalsView" -->
<html>
<head>
    <title>Local bus departures</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/time-to-go.css">
</head>
<body>
<#list arrivalGroups as group>
        <h4>Route: ${group.getRoute()} to ${group.getDestination()} (${group.getStop()})</h4>
        <table class="table table-striped">
            <tr><th>ETA</th>
            <#list getEtas(group) as eta>
            <tr><td>${eta.getEta()}</td></tr>
            </#list>
        </table>
</#list>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>