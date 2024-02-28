<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Login page</title>
</head>
<body>
<h1><%= "Login Page:" %></h1>
<br/>
<form action="login-servlet" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username"><br/><br/>

  <label for="password">Password:</label>
  <input type="password" id="password" name="password"><br/><br/>

  <input type="submit" name="submit" value="submit"><br/><br/>
</form>
<textarea name="Text1" cols="40" rows="5"></textarea><br/><br/>
<input type="submit" name="text" value="text"><br/><br/>
<table style="width: 200px">
  <tr>
    <td><b>FILE</b></td>
    <td><b>OWNER</b></td>
  </tr>
  <tr>
    <td> first </td>
    <td> second </td>
    <td> third </td>
  </tr>
</table>
</body>
</html>
