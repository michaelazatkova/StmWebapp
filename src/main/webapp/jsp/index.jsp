<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="meeting" class="com.projects.savethemeeting.objectmodel.Meeting" scope="request"/>
<jsp:useBean id="participants" type="java.util.List" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="lastMeetings" type="java.util.List" scope="request"/>

<t:page>
    <jsp:body>
        <div class="container">
            <div class="section">
                <!--   Icon Section   -->
                <div class="row">
                    <div class="col s12 m6">
                        <div class="icon-block grey lighten-4 medium">
                            <h2 class="center black-text margin1"><i class="material-icons">description</i></h2>

                            <c:choose>
                                <c:when test="${meeting.idMeeting != null and not empty participants}">
                                    <p class="light"><t:lastMeeting meeting="${meeting}"
                                                                    participants="${participants}"/></p>
                                </c:when>
                                <c:otherwise>
                                    <p class="light"><h4 class="center">No last meeting found!</h4></p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="col s12 m6">
                        <div class="icon-block grey lighten-4 medium">
                            <h2 class="center black-text margin1"><i class="material-icons">view_list</i></h2>

                            <c:choose>
                                <c:when test="${not empty lastMeetings}">
                                    <p class="light"><t:meetingList lastMeetings="${lastMeetings}"/></p>
                                </c:when>
                                <c:otherwise>
                                    <p class="light"><h4 class="center">Nothing reported!</h4></p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:page>
