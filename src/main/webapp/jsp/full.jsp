<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="meeting" class="com.projects.savethemeeting.objectmodel.Meeting" scope="request"/>
<jsp:useBean id="participants" type="java.util.List" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="comments" type="java.util.List" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="actualUser" class="com.projects.savethemeeting.objectmodel.User" scope="request"/>

<t:page>
    <jsp:body>
        <div class="container">
            <div class="section">

                <!--   Icon Section   -->
                <div class="row">
                    <div class="col s12 m12">
                        <div class="icon-block grey lighten-4">
                            <h2 class="center black-text margin1"><i class="material-icons">description</i></h2>
                            <p class="light"><t:fullMeeting meeting="${meeting}" participants="${participants}" comments="${comments}" actualUser="${actualUser}"/></p>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <script>
            $(function () {
                $('.reply').bind('click', function () {
                    if ($('#comment-field').length>0) {
                        return;
                    }
                    var commentId = $(this).attr("id");
                    var comment1 = $('<li class="collection-item avatar">');
                    var comment2 = $('<img src="<c:url value="/resources/images/person-icon.png"/>" alt="" class="circle">');
                    var comment3 = $('<h4 class="title">${actualUser.name} <img class="right close-comment" width="20px" height="20px" src="<c:url value="/resources/images/close.png"/>" alt="reply" ></h4>');
                    var comment4 = $(' <div class="input-field">');
                    var comment5 = $('<input placeholder="Comment..." id="comment-field" type="text" class="validate">');
                    var comment6 = $('<div> </li>');

                    comment1.append(comment2);
                    comment1.append(comment3);
                    comment1.append(comment4);
                    comment1.append(comment5);
                    comment1.append(comment6);

                    $(this).next('.collection').append(comment1);

                    $('.close-comment').bind('click', function() {
                       $(this).parent().parent().remove() ;
                    });


                    $('#comment-field').keyup(function(e){
                        var input = $(this);
                        if (e.keyCode == 13) { //enter
                            input.attr('disabled','disabled');
                            $('.close-comment').hide();
                            $.ajax({
                                url: '/api/comment',
                                type: 'POST',
                                dataType: 'json',
                                data: {
                                    mid: '${meeting.idMeeting}',
                                    uid: '${actualUser.fbID}',
                                    text: $(this).val(),
                                    pid: commentId
                                }
                            }).done(function() {
//                                location.reload();
                                input.replaceWith('<p>'+input.val()+'</p>');
                                $('.close-comment').remove();
                            }).fail(function() {
                                console.error("Error while adding new comment :(");
                                input.removeAttr('disabled');
                                $('.close-comment').show();
                            });
                            <%--post('/comment/', {mid: ${meeting.idMeeting}, uid: ${actualUser.fbID}, text: $(this).text(), pid: commentId});--%>
                        }
                    });
                });

            });
        </script>

    </jsp:body>
</t:page>

