<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Michaela
  Date: 30.03.2016
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Login</title>
    <link rel="icon" href="<c:url value="/resources/images/favicon.ico"/>"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no">
    <meta charset="UTF-8">

    <%--CSS--%>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="<c:url value="/resources/css/materialize.min.css"/>"
          media="screen,projection"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" media="screen,projection"/>

    <%--JS--%>
    <script src="<c:url value="/resources/js/jquery-2.2.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/blockUI.js"/>"></script>
    <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
</head>
<body>
<script>
    var first = false;
    function statusChangeCallback(response) {
        if (response.status === 'connected') {
            first = true;
            customBlockUI();
            loginToWebApp();
        }
    }

    function customBlockUI() {
        $.blockUI({
            message: '<div class="container">' +
            '<p>Please wait</p>' +
            '<div class="preloader-wrapper big active">' +
            '<div class="spinner-layer spinner-blue">' +
            '<div class="circle-clipper left">' +
            '<div class="circle"></div>' +
            '</div><div class="gap-patch">' +
            '<div class="circle"></div>' +
            '</div><div class="circle-clipper right">' +
            '<div class="circle"></div>' +
            '</div>' +
            '</div>' +
            '</div>',
            fadeIn: 0,
            fadeOut: 0,
            showOverlay: true,
            centerY: true,
            centerX: true,
            css: {
                width: '350px',
                left: '50%',
                transform: 'translate(-50%, 0)',
                border: 'none',
                padding: '5px',
                backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
                opacity: .95,
                color: '#fff'
            }
        });
    }

    function unblockUI() {
        $.unblockUI();
    }

    window.fbAsyncInit = function () {
        customBlockUI();
        FB.init({
            appId: '980184545362030',
            cookie: true,
            xfbml: true,
            version: 'v2.5'
        });

        unblockUI();
        FB.getLoginStatus(function (response) {
            statusChangeCallback(response);
        });
    };

    (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/sk_SK/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    function loginToWebApp() {
        if(first) {
            unblockUI();
        } else {
            customBlockUI();
        }
        FB.api('/me', function (response) {
            $('#userinput').val(encodeURIComponent(response.name));
            $("#userid").val(response.id);
            if(first) {
                $('#continueModal').openModal();
                first = false;
            } else {
                $('#loginForm').submit();
            }
        });
    }
</script>

<div class="container">
    <div class="row center-align">
        <div class="col s12 card ">
            <div class="card-content">
                <div class="card-image">
                    <div class="container">
                        <img src="<c:url value="/resources/images/backround1.jpg"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="col s12 center card-title">
                        <h4>Login</h4>
                        <h5>Please login with your facebook account.</h5>
                    </div>

                    <div class="col s12 center">
                        <div class="fb-login-button"
                             data-max-rows="1"
                             data-size="xlarge"
                             data-show-faces="false"
                             data-auto-logout-link="false"
                             data-scope="public_profile,email"
                             onlogin="loginToWebApp()"
                        >
                        </div>
                    </div>
                </div>

                <div class="hidden">
                    <form action="<c:url value="/login"/>"
                          method="post"
                          id="loginForm">
                        <input id="userinput" name="fbId" type="text" hidden>
                        <input id="userid" name="user" type="text" hidden>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="continueModal" class="modal">
    <div class="modal-content">
        <h4>Already logged in</h4>
        <p>We've detected, that you are already logged into your facebook account. Continue to dashboard?
        Click outside this window to close.</p>
    </div>
    <div class="modal-footer">
        <a href="javascript:$('#loginForm').submit()" class=" modal-action modal-close waves-effect waves-green btn-flat">Continue</a>
    </div>
</div>

</body>
</html>
