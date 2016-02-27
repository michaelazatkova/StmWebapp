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
	<script type="text/javascript">
		soundcloud.addEventListener('onPlayerReady', function(player, data) {
			player.api_play();
		});
	</script>
</head>
<body class="tundra">

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
			<object height="81" width="100%" id="cb5ef3b1acde0f9998eafecfb2356678" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000">
				<param name="movie" value="http://player.soundcloud.com/player.swf?url=http%3A%2F%2Fsoundcloud.com%2Fmatas%2Fhobnotropic&enable_api=true&object_id=cb5ef3b1acde0f9998eafecfb2356678"></param>
				<param name="allowscriptaccess" value="always"></param>
				<embed allowscriptaccess="always" height="81" src="http://player.soundcloud.com/player.swf?url=http%3A%2F%2Fsoundcloud.com%2Fmatas%2Fhobnotropic&enable_api=true&object_id=cb5ef3b1acde0f9998eafecfb2356678" type="application/x-shockwave-flash" width="100%" name="cb5ef3b1acde0f9998eafecfb2356678"></embed>
			</object>
		</div>
		<div id="main" class="span-18 last">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<hr />
	<div id="footer">
		<small>© Copyright 2016, Michaela Zaťková</small>
	</div>
</div>
</body>
</html>