<!DOCTYPE html>
<html>
<head>
	<title>MyPLS Registration</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>


	<div class= "container">
		
	<div class= "header">

		<h2>Register</h2>

	</div>

	<div class="container">

	<form action= "registration" method ="post">


    <div class="form-group">

			<label for="username">First Name : </label>
			<input type ="text" name="fname" class="form-control" required>

		</div>

		<div class="form-group">

			<label for="username">Last Name : </label>
			<input type ="text" name="lname" class="form-control" required>

		</div>

    <div class="form-group">

			<label for="email">Email : </label>
			<input type ="email" name="email" class="form-control" required>
			
		</div>


    <div class="form-group">

			<label for="password">Password : </label>
			<input type ="password" name="password_1" class="form-control" required>
			
		</div>

    <div class="form-group">

			<label for="password">Confirm Password: </label>
			<input type ="password" name="password_2" class="form-control" required>
			
		</div>

		<div class="form-group">
			<label>Role</label>
			<select class="form-control" name="role" id="role">
				<option>Select Role</option>
				<option>Student</option>

				<option>Staff</option>
				<option>Alumni</option>
				<option>Faculty</option>
				<option>Professor</option>
			</select>
		</div>

		<button class="btn btn-success" type = "submit" name ="reg_user"> Submit </button>




</form>
</div>

</div>
	

</body>
</html>