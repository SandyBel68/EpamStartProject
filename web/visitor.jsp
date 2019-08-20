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

            <form action="visitor" method="post">
                <select name="visitorName">
                    <c:forEach var ="allVisitors" items="${listOfVisitors}">
                    <option value='${allVisitors.visitorName}'>${allVisitors.visitorName}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Submit">
            </form>
</div>

</body>
</html>