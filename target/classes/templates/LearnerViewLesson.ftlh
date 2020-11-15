<!DOCTYPE html>
<html>
   <head>
      <title> MyPLS  </title>
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
               <li class="active"><a href="/HomepageLearner">Home</a></li>
               <li><a href="/Signout">Sign Out</a></li>
            </ul>
         </div>
      </nav>
      <div class="container">
         <div class="well">
            <h1 class="text-center">  ${course.name}  </h1>
            <h2  class="text-center">${lesson.title}  </h2>
         </div>
         <center>
            <#if lesson.documentPath !=" ">
            <div class="row">
               <iframe src="/${lesson.documentPath}" width="100%" height="1000px">
               </iframe>
            </div>
            <#else>
            <h3>No Document has been added.</h3>
            </#if>
            <#if lesson.documentPath !=" ">
            <div class="row">
               <video width="640" height="480" controls>
                  <source src="/${lesson.mediaPath}" type="video/mp4">
               </video>
            </div>
            <#else>
            <h3>No Media has been added.</h3>
            </#if>
         </center>
      </div>
   </body>
</html>