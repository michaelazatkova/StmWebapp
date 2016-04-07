<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="meeting" type="com.projects.savethemeeting.objectmodel.Meeting" required="true" %>
<%@attribute name="actualUser" type="com.projects.savethemeeting.objectmodel.User" required="true" %>

<h4 class="center teal-text">Last meeting report preview</h4>
<h5 class="teal-text " >${meeting.name}</h5>
<hr>
<span class="teal-text chip" >Date and time </span> <span class="teal-text darken3 margin2"><fmt:formatDate value="${meeting.started}" pattern="HH:mm dd.MM.yyyy" /></span>
<span class="teal-text chip margin2 " >Duration </span> <span class="teal-text darken3 margin2">${meeting.formatedDuration}</span>
<br>
<div id="player" class="teal margin4" data="${meeting.idMeeting}" token="${meeting.recordToken}" />
<br><br>



