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
    <title>Visitors</title>
    <style>
        <%@include file="/css/bootstrap.min.css" %>
    </style>
</head>
<body class="p-3 mb-2 bg-info text-white">
<div>
    <h2 class="text-center">Available visitors </h2>

    <tr>
        <th>Visitor name</th>
    </tr>
    </br>

    <form action="addVisitor" method="get">
        <table class="table table-striped">
            <tr>
                <th>Name</th>
            </tr>
            <c:forEach var="allVisitors" items="${allVisitors}">
                <tr>
                    <td><c:out value='${allVisitors.visitorName}'/></td>
                </tr>
            </c:forEach>

        </table>
    </form>

    <form action="/addVisitor" method="post">
        <p><strong>Enter Name:</strong></p>
        <input class="form-control" type="text" name="visitorName" value="" required autofocus/>
        <br/>
        <input class="btn btn-success btn-lg btn-block" type="submit" value="Add Visitor"/>
    </form>

    <form action="/index" method="post">
        <input class="btn btn-success btn-lg btn-block" type="submit" value="Go back to start page"/>
    </form>
</div>
</body>
</html>