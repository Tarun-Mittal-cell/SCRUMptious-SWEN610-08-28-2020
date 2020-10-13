<!DOCTYPE html>
<html>
<head>
	<title>MyPLS Update Course</title>
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
           <li  class="active" ><a href="/homepageadmin">Home</a></li>
           <li><a href="/signout">Sign Out</a></li>
        </ul>
      </div>
    </nav>


	<div class= "container">

	<div class= "header">

		<h2>Update Course</h2>

	</div>

	<div class="container">

	<form action= "updateCourse" method ="post">


    <div class="form-group">
        <label for="courseName">Course Name :  </label>
		<input type ="text" name="courseName" class="form-control" value="${course.name} "required>
	</div>

	 <div class="form-group">
            <label for="requirement"> Requirments : </label>
    		<textarea name="requirement" class="form-control"  rows="10" cols="500"> ${course.requirements} </textarea>
    	</div>

    <div class="form-group">
                <label for="objectives"> Learning Objectives : </label>
        		<textarea name="objectives" class="form-control" rows="10" cols="500" >${course.objectives} </textarea>
        	</div>

     <div class="form-group">
                 <label for="outcomes"> Learning Outcomes : </label>
         		<textarea name="outcomes" class="form-control" rows="10" cols="500" > ${course.outcomes}</textarea>
         	</div>

    <div class="form-group">
    			<label>Assign Pre-requisite :</label>
    			<select class="form-control" name="prerequisite" id="type">

    			</select>
    	</div>



     <div class="form-group">
			<label>Assign Professor :</label>
			<select class="form-control" name="professor" id="professor" >
				<option>Select Professor</option>
				<#list professors as professor>
				    <#if course.assignedProfessorId==professor.professorID>
                        <option selected >ID:${professor.professorID} ${professor.firstName} ${professor.lastName}  ${professor.email}</option>
                    <#else>
                        <option>ID:${professor.professorID} ${professor.firstName} ${professor.lastName}  ${professor.email} </option>
                    </#if>
                </#list>

			</select>
	</div>

		<button class="btn btn-success" type = "submit" name ="reg_user"> Submit </button>

</form>
</div>

</div>


</body>
</html>