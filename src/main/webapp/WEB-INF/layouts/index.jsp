<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>Save the meeting</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

    <script src="https://connect.soundcloud.com/sdk/sdk-3.0.0.js"></script>
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

<%--<p>soundloud user: User 790524602--%>
<%--client id: cb5ef3b1acde0f9998eafecfb2356678--%>
<%--client secret: 598edc71dc99298c8c25c65a9d2f3b8a--%>
<%--End User Authorization--%>

<%--https://soundcloud.com/connect--%>
<%--Token--%>

<%--https://api.soundcloud.com/oauth2/token--%>
<%--Save app</p>--%>

<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container">
        <a id="logo-container" href="/" class="brand-logo">Save the meeting</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="/"><i class="material-icons left">library_books</i>My meeting reports</a></li>
        </ul>

        <ul id="nav-mobile" class="side-nav">
            <li><a href="#">My meeting reports</a></li>
        </ul>
        <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
</nav>
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <br><br>
        <h1 class="header center cyan-text">Save the meeting</h1>
        <div class="row center">
            <h5 class="header col s12 light">All your meeting reports in one place</h5>
        </div>
        <br><br>

    </div>
</div>
<div class="container">
    <div class="section">

        <!--   Icon Section   -->
        <div class="row">
            <div class="col s12 m6">
                <div class="icon-block">
                    <tiles:insertAttribute name="last"/>
                </div>

            </div>

            <div class="col s12 m6">
                <div class="icon-block">
                    <tiles:insertAttribute name="meetinglist"/>
                </div>

            </div>

        </div>
        <br><br>

        <div class="section">

        </div>
    </div>

    <footer class="page-footer cyan lighten-2">
        <div class="container">

        </div>
        <div class="footer-copyright">
            <div class="container">
                Made by Michaela Zaùkov·
            </div>
        </div>
    </footer>


    <!--  Scripts-->
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="/resources/js/materialize.js"></script>
    <script src="/resources/js/init.js"></script>

</body>
</html>
