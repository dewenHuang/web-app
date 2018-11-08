<html>
<body>
<%
    System.out.println("+++++++++++++ index.jsp is rendering +++++++++++++");
%>
<h2>Hello World!</h2>

<a href="restTest/emp/1">Test Rest Get</a>
<br><br>
<form action="restTest/emp" method="post">
    <input type="submit" value="Test Rest Post">
</form>
<br>
<form action="restTest/emp/1" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="Test Rest Delete">
</form>
<br>
<form action="restTest/emp" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="Test Rest Put">
</form>
</body>
</html>
