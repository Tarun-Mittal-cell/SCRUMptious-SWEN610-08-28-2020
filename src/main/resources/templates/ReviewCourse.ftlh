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
         <h2 class="text-center"> Review Course </h2>
      </div>
      <div class="row">
         <div class="col-md-12" style="border: 5px solid; padding: 20px; margin: 5px 0px;">

                <form action="/HomepageLearner/ViewCourse/ReviewCourse" method="post">
                <h4> Rate Professor</h4>
                 <label for="profRating">Prof. ${professor.firstName} ${professor.lastName}</label>
                            <select name="profRating" >
                                <option disabled="disabled" selected="selected">Select Rating</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                             </select>

               <h4> Rate Lessons</h4>
             <table   class="table table-hover table-striped table-bordered" >
               <thead class="thead-dark">
                  <tr>
                     <th>Lessson Title</th>
                     <th>Rating</th>
                  </tr>
               </thead>
              <tbody>
                  <#list course.lessons as lesson>
                       <tr>
                          <td>${lesson.title}</td>
                          <td>
                            <select name="LessonRating${lesson?counter}" >
                                <option disabled="disabled" selected="selected">Select rating</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                             </select>
                          </td>

                  </#list>
             </table>
             <h4> Rate Course</h4>
             <label for="courseRating">${course.name}</label>
              <select name="courseRating" >
                <option disabled="disabled" selected="selected">Select rating</option>
                 <option value="1">1</option>
                 <option value="2">2</option>
                 <option value="3">3</option>
                 <option value="4">4</option>
                 <option value="5">5</option>
              </select>
              <br>
              <button buttontype = "submit" > Submit </button>
              <#if blanks??>
                <p style="color:red;"> Please fill out all review item.</p>
              </#if>
             </form>
            </div>
         </div>
      </div>
   </body>
</html>