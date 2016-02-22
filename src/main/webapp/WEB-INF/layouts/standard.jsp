<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="windows-1252" />
	<title>Save the meeting</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/javascript/dijit/themes/tundra/tundra.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/screen.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/print.css" />" type="text/css" media="print" />
	<!--[if lt IE 8]>
	        <link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection" />
	<![endif]-->
	<link rel="stylesheet" href="<c:url value="/resources/styles/stm.css" />" type="text/css" media="screen" />
    <script type="text/javascript" src="<c:url value="/resources/javascript/dojo/dojo.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/spring/Spring.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/spring/Spring-Dojo.js" />"></script>
</head>
<body class="tundra">
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