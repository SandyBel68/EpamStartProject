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
    <title>Rooms</title>
    <style>
        <%@include file="/css/bootstrap.min.css" %>
        body {background-color: lightblue;}
    </style>
</head>
<body class="p-3 mb-2 text-black">
<div>
    <h2 class="text-center">Available rooms </h2>

        <tr>
            <th>Room number</th>
        </tr>
    </br>

            <form action="room" method="post">
                <select name="roomNumber">
                    <c:forEach var ="allRooms" items="${allRooms}">
                    <option value='${allRooms.numberRoom}'>${allRooms.numberRoom}</option>
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