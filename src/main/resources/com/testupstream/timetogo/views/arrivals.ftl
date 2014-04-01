<#-- @ftlvariable name="" type="com.testupstream.timetogo.views.ArrivalsView" -->
<html>
<body>
<table>
    <tr><th>ETA</th><th>Stop Name</th><th>Route</th></tr>
    <#list arrivals as arrival>
    <tr><td>${arrival.eta}</td><td>${arrival.stopName}</td><td>${arrival.route}</td></tr>
    </#list>
</table>
</body>
</html>