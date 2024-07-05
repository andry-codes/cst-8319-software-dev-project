<!DOCTYPE html>
<html>
<head>
    <title>Homepage</title>
</head>
<body>

<h1>Welcome, <%= session.getAttribute("username") %>!</h1>

<form action="profile" method="get">
    <input type="submit" value="Edit Profile">
</form>

<form action="logout" method="post">
    <input type="submit" value="Log Out">
</form>

</body>
</html>
