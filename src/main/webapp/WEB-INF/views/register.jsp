<!DOCTYPE html>
<html>
<head>
<title>Register</title>
</head>
<body>

<h1>Cap'n Stone Fitness</h1>
<p>Please enter your details below to register for your account. </p>

<form action="register" method="post">
<label for="user_email">Email:</label>
<input type="email" id="user_email" name="user_email" required>
<br>
<label for="new_user">Username:</label>
<input type="text" id="new_user" name="new_user" required>
<br>
<label for="new_password">Password: </label>
<input type="password" id="new_password" name="new_password" required>
<br>
<label for="confirm_password">Re-enter password: </label>
<input type="password" id="confirm_password" name="confirm_password" required>
<br>
<input type="submit" id="submit" name="submit" value="Register">
</form>


<script>
	// Adding an event listener to the form when you click the submit button. 
	document.querySelector('form').addEventListener('submit', function(event) {
		const password = document.getElementById('new_password').value;
		const confirm_password = document.getElementById('confirm_password').value;
	
		if (password != confirm_password) {
			event.preventDefault();
			alert('Passwords do not match.');
		}
	});
</script>

<!--  https://www.geeksforgeeks.org/introduction-to-jsp/  -->
<!--  Scriplet to pass error messages from RegisterServlet  -->

<% String errorMessage = (String) request.getAttribute("errorMessage");
	if (errorMessage != null) {
%>

	<p style="color:red;"><%= errorMessage %></p>

<% }
%>

</body>
</html>
