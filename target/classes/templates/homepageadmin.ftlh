<!DOCTYPE html>
<html>
   <head>
      <title> MyPLS Homepage </title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
      <style>
         body {margin:0;
         padding-top:100px;
         }
         .navbar {
         overflow: hidden;
         background-color: #333;
         position: fixed !important;
         top: 0;
         width: 100%;
         z-index: 500 ;
         }
         .navbar a {
         float: left;
         display: block;
         color: #f2f2f2;
         text-align: center;
         padding: 14px 16px;
         text-decoration: none;
         font-size: 17px;
         }
         .navbar a:hover {
         background: #ddd;
         color: black;
         }
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
      <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
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
         <h2 class="text-center"> Administrator </h2>
      </div>
      <div class="row">
         <div class="col-md-12" style="border: 5px solid; padding: 20px; margin: 5px 0px;" id="courseDiv">
            <a class="btn btn-primary"  href="/HomepageAdmin/CreateCourse" role="button" >Create Course</a>
            <a class="btn btn-primary" href="/HomepageAdmin/CreateDiscussionGroup" role="button">Create Discussion Group</a>
            <a class="btn btn-primary" href="/DiscussionBoard" role="button">View Discussion Group</a>
            <#if (courseCount>0)>
            <div>
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
                        <a href='HomepageAdmin/UpdateCourse/${course.courseID?replace(",", "")}'>Update Course</a>
                        <form action="/HomepageAdmin/DeleteCourse" method="post">
                           <input type="hidden" name="courseid" value="${course.courseID?replace(",", "")}">
                           <button id="delete" type="submit">Delete Course</button>
                        </form>
                     </td>
                  </tr>
                  </#list>
               </table>
               </#if>
            </div>
         </div>
         <div class="row">
            <div class="col-md-6">
               <div class="row">
                  <div style="border: 5px solid;  margin: 5px 0px; padding: 20px;" id="feedbackDiv">
                     <center>
                        <h2>Learners</h2>
                     </center>
                     <br>
                     <div>
                        <table style="width:100%"  class="table table-hover table-striped table-bordered"  margin-right:10px">
                           <thead class="thead-dark">
                              <tr>
                                 <th>Learner ID </th>
                                 <th>Learner Name</th>
                                 <th>Rating</th>
                              </tr>
                           </thead>
                           <#list learners as learner>
                           <tr>
                              <td>${learner.learnerID} </td>
                              <td>${learner.firstName} ${learner.lastName}</td>
                              <td>${learner.rating} </td>
                           </tr>
                           </#list>
                        </table>
                     </div>
                  </div>
               </div>
            </div>
            <div class="col-md-6">
               <div style="border: 5px solid;  margin: 5px 0px; padding: 20px;">
                  <center>
                     <h2>Professors</h2>
                  </center>
                  <br>
                  <div>
                     <table style="width:100%"  class="table table-hover table-striped table-bordered"  style="float: left" >
                        <tr>
                           <th>Professor ID </th>
                           <th>Professor Name</th>
                           <th>Rating</th>
                        </tr>
                        <#list professors as professor>
                        <tr>
                           <td>${professor.professorID} </td>
                           <td>${professor.firstName} ${professor.lastName}</td>
                           <td>${professor.rating} </td>
                        </tr>
                        </#list>
                     </table>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </body>
</html>