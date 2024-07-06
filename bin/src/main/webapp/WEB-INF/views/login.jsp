<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Cap'n Stone Fitness</h1>
<p>Please login using your username or email:</p>

<form action="login" method="post">
<<<<<<< HEAD
<label for="username">Username:</label>
<input type="text" id="username" name="username" required>
<br>
<label for="password">Password: </label>
<input type="password" id="password" name="password" required>
<br>
<input type="submit" id="submit" name="submit" value="Log in">
<input type="button" id="reset" name="reset" onclick="location.href='/capstoneProject/password'" value="Forgot Password">
<br>
<br>
<br>
<label for="register">You need an account to access this service.</label>
<br>
<input type="button" id="register" name="register" onclick="location.href='/capstoneProject/register'" value="Register">

=======
    <label for="username_or_email">Username/Email:</label>
    <input type="text" id="username_or_email" name="username_or_email" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <input type="submit" value="Log in">
    <input type="button" value="Forgot Password" onclick="location.href='/capstoneProject/forgotPassword'">
>>>>>>> master
</form>

<p>Don't have an account? <a href="/capstoneProject/register">Register here</a></p>

<% 
String errorMessage = (String) request.getAttribute("errorMessage");
if (errorMessage != null) { 
%>
    <p style="color:red;"><%= errorMessage %></p>
<% 
} 
%>

</body>
</html>
