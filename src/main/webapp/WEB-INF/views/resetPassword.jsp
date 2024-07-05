<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
</head>
<body>

<h1>Reset Password</h1>
<p>Enter the reset code sent to your email and your new password:</p>

<form action="resetPassword" method="post">
    <input type="hidden" name="email" value="<%= request.getAttribute("email") %>">
    <label for="reset_code">Reset Code:</label>
    <input type="text" id="reset_code" name="reset_code" required>
    <br>
    <label for="new_password">New Password:</label>
    <input type="password" id="new_password" name="new_password" required>
    <br>
    <label for="confirm_password">Confirm Password:</label>
    <input type="password" id="confirm_password" name="confirm_password" required>
    <br>
    <input type="submit" value="Reset Password">
</form>

<% String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) { %>
    <p style="color:red;"><%= errorMessage %></p>
<% } %>

</body>
</html>
