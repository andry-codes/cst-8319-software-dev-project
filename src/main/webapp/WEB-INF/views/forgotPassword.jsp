<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
</head>
<body>

<h1>Forgot Password</h1>
<p>Enter your email or username to receive a reset token:</p>

<form action="forgotPassword" method="post">
    <label for="username_or_email">Username/Email:</label>
    <input type="text" id="username_or_email" name="username_or_email" required>
    <br>
    <input type="submit" value="Submit">
</form>

<% String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) { %>
    <p style="color:red;"><%= errorMessage %></p>
<% } %>

</body>
</html>
