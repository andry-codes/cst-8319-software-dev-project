<!DOCTYPE html>
<html>
<head>
    <title>Email Verification</title>
</head>
<body>

<h1>Email Verification</h1>
<p>Please enter the verification code sent to your email: <%= request.getAttribute("email") %></p>

<form action="verify" method="post">
    <input type="hidden" name="email" value="<%= request.getAttribute("email") %>">
    <label for="verification_code">Verification Code:</label>
    <input type="text" id="verification_code" name="verification_code" required>
    <br>
    <input type="submit" value="Verify">
</form>

<form action="resendVerification" method="post">
    <input type="hidden" name="email" value="<%= request.getAttribute("email") %>">
    <input type="submit" value="Resend Verification Code">
</form>

<% String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) { %>
    <p style="color:red;"><%= errorMessage %></p>
<% } %>

<% String message = (String) request.getAttribute("message");
    if (message != null) { %>
    <p style="color:green;"><%= message %></p>
<% } %>

</body>
</html>
