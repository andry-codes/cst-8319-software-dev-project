<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Edit Profile</h1>
<form action="profile" method="post">
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" value="<%= request.getAttribute("firstName") %>">
    <br>
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" value="<%= request.getAttribute("lastName") %>">
    <br>
    <label for="age">Age:</label>
    <input type="number" id="age" name="age" value="<%= request.getAttribute("age") %>">
    <br>
    <label for="gender">Gender:</label>
    <input type="text" id="gender" name="gender" value="<%= request.getAttribute("gender") %>">
    <br>
    <label for="weight">Weight:</label>
    <input type="number" id="weight" name="weight" value="<%= request.getAttribute("weight") %>">
    <br>
    <label for="height">Height:</label>
    <input type="number" id="height" name="height" value="<%= request.getAttribute("height") %>">
    <br>
    <input type="submit" value="Update Profile">
</form>

<form action="homepage" method="get">
    <input type="submit" value="Back to Homepage">
</form>

<form action="logout" method="get">
    <input type="submit" value="Log Out">
</form>

<% String message = (String) request.getAttribute("message");
   if (message != null) { %>
   <p style="color:green;"><%= message %></p>
<% } %>

</body>
</html>