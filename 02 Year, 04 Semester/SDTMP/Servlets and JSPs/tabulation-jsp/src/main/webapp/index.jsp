<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Tabulation</title>
    <link href = "styles.css" rel = "stylesheet" type = "text/css">
</head>
<body>

<form align="center" action="logic-servlet" method="post" onsubmit="validityCheck(x1, x2)">

    <label>Початок інтервалу</label><br>
    <input type="number" name="x1" step="any" id="x1" required><br><br>
    <label>Кінець інтервалу</label><br>
    <input type="number" name="x2" step="any" id="x2" required><br><br>
    <label>Крок зміни аргумента</label><br>
    <input type="number" name="step" step="any" required><br><br>
    <label>Значення змінної A</label><br>
    <input type="number" name="a" step="any" required><br><br>
    <input type="submit" value="Обчислити"><br><br>
    <input type="reset" value="Сброс даних"><br><br>
</form>
<p></p>

<form align="center">

    <label>Найбільше значення функції</label><br>
    <input type="number" name="yMax" value="<%= request.getAttribute("maxY")%>" disabled><br><br>
    <label>Найменше значення функції:</label><br>
    <input type="number" name="yMin" value="<%= request.getAttribute("minY")%>" disabled><br><br>
    <label>Аргумент x найбільшого значення функції</label><br>
    <input type="number" name="xMax" value="<%= request.getAttribute("maxX")%>" disabled><br><br>
    <label>Аргумент x найменшого значення функції</label><br>
    <input type="number" name="xMin" value="<%= request.getAttribute("minX")%>" disabled><br><br>
    <label>Сумма елементів</label><br>
    <input type="number" name="sum" value="<%= request.getAttribute("sum")%>" disabled><br><br>
    <label>Середнє арифметичне</label><br>
    <input type="number" name="average" value="<%= request.getAttribute("average")%>" disabled><br><br>
</form>

<p></p>
<form align="center" action="logic-servlet" method="get">

    <label>Значення елементу массиву значень функції y за індексом</label>
    <input type="number" name="indexY" id="indexY"><br><br>
    <label>Значення елементу массиву аргументу x за індексом</label>
    <input type="number" name="indexX" id="indexX"><br><br>
    <input type="submit" value="Отримати"><br><br>

</form>

<form align="center">
    <label>Y</label>
    <input type="number" name="yByIndex" value="<%= request.getAttribute("valueByIndexY")%>" disabled><br><br>
    <label>X</label>
    <input type="number" name="xByIndex" value="<%= request.getAttribute("valueByIndexX")%>" disabled><br><br>
</form>


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
        }
    }


</script>