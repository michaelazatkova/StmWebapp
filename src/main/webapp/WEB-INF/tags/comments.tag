<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="comments" type="java.util.List<com.projects.savethemeeting.objectmodel.Comment>" required="true" %>

<h5 class="grey-text darken-2">Comments</h5>
<span class="reply" title="New comment"><img width="30px" height="30px" src="<c:url value="/resources/images/comment.png"/>" alt="reply" ></span>
<ul class="collection">
    <c:forEach items="${comments}" var="comment">
        <c:if test="${comment.parentComment==null}">
            <li class="collection-item avatar">
                <img src="<c:url value="/resources/images/person-icon.png"/>" alt="" class="circle">
                <h4 class="title">${comment.user.name}  <span class="grey-text lighten-2"> ( at <fmt:formatDate value="${comment.timestamp}" pattern="HH:mm dd.MM.yyyy" /> )</span></h4>

                <p>${comment.content}
                </p>

                    <span id="${comment.idComment}" class="secondary-content reply"><img  width="20px" height="20px" src="<c:url value="/resources/images/reply.png"/>" alt="reply" ></span>
                <ul class="collection">
                    <c:forEach items="${comments}" var="innerComment">
                        <c:if test="${innerComment.parentComment == comment}">
                            <li class="collection-item avatar">
                                <img src="<c:url value="/resources/images/person-icon.png"/>" alt="" class="circle">
                                <h4 class="title">${innerComment.user.name}   <span class="grey-text lighten-2"> ( at <fmt:formatDate value="${innerComment.timestamp}" pattern="HH:mm dd.MM.yyyy" /> )</span></h4>

                                <p>${innerComment.content}
                                </p>
                                <span id="${innerComment.idComment}" class="secondary-content reply"><img  width="20px" height="20px" src="<c:url value="/resources/images/reply.png"/>" alt="reply" ></span>
                                <ul class="collection">
                                    <c:forEach items="${comments}" var="innerInnerComment">
                                        <c:if test="${innerInnerComment.parentComment == innerComment}">
                                            <li class="collection-item avatar">
                                                <img src="<c:url value="/resources/images/person-icon.png"/>" alt="" class="circle">
                                                <h4 class="title">${innerInnerComment.user.name}   <span class="grey-text lighten-2"> ( at <fmt:formatDate value="${innerInnerComment.timestamp}" pattern="HH:mm dd.MM.yyyy" /> )</span></h4>

                                                <p>${innerInnerComment.content}
                                                </p>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </li>
        </c:if>
    </c:forEach>
</ul>