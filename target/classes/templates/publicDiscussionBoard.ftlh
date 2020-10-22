<!DOCTYPE html>
<html>
	<head>
		<title> Discussion Group </title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	</head>
	<style>
		hr 
		{ 
			margin: 0.3em auto; 
		}
	</style>
	<body>
		<nav class="navbar navbar-inverse">
              <div class="container-fluid">
                <div class="navbar-header">
                  <a class="navbar-brand" href="#">MyPLS</a>
                </div>
                <ul class="nav navbar-nav">
                   <li  class="active"><a href="/homepageadmin">Home</a></li>
                   <li><a href="/signout">Sign Out</a></li>
                </ul>
              </div>
            </nav>

		<div class="container">	
			<div class="well">
				<h2 class="text-center">Discussion Group</h2>
			</div>

			<div class="row overflow-auto">
				<div class="col-md-12 text-right" style="border: 1px solid; padding: 20px; height:160px; margin: 5px 0px; overflow: scroll;   display: flex;
				flex-direction: column-reverse;">
					<h5 id="chatBoxValue" style="margin:0px; padding: 0px;">
					<#if textFromUser??>
					    ${textFromUser?no_esc}
					</#if>
					</h5>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12" style="border: 1px solid; padding: 20px; margin: 5px 0px;">
				    <form action="/DiscussionBoard" method="Post">
					<input type="text" class="form-control" id="textFromUser" name="textFromUser">
					<button class="btn btn-primary pull-right" style="margin:2px" value="Submit" >Send</button>
					 </form>
				</div>
			</div>
		</div>
	</body>
</html>