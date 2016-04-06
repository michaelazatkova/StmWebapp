<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="meeting" type="com.projects.savethemeeting.objectmodel.Meeting" required="true" %>
<%@attribute name="participants" type="java.util.List<com.projects.savethemeeting.objectmodel.User>" required="true" %>
<%@attribute name="comments" type="java.util.List<com.projects.savethemeeting.objectmodel.Comment>" required="true" %>
<%@attribute name="actualUser" type="com.projects.savethemeeting.objectmodel.User" required="true" %>

<h4 class="center teal-text">Last meeting report preview</h4>
<h5 class="teal-text " >${meeting.name}</h5>
<hr>
<span class="teal-text chip" >Date and time </span> <h6 class="teal-text darken3 margin2"><fmt:formatDate value="${meeting.started}" pattern="HH:mm dd.MM.yyyy" /></h6>
<br>
<span class="teal-text chip" >Duration </span> <h6 class="teal-text darken3 margin2">${meeting.formatedDuration}</h6>

<div id="player" class="teal" data="${meeting.idMeeting}">
</div>
<br>
<div class="row">
    <div class="col s9">
    <t:comments comments="${comments}"/>
    </div>
    <div class="col s3"
<t:participantsTable participants="${participants}"/>
    </div>
</div>

