<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Location app</title>
    <style>
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
<%--            <form action="sign_in" method="get">--%>
<%--                <input class="btn btn-primary btn-lg btn-block" type="submit" value="Sign In"/>--%>
<%--            </form>--%>
<%--        START ROUTE button --%>
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
