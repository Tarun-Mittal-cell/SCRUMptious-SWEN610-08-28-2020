<!DOCTYPE html>
<html>
<head>
	<title> MyPLS Homepage </title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">MyPLS</a>
        </div>
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">Page 1</a></li>
          <li><a href="#">Page 2</a></li>
          <li><a href="signout">Sign Out</a></li>
        </ul>
      </div>
    </nav>


<div class="container">
	
	<div class="well">

			<h2 class="text-center"> Welcome $(userData.userDataName}  ! </h2>



	</div>

	<div class="row">
		<div class="col-md-12" style="border: 1px solid; padding: 20px; margin: 5px 0px;">
			<div><input class="pull-right" type="form-control" placeholder="Search" name=""></div>
			<div class="text-center"><button class="btn btn-primary"> Courses </button>
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<div class="row">
				<div style="border: 1px solid; padding: 20px; height: 80px; margin: 5px 0px;"><button class="btn btn-primary">Quizzes</
					></div>
			</div>
			<div class="row">
				<div style="border: 1px solid; padding: 20px; height: 80px; margin: 5px 0px;"><button class="btn btn-primary">Feedbacks</
					></div>
			</div>
		</div>
		<div class="col-md-6">
			<div style="border: 1px solid; padding: 20px; height: 160px; margin: 5px 0px;"><button class="btn btn-primary">Discussion Group</button></div>
		</div>
	</div>

</div>

</body>
</html>