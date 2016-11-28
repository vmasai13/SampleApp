<html>
<head>
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<!-- JQuery -->
<script src="../static/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>

<script type="text/javascript">

    // Load the Visualization API and the controls package.
    google.charts.load('current', {'packages':['corechart', 'controls']});

    // Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawDashboard);

    // Callback that creates and populates a data table,
    // instantiates a dashboard, a range slider and a pie chart,
    // passes in the data and draws it.
    function drawDashboard() {
    	
/*     	var data1 = google.visualization.arrayToDataTable([
	       ['Name', 'Donuts eaten'],
	       ['Michael' , 5],
	       ['Elisa', 7],
	       ['Robert', 3],
	       ['John', 2],
	       ['Jessica', 6],
	       ['Aaron', 1],
	       ['Margareth', 8]
	     ]); 
	     
	     Can delete this vaiable initialization
	     
	     */
    	
    	var jsonData = $.ajax({
            url: "./getBillLogData",
            dataType: "json",
            async: false
            }).responseText;
    	
    	// Create our data table out of JSON data loaded from server.
        var data = new google.visualization.DataTable(jsonData);
    	
		// Create our data table.
		var data = google.visualization.arrayToDataTable([
	      ['Name', 'Gender', 'Age', 'Donuts eaten'],
	      ['Michael' , 'Male', 12, 5],
	      ['Elisa', 'Female', 20, 7],
	      ['Robert', 'Male', 7, 3],
	      ['John', 'Male', 54, 2],
	      ['Jessica', 'Female', 22, 6],
	      ['Aaron', 'Male', 3, 1],
	      ['Margareth', 'Female', 42, 8],
	      ['Miranda', 'Female', 33, 6]
	    ]);
	
		// Create a dashboard.
		var dashboard = new google.visualization.Dashboard(
				document.getElementById('dashboard_div'));
	
		// Create a range slider, passing some options
		var donutRangeSlider = new google.visualization.ControlWrapper({
			'controlType' : 'NumberRangeFilter',
			'containerId' : 'filter_div',
			'options' : {
				'filterColumnLabel' : 'Donuts eaten'
			}
		});
	
		// Create a pie chart, passing some options
		var pieChart = new google.visualization.ChartWrapper({
			'chartType' : 'PieChart',
			'containerId' : 'chart_div',
			'options' : {
				'width' : 700,
				'height' : 600,
				'pieSliceText': 'label',
				'legend' : 'right'
			},
			'view': {'columns': [0, 3]}
		});
		
		// Establish dependencies, declaring that 'filter' drives 'pieChart',
		// so that the pie chart will only display entries that are let through
		// given the chosen slider range.
		// dashboard.bind(donutRangeSlider, pieChart);
		
		// For "table_div" implementation
		var categoryPicker = new google.visualization.ControlWrapper({
	      'controlType': 'CategoryFilter',
	      'containerId': 'categoryPicker_div',
	      'options': {
	        'filterColumnIndex': 1,
	        'ui': {
	          'labelStacking': 'vertical',
	          'label': 'Gender Selection:',
	          'allowTyping': false,
	          'allowMultiple': false
	        }
	      }
	    });
		
		 var table = new google.visualization.ChartWrapper({
	      'chartType': 'Table',
	      'containerId': 'table_div',
	      'options': {
	      }
	    });	 
		dashboard.bind([donutRangeSlider, categoryPicker], [pieChart, table]);
	
		// Draw the dashboard.
		dashboard.draw(data);
	}
</script>
</head>

<body>
	<!--Div that will hold the dashboard-->
	<div id="dashboard_div">
		<!--Divs that will hold each control and chart-->
	<div class="hdRht">
		<button type="button"
			class="btn btn-primary btn-lg div-create-scenario"
			onclick="window.location='index';">Back</button>
	</div>
	
		<table class="columns">
		    <tr>
		      <td>
		        <div id="filter_div" style="padding-left: 15px"></div>
		      </td><td>
		        <div id="categoryPicker_div"></div>
		      </td>
		    </tr><tr>
		      <td>
		        <div id="chart_div" style="padding-top: 15px"></div>
		      </td><td>
		        <div id="table_div" style="padding-top: 30px"></div>
		      </td>
		    </tr>
  		</table>
	</div>

</body>
</html>