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
               <li  class="active"><a href="/HomepageAdmin">Home</a></li>
               <li><a href="/Signout">Sign Out</a></li>
            </ul>
         </div>
      </nav>
      <div class= "container">
         <div class= "header">
            <h2>Create Course</h2>
         </div>
         <div class="container">
            <form action= "/HomepageAdmin/CreateCourse" method ="post">
               <#if blankSpaces??>
               <p style="color:red;">Invalid Input! Please re-enter values properly.</p>
               </#if>
               <div class="form-group">
                  <label for="courseName">Course Name : </label>
                  <input type ="text" name="courseName" class="form-control" required>
               </div>
               <div class="form-group">
                  <label for="requirement"> Requirments : </label>
                  <textarea name="requirement" class="form-control"  rows="10" cols="500"> </textarea>
               </div>
               <div class="form-group">
                  <label for="objectives"> Learning Objectives : </label>
                  <textarea name="objectives" class="form-control" rows="10" cols="500" > </textarea>
               </div>
               <div class="form-group">
                  <label for="outcomes"> Learning Outcomes : </label>
                  <textarea name="outcomes" class="form-control" rows="10" cols="500" > </textarea>
               </div>
               <div class="form-group">
                  <label>Assign Pre-requisite :</label>
                  <select class="form-control" name="prerequisite" id="prerequisite">
                     <option>None</option>
                     <#list courses as course>
                     <option>${course.name} Course ID: ${course.courseID?replace(",","")} </option>
                     </#list>
                  </select>
               </div>
               <div class="form-group">
                  <label>Assign Professor :</label>
                  <select class="form-control" name="professor" id="professor">
                     <#list professors as professor>
                     <option>${professor.firstName} ${professor.lastName} Professor ID: ${professor.professorID}</option>
                     </#list>
                  </select>
               </div>
               <button class="btn btn-success" type = "submit" name ="reg_user"> Submit </button>
            </form>
         </div>
      </div>
   </body>
</html>