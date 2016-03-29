<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="list" type="java.util.List<com.projects.savethemeeting.objectmodel.Meeting>" required="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@attribute name="lastMeetings" type="java.util.List<com.projects.savethemeeting.objectmodel.Meeting>" required="true" %>

<h4 class="center teal-text">Latest reports</h4>
<div class="collection">
  <c:forEach var="meeting" items="${lastMeetings}">
    <a href="#!" class="collection-item">${meeting.name}</a>
  </c:forEach>
</div>


</iframe>


