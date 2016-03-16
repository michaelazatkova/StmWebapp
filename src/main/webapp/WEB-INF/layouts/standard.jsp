<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="windows-1252" />
	<title>Save the meeting</title>
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
	<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

</head>
<body class="tundra">
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>



<div id="page" class="container">
	<div id="header">
		<div id="logo">
			<p>
				<a href="<c:url value="/" />">

				</a>
			</p>
		</div>
	</div>
	<div id="content">
		<div id="local" class="span-6">
			<tiles:insertAttribute name="meetinglist" />
			<div
					class="fb-like"
					data-share="true"
					data-width="450"
					data-show-faces="true">
			</div>
		</div>
		<div id="main" class="span-18 last">
			<tiles:insertAttribute name="last" />


		</div>
	</div>
	<hr />
	<div id="footer">
		<small>© Copyright 2016, Michaela Zaťková</small>
	</div>
</div>
</body>
</html>