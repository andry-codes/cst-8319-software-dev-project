<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
</head>
<body>

<h1>Cap'n Stone Fitness</h1>
<p>Please login using username or email.</p>

<form action="login" method="post">
<label for="username">Username:</label>
<input type="text" id="username" name="username" required>
<br>
<label for="password">Password: </label>
<input type="password" id="password" name="password" required>
<br>
<input type="submit" id="submit" name="submit" value="Log in">
<input type="button" id="reset" name="reset" value="Forgot Password">
<br>
<br>
<br>
<label for="register">You need an account to access this service.</label>
<br>
<input type="button" id="register" name="register" onclick="location.href='/capstoneProject/register'" value="Register">

</form>

</body>
</html>
