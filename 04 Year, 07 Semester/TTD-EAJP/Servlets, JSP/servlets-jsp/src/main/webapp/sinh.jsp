<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sinh Calculation</title>
    <link href="styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="index.jsp">На домашню сторінку</a>
<h2>Введіть дані для обчислення sinh(x)</h2>
<form action="sinh-servlet" method="post" align="center">
    <label for="x">x:</label>
    <input type="number" step="0.0001" id="x" name="x"
           value="<%= request.getAttribute("x") != null ? request.getAttribute("x") : "" %>"><br><br>

    <label for="n">Кількість доданків n:</label>
    <input type="number" step="0.0001" id="n" name="n"
           value="<%= request.getAttribute("n") != null ? request.getAttribute("n") : "" %>"><br><br>

    <label for="e">e:</label>
    <input type="number" id="e" step="0.0001" name="e"
           value="<%= request.getAttribute("e") != null ? request.getAttribute("e") : "" %>"><br><br>

    <input type="submit" value="Обчислити">
</form>

<% if (request.getAttribute("sinhResult") != null) { %>
<jsp:useBean id="sinhResult" type="org.example.servletsjsp.model.sinh.SinhResult" scope="request"/>
<h2>Результати обчислення sinh(x)</h2>
<p>Сума n доданків: ${sinhResult.sumN}</p>
<p>Сума доданків, більших за e: ${sinhResult.sinhResultWithE.sumWithE}
    (Кількість: ${sinhResult.sinhResultWithE.sumWithETermCount})</p>
<p>Точне значення sinh(x): ${sinhResult.exactSinh}</p>
<% } %>
</body>
</html>
