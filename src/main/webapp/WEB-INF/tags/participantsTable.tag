<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@attribute name="participants" type="java.util.List<com.projects.savethemeeting.objectmodel.User>" required="true" %>

<br>
<h5 class="grey-text darken-2">Participants</h5>
<c:forEach var="person" items="${participants}">
        <div class="chip veralign">
            <img src="<c:url value="/resources/images/person-icon.png"/>" width="16px">${person.name}
        </div>
        <img  src="<c:url value="/resources/images/received.png"/>" width="50px">
</c:forEach>
