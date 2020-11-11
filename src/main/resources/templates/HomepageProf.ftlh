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
               <li><a href="/Signout">Sign Out</a></li>
            </ul>
         </div>
      </nav>
      <div class="container">
      <div class="well">
         <h2 class="text-center"> Welcome Professor ${userData.lastName} ! </h2>
      </div>
      <div class="row">
      <div class="col-md-12" style="border: 1px solid; padding: 20px; margin: 5px 0px;">
         <div><input class="pull-right" type="form-control" placeholder="Search" name=""></div>
         <div class="text-center">
            <div>
               <#if courses??>
               <center>
                  <h2>Courses</h2>
               </center>
               <br>
               <table style="width:100%"  class="table table-hover table-striped table-bordered" >
                  <thead class="thead-dark">
                     <tr>
                        <th scope="col">Course Name</th>
                        <th scope="col">Enrollment</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Options</th>
                     </tr>
                  </thead>
                  <#list courses as course>
                  <tr>
                     <td style="width:50%">${course.name}</td>
                     <td style="width:10%">${course.enrollment}</td>
                     <td style="width:10%">${course.rating}</td>
                     <td style="width:30%" >
                        <a href='HomepageProf/ViewCourse/${course.courseID?replace(",", "")}'>View Course</a> |
                        <a href='HomepageProf/AddLesson/${course.courseID?replace(",", "")}'>Add Lesson</a>
                     </td>
                  </tr>
                  </#list>
               </table>
               <#else>
                        <h3> No Assigned Courses</h3>
                 </#if>
            </div>
         </div>
         <div class="row">
            <div class="col-md-6">
               <div class="row">
                  <div style="border: 1px solid; padding: 20px; height: 80px; margin: 5px 0px;">
                     <button class="btn btn-primary">
                        Assessments</>
                  </div>
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