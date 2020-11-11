<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Create Quiz</title>
      <link rel="stylesheet" href="/CSS/style.css">
   </head>
   <body>
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
            <!-- Question 1 -->
            <div class="mcq">
               <label for="question" class="question">Question 1 :<textarea name="question1" rows="10" cols="65" ></textarea></label>
               <div class="block">
                  <label for="answer1">Answer: </label>
                  <select name="answer1" id="answer" class="answer">
                     <option value="A">A</option>
                     <option value="B">B</option>
                     <option value="C">C</option>
                     <option value="D">D</option>
                  </select>
               </div>
            </div>
            <!-- Question 2 -->
            <div class="mcq">
               <label for="question" class="question">Question 2 : <textarea name="question2" rows="10" cols="65" ></textarea></label>
               <div class="block">
                  <label for="answer2">Answer: </label>
                  <select name="answer2" id="answer" class="answer">
                     <option value="A">A</option>
                     <option value="B">B</option>
                     <option value="C">C</option>
                     <option value="D">D</option>
                  </select>
               </div>
            </div>
            <!-- Question 3 -->
            <div class="mcq">
               <label for="question" class="question">Question 3 : <textarea name="question3" rows="10" cols="65" ></textarea></label>
               <div class="block">
                  <label for="answer3">Answer: </label>
                  <select name="answer3" id="answer" class="answer">
                     <option value="A">A</option>
                     <option value="B">B</option>
                     <option value="C">C</option>
                     <option value="D">D</option>
                  </select>
               </div>
            </div>
      </section>
      <!-- Footer Section -->
      <footer>
      <button type="submit" class="button">Submit</button>
      </form >
      </footer>
   </body>
</html>