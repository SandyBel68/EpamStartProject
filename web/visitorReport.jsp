<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Beaver</title>
</head>
<body class="p-3 mb-2 bg-info text-white">
<div>
    <h2 class="text-center">visitors </h2>

    <form action="visitorReport" method="get">

        <table class="table table-striped">
            <tr>
                <th>idVisitor</th>
                <th>idRoom</th>
                <th>timeStart</th>
                <th>timeFinish</th>
            </tr>
            <c:forEach var="listByVisitor" items="${listByVisitor}">

                <tr>
                    <td><c:out value='${listByVisitor.idVisitor}'/></td>
                    <td><c:out value='${listByVisitor.idRoom}'/></td>
                    <td><c:out value='${listByVisitor.timeStart}'/></td>
                    <td><c:out value='${listByVisitor.timeFinish}'/> $</td>
                </tr>

            </c:forEach>

        </table>
    </form>
</div>
</body>
</html>