<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring Boot - Consuming web service</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
.loader {
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 120px;
  height: 120px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>

</head>
<body>
	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<h2 class="text-center">Spring Boot Consuming Web Service</h2>
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Get Country By Country Calling Code</div>
				</div>
				<div class="panel-body">
						<div class="row">
							<div class="col-md-3">
								<label>Country Calling code</label>
							</div>
							<div class="col-md-1"><label>:</label></div>
							<div class="col-md-6">
								<input type="text" id="code" required>
							</div>
						</div>
						<div id="countryDetails" style="display:none">
							<div class="row">
								<div class="col-md-3">
								<label>Country Name</label>
								</div>
								<div class="col-md-1"><label>:</label></div>
								<div class="col-md-6" id="name">
							</div>
							</div>
							<div class="row">
								<div class="col-md-3">
								<label>Capital</label>
								</div>
								<div class="col-md-1"><label>:</label></div>
								<div class="col-md-6" id="capital">
							</div>
							</div>
							<div class="row">
								<div class="col-md-3">
								<label>Continent</label>
								</div>
								<div class="col-md-1"><label>:</label></div>
								<div class="col-md-6" id="region">
							</div>
							</div>
							<div class="row">
								<div class="col-md-3">
								<label>Population</label>
								</div>
								<div class="col-md-1"><label>:</label></div>
								<div class="col-md-6" id="population">
							</div>
							</div>
						</div>
						<div class="row text-danger" style="display:none" id="err"><center><label id="errorDiv"></label></center></div>
						</div>
						<center><button type="button" class="btn btn-info">Submit</button></center>
				</div>
			</div>
		</div>
	</div>
	
	<div id="myModal" class="modal fade" role="dialog" style="top:35%;left:45%">

    <div class="modal-body loader">
    </div>

</div>
	
</body>
<script>
$(document).ready(function(){
	  $("button").click(function(){
		  $("#err").css("display","none");
			$("#errorDiv").html('');
		  $("#myModal").modal({
			  backdrop: 'static',
		      keyboard: false
		  });
		  $.ajax({
				type : 'GET',
				url : '${pageContext.request.contextPath}/api/getCountry',
				data : {code : $('#code').val()},
				success : function(data) {
					if (data != null && data.toUpperCase() == "FALSE") {
						$("#myModal").modal('hide');
						$("#countryDetails").css("display","none");
						$("#err").css("display","block");
						$("#errorDiv").html('Country code does not exist');
					} else {
						$("#myModal").modal('hide');
						var tmpData = JSON.parse(data);
						$("#countryDetails").css("display","block");
						$("#name").html(tmpData.name);
						$("#capital").html(tmpData.capital);
						$("#region").html(tmpData.region);
						$("#population").html(tmpData.population);
					}
				},
				error : function(e) {
					$("#myModal").modal('hide');
					result = false;
				}

			});
	  });
	});
</script>
</html>