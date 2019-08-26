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
        body {
            background-color: lightblue;
            text: black;
        }
    </style>
</head>
<body class="p-3 mb-2">
<div>
    <h2 class="text-center">New route was added! </h2>
    <h3 class="text-center">Now get back to the start page to check the reports...</h3>
    <p style="text-align:center;"><img src="/img/b1_f1_raw.jpg" alt="map"></p>
    <form action="/" method="post">
        <input class="btn btn-success btn-lg btn-block" type="submit" value="Go back to start page"/>
    </form>
</div>
</body>
</html>