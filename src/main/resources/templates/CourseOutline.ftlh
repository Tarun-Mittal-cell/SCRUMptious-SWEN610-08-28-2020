<!DOCTYPE html>
<html>
   <head>
      <title> MyPLS Homepage </title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
      <style>
         #delete {
         background: none!important;
         border: none;
         padding: 0!important;
         font-family: arial, sans-serif;
         color: #069;
         cursor: pointer;
         }
      </style>
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
      <div class="container">
      <div class="well">
         <h2 class="text-center"> ${course.courseID?replace(",", "")} ${course.name} </h2>
      </div>
      <div class="row">
      <div class="col-md-12" style="border: 5px solid; padding: 20px; margin: 5px 0px;">
         <div class="text-center">
            <div>
               <center>
                  <h3>Objectives</h3>
               </center>
               <p>${course.objectives}</p>
               <center>
                  <h3>Outcomes</h3>
               </center>
               <p>${course.outcomes}</p>
               <center>
                  <h3>Requirements</h3>
               </center>
               <p>${course.requirements}</p>
               <center>
                  <h3>Lessons</h3>
               </center>
               <br>
               <table  class="table table-hover table-striped table-bordered" >
                  <thead class="thead-dark">
                     <tr>
                        <th scope="col">Lesson Title</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Requirements</th>
                     </tr>
                  </thead>
                  <#list course.lessons as lesson>
                  <tr>
                     <td>${lesson.title}</td>
                     <td>${lesson.rating}</td>
                     <td>${lesson.requirements}</td>
                  </tr>
                  </#list>
               </table>
            </div>
         </div>
      </div>
   </body>
</html>