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
            }

            #buttonx {
                position: relative;
                left: 10px;
                background-color: #EC7063;
                border: none;
                color: white;
                text-align: center;
                display: inline-block;
                font-size: 18px;
                cursor: pointer;
                height: 40px;
                width : 200px;
                padding: 10px;
                margin: auto;
            }

            #hrefbutton {
                position: absolute;
                background-color: #2E86C1;
                border: none;
                right: 10px;
                color: white;
                text-align: center;
                display: inline-block;
                font-size: 18px;
                cursor: pointer;
                height: 40px;
                width : 200px;
                padding: 10px;
                margin: auto;
            }

             #nav1 {
                 height: 70px;
                 width: 100%;
                 border-style: none;
                 background-color: #F8C471;
                 padding: 9px 10px;
             }

             h2{
                font-family: 'Courier New';
                font-weight: bold;
             }

             #a1 {
                 background-color: #F8C471;
                 font-family: 'Lucida Sans Unicode';
                 color: black;
             }

             #txtbox {
                 font-size: 14pt;
                 height: 40px;
                 width : 700px;
             }

             #div_align {
                 width: 100%;
                 padding: 10px;
                 border: 5px;
                 border-style: double;
                 border-color: black;
                 margin: auto;
             }

             table{
                 width: 100%;
                 border-style: groove;
                 border-width: thin;
                 border-color: black;
             }

             td {
               background-color: #FEF9E7;
               font-size: 15px;
               border-style: groove;
               border-color: black;
               border-width: thin;
               text-align: left;
               height: 50px;
               width: 50px;
             }

             th {
               background-color: #F4D03F;
               border-style: groove;
               font-size: 16px;
               border-width: thin;
               border-color: black;
               text-align: center;
               height: 30px;
               width: 50px;
             }

             #footerx {
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
                background-color: #F8C471;
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
                <a id="a1" class="navbar-brand" href="/HomepageLearner">Go Back</a>
            </div>
        </div>
      </nav>

      <div class="container">
      <div class="well" style="background-color: #F8C471; width:100%">
         <h2 class="text-center"> ${course.name} </h2>
      </div>

      <div class="row">
      <div id="div_align">
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
                         <#if lesson?index == 0>
                            <#if lesson.quiz ?? && (lesson.quiz.grade> -1) >
                               <#if lesson.quiz.grade < course.minScore>
                                   | <a href="/HomepageLearner/ViewCourse/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}/TakeQuiz">Retake Quiz</a>
                               </#if>
                            <#elseif lesson.quiz ?? >
                               | <a href="/HomepageLearner/ViewCourse/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}/TakeQuiz">Take Quiz</a>
                            </#if>
                         <#elseif (course.lessons[lesson?index-1].quiz.grade > course.minScore)>
                             <#if lesson.quiz ?? && (lesson.quiz.grade> -1) >
                               <#if lesson.quiz.grade < course.minScore>
                                   | <a href="/HomepageLearner/ViewCourse/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}/TakeQuiz">Retake Quiz</a>
                               </#if>
                            <#elseif lesson.quiz ?? >
                               | <a href="/HomepageLearner/ViewCourse/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}/TakeQuiz">Take Quiz</a>
                            </#if>
                         </#if>

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