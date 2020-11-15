<!DOCTYPE html>
<html>
   <head>
      <title> MyPLS Homepage </title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
      <style>
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
      <div class="container">
      <div class="well">
         <h2 class="text-center"> ${course.name} </h2>
      </div>
      <div class="row">
      <div class="col-md-12" style="border: 1px solid; padding: 20px; margin: 5px 0px;">
         <div><input class="pull-right" type="form-control" placeholder="Search" name=""></div>
         <div class="text-center">
            <div>
               <center>
                  <h2>Lessons</h2>
               </center>
               <br>
               <table style="width:100%"  class="table table-hover table-striped table-bordered" >
                  <thead class="thead-dark">
                     <tr>
                        <th scope="col">Lesson Title</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Status</th>
                        <th scope="col">Quiz Grade</th>
                        <th scope="col">Options</th>
                     </tr>
                  </thead>
                  <#list course.lessons as lesson>
                  <tr>
                     <td >${lesson.title}</td>
                     <td >${lesson.rating}</td>
                     <td >
                          <#if lesson.quiz ??  >
                            <#if lesson.quiz.grade < course.minScore>
                                   Ongoing
                            <#else>
                                   Passed
                            </#if>
                          </#if>
                     </td>
                     <td >
                            <#if lesson.quiz ??  >
                                <#if lesson.quiz.grade == -1 >
                                    -%
                                 <#else>
                                   ${lesson.quiz.grade}%
                                 </#if>
                          </#if>
                     </td>
                     <td >
                        <a href='/HomepageLearner/ViewCourse/ViewLesson/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}'>View Lesson</a>
                         <#if lesson.quiz ?? && (lesson.quiz.grade> -1) >
                            <#if lesson.quiz.grade < course.minScore>
                                | <a href="/HomepageLearner/ViewCourse/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}/TakeQuiz">Retake Quiz</a>
                            </#if>
                         <#elseif lesson.quiz ?? >
                            | <a href="/HomepageLearner/ViewCourse/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}/TakeQuiz">Take Quiz</a>
                         </#if>
                     </td>
                  </tr>
                  </#list>
               </table>
            </div>
         </div>
      </div>
   </body>
</html>