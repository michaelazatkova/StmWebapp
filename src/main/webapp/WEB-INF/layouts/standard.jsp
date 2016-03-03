<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="windows-1252" />
	<title>Save the meeting</title>

	<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/screen.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/print.css" />" type="text/css" media="print" />

	<link rel="stylesheet" href="<c:url value="/resources/styles/stm.css" />" type="text/css" media="screen" />
    <script type="text/javascript" src="<c:url value="/resources/javascript/soundcloud.player.api.js" />"></script>
	<script src="https://connect.soundcloud.com/sdk/sdk-3.0.0.js"></script>
	<script>
		SC.initialize({
			client_id: 'cb5ef3b1acde0f9998eafecfb2356678',
			redirect_uri: 'http://example.com/callback'
		});
	</script>

</head>
<body class="tundra">
<script>
	window.fbAsyncInit = function() {
		FB.init({
			appId      : '1729291490636063',
			xfbml      : true,
			version    : 'v2.5'
		});
	};

	(function(d, s, id){
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) {return;}
		js = d.createElement(s); js.id = id;
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

<div id="page" class="container">
	<div id="header">
		<div id="logo">
			<p>
				<a href="<c:url value="/" />">
					<img src="<c:url value="/resources/images/header_picture.jpg"/>" alt="Spring Travel" class="span-24" />
				</a>
			</p>
		</div>
	</div>
	<div id="content">
		<div id="local" class="span-6">
			<tiles:insertAttribute name="menu" />
			<div
					class="fb-like"
					data-share="true"
					data-width="450"
					data-show-faces="true">
			</div>
		</div>
		<div id="main" class="span-18 last">
			<tiles:insertAttribute name="body" />
			<%--<script type="text/javascript">--%>
				<%--(function(){--%>
					<%--var widgetIframe = document.getElementById('sc-widget'),--%>
							<%--widget       = SC.Widget(widgetIframe),--%>
							<%--newSoundUrl = 'http://api.soundcloud.com/tracks/13692671';--%>

					<%--widget.bind(SC.Widget.Events.READY, function() {--%>
						<%--// load new widget--%>
						<%--widget.bind(SC.Widget.Events.FINISH, function() {--%>
							<%--widget.load(newSoundUrl, {--%>
								<%--show_artwork: false--%>
							<%--});--%>
						<%--});--%>
					<%--});--%>

				<%--}());--%>
			<%--</script>--%>
			<iframe class="iframe" width="100%" height="465" scrolling="no" frameborder="no" src="https://w.soundcloud.com/player/?url=http://api.soundcloud.com/users/790524602/favorites">
			</iframe>
		</div>
	</div>
	<hr />
	<div id="footer">
		<small>© Copyright 2016, Michaela Zaťková</small>
	</div>
</div>
</body>
</html>