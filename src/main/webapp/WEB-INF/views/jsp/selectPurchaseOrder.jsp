<!DOCTYPE html>
<html>
<script>
var x;
function myFunction(){
    x = document.getElementById("myFiles");
    var txt = "";
    if ('files' in x) {
        if (x.files.length == 0) {
            txt = "";
        } else {
            for (var i = 0; i < x.files.length; i++) {
                txt += "<br><strong>" + (i+1) +". </strong>";
                var file = x.files[i];
                if ('name' in file) {
                    txt += file.name + "<br>";
                }
            }
        }
    }
    else {
        if (x.value == "") {
            txt += "";
        } else {
            txt += "The files property is not supported by your browser!";
            txt  += "<br>The path of the selected file: " + x.value; // If the browser does not support the files property, it will return the path of the selected file instead.
        }
    }
    document.getElementById("status").innerHTML = txt;
    document.getElementById("filePath").innerHTML = x.value;
}

function onSubmit() {
	document.getElementById("status").innerHTML = x.files.length;
}
</script>
<body onload="myFunction();">

	<div class="arrow">
		<div class="hdRht">
			<button type="button"
				class="btn btn-primary btn-lg div-create-scenario"
				onclick="window.location='index';">Back</button>
		</div>
	</div>
	
<form method="post" action="/SampleApp/app/getBillLogData" name="submit" enctype="multipart/form-data">
	<input type="file" id="myFiles" multiple size="50" onchange="myFunction();" accept=".pdf"/>
	<div><p id="status"></p></div>
	<input type="hidden" id="filePath" value=""/>
	<input type="submit" value="Send">
</form>

<div><p id="all"></p></div>


</body>
</html>
