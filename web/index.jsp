<%--
  Created by IntelliJ IDEA.
  User: norri
  Date: 20.08.2019
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Location app</title>
    <style>
        <%@include file="/css/bootstrap.min.css" %>
    </style>
</head>
<body class="p-3 mb-2 bg-info text-white">
<%---------------------------content--%>
<div class="container">
    <div>
            <div class="page-header">
                <h1>
                    Glad to see you in our location app!
                </h1>
            </div>
        <p>
            Choose the option from the list below.
        </p>
            <form action="/addVisitor" method="get">
                <input class="btn btn-primary btn-lg btn-block" type="submit" value="Create Visitor"/>
            </form>
            <form action="/visitor" method="get">
                <input class="btn btn-success btn-lg btn-block" type="submit" value="Get report by visitor"/>
            </form>
            <form action="/room" method="get">
                <input class="btn btn-warning btn-lg btn-block" type="submit" value="Get report by room"/>
            </form>
    </div>
</div>
</body>
</html>
