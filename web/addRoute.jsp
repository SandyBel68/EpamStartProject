<%--
  Created by IntelliJ IDEA.
  User: norri
  Date: 20.08.2019
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Route</title>
    <style>
        <%@include file="/css/bootstrap.min.css" %>
    </style>
</head>
<body class="p-3 mb-2 bg-info text-white">
<div>
    <h2 class="text-center">Chose a visitor to start the route </h2>

    <tr>
        <th>Visitor names</th>
    </tr>
    </br>

    <form action="/addRoute" method="post">
        <select name="visitorName">
            <c:forEach var="allVisitors" items="${allVisitors}">
                <option value='${allVisitors.visitorName}'>${allVisitors.visitorName}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Submit">
    </form>

    <form action="/" method="post">
        <input class="btn btn-success btn-lg btn-block" type="submit" value="Go back to start page"/>
    </form>
</div>
</body>
</html>