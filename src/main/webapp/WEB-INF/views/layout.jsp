<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
		
		<link rel="stylesheet" type="text/css" href="../static/bootstrap/dist/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../static/bootstrap/dist/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="../static/others/css/style.css">
		<!-- <link rel="stylesheet" type="text/css" href="../static/css/sticky-footer.css"> -->
		<link rel="stylesheet" type="text/css" href="../static/others/css/layout.css">
		<script>var ISOLDIE=false;</script>
		<!--[if lt IE 9]>
			<script>ISOLDIE=true;</script>
			<meta http-equiv="refresh" content="0;url=http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode" />
    	<![endif]-->
    	<script>
    		if(ISOLDIE)
    			{
    			alert("You are using an old Internet Explorer browser. Please upgrade !");
    			}
    	</script>	    	
	</head>
	<body>
		<div class="wrapper">	
			<div class="cont">
				<div class="hd">	
					<div class="container">
						<div>
							<tiles:insertAttribute name="header" />
							<div class="midLay">
								<div class="arrow">
									 <div align="left">
										<span>
											<span class="glyphicon glyphicon-chevron-right" style="font-size: 26px">
										</span>&nbsp<tiles:insertAttribute name="title" ignore="true" /></span>
									</div>
								</div>
							</div>
							<div class="bottompadding"></div>
							<tiles:insertAttribute name="body" />
						</div>
					</div>
					<%-- <tiles:insertAttribute name="footer" /> --%>
				</div>
			</div>
			<div class="push"></div>
		</div>
		<div class="footer">
            <tiles:insertAttribute name="footer" />
        </div>
	</body>
</html>