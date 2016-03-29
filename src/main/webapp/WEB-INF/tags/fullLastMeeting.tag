<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@attribute name="lastMeeting" type="com.projects.savethemeeting.objectmodel.Meeting" required="true" %>
<%@attribute name="participants" type="java.util.List<com.projects.savethemeeting.objectmodel.User>" required="true" %>

<h4 class="center teal-text">Last meeting report preview</h4>
<h5 class="teal-text " >${lastMeeting.name}</h5>
<h7 class="teal-text " >Date and time: </h7> <h7 class="teal-text darken2 margin2"><fmt:formatDate value="${lastMeeting.started}" pattern="yyyy-MM-dd HH:mm:ss" /></h7>
<br>
<h7 class="teal-text" >Duration: </h7> <h7 class="teal-text darken2 margin3">${lastMeeting.formatedDuration}</h7>

<iframe class="iframe"  width="100%" height="100%" scrolling="no" frameborder="no" src="https://w.soundcloud.com/player/?url=https://soundcloud.com/user-790524602/1457631502605a?in=user-790524602/sets/meeting-records">
</iframe>
<c:forEach var="player" items="${participants}">
    <h7> ${player.name} </h7>
</c:forEach>
<h7 class="teal-text right" ><a href="/full"> See full report >></a> </h7>


