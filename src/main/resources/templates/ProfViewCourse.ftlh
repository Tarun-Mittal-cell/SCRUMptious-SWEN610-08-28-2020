<!DOCTYPE html>
<html>
   <head>
      <title> MyPLS Homepage </title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
      <style>
      body {
        background-color: white;
        font-family: 'Lucida Sans Unicode';
        font-size: 18px;
        margin-bottom: 70px;
      }

      #buttonx {
        background-color: #EC7063;
        border: none;
        color: white;
        text-align: center;
        display: inline-block;
        font-size: 18px;
        cursor: pointer;
        height: 45px;
        width : 350px;
        padding: 10px;
        margin: auto;
      }

      #nav1 {
        height: 70px;
        width: 100%;
        border-style: none;
        background-color: #5DADE2;
        padding: 9px 10px;
      }

      h2{
        font-family: 'Courier New';
        font-weight: bold;
      }

      #a1 {
        background-color: #5DADE2;
        font-family: 'Lucida Sans Unicode';
        color: black;
      }

      #txtbox {
        font-size: 14pt;
        height: 40px;
        width : 700px;
      }

      #div_align {
        position: relative;
        padding: 7px;
        border: 5px;
        border-style: double;
        border-color: black;
        margin: auto;
      }

      #table1 {
        width: 100%;
        border-width: thin;
        border-style: groove;
        border-color: black;
      }

      #td1 {
        background-color: #D6EAF8;
        border-width: thin;
        border-style: groove;
        border-color: black;
        text-align: left;
        height: 40px;
        width: 50px;
        padding: 10px;
      }

      #th1 {
        background-color: #2471A3;
        border-width: thin;
        border-style: groove;
        border-color: black;
        color: white;
        text-align: center;
        height: 40px;
        width: 50px;
        padding: 10px;
      }

      #delete {
        background: none!important;
        border: none;
        padding: 0!important;
        color: #069;
        cursor: pointer;
      }

      #footerx {
        position: fixed;
        left: 0;
        bottom: 0;
        height: 40px;
        width: 100%;
        background-color: #5DADE2;
        color: white;
        font-size: 12px;
        text-align: center;
      }

      </style>
   </head>
   <body>
      <nav id="nav1" class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header w3-hover-green">
                <a id="a1" class="navbar-brand" href="#">MyPLS</a>
            </div>
            <ul id="a1" class="nav navbar-nav">
                <li id="a1" class="active"><a href="/HomepageProf">Home</a></li>
            </ul>
            <div class="navbar-header pull-right">
                <a id="a1" class="navbar-brand" href="/HomepageProf">Go Back</a>
            </div>
        </div>
      </nav>

      <div class="container">
      <div class="well" style="background-color: #5DADE2">
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

               <table id="table1">
                  <thead class="thead-dark">
                     <tr>
                        <th id="th1">Lesson Title</th>
                        <th id="th1">Rating</th>
                        <th id="th1">Options</th>
                     </tr>
                  </thead>

                  <#list course.lessons as lesson>
                  <tr>
                     <td id="td1">${lesson.title}</td>
                     <td id="td1">${lesson.rating}</td>
                     <td id="td1">
                        <a href='/HomepageProf/ViewCourse/ViewLesson/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}'>View Lesson</a> |
                        <a href='UpdateLesson/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}'>Update Lesson</a> |

                        <#if lesson.quiz ??  >
                        <a href='/HomepageProf/ViewCourse/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}/UpdateQuiz'>Update Quiz</a>
                        <form action="/HomepageProf/ViewCourse/DeleteQuiz" method="post">
                           <input type="hidden" name="lessonid" value="${lesson.lessonID?replace(",", "")}">
                           <button id="delete" type="submit">Delete Quiz</button>
                        </form>

                        <#else>
                        <a href='/HomepageProf/ViewCourse/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}/AddQuiz'>Add Quiz</a> |
                        </#if>

                        <form action="/HomepageProf/ViewCourse/DeleteLesson" method="post">
                           <input type="hidden" name="lessonID" value="${lesson.lessonID?replace(",", "")}">
                           <input type="hidden" name="mediaPath" value="${lesson.mediaPath?replace(",", "")}">
                           <input type="hidden" name="documentPath" value="${lesson.documentPath?replace(",", "")}">
                           <button id="delete" type="submit">Delete Lesson</button>
                        </form>
                     </td>
                  </tr>
                  </#list>
               </table>
            </div>
         </div>
      </div>

      <div id="footerx">
        <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
        Conducted in the Academic Year 2020</p>
      </div>

   </body>
</html>