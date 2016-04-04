<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="resultMap"
             type="java.util.Map<com.projects.savethemeeting.objectmodel.Meeting,java.util.List<com.projects.savethemeeting.objectmodel.User>>"
             scope="request"/>
<jsp:useBean id="allusers" type="java.util.List" scope="request"/>

<t:page>
    <jsp:body>
        <div class="container">
            <div class="section">
                <!-- Icon Section -->
                <div class="row">
                    <c:choose>
                        <c:when test="${not empty resultMap}">
                            <div class="col s12 m6">
                                <div class="icon-block grey lighten-4">
                                    <h2 class="center black-text margin1"><i class="material-icons">search</i></h2>
                                    <h5 class="center">Filter</h5>

                                    <ul class="collapsible" data-collapsible="expandable">
                                        <li>
                                            <div class="collapsible-header active"><i class="material-icons">label</i>By
                                                meeting name
                                            </div>
                                            <div class="collapsible-body">
                                                <div class="container">
                                                    <nav class="grey darken-1">
                                                        <div class="nav-wrapper">
                                                            <form class=searchform>
                                                                <div class="input-field">
                                                                    <input id="search" type="search" required
                                                                           placeholder="Enter meeting name...">
                                                                    <label for="search"><i
                                                                            class="material-icons">search</i></label>
                                                                    <i class="material-icons">close</i>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </nav>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="collapsible-header"><i class="material-icons">query_builder</i>By
                                                time
                                                frame
                                            </div>
                                            <div class="collapsible-body">
                                                <div class="container">
                                                    <nav class="grey darken-1">
                                                        <div class="nav-wrapper">
                                                            <form class=searchform>
                                                                <div class="input-field">
                                                                    <input type="search" required id="fromdatepicker"
                                                                           placeholder="Click to choose FROM date">
                                                                    <label for="fromdatepicker"><i
                                                                            class="material-icons">search</i></label>
                                                                    <i class="material-icons">close</i>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </nav>

                                                    <nav class="grey darken-1">
                                                        <div class="nav-wrapper">
                                                            <form class=searchform>
                                                                <div class="input-field">
                                                                    <input type="search" required id="todatepicker"
                                                                           placeholder="Click to choose TO date "
                                                                           class="black-text">
                                                                    <label for="todatepicker"><i
                                                                            class="material-icons">search</i></label>
                                                                    <i class="material-icons">close</i>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </nav>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="collapsible-header"><i class="material-icons">perm_contact_calendar</i>By
                                                participants
                                            </div>
                                            <div class="collapsible-body">
                                                <div class="container">
                                                    <nav class="grey darken-1">
                                                        <div class="nav-wrapper">
                                                            <form class=searchform>
                                                                <div class="input-field">
                                                                    <input id="searchparticipant" type="search" required
                                                                           placeholder="Enter participant name...">
                                                                    <label for="search"><i
                                                                            class="material-icons">search</i></label>
                                                                    <i class="material-icons">close</i>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </nav>
                                                </div>

                                                <div class="container participant-container">
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="col s12 m6">
                                <div class="icon-block grey lighten-4">
                                    <h2 class="center black-text margin1"><i class="material-icons">description</i></h2>
                                    <h5 class="center">Reports</h5>

                                    <div class="responsive-height">
                                        <p class="light">
                                            <c:forEach items="${resultMap}" var="item">
                                                <t:shortMeetingInfo lastMeeting="${item.key}"
                                                                    participants="${item.value}"/>
                                            </c:forEach>
                                        </p>
                                    </div>
                                </div>
                            </div>

                        </c:when>
                        <c:otherwise>
                            <h4 class="center">Nothing reported!</h4>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <script>
            $(function () {
                var participants = [];
                <c:forEach items="${allusers}" var="user">
                participants.push('${user}');
                </c:forEach>
                $("#searchparticipant").autocomplete({
                    source: participants
                });

                $('#searchparticipant').bind("autocompleteselect", function (event, ui) {
                    event.stopPropagation();
                    event.preventDefault();
                    $('#searchparticipant').val('');
                    if (ui.item) {
                        var contains = false;
                        $('.participant-container').find('.chip').each(function (index, item) {
                            if ($(this).text().trim().indexOf(ui.item.value) !== -1) {
                                contains = true;
                            }
                        });

                        if (!contains) {
                            var chip = $('<div class="chip">');
                            var close = $('<i class="material-icons">');
                            var span = $('<span>');
                            span.text(ui.item.value);
                            close.text('close');
                            chip.append(span);
                            chip.append(close);
                            $('.participant-container').append(chip);

                            doFilter();
                        }
                    }
                });
            });
        </script>
    </jsp:body>
</t:page>