<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js">
</script>
<script>
//we are reusing this URL, somove it to a variable
base_url="http://localhost:8080/Mirchi/rest/type/";
$(document).ready(function(){
    $('#song_form').submit(function(){
    	deleteData($('.ids').val());
    	return false;
    });
    function deleteData(ids){
        $.ajax({
                type: "DELETE",
                url: base_url+ids,
                success: function (data, status, jqXHR) {
                    alert("deleted");
                },
                error: function (jqXHR, status) {
                    console.log(status);
                    alert('failed, please check console for errors');
                }
             });
       }
});
</script>
</head>
<body>
<h1 style="text-align:center;">Delete a Song</h1>
<form id="song_form" method="POST">
Enter the Id of the song to be deleted: <input type="text" class="ids">

<input type="Submit">
</form>
</body>
</html>