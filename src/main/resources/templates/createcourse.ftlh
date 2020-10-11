<!DOCTYPE html>
<html>
<head>
	<title>MyPLS Create Course</title>
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
          <li><a href="/">Back to login</a></li>
        </ul>
      </div>
    </nav>


	<div class= "container">

	<div class= "header">

		<h2>Create Course</h2>

	</div>

	<div class="container">

	<form action= "createCourse" method ="post">


    <div class="form-group">
        <label for="username">Course Name : </label>
		<input type ="text" name="courseName" class="form-control" required>
	</div>


     <div class="form-group">
			<label>Assign Professor :</label>
			<select class="form-control" name="type" id="type">
				<option>Select Role</option>
				<option> Prof. John Doe jon.doe@rit.edu</option>

			</select>
	</div>

		<button class="btn btn-success" type = "submit" name ="reg_user"> Submit </button>

</form>
</div>

</div>


</body>
</html>