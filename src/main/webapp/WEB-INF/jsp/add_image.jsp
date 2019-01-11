<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--<html>--%>
<html xmlns:th="http://www.thymeleaf/org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Images</title>
    <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Task List</h2>
    <%--<table class="table table-striped">--%>
        <%--<div>--%>
            <%--<h3 th:text="${page.number + 1} + ' of ' + ${page.totalPages}" />--%>
        <%--</div>--%>

        <%--<thead>--%>
            <%--<tr>    <td>Id</td>   <td>Name</td>   <td>Image</td>  </tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
            <%--<tr th:each="image : ${page.content}">--%>
                <%--Images--%>
                <%--<td th:text="${image.id}"/>--%>
                <%--<td th:text="${image.name}"/>--%>
                <%--<td><img th:src="@{'/image/images/' + ${image.name}} + '/raw'}" /></td>--%>
            <%--</tr>--%>
        <%--</tbody>--%>
    <%--</table>--%>

    <table class="table table-striped">
        <thead>
        <tr>    <td>Id</td>   <td>Name</td>   <td>Image</td>  </tr>
        </thead>
        <tbody>
        <tr th:each="image">
        <%--<c:forEach items="${image}" var="image" >--%>
            Images
            <td th:text="${image.id}"/>
            <td th:text="${image.name}"/>
            <td><img th:src="@{'/image/images/' + ${image.name}} + '/raw'}" /></td>
        <%--</c:forEach>--%>
        </tr>
        </tbody>
    </table>

</div>
</body>
</html>