/*	Function to add a new row in billLog.jsp */
function fnClickAddRow() {
	$('#billLog').dataTable().fnAddData( [
	                                      "",
	                                      "",
	                                      "T&M",
	                                      "" ] );
}