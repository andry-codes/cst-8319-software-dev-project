<!DOCTYPE html>
<html>
<head>
    <title>Physical Exercises</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<h2>Select a Category</h2>
<form action="selectCategory" method="get">
    <input type="hidden" name="category" value="Target Muscle Groups">
    <input type="submit" value="Target Muscle Groups">
</form>
<form action="selectCategory" method="get">
    <input type="hidden" name="category" value="Cardio Exercises">
    <input type="submit" value="Cardio Exercises">
</form>
<form action="selectCategory" method="get">
    <input type="hidden" name="category" value="Yoga Poses">
    <input type="submit" value="Yoga Poses">
</form>

<jsp:include page="backToHomeButton.jsp" />

</body>
</html>