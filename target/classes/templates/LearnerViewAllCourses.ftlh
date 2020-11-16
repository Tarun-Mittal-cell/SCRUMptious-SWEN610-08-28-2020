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
         <h2 class="text-center"> Course Offering </h2>
      </div>
      <div class="row">
       <table id="table1">
                  <thead class="thead-dark">
                     <tr>
                        <th id="th1">Course Name</th>
                        <th id="th1">Enrollment</th>
                        <th id="th1">Rating</th>
                     </tr>
                  </thead>
                  <#list courses as course>
                  <tr>
                     <td id="td1">${course.name}</td>
                     <td id="td1">${course.enrollment}</td>
                     <td id="td1">${course.rating}</td>
                  </tr>
                  </#list>
               </table>
      </div>
   </body>
</html>