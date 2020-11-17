<!DOCTYPE html>
<html lang="en">
   <title>MyPLS Take Quiz</title>
   <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <style>
           body {
               background-color: white;
               font-family: 'Lucida Sans Unicode';
               font-size: 18px;
               margin-bottom: 150px;
           }

           #txtbox {
               font-size: 16px;
               height: 42px;
               width : 100px;
           }

           #buttonx {
               background-color: #5DADE2;
               border: none;
               color: white;
               position: relative;
               left: 450px;
               text-align: center;
               display: inline-block;
               font-size: 18px;
               cursor: pointer;
               height: 50px;
               width : 300px;
           }

         #nav1 {
           height: 70px;
           width: 100%;
           border-style: none;
           background-color: #5DADE2;
           padding: 9px 10px;
         }

           #a1 {
               background-color: #5DADE2;
               font-family: 'Lucida Sans Unicode';
               color: black;
           }

           h1, h2{
               font-family: 'Courier New';
               font-weight: bold;
           }

           #footerx {
               position: fixed;
               left: 0;
               height: 40px;
               bottom: 0;
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
                <label class="pull-right" for="goback" onclick="goBack()">Go Back</label>
                <input id="goback" style="visibility:hidden;">
            </div>
        </div>
      </nav>

      <!-- Header Section -->
      <header>
         <h1>Multiple Choice Quiz </h1>
         <h2>Topic: ${lesson.title} </h2>
      </header>

      <!-- Main Section -->
      <section class="container">
      <#if !score??>
            <#if (lesson.quiz.grade > -1) >
                <form action="/HomepageProf/ViewCourse/RetakeQuiz" method="Post">
            <#else>
                  <form action="/HomepageProf/ViewCourse/TakeQuiz" method="Post">
            </#if>
               <input type ="hidden" name="lessonID" value="${lesson.lessonID?replace(",", "")}">

               <#list lesson.quiz.questions as question>
               <div class="mcq">

               <br>

                  <label for="question" class="question">Question ${question?counter} &emsp;<textarea name="question${question?counter}" rows="10" cols="100" disabled>${question}</textarea></label>
                  <div class="block">
                     <label for="answer${question?counter}">Answer &emsp; </label>
                     <select id="txtbox" name="answer${question?counter}" id="answer" class="answer">
                        <option value="" hidden  disabled selected></option>
                        <option value="A" >A</option>
                        <option value="B" >B</option>
                        <option value="C" >C</option>
                        <option value="D" >D</option>
                     </select>

                     <br>

                  </div>

               </div>
               </#list>

               <br>

               <input type ="hidden" name="lessonCount" value="${lessonIndex+1}">
               <button id="buttonx" type="submit" class="button">Submit</button>

            </section>
      <#else>
            <form action="/HomepageProf/ViewCourse/TakeQuiz" method="Post">
            <input type ="hidden" name="lessonID" value="${lesson.lessonID?replace(",", "")}">
            <#list lesson.quiz.questions as question>
            <div class="mcq">
               <label for="question" class="question">Question ${question?counter} &emsp; <textarea name="question${question?counter}" rows="10" cols="65" disabled>${question}</textarea></label>
               <#if lesson.quiz.answers[question?index] == choices[question?index] >

               <br>

                   <p style="color:green"> Correct </p>
               <#else>
                    <p style="color:red"> Wrong </p>
               </#if>

            </div>
            </#list>
            </section>

            <br>

                    <center>
                    <h3>${score?string["0.##"]}% <br>
                        <a href='/HomepageLearner'>Back to home</a>
                    </h3>
                    </center>
            </form >
      </#if>

      <div id="footerx">
        <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
        Conducted in the Academic Year 2020</p>
      </div>

   </body>
</html>