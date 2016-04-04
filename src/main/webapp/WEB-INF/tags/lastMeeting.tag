<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="lastMeeting" type="com.projects.savethemeeting.objectmodel.Meeting" required="true" %>
<%@attribute name="participants" type="java.util.List<com.projects.savethemeeting.objectmodel.User>" required="true" %>


<h4 class="center grey lighten-4">Last meeting report preview</h4>
<h5 class="teal-text ">${lastMeeting.name}</h5>
<span class="teal-text ">Date and time: </span> <span class="teal-text darken2 margin2"><fmt:formatDate
        value="${lastMeeting.started}" pattern="HH:mm dd.MM.yyyy"/></span>
<br>
<span class="teal-text">Duration: </span> <span class="teal-text darken2 margin3">${lastMeeting.formatedDuration}</span>

<div id="player" data="${lastMeeting.idMeeting}">
</div>
<t:participantsTable participants="${participants}"/>

<form action="<c:url value="/full"/>" method="GET">
    <button class="btn waves-effect waves-light right" type="submit" name="action">See full report
        <i class="material-icons right">send</i>
    </button>
</form>


