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
          background-image: url("/Images/cool.jpeg");

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
           /*optional*/
           font-family: arial, sans-serif;
           /*input has OS specific font-family*/
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
               <li><a href="signout">Sign Out</a></li>
            </ul>
         </div>
      </nav>
      <div class="container">
      <div class="well">
         <h2 class="text-center"> Welcome Admin  ! </h2>
      </div>
      <div class="row">
         <div class="col-md-12" style="border: 5px solid; padding: 20px; margin: 5px 0px;" id="courseDiv">

            <a class="btn btn-primary"  href="/homepageadmin/createCourse" role="button" >Create Course</a>
            <a class="btn btn-primary" href="/homepageadmin/createDiscussionGroup" role="button">Create Discussion Group</a>
            <a class="btn btn-primary" href="/DiscussionBoard" role="button">View Discussion Group</a>
            <#if (courseCount>0)>
            <div>
                <center><h2>Courses</h2></center>
                <br>
               <table style="width:100%"  class="table table-hover table-striped table-bordered" >
                  <tr>
                     <th>Course Name</th>
                     <th>Enrollment</th>
                     <th>Rating</th>
                     <th>Options</th>
                  </tr>
                  <#list courses as course>
                  <tr>
                     <td style="width:50%">${course.name}</td>
                     <td style="width:10%">${course.enrollment}</td>
                     <td style="width:10%">${course.rating}</td>
                     <td style="width:30%" >
                        <a href='homepageadmin/updateCourse/${course.courseID?replace(",", "")}'>Update Course</a>
                        <form action="/homepageadmin/deleteCourse" method="post">
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
                  <div style="border: 1px solid;  margin: 5px 0px; padding: 20px;" id="feedbackDiv">
                  <center><h2>Learners</h2></center>
                  <br>
                  <div>
                     <table style="width:100%"  class="table table-hover table-striped table-bordered"  margin-right:10px">
                        <tr>
                           <th>Learner ID </th>
                           <th>Learner Name</th>
                           <th>Rating</th>
                        </tr>
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
               <div style="border: 1px solid;  margin: 5px 0px; padding: 20px;">
               <center><h2>Professors</h2></center>
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