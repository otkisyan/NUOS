<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP - Tabulation</title>
    <link href="styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="index.jsp">На домашню сторінку</a>

<form align="center" action="tabulation-servlet" method="post" onsubmit="return validityCheck(x1, x2)">

    <label for="x1">Початок інтервалу</label><br>
    <input type="number" name="x1" step="any" id="x1" required><br><br>
    <label for="x2">Кінець інтервалу</label><br>
    <input type="number" name="x2" step="any" id="x2" required><br><br>
    <label for="step">Крок зміни аргумента</label><br>
    <input type="number" id="step" name="step" step="any" required><br><br>
    <input type="submit" value="Обчислити"><br><br>
    <input type="reset" value="Сброс даних"><br><br>
</form>
<p></p>

<% if (request.getAttribute("tabulationResult") != null) { %>
<jsp:useBean id="tabulationResult" type="org.example.servletsjsp.model.tabulation.TabulationResult" scope="request"/>
<form align="center">

    <label>Найбільше значення функції</label><br>
    <input type="number" name="yMax" value="${tabulationResult.maxElement}" disabled><br><br>
    <label>Найменше значення функції:</label><br>
    <input type="number" name="yMin" value="${tabulationResult.minElement}" disabled><br><br>
    <label>Аргумент x найбільшого значення функції</label><br>
    <input type="number" name="xMax" value="${tabulationResult.maxElementArgument}" disabled><br><br>
    <label>Аргумент x найменшого значення функції</label><br>
    <input type="number" name="xMin" value="${tabulationResult.minElementArgument}" disabled><br><br>
    <label>Сумма елементів</label><br>
    <input type="number" name="sum" value="${tabulationResult.sum}" disabled><br><br>
    <label>Середнє арифметичне</label><br>
    <input type="number" name="average" value="${tabulationResult.average}" disabled><br><br>
</form>

<table align="center" border="1" cellpadding="5" cellspacing="10">
    <thead>
    <tr>
        <th>X</th>
        <th>Y</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="point" items="${tabulationResult.points}">
        <tr>
            <td><fmt:formatNumber value="${point.x}" type="number" maxFractionDigits="3"/></td>
            <td><fmt:formatNumber value="${point.y}" type="number" maxFractionDigits="3"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<% } %>

<% if (request.getAttribute("error") != null) { %>

<script>

    alert("<%=request.getAttribute("error")%>");

</script>

<% } %>


</body>
</html>

<script type="text/javascript">

    function validityCheck(x1, x2) {

        if (x2.valueAsNumber < x1.valueAsNumber) {

            alert("Кінець інтервалу не може бути менше початку інтервалу");
            return false
        }
        return true
    }


</script>