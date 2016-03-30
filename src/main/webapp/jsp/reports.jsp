<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="resultMap"
             type="java.util.Map<com.projects.savethemeeting.objectmodel.Meeting,java.util.List<com.projects.savethemeeting.objectmodel.User>>"
             scope="request"/>

<t:page>
    <jsp:body>
        <div class="container">
            <div class="section">
                <!--   Icon Section   -->
                <div class="row">
                    <div class="col s12 m6">
                        <div class="icon-block grey lighten-4">
                            <h2 class="center black-text margin1"><i class="material-icons">search</i></h2>
                            <h5 class="center">Filter</h5>

                            <nav class="teal">
                                <div class="nav-wrapper">
                                    <form class=searchform>
                                        <div class="input-field">
                                            <input id="search" type="search" required
                                                   placeholder="Enter meeting name...">
                                            <label for="search"><i class="material-icons">search</i></label>
                                            <i class="material-icons">close</i>
                                        </div>
                                    </form>
                                </div>
                            </nav>
                            <nav class="teal">
                                <div class="nav-wrapper">
                                    <form class=searchform>
                                        <div class="input-field">
                                            <input type="search" required id="fromdatepicker"
                                                   placeholder="Click to choose FROM date">
                                            <label for="fromdatepicker"><i class="material-icons">search</i></label>
                                            <i class="material-icons">close</i>
                                        </div>
                                    </form>
                                </div>
                            </nav>
                            <nav class="teal">
                                <div class="nav-wrapper">
                                    <form class=searchform>
                                        <div class="input-field">
                                            <input type="search" required id="todatepicker"
                                                   placeholder="Click to choose TO date ">
                                            <label for="todatepicker"><i class="material-icons">search</i></label>
                                            <i class="material-icons">close</i>
                                        </div>
                                    </form>
                                </div>
                            </nav>
                        </div>
                    </div>
                    <div class="col s12 m6">
                        <div class="icon-block grey lighten-4">
                            <h2 class="center black-text margin1"><i class="material-icons">description</i></h2>
                            <h5 class="center">Reports</h5>
                            <div class="responsive-height">
                            <p class="light">
                                <c:forEach items="${resultMap}" var="item">
                                    <t:shortMeetingInfo lastMeeting="${item.key}" participants="${item.value}"/>
                                </c:forEach>
                            </p>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </jsp:body>
</t:page>