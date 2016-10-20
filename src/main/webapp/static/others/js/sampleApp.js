/*	Function to add a new row in billLog.jsp */
function fnClickAddRow() {
	$('#billLog').dataTable().fnAddData( [
	                                      "",
	                                      "",
	                                      "T&M",
	                                      "" ] );
}

function actionStatus(color, msg) {
	 $('#actionStatus').append('<div style="font-style: bold;color: '+ color +';"> '+ msg + '</div>');
}