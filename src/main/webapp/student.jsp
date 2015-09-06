<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<h2>Info</h2>
<form action="${pageContext.request.contextPath}/student/save" method="post">
  <input name="id" type="hidden" value="${student.id}">
  <div>
    <label for="firstName">First Name</label>
    <input name="firstName" id="firstName" type="text" value="${student.firstName}">
  </div>
  <div>
    <label for="lastName">Last Name</label>
    <input name="lastName" id="lastName" type="text" value="${student.lastName}">
  </div>
  <div style="margin-top: 10px;">
    <button type="submit">Save</button>
  </div>
</form>
</body>
</html>
