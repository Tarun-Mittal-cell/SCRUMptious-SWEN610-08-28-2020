<!DOCTYPE html>
<html>
   <head>
      <title> MyPLS Homepage </title>
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
               <li><a href="/HomepageLearner/SearchCourses">Search Courses</a></li>
               <li><a href="#">Page 2</a></li>
               <li><a href="/Signout">Sign Out</a></li>
            </ul>
         </div>
      </nav>
      <div class="container">
         <div class="well">
            <h2 class="text-center"> Welcome ${userData.firstName}  ! </h2>
         </div>
         <div class="row">
            <div class="col-md-12" style="border: 5px solid; padding: 20px; margin: 5px 0px;">
               <table style="width:100%"  class="table table-hover table-striped table-bordered" >
               <thead class="thead-dark">
                  <tr>
                     <th>Course Name Title</th>
                     <th>Course ID</th>
                     <th>Course Rating</th>
                     <th>Professor Name</th>
                     <th>Professor Rating</th>
                     <th>Options</th>
                  </tr>
               <#if courses??>
                  <#list courses as course>
                       <tr>
                          <td>${course.name}</td>
                          <td>${course.courseID?replace(",", "")}</td>
                          <td>${course.rating}</td>
                          <td>${professors[course?index].firstName} ${professors[course?index].lastName}</td>
                          <td>${professors[course?index].rating}</td>
                          <td>
                               <form action="/HomepageLearner/Search" method="post">
                                   <input type ="register" name="search">
                                   <button class="btn btn-success" type = "submit" > Register </button>
                               </form>
                          </td>
                       </tr>
                  </#list>
               <#elseif course??>
                  <tr>
                     <td>${course.name}</td>
                     <td>${course.courseID?replace(",", "")}</td>
                     <td>${course.rating}</td>
                     <td> Prof. ${professor.firstName} ${professor.lastName}</td>
                     <td>${professor.rating}</td>
                     <td>
                         <form action="/HomepageLearner/CourseRegister" method="post">
                            <input type ="hidden" value="${course.courseID?replace(",", "")}" name="courseID">
                            <button class="btn btn-success" type = "submit" > Register </button>
                         </form>
                          <a href='/HomepageLearner/ViewCourseOutline/${course.courseID?replace(",", "")}'>View Course Outline</a>
                           <#if prereqPassed??>
                                    <p style="color:red;"> Prerequisite ID: ${course.prerequisiteCourseId?replace(",", "")} not Satisfied.</p>
                           </#if>
                     </td>
                  </tr>
              <#else>
                  <h2> No Courses found. </h2>
              </#if>
            </div>
         </div>
      </div>
   </body>
</html>