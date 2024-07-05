<!DOCTYPE html>
<html>
<head>
    <title>Homepage</title>
</head>
<body>

<h1>Welcome, <%= session.getAttribute("username") %></h1>
<p>You have successfully logged in.</p>
<a href="logout">Logout</a>

</body>
</html>
