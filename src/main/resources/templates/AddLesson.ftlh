<!DOCTYPE html>
<html>
<head>
	<title>MyPLS Add Lesson</title>
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
           <li  class="active"><a href="/HomepageProf">Home</a></li>
           <li><a href="/Signout">Sign Out</a></li>
        </ul>
      </div>
    </nav>


	<div class= "container">

	<div class= "header">

		<h2>Add Lesson</h2>

	</div>

	<div class="container">

	<form action= "/AddLesson" method ="Post" enctype='multipart/form-data' >

        <label>Course Names :${course.name}         Course ID:${course.courseID?replace(",", "")}  </label>
        <input type ="hidden" name="courseID" value="${course.courseID}">
    <div class="form-group">
        <label for="title">Lesson Title : </label>
		<input type ="text" name="title" class="form-control" required>
	</div>

	 <div class="form-group">
            <label for="requirement"> Requirements : </label>
    		<textarea name="requirement" class="form-control"  rows="10" cols="500"> </textarea>
    	</div>

    <div class="form-group">
                <label for="document"> Add Lesson Slides : </label>
        		<input type ="file" name="document" class="form-control" >
        	</div>

     <div class="form-group">
                 <label for="media"> Add Lesson Video: </label>
         		<input type ="file" name="medi" class="form-control" >
         	</div>

		<button class="btn btn-success" type = "submit" name ="reg_user"> Submit </button>

</form>
</div>

</div>


</body>
</html>