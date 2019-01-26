<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Task List</title>
    <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Task List</h2>
    <table class="table table-striped">
        <thead>
        <th scope="row">Title</th>
        <th scope="row">Description</th>
        <th scope="row">Created</th>
        <th scope="row">Update</th>
        <th scope="row">Delete</th>
        <th scope="row">Set As Done/Undone</th>
        </thead>
        <tbody>
        <c:forEach items="${taskList}" var="task" >
            <tr>
                <td>${task.name}</td>
                <td>${task.description }</td>
                <td>${task.dateCreated }</td>
                <td>
                    <spring:url value="/task/updateTask/${task.id}" var="updateURL" />
                    <a class="btn btn-primary" href="${updateURL}" role="button" >Update</a>
                </td>
                <td>
                    <spring:url value="/task/deleteTask/${task.id }" var="deleteURL" />
                    <a class="btn btn-primary" href="${deleteURL }" role="button" >Delete</a>
                </td>
                <td>
                    <spring:url value="/task/changeDone/${task.id }" var="changeDoneURL" />
                    <c:if test = "${task.done == true}">
                        <a class="btn btn-primary" href="${changeDoneURL }" role="button" >Done</a>
                    </c:if>

                    <c:if test = "${task.done == false}">
                        <a class="btn btn-primary" href="${changeDoneURL }" role="button" >Undone</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <spring:url value="/task/addTask/" var="toTaskList" />
    <a class="btn btn-primary" href="${toTaskList }" role="button" >Add New Task</a>
</div>
</body>
</html>