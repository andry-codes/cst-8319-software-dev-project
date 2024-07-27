
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<h1>Cap'n Stone Fitness</h1>
<p>Please login using your username or email:</p>

<form action="login" method="post">
    <label for="username_or_email">Username/Email:</label>
    <input type="text" id="username_or_email" name="username_or_email" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <input type="submit" value="Log in">
    <input type="button" value="Forgot Password" onclick="location.href='/capstoneProject/forgotPassword'">
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
