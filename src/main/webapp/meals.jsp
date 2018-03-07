<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Meals</title>
    <style>
        table {
            font-size: 120%;
            font-family: verdana, serif;
        }
        #true {
            color: red;
        }
        #false {
            color: green;
        }
    </style>
</head>

<body>
<h2 align="center">Meals</h2>
<h3 align="center"><a href="index.html">Home</a>&nbsp;&nbsp;&nbsp;
    <a href="meal.jsp">Add New Meal</a></h3>

<table align="center" border="1" rules="all" width=50% cellpadding="7">

    <tr align="center" bgcolor="#f5f5dc">
        <td>Дата/Время</td>
        <td>Описание</td>
        <td>Калории</td>
        <td></td>
        <td></td>
    </tr>

    <c:forEach items="${meals}" var="meal">
    <tr align="center" bgcolor="#e6e6fa" id="${meal.isExceed()}">
        <td>${fn:replace(meal.getDateTime(), 'T', ' ')}</td>
        <td>${meal.getDescription()}</td>
        <td>${meal.getCalories()}</td>
        <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">редактировать</a></td>
        <td><a href="meals?action=delete&id=<c:out value='${meal.id}'/>">удалить</a></td>
    <tr>
        </c:forEach>
</table>
</body>
</html>































