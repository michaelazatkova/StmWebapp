<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="list" type="java.util.List<com.projects.savethemeeting.objectmodel.Meeting>" required="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@attribute name="lastMeetings" type="java.util.List<com.projects.savethemeeting.objectmodel.Meeting>" required="true" %>

<h4 class="center grey lighten-4">Latest reports</h4>
<div class="collection">
  <c:forEach var="meeting" items="${lastMeetings}">
    <a class="collection-item" href="<c:url value="/full/${meeting.idMeeting}"/>"><div>${meeting.name}<span class="secondary-content" ><i class="material-icons teal-text">send</i></span></div></a>
  </c:forEach>
</div>

<form action="<c:url value="/reports"/>" method="GET">
  <button class="btn waves-effect waves-light right" type="submit" name="action">See all reports
    <i class="material-icons right">send</i>
  </button>
</form>

