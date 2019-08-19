<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>VisitorsReport</title>
</head>
<body class="p-3 mb-2 bg-info text-white">
<div>
    <h2 class="text-center">Report for selected visitor </h2>

    <form action="visitorReport" method="get">
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Room </th>
                <th>Entrance Time</th>
                <th>Exit Time</th>
            </tr>
            <c:forEach var="listByVisitor" items="${listByVisitor}">

                <tr>
                    <td><c:out value='${listByVisitor.visitorName}'/></td>
                    <td><c:out value='${listByVisitor.numberRoom}'/></td>
                    <td><c:out value='${listByVisitor.start}'/></td>
                    <td><c:out value='${listByVisitor.finish}'/></td>
                </tr>

            </c:forEach>

        </table>
    </form>
    <form action="/index" method="post">
        <input class="btn btn-success btn-lg btn-block" type="submit" value="Go back to start page"/>
    </form>

</div>
</body>
</html>