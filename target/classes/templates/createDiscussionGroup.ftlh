<!DOCTYPE html>
<html>
   <head>
      <title>MyPLS Create Discussion Group</title>
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
               <li  class="active"><a href="/HomepageAdmin">Home</a></li>
               <li><a href="/Signout">Sign Out</a></li>
            </ul>
         </div>
      </nav>
      <div class= "container">
         <div class= "header">
            <h2>Create Discussion Group</h2>
         </div>

         <div class="container">
            <form action= "/HomepageAdmin/CreateDiscussionGroup" method ="Post">
               <#if blankSpaces??>
               <p style="color:red;">Invalid Input! Please re-enter values properly.</p>
               </#if>
               <#if topicUnique??>
                 <p style="color:red;">Discussion topic already created.</p>
                 </#if>
               <div class="form-group">
                  <label for="topic">Topic : </label>
                  <input type ="text" name="topic" class="form-control" required>
               </div>
               <div class="form-group">
                  <label>Type :</label>
                  <select class="form-control" name="type" id="type">
                     <option>Public</option>
                     <option>Private</option>
                  </select>
               </div>
               <div class="form-group">
                  <label>Related Course :</label>
                  <select class="form-control" name="relatedCourse" id="relatedCourse">
                     <option>None</option>
                     <#list courses as course>
                     <option>${course.name} Course ID: ${course.courseID?replace(",","")} </option>
                     </#list>
                  </select>
               </div>
               <button class="btn btn-success" type = "submit" name ="reg_user"> Submit </button>
            </form>
         </div>
      </div>
   </body>
</html>