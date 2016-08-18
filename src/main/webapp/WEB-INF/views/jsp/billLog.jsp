<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<title>Sample App</title>
<style type="text/css" title="currentStyle">
@import "../static/datatable/css/demo_page.css";

@import "../static/datatable/css/demo_table.css";
</style>
<script type="text/javascript" language="javascript"
	src="../static/datatable/js/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="../static/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#billLog').dataTable({
			"bPaginate" : true,
			"bLengthChange" : true,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : true,
			"bAutoWidth" : true,
			/* DataTables alternative pagination example */
			"sPaginationType" : "full_numbers",
			/* For Ajax source */
			"sAjaxSource": '../arrays.txt',
			/* "bServerSide" : true,
			"bProcessing" : true,
			"sAjaxSource" : './getBillLogData', */
			"aoColumns": [
						{ "mDataProp": "item" },
						{ "mDataProp": "quantity" },
						{ "mDataProp": "pricePerUnit" },
						{ "mDataProp": "milestoneValue" },
						{ "mDataProp": "milestoneDesc" }
					]
		});
	});
</script>
</head>
<body id="dt_example">

	<div class="arrow">
		<div class="hdRht">
			<button type="button"
				class="btn btn-primary btn-lg div-create-scenario"
				onclick="window.location='index';">Back</button>
		</div>
	</div>

	<div id="container">
		<div id="demo">
			<table cellpadding="0" cellspacing="0" border="0" class="display"
				id="billLog">
				<thead>
					<tr>
						<th>Rendering engine</th>
						<th>Browser</th>
						<th>Platform(s)</th>
						<th>Engine version</th>
						<th>CSS grade</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="5" class="dataTables_empty">Loading, Please wait ...</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th>Rendering engine</th>
						<th>Browser</th>
						<th>Platform(s)</th>
						<th>Engine version</th>
						<th>CSS grade</th>
					</tr>
				</tfoot>
			</table>
		</div>

		<div id="footer" class="clear" style="text-align: center;">
			<span style="font-size: 10px;"> </span>
		</div>
	</div>
</body>
</html>
