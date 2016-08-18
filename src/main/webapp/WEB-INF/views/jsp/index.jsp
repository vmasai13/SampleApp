<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
	<head>		
		<link rel="stylesheet" href="../static/css/bootstrap.min.css">
		<link rel="stylesheet" href="../static/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="../static/css/jquery-ui.css">
		<link rel="stylesheet" href="../static/css/style.css"></link>
		<link rel="stylesheet" href="../static/bootstrap/datepicker/1.4.0/dist/css/bootstrap-datepicker3.css" rel="stylesheet">
		
		<!-- <script type="text/javascript" src="../static/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="../static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../static/jquery/jquery-ui.js"></script> -->
	</head>
	<body>
		<div class="midLay">
			<div class="arrow">
				<div>
					<div class="row">
						<div class="col-md-6">
							<a href="billLog">
							    <div class="thumbnail indexthumb">
									<img src="../static/img/report-writing.jpg" alt="" style="min-height:160px;height:160px;min-width:286px;width:386px;">
									<div class="caption" align="center">
										<h4 class="title-text">Bill logs</h4>
										<p><h5 class="title-text">See the details of bill logs.</h5></p>
									</div>
								</div>
							</a>								
						</div>
						<div class="col-md-6">
							<a href="selectPurchaseOrder">
								<div class="thumbnail indexthumb">
									<img src="../static/img/chipandpin.jpg" alt="" style="min-height:160px;height:160px;min-width:286px;width:386px;">
									<div class="caption" align="center">
									    <h4 class="title-text">Process Purchase Order's</h4>
									    <p><h5 class="title-text">To process the new purchase order's</h5></p>
									</div>
								</div>
							</a>								
						</div>
					</div>
					<div class="headerbottompadding"></div>
					<div class="row">
						<!-- <div class="col-md-4">
							<a href="kioskSeriesConfiguration">
							    <div class="thumbnail indexthumb">
							        <img src="../static/img/KLM_kiosks.jpg" alt="" style="min-height:100px;height:100px;min-width:160px;width:160px;">
							      	<div class="caption" align="center">
								        <h4 class="title-text">Kiosk Series Configuration</h4>
								        </p><h5 class="title-text">Configure the kiosk series data which is used to calculate the various reports.</h5></p>
								    </div>
							    </div>
							</a>								
						</div>
						 <div class="col-md-4">
							<a href="settings">
								<div class="thumbnail indexthumb">
								    <img src="../static/img/settings.jpg" alt="" style="min-height:100px;height:100px;min-width:160px;width:160px;">
								    <div class="caption" align="center">
									    <h4 class="title-text">Settings</h4>
									    <p><h5 class="title-text">Settings for this application.</h5></p>
									</div>
								</div>
							</a>								
						</div>
						<div class="col-md-4">
							<a href="about">
								<div class="thumbnail indexthumb">
								    <img src="../static/img/AirFrance_KLM_Tails.jpg" alt="" style="min-height:100px;height:100px;min-width:160px;width:160px;">
								    <div class="caption" align="center">
									    <h4 class="title-text">About</h4>
									    <p><h5 class="title-text">Information about this application.</h5></p>
									</div>
								</div>
							</a>								
						</div> -->
					</div>
				</div>
			</div>
		</div>
		<div class="arrow headerbottompadding">			
			<div class="hdRht" align="right">
				<button type="button" style="visibility: hidden;" class="btn btn-primary btn-lg div-create-scenario" onclick="window.location='index';">Back</button>				
			</div>
		</div>
	</body>
</html>