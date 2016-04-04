<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@attribute name="participants" type="java.util.List<com.projects.savethemeeting.objectmodel.User>" required="true" %>

<br>
<div class="meeting-participant-container">
<c:forEach var="person" items="${participants}">
    <div class="chip">
        <img src="<c:url value="/resources/images/person-icon.png"/>" width="16px">
        <span>${person.name}</span>
    </div>
</c:forEach>
</div>

