<!DOCTYPE html>
<html>
<head>
	<title> MyPLS Login </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class= "container">
		
	<div class= "well">

		<h2 class="text-center" >Welcome to MyPLS</h2>

	</div>

	<form action= "login" method ="post">

    <div class="form-group">

			<label for="username">Email : </label>
			<input type ="text" name="email" class="form-control" required>

			<#if loginStatus??>
			    <#if loginStatus== "EMAIL_INVALID">
			    <#assign loginStatus= "None">
                <p> Email is not registered!
                </#if >
            </#if >
		</div>



    <div class="form-group">

			<label for="password">Password : </label>
			<input type ="password" name="password" class="form-control" required>

			<#if loginStatus??>
			     <#if loginStatus== "PASSWORD_INVALID">
                 <p> Invalid Password!
                 <#assign loginStatus= "None">
                </#if >
            </#if >
			
		</div>

		

		<button class="btn btn-success" type = "submit" name ="login_user"> Log In </button>

		<p>New User?<a href="registration"><b>Register</b></a></p>


</form>

</div>
	

</body>
</html>