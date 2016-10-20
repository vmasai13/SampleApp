<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<title>Sample App</title>
<style type="text/css" title="currentStyle">
@import "https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css";
@import "https://cdn.datatables.net/buttons/1.2.1/css/buttons.dataTables.min.css";
@import "https://cdn.datatables.net/select/1.2.0/css/select.dataTables.min.css";
@import "https://editor.datatables.net/extensions/Editor/css/editor.dataTables.min.css";
</style>
<script type="text/javascript" language="javascript"
	src="https://code.jquery.com/jquery-1.12.3.js"></script>
<script type="text/javascript" language="javascript"
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" language="javascript"
	src="https://cdn.datatables.net/buttons/1.2.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" language="javascript"
	src="https://cdn.datatables.net/select/1.2.0/js/dataTables.select.min.js"></script>

	
	
	
<script type="text/javascript" language="javascript" src="../static/others/js/sampleApp.js"></script>
	
<script type="text/javascript" charset="utf-8">
var editor; // use a global for the submit and return data rendering in the examples

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
        ajax: "./getBillLogData",
        table: "#billLog",
        fields: [ {
                label: "billingMonth: ",
                name: "billingMonth"
            }, {
                label: "won:",
                name: "won"
            }, {
                label: "projectType:",
                name: "projectType"
            }, {
                label: "project:",
                name: "project"
            }, {
                label: "customerInvoiceNumber:",
                name: "customerInvoiceNumber"
            }, {
                label: "klmPm:",
                name: "klmPm"
            }, {
                label: "milestoneStatus:",
                name: "milestoneStatus"
            }, {
                label: "invoiceStatus:",
                name: "invoiceStatus"
            }, {
                label: "poNumber:",
                name: "poNumber"
            }, {
                label: "clientInvoiceDate:",
                name: "clientInvoiceDate"
            }, {
                label: "item:",
                name: "item"
            }, {
                label: "quantity:",
                name: "quantity"
            }, {
                label: "pricePerUnit:",
                name: "pricePerUnit"
            }, {
                label: "milestoneValue:",
                name: "milestoneValue"
            }, {
                label: "milestoneValuewithVAT:",
                name: "milestoneValuewithVAT"
            }, {
                label: "milestoneValuewithVAT:",
                name: "milestoneValuewithVAT"
            }, {
                label: "remarks:",
                name: "remarks"
            }
        ]
    } );
 
    $('#billLog').DataTable( {
        dom: "Bfrtip",
        ajax: "../php/staff.php",
        columns: [
            { data: null, render: function ( data, type, row ) {
                // Combine the first and last names into a single table field
                return data.first_name+' '+data.last_name;
            } },
            { data: "position" },
            { data: "office" },
            { data: "extn" },
            { data: "start_date" },
            { data: "salary", render: $.fn.dataTable.render.number( ',', '.', 0, '$' ) }
        ],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
} );
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
