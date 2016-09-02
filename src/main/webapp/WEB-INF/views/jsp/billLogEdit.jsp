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
<link href="../static/bootstrap/css/docs.css" rel="stylesheet">

<!-- Bootstrap javaScript -->
<script src="../static/bootstrap/js/bootstrap.js" type="text/javascript"></script>

<!-- JTable -->
<link href="../static/jtable/themes/lightcolor/gray/jtable.css" rel="stylesheet" type="text/css" />
<script src="../static/jtable/jquery.jtable.min.js" type="text/javascript"></script>

<!-- Custom styles for this template -->
<link href="../static/css/fancylog.css" rel="stylesheet">

<style type="text/css" id="holderjs-style"></style>

<title>Bill log</title>

<script type="text/javascript">
 
    $(document).ready(function () {
 
        $('#BillLogContainer').jtable({
            title: 'Bill log',
            sorting: true,
            paging: true,
            pageSize: 10,
            defaultSorting: 'PO ASC',
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
                    title: 'Bill Month',
                    width: '5%',
                    sorting: true
                },
                won: {
                    title: 'Won',
                    sorting: false,
                },
                projectType: {
                	title: 'Type',
                	sorting: true
                },
                project: {
                	title: 'Project',
                	sorting: true
                },
                customerInvoiceNumber: {
                	title: 'KLM Inv Num',
                	sorting: true
                },
                klmPm: {
                	title: 'KLM PM',
                	sorting: true
                },
                milestoneStatus: {
                	title: 'Milestone Stat',
                	options: { 'a': '-select-', 'b': 'Submitted', 'c': 'Approved', 'd':'Clarity required'},
                	sorting: true
                },
                invoiceStatus: {
                	title: 'Invoice Stat',
                	options: {'a': '-select-', 'b': 'Create', 'c': 'Submitted', 'd':'Paid'},
                	sorting: true
                },
                poNumber: {
                	title: 'PO',
                	sorting: true
                },
                clientInvoiceDate: {
                	title: 'Milestone date',
                	sorting: true
                },
                item: {
                	title: 'Item.no',
                	sorting: true
                },
                quantity: {
                	title: 'Qty',
                	sorting: true
                },
                pricePerUnit: {
                	title: 'Price per unit',
                	sorting: true
                },
                milestoneValue: {
                	title: 'Amount',
                	sorting: true
                },
                milestoneValuewithVAT: {
                	title: 'Amount With VAT',
                	sorting: true
                },
                description: {
                    title: 'MilestoneDesc',
                    width: '18%',
                    sorting: false
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
                },
                remarks: {
                    title: 'Remarks',
                    sorting: false
                }/* ,
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