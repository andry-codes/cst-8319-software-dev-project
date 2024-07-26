<%@ page import="models.Exercise" %>
<!DOCTYPE html>
<html>
<head>
    <title><%= request.getAttribute("exercise") != null ? ((Exercise) request.getAttribute("exercise")).getName() : "" %></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<%
    Exercise exercise = (Exercise) request.getAttribute("exercise");
    if (exercise != null) {
%>
    <h2><%= exercise.getName() %></h2>
    <p>Category: <%= exercise.getCategory() %></p>
    <p><%= exercise.getDescription() %></p>
    <img class="exercise-image" src="<%= exercise.getImageUrl() %>" alt="<%= exercise.getName() %>">
    <p><strong>Instructions:</strong></p>
    <pre><%= exercise.getInstructions() %></pre>
<%
    } else {
%>
    <p>No exercise selected.</p>
<%
    }
%>

<jsp:include page="backToHomeButton.jsp" />

</body>
</html>