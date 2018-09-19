<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Task Form</title>
    <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <spring:url value="/task/saveTask" var="saveURL" />
    <h2>Task</h2>
    <form:form modelAttribute="taskForm" method="post" action="${saveURL }" cssClass="form" >
        <form:hidden path="id"/>
        <form:hidden path="done" value="false"/>
        <%--<form:hidden path="datecreated" value="<% new Date() %>"/>--%>
        <div class="form-group">
            <label>Title</label>
            <form:input path="name" cssClass="form-control" id="name" />
        </div>
        <div class="form-group">
            <label>Description</label>
            <form:input path="description" cssClass="form-control" id="description" />
        </div>
        <button type="submit" class="btn btn-primary">Save</button>

        <form:form method = "GET" action = "/task/list">
            <button type="submit" class="btn btn-primary">Back</button>
        </form:form>

    </form:form>

</div>
</body>
</html>