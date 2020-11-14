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
            <div>
                <form action="/Homepage/Search" method="post">
                    <input type ="number" name="search">
                    <button class="btn btn-success" type = "submit" > Search </button>
                </form>

             </div>

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
               </thead>
              <tbody>
                <#if registeredCourses??>
                  <#list registeredCourses as course>
                       <tr>
                          <td>${course.name}</td>
                          <td>${course.courseID?replace(",", "")}</td>
                          <td>${course.rating}</td>
                          <td>${courseProfessors[course?index].firstName} ${courseProfessors[course?index].lastName}</td>
                          <td>${courseProfessors[course?index].rating}</td>
                          <td>
                               <a href='HomepageLearner/ViewCourse/${course.courseID?replace(",", "")}'>View Course</a>
                          </td>
                       </tr>
                  </#list>
                </#if>
              </tbody>
             </table>
            </div>
         </div>
      </div>
   </body>
</html>