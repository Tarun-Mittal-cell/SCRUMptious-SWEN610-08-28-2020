<!DOCTYPE html>
<html>
   <head>
      <title>MyPLS Update Lesson</title>
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
               <li  class="active"><a href="/HomepageProf">Home</a></li>
               <li><a href="/Signout">Sign Out</a></li>
            </ul>
         </div>
      </nav>
      <div class= "container">
         <div class= "header">
            <h2>Add Lesson</h2>
         </div>
         <div class="container">
<form action= "/HomepageProf/ViewCourse/UpdateLesson" method ="POST" enctype='multipart/form-data' >
               <label>Course Name :${course.name}         Course ID:${course.courseID?replace(",", "")}  </label>
               <input type ="hidden" name="courseID" value="${course.courseID?replace(",", "")}">
               <#if blankSpaces??>
                               <p style="color:red;">Invalid Input! Please re-enter values properly.</p>
                           </#if>
               <div class="form-group">
                  <label for="title">Lesson Title : </label>
                  <input type ="text" name="title" class="form-control" value= "${lesson.title}" required>
               </div>
               <div class="form-group">
                  <label for="requirement"> Requirements : </label>
                  <textarea name="requirement" class="form-control"  rows="10" cols="500">${lesson.requirements} </textarea>
               </div>
               <div class="form-group">
                  <label for="uploaded_pdf"> Update Lesson PDF : </label>
                  <input type ="file" name="uploaded_pdf"  accept='.pdf'>
               </div>
               <div class="form-group">
                  <label for="uploaded_media">  Update Lesson Video: </label>
                  <input type ="file" name="uploaded_media"  accept='.mp4'>
               </div>

               <button class="btn btn-success" type = "submit" name ="reg_user"> Submit </button>
            </form>
            <Br>
            <Br>
            <form action= "/HomepageProf/ViewCourse/UpdateLesson/DeleteMedia" method ="POST"  >
            <div class="form-group">
            <div class="form-group">
                  <label for="delete_document"> Delete Lesson PDF : </label>
                  <input type ="checkbox" name="delete_document"  value="${lesson.documentPath}">
                    </div>
                  <label for="delete_media"> Delete Lesson Video : </label>
                  <input type ="checkbox" name="delete_media"  value="${lesson.mediaPath}">
               </div>
               <button class="btn btn-success" type = "submit" name ="reg_user"> Delete </button>
            </form>
         </div>
      </div>
   </body>
</html>