<!DOCTYPE html>
<html>
<head>
    <title>Mental Health Activities</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<h2>Select a Category</h2>
<form action="selectCategory" method="get">
    <input type="hidden" name="category" value="Relaxation Techniques">
    <input type="submit" value="Relaxation Techniques">
</form>
<form action="selectCategory" method="get">
    <input type="hidden" name="category" value="Mindfulness Exercises">
    <input type="submit" value="Mindfulness Exercises">
</form>
<form action="selectCategory" method="get">
    <input type="hidden" name="category" value="Anger Management">
    <input type="submit" value="Anger Management">
</form>

<jsp:include page="backToHomeButton.jsp" />

</body>

</html>
