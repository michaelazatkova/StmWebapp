
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <title>Save the meeting</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="<c:url value="/resources/css/materialize.css"/>" type="text/css" rel="stylesheet"
          media="screen,projection"/>
    <link href="<c:url value="/resources/css/style.css"/>" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

    <script src="https://connect.soundcloud.com/sdk/sdk-3.0.0.js"></script>
    <script src="<c:url value="/resources/js/jquery-2.2.1.min.js"/>"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
    <script src="<c:url value="/resources/js/common.js"/>"></script>
    <script>
        SC.initialize({
            client_id: 'cb5ef3b1acde0f9998eafecfb2356678',
            redirect_uri: 'http://example.com/callback'
        });
    </script>
</head>
<body>

<script>
    window.fbAsyncInit = function () {
        FB.init({
            appId: '1729291490636063',
            xfbml: true,
            version: 'v2.5'
        });
    };

    (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {
            return;
        }
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>

<nav class="teal" role="navigation">
    <div class="nav-wrapper">
        <a id="logo-container" href="<c:url value="/"/>" class="brand-logo">Save the meeting</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="<c:url value="/reports"/>" class="white-text"><i class="material-icons left">library_books</i>My
                meeting reports</a></li>
        </ul>
    </div>
</nav>

<div id="index-banner" class="parallax-container">
    <div class="section no-pad-bot">
        <div class="container">
            <br><br>

            <h1 class="header center teal-text ">Save the meeting</h1>

            <div class="row center">
                <h5 class="header col s12 teal-text">All your meeting reports in one place</h5>
            </div>
            <br><br>

        </div>
    </div>
    <div class="parallax"><img src="<c:url value="/resources/images/backround1.jpg"/>"
                               alt="Unsplashed background img 1"></div>
</div>

<jsp:doBody/>

<div class="parallax-container valign-wrapper">
    <div class="parallax"><img src="<c:url value="/resources/images/backround2.jpg"/>"
                               alt="Unsplashed background img 2"></div>
</div>

<footer class="page-footer teal">
    <div class="container">

    </div>
    <div class="footer-copyright">
        <div class="container">
            Made by Michaela Zaťková
        </div>
    </div>
</footer>

</body>
</html>
