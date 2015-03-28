<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js">
	
</script>
<script>
	//we are reusing this URL, somove it to a variable
	base_url = "http://localhost:8080/Mirchi/rest/type";
	meta_url = "http://localhost:8080/Mirchi/rest/type/metadata/";
	$(document)
			.ready(
					function() {
						$
								.getJSON(
										meta_url,
										function(data) {
											$
													.each(
															data,
															function(key, value) {
																$(
																		"div.song_form")
																		.append(
																				"<br/>Please enter "
																						+ key
																						+ ":"
																						+ "<input type='text' name='"+key+"'"+">");
															});
										});
						$.fn.serializeObject = function() {
							var o = {};
							var a = this.serializeArray();
							$.each(a, function() {
								if (o[this.name]) {
									if (!o[this.name].push) {
										o[this.name] = [ o[this.name] ];
									}
									o[this.name].push(this.value || '');
								} else {
									o[this.name] = this.value || '';
								}
							});
							return o;
						};
						$('#song_form').submit(function() {
							console.log($('#song_form').serializeObject());
							postData($('#song_form').serializeObject());
							return false;
						});
						function postData(data) {
							$
									.ajax({
										type : "POST",
										url : "http://localhost:8080/Mirchi/rest/type",
										data : JSON.stringify(data),
										contentType : "application/json; charset=utf-8",
										crossDomain : true,
										dataType : "json",
										success : function(data, status, jqXHR) {
											alert("success");
										},
										error : function(jqXHR, status) {
											console.log(jqXHR);
											alert("success");
										}
									});
						}
					});
</script>
</head>
<body>
	<h1 style="text-align: center;">Create a new Type</h1>
	<form id="song_form">
		<div class="song_form">
			Type : <input type="text" name="type"><br /> Rate : <input
				type="text" name="rate"><br />
		</div>
		<input type="Submit">
	</form>
	<div >
		<a href="update.jsp"><INPUT TYPE="BUTTON" VALUE="Update Type"></a><br />
		<a href="alltypes.jsp"><INPUT TYPE="BUTTON" VALUE="Get all Types"></a><br />
		<a href="typebyid.jsp"><INPUT TYPE="BUTTON" VALUE="Get Type by id"></a><br />
		<a href="delete.jsp"><INPUT TYPE="BUTTON" VALUE="Delete Tppe"></a><br />
	</div>
</body>
</html>