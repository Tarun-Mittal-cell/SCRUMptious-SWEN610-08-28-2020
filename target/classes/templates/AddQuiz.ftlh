<!DOCTYPE html>
<html lang="en">
   <head>
      <title>MyPLS Add Quiz</title>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

      <script>
      function goBack() {
        window.history.back();
      }
      </script>

      <style>
        body {
            background-color: white;
            font-family: 'Lucida Sans Unicode';
            font-size: 18px;
            margin-bottom: 150px;
        }

        #txtbox {
            font-size: 14pt;
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
         <form action="/HomepageProf/ViewCourse/Lesson/AddQuiz" method="Post">
            <input type ="hidden" name="lessonID" value="${lesson.lessonID?replace(",", "")}">
            <#if blankSpaces??>
            <p style="color:red;">Please fill out all 3 questions.</p>
            </#if>

            <br>

            <!-- Question 1 -->
            <div class="mcq">
               <label for="question" class="question">Question 1: &ensp; <textarea name="question1" rows="10" cols="100" ></textarea></label>
               <div class="block">
                  <label for="answer1">Answer: </label>
                  <select id="txtbox" name="answer1" class="answer">
                     <option value="A">A</option>
                     <option value="B">B</option>
                     <option value="C">C</option>
                     <option value="D">D</option>
                  </select>
               </div>
            </div>

            <br>

            <!-- Question 2 -->
            <div class="mcq">
               <label for="question" class="question">Question 2: &ensp;<textarea name="question2" rows="10" cols="100" ></textarea></label>
               <div class="block">
                  <label for="answer2">Answer: </label>
                  <select name="answer2" id="txtbox" class="answer">
                     <option value="A">A</option>
                     <option value="B">B</option>
                     <option value="C">C</option>
                     <option value="D">D</option>
                  </select>
               </div>
            </div>

            <br>

            <!-- Question 3 -->
            <div class="mcq">
               <label for="question" class="question">Question 3: &ensp;<textarea name="question3" rows="10" cols="100" ></textarea></label>
               <div class="block">
                  <label for="answer3">Answer: </label>
                  <select name="answer3" id="txtbox" class="answer">
                     <option value="A">A</option>
                     <option value="B">B</option>
                     <option value="C">C</option>
                     <option value="D">D</option>
                  </select>
               </div>
            </div>

            <br>

            <button id="buttonx" type="submit" class="button">Submit</button>
      </section>

      <div id="footerx">
        <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
        Conducted in the Academic Year 2020</p>
      </div>

      <br>

   </body>
</html>