<!DOCTYPE html>
<html>
   <head>
      <title> MyPLS Homepage </title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
      <style>
         body {margin:0;}
         .navbar {
         overflow: hidden;
         background-color: #333;
         position: fixed;
         top: 0;
         width: 100%;
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
      </style>
   </head>
   <body>
      <nav class="navbar navbar-inverse">
         <div class="container-fluid">
            <div class="navbar-header">
               <a class="navbar-brand" href="#">MyPLS</a>
            </div>
            <ul class="nav navbar-nav">
               <li class="active"><a href="#">Home</a></li>
               <li><a href="#">Page 1</a></li>
               <li><a href="#">Page 2</a></li>
               <li><a href="signout">Sign Out</a></li>
            </ul>
         </div>
      </nav>
      <div class="container">
      <div class="well">
         <h2 class="text-center"> Welcome Admin  ! </h2>
      </div>
      <div class="row">
         <div class="col-md-12" style="border: 1px solid; padding: 20px; margin: 5px 0px;">
            <a href="/homepageadmin/createCourse">Create Course</a>
            <div><input class="pull-right" type="form-control" placeholder="Search" name=""></div>
            <div class="text-center"><button class="btn btn-primary"> Courses </button>
            </div>
         </div>
         <#if (courseCount>0)>
         <div>
            <table style="width:50%"  class="table table-hover" >
               <tr>
                  <th>Course Name</th>
                  <th>Rating</th>
                  <th>Options</th>

               </tr>
               <#list courses as course>
               <tr>
                  <td>${course.name}</td>
                  <td>${course.rating}</td>
                  <td>
                     <a href='homepageadmin/updateCourse/${course.courseID?replace(",", "")}'>Update Course</a>
                     <form action="/homepageadmin/deleteCourse" method="post">
                     	<input type="hidden" name="courseid" value="${course.courseID?replace(",", "")}">
                     	<input type="submit" value="Delete Course">
                     </form>
                  </td>
               </tr>
               </#list>
            </table>
            </#if>
         </div>
         <div class="row">
            <div class="col-md-6">
               <div class="row">
                  <div style="border: 1px solid; padding: 20px; height: 80px; margin: 5px 0px;">
                     <button class="btn btn-primary">
                        User Administration</>
                  </div>
               </div>
               <div class="row">
               <div style="border: 1px solid; padding: 20px; height: 80px; margin: 5px 0px;"><button class="btn btn-primary">Feedbacks</>


               </div>
               <table style="width:50%"  class="table table-hover" >
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

                  <table style="width:50%"  class="table table-hover" >
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
            <div class="col-md-6">
            <div style="border: 1px solid; padding: 20px; height: 160px; margin: 5px 0px;"><button class="btn btn-primary">Ratings</button></div>
            </div>
         </div>
      </div>
   </body>
</html>