<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="lastMeeting" type="com.projects.savethemeeting.objectmodel.Meeting" required="true" %>
<%@attribute name="participants" type="java.util.List<com.projects.savethemeeting.objectmodel.User>" required="true" %>

<div class="meeting-wrapper">
    <h5 class="teal-text meeting-name">${lastMeeting.name} <a class="waves-effect waves-teal btn-floating" href="<c:url value="/full/${lastMeeting.idMeeting}"/>"><i class="material-icons right">send</i>See full report</a></h5>
    <span class="teal-text ">Date and time: </span> <span class="teal-text darken2 margin2 meeting-date"><fmt:formatDate
        value="${lastMeeting.started}" pattern="HH:mm dd.MM.yyyy"/></span>
    <t:participantsList participants="${participants}"/>
    <br>
    <hr>
</div>
