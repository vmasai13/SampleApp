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
	
<script type="text/javascript" language="javascript" src="../static/others/js/sampleApp.js"></script>
	
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#billLog').dataTable({
			"paging" : true,
			"ordering" : true,
			"info" : true,
			/* DataTables alternative pagination example */
			"sPaginationType" : "full_numbers",
			/* For Ajax source 
			"sAjaxSource": '../arrays.txt',*/
			"bServerSide" : false, // Processing of the actions at server side,
			"bProcessing" : true,
			"sAjaxSource" : './getBillLogData',
			"aoColumns": [
						{ "mDataProp": "billingMonth" },
						{ "mDataProp": "won" },
						{ "mDataProp": "projectType" },
						{ "mDataProp": "project" },
						{ "mDataProp": "customerInvoiceNumber" },
						{ "mDataProp": "klmPm" },
						{ "mDataProp": "milestoneStatus" }, // { 'a': '-select-', 'b': 'Submitted', 'c': 'Approved', 'd':'Clarity required'},
						{ "mDataProp": "invoiceStatus" }, // {'a': '-select-', 'b': 'Create', 'c': 'Submitted', 'd':'Paid'},
						{ "mDataProp": "poNumber" },
						{ "mDataProp": "clientInvoiceDate" },
						{ "mDataProp": "item" },
						{ "mDataProp": "quantity" },
						{ "mDataProp": "pricePerUnit" },
						{ "mDataProp": "milestoneValue" },
						{ "mDataProp": "milestoneValuewithVAT" },
						{ "mDataProp": "description" },
						{ "mDataProp": "remarks" },
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
	<div class="hdLft">
		<button type="button"
			class="btn btn-primary btn-lg div-create-scenario"
			onclick="fnClickAddRow();">Add New</button>
	</div>
	
	<div id="container">
		<div id="demo">
		
			<table cellpadding="0" cellspacing="0" border="0" class="display"
				id="billLog">
				<thead>
					<tr>
						<th>Bill Month</th>
						<th>Won</th>
						<th>Type</th>
						<th>Project</th>
						<th>KLM Inv Num</th>
						<th>KLM PM</th>
						<th>Milestone Stat</th>
						<th>Invoice Stat</th>
						<th>PO</th>
						<th>Milestone date</th>
						<th>Item.no</th>
						<th>Qty</th>
						<th>Price per unit</th>
						<th>Amount</th>
						<th>Amount With VAT</th>
						<th>MilestoneDesc</th>
					<!-- 	<th>TCS Inv Map1</th>
						<th>TCS Inv Map2</th>
						<th>TCS Inv Map3</th> -->
						<th>Remarks</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="5" class="dataTables_empty">Loading, Please wait ...</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th>Bill Month</th>
						<th>Won</th>
						<th>Type</th>
						<th>Project</th>
						<th>KLM Inv Num</th>
						<th>KLM PM</th>
						<th>Milestone Stat</th>
						<th>Invoice Stat</th>
						<th>PO</th>
						<th>Milestone date</th>
						<th>Item.no</th>
						<th>Qty</th>
						<th>Price per unit</th>
						<th>Amount</th>
						<th>Amount With VAT</th>
						<th>MilestoneDesc</th>
					<!-- 	<th>TCS Inv Map1</th>
						<th>TCS Inv Map2</th>
						<th>TCS Inv Map3</th> -->
						<th>Remarks</th>
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
