<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Meals</title>
    <style>
        table {
            font-size: 120%;
            font-family: verdana, serif;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table align="center" border="1" rules="all" width=50% cellpadding="7">

    <tr align="center" bgcolor="#f5f5dc">
        <td width=30%>Дата/Время</td>
        <td width=40%>Описание</td>
        <td width=30%>Калории</td>
    </tr>

    <c:forEach items="${meals}" var="meal">
    <tr align="center" bgcolor="#e6e6fa">
        <td><span style="${meal.isExceed() ? "color: red" : "color: green"}">${meal.getDateTime()}</span></td>
        <td><span style="${meal.isExceed() ? "color: red" : "color: green"}">${meal.getDescription()}</span></td>
        <td><span style="${meal.isExceed() ? "color: red" : "color: green"}">${meal.getCalories()}</span></td>
    <tr>
        </c:forEach>
</table>
</body>
</html>
