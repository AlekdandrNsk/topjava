<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <style>
        FORM {
            font-size: 120%;
            font-family: verdana, serif;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Edit</h2>

<form method="POST" action='meals'>
    ID : <input type="text" readonly="readonly" name="id_edit"
                value="<c:out value="${meal.id == 0 ? '' : meal.id}" />"/> <br/>
    Дата/Время : <input
        type="datetime" name="datetime" value="<c:out value="${meal.id == 0 ? '' : meal.getDateTimeT()}" />"/> <br/>
    Описание : <input
        type="text" name="description"
        value="<c:out value="${meal.id == 0 ? '' : meal.description}" />"/> <br/>
    Калории : <input type="text" name="calories"
                     value="<c:out value="${meal.id == 0 ? '' : meal.calories}" />"/> <br/>
    <input type="submit" value="save">
</form>
</body>
</html>
