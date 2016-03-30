<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="lastMeeting" type="com.projects.savethemeeting.objectmodel.Meeting" required="true" %>
<%@attribute name="participants" type="java.util.List<com.projects.savethemeeting.objectmodel.User>" required="true" %>

<h4 class="center teal-text">Last meeting report preview</h4>
<h5 class="teal-text " >${lastMeeting.name}</h5>
<h6 class="teal-text " >Date and time: </h6> <h6 class="teal-text darken2 margin2"><fmt:formatDate value="${lastMeeting.started}" pattern="HH:mm:ss dd.MM.yyyy" /></h6>
<br>
<h6 class="teal-text" >Duration: </h6> <h6 class="teal-text darken2 margin3">${lastMeeting.formatedDuration}</h6>

<div id="player" data="${lastMeeting.idMeeting}">
</div>

<t:participantsTable participants="${participants}"/>

