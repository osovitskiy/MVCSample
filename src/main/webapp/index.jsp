<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MVCSample</title>
</head>
<body>
<h2>Students</h2>
<div style="margin-bottom: 10px;">
    <a href="student/add"><button>Add</button></a>
</div>
<table id="students" border="1px solid black">
    <thead>
    <tr>
        <th width="20px">Id</th>
        <th width="100px">First Name</th>
        <th width="100px">Last Name</th>
        <th width="100px">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td align="center">
                <a href="student/edit/${student.id}"><button>Edit</button></a>
                <form action="student/delete" method="post" style="display: inline; margin: 0;">
                    <input type="hidden" name="id" value="${student.id}">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2>Groups</h2>
<div style="margin-bottom: 10px;">
    <a href="group/add"><button>Add</button></a>
</div>
<table id="groups" border="1px solid black">
    <thead>
    <tr>
        <th width="20px">Id</th>
        <th width="100px">Name</th>
        <th width="100px">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${groups}" var="group">
        <tr>
            <td>${group.id}</td>
            <td>${group.name}</td>
            <td align="center">
                <a href="group/edit/${group.id}"><button>Edit</button></a>
                <form action="group/delete" method="post" style="display: inline; margin: 0;">
                    <input type="hidden" name="id" value="${group.id}">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
