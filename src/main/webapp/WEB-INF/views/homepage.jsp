<!DOCTYPE html>
<html>
<head>
    <title>Homepage</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#searchInput").on("input", function() {
                var query = $(this).val();
                if (query.length > 0) {
                    $.ajax({
                        url: "searchExercise",
                        type: "GET",
                        data: { query: query },
                        success: function(data) {
                            $("#searchResults").html(data);
                        }
                    });
                } else {
                    $("#searchResults").empty();
                }
            });
        });
    </script>
</head>
<body>

<h1>Welcome, <%= session.getAttribute("username") %>!</h1>

<form action="profile" method="get">
    <input type="submit" value="Edit Profile">
</form>

<form action="logout" method="post">
    <input type="submit" value="Log Out">
</form>

<h2>Select an Option</h2>
<form action="physicalExercises" method="get">
    <input type="submit" value="Physical Exercises">
</form>

<form action="mentalHealthActivities" method="get">
    <input type="submit" value="Mental Health Activities">
</form>

<h2>Search for an Exercise</h2>
<input type="text" id="searchInput" placeholder="Search for an exercise">
<div id="searchResults"></div>


</body>
</html>
