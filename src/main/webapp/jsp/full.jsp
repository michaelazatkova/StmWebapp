<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="lastSession" class="com.projects.savethemeeting.objectmodel.Meeting" scope="request"/>
<jsp:useBean id="participants" type="java.util.List" class="java.util.ArrayList" scope="request"/>

<t:page>
    <jsp:body>
        <div class="container">
            <div class="section">

                <!--   Icon Section   -->
                <div class="row">
                    <div class="col s12 m12">
                        <div class="icon-block grey lighten-4">
                            <h2 class="center black-text margin1"><i class="material-icons">description</i></h2>

                            <p class="light"><t:fullMeeting meeting="${lastMeeting}"
                                                                participants="${participants}"/></p>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </jsp:body>
</t:page>

