<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Uploaded Files</title>
</head>
<body>
<h1>
	List of Uploaded Files!!  
</h1>
	<ol>
	   <c:forEach var="file" items="${files}" >
            <li>${file}</li>
        </c:forEach>
    </ol>
</body>
</html>
