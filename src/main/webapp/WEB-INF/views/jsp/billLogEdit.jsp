<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- JQuery -->
<script src="../static/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="../static/jquery/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<link href="../static/jquery/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css" />

<!-- Bootstrap core CSS -->
<link href="../static/bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- Bootstrap javaScript -->
<script src="../static/bootstrap/dist/js/bootstrap.js" type="text/javascript"></script>

<!-- JTable -->
<link href="../static/jtable/themes/lightcolor/gray/jtable.css" rel="stylesheet" type="text/css" />
<script src="../static/jtable/jquery.jtable.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../static/jtable/extensions/jquery.jtable.editinline.js"></script>
<script type="text/javascript" src="../static/jtable/extensions/jquery.jtable.toolbarsearch.js"></script>
<script type="text/javascript" src="../static/others/js/sampleApp.js"></script>


<style type="text/css" id="holderjs-style"></style>

<title>Bill log</title>

<script type="text/javascript">
 
    $(document).ready(function () {
 
        $('#BillLogContainer').jtable({
            title: 'Bill log',
            sorting: true,
            paging: true,
            pageSize: 10,
            selecting: true, // For selecting feature
            multiselect : true, // For multi-select
            selectingCheckboxes: true, // For showing check-box for selection
            //selectOnRowClick: true,
            jqueryuiTheme: true, // For the theme effect
            defaultSorting: 'projectType',
            editinline:{enable:true},
            toolbarsearch : false,
            toolbar: {
                items: [/* {
                    icon: '/images/excel.png',
                    text: 'Export to Excel',
                    click: function () {
                        //perform your custom job...
                    }
                }, */{
                    icon: '../static/others/img/pdf.png',
                    text: 'Create',
                    tooltip: 'Create Invoice',
                    click: function () {
                    	var $selectedRows = $('#BillLogContainer').jtable('selectedRows');
                        $('#actionStatus').empty();
                        var selectedIDs = '';
                        var record;
                        if ($selectedRows.length > 0) {
                            //Show selected rows
                            $selectedRows.each(function () {
                                record = $(this).data('record');
                                if (null != record.customerInvoiceNumber && "" != record.customerInvoiceNumber) {
                                	 if (selectedIDs) {
                                     	selectedIDs = selectedIDs + "," + record.id;
                                     } else {
                                     	selectedIDs = record.id;
                                     }
                                }
                               
                               /*  $('#actionStatus').append(
                                    '<b>Id: </b>: ' + record.id +
                                    '<br /><b>PO number: </b>:' + record.poNumber + '<br /><br />'
                                    ); */
                            	});
                               // If selected ID's have customer invoice number, please create invoice
                               if (selectedIDs) {
                               	$.ajax({
                               		url: "../app/billlogtableentry/createInvoice",
                               		data:{ selectedIDs : selectedIDs},
                               		error: function() {
                               			$('#actionStatus').append('failure');
                               		},
                               		success: function(data) {
                               			$('#actionStatus').append(data.Result);
                               		}                    		
                               	})
                               } else {
                               	var msg = "Please select Invoice(s) with invoice number for invoice creation.";
                               	actionStatus('red', msg);
                               }
                            
                        } else {
                            //No rows selected
                            var msg = "No row selected! Please select atleast one.";
                        	actionStatus('red', msg);
                        }
                    }
                },
                {
                    icon: '../static/others/img/copy.png',
                    text: 'Copy',
                    tooltip: 'Copy Milestones',
                    click: function () {
                    	var $selectedRows = $('#BillLogContainer').jtable('selectedRows');
                        $('#actionStatus').empty();
                        var selectedIDs = '';
                        var record;
                        if ($selectedRows.length > 0) {
                            //Show selected rows
                            $selectedRows.each(function () {
                                record = $(this).data('record');
                               	 if (selectedIDs) {
                                 	selectedIDs = selectedIDs + "," + record.id;
                                 } else {
                                 	selectedIDs = record.id;
                                 }
                               
                               /* $('#actionStatus').append(
                                    '<b>Id: </b>: ' + record.id +
                                    '<br /><b>PO number: </b>:' + record.poNumber + '<br /><br />'
                                    ); */
                            });
                            if (selectedIDs) {
                            	$.ajax({
                            		url: "../app/billlogtableentry/copyMilestone",
                            		data:{ selectedIDs : selectedIDs},
                            		error: function() {
                            			$('#actionStatus').append('failure');
                            		},
                            		success: function(data) {
                            			$('#actionStatus').append(data.Result);
                            		}                    		
                            	})
                            } else {
                            	var msg = "Please select Invoice(s) with invoice number for invoice creation.";
                            	actionStatus('red', msg);
                            }
                        } else {
                            //No rows selected
                            var msg = "No row selected! Please select atleast one.";
                        	actionStatus('red', msg);
                        }
                    }
                }]
            },
            actions: {
                listAction: '../app/billlogtableentry',
                deleteAction: '../app/billlogtableentry/delete',
                updateAction: '../app/billlogtableentry/save',
                createAction: '../app/billlogtableentry/new'
            },
            fields: {
                id: {
                    key: true,
                    create: false,
                    edit: false,
                    list: false
                },
                billingMonth: {
                    title: 'BillMonth',
                    width: '5%',
                    sorting: true
                },
                won: {
                    title: 'Won',
                    sorting: false,
                },
                projectType: {
                	title: 'Type'
                },
                project: {
                	title: 'Project',
                	sorting: true
                },
                customerInvoiceNumber: {
                	title: 'KLMInvNum',
                },
                klmPm: {
                	title: 'KLMPM',
                	sorting: true
                },
                milestoneStatus: {
                	title: 'MilestoneStat',
                	options: { '': '', 'Check': 'Check', 'Submitted': 'Submitted', 'Approved': 'Approved', 'Clarity req':'Clarity req'},
                	sorting: true
                },
                invoiceStatus: {
                	title: 'InvoiceStat',
                	options: {'': '', 'Create': 'Create', 'Submitted': 'Submitted', 'Paid':'Paid'},
                	sorting: true
                },
                poNumber: {
                	title: 'PO',
                	sorting: true
                },
                clientInvoiceDate: {
                	title: 'Date',
                	sorting: true
                },
                item: {
                	title: 'Itemno',
                	sorting: true
                },
                quantity: {
                	title: 'Qty',
                	sorting: true
                },
                pricePerUnit: {
                	title: 'PPU',
                	sorting: true
                },
                milestoneValue: {
                	title: 'Amt',
                	sorting: true
                },
                milestoneValuewithVAT: {
                	title: 'AmtWithVAT',
                	sorting: true
                },
                description: {
                    title: 'Description',
                    width: '18%',
                    sorting: false
                },
                remarks: {
                    title: 'Remarks',
                    sorting: true,
                    type:'textarea'
                },
                tcsInvoiceMapping1: {
                    title: 'TCS Inv Map1',
                    sorting: false,
                    visibility:'hidden'
                },
                tcsInvoiceMapping2: {
                    title: 'TCS Inv Map2',
                    sorting: false,
                    visibility:'hidden'
                },
                tcsInvoiceMapping3: {
                    title: 'TCS Inv Map3',
                    sorting: false,
                    visibility:'hidden'
                }
                 /* ,
                userName: {
                	title: 'Username',
                	sorting: false,
                	visibility:'hidden'
                },
                passWord: {
                	title: 'Password',
                	sorting: false,
                	visibility:'hidden'
                } */
             }
        });
 
        //Load all records when page is first shown
        $('#BillLogContainer').jtable('load');
    });
 
</script>
</head>
<body>

<div id="actionStatus"></div>

<div class="bs-example">
	<div id="BillLogContainer"></div>
</div>

<div class="hdRht">
	<button type="button"
		class="btn btn-primary btn-lg div-create-scenario"
		onclick="window.location='index';">Back</button>
</div>
</body>
</html>