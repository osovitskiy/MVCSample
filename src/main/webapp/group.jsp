<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<h2>Info</h2>
<form action="${pageContext.request.contextPath}/group/save" method="post">
    <input name="id" type="hidden" value="${group.id}">
    <div>
        <label for="name">Name</label>
        <input id="name" type="text" name="name" value="${group.name}">
    </div>
    <div style="margin-top: 10px;">
        <button type="submit">Save</button>
    </div>
</form>
<c:if test="${group.id gt 0}">
    <h2>Students</h2>
    <c:if test="${fn:length(availableStudents) gt 0}">
        <form action="${pageContext.request.contextPath}/group/addstudent" method="post">
            <input type="hidden" name="groupId" value="${group.id}">
            <select name="studentId">
                <c:forEach items="${availableStudents}" var="student">
                    <option value="${student.id}">${student.firstName} ${student.lastName}</option>
                </c:forEach>
            </select>
            <button type="submit">Add</button>
        </form>
    </c:if>
    <table border="1px solid black">
        <thead>
        <tr>
            <th width="20px">Id</th>
            <th width="100px">First Name</th>
            <th width="100px">Last Name</th>
            <th width="100px">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${groupStudents}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td align="center">
                    <form action="${pageContext.request.contextPath}/group/removestudent" method="post" style="display: inline; margin: 0;">
                        <input type="hidden" name="groupId" value="${group.id}">
                        <input type="hidden" name="studentId" value="${student.id}">
                        <button type="submit">Remove</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
