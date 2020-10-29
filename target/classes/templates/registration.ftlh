<!DOCTYPE html>
<html>
<head>
	<title>MyPLS Registration</title>
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
          <li><a href="/">Back to login</a></li>
        </ul>
      </div>
    </nav>


	<div class= "container">
		
	<div class= "header">

		<h2>Register</h2>

	</div>

	<div class="container">

	<form action= "/Registration" method ="post">


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
            <#if !isEmailUnique>
                <p> Email already assigned to an account!
            </#if>


			
		</div>


    <div class="form-group">

			<label for="password">Password : </label>
			<input type ="password" id="password_1" name="password_1" class="form-control" required>
			
		</div>

    <div class="form-group">

			<label for="password">Confirm Password: </label>
			<input type ="password" id="password_2" name="password_2" class="form-control" required>
			<span id='message'></span>
		</div>

		<div class="form-group">
			<label>Role</label>
			<select class="form-control" name="type" id="type">
				<option>Select Role</option>
				<option>Learner</option>
				<option>Professor</option>
			</select>
		</div>

		<button class="btn btn-success" type = "submit" name ="reg_user"> Submit </button>

		<script>
                $(document).ready(function(){
        		  $("button").hide();
                  $("#password_1,#password_2").on("keyup", function () {
                    if ($("#password_1").val() == $("#password_2").val())
                    {
                        $('#message').html('Matching').css('color', 'green');
                        $("button").show();
                    }
                    else
                    {
                        $('#message').html('Not Matching').css('color', 'red');
                        $("button").hide();
                        }

                  });
                });

                </script>




</form>
</div>

</div>
	

</body>
</html>