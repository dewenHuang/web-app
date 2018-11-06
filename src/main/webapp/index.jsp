<html>
<body>
<%
    System.out.println("+++++++++++++ index.jsp is rendering +++++++++++++");
%>
<h2>Hello World!</h2>

<a href="restTest/order/1">Test Rest Get</a>
<br><br>
<form action="restTest/order" method="post">
    <input type="submit" value="Test Rest Post"></input>
</form>
<br>
<form action="restTest/order/1" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="Test Rest Delete"></input>
</form>
<br>
<form action="restTest/order/1" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="Test Rest Put"></input>
</form>
</body>
</html>
