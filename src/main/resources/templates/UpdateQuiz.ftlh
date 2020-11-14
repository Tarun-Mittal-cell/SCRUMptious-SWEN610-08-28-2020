<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Update Quiz</title>
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
         <form action="/HomepageProf/ViewCourse/UpdateQuiz" method="Post">
            <input type ="hidden" name="lessonID" value="${lesson.lessonID?replace(",", "")}">
            <#if blankSpaces??>
            <p style="color:red;">Please fill out all 3 questions.</p>
            </#if>
            <!-- Question 1 -->
            <#list lesson.quiz.questions as question>
            <div class="mcq">
               <label for="question" class="question">Question ${question?counter} :<textarea name="question${question?counter}" rows="10" cols="65" >${question}</textarea></label>
               <div class="block">
                  <label for="answer${question?counter}">Answer: </label>
                  <select name="answer${question?counter}" id="answer" class="answer">
                     <#if lesson.quiz.answers[question?counter-1]=="A">
                     <option value="A" selected>A</option>
                     <#else>
                     <option value="A" >A</option>
                     </#if>
                     <#if lesson.quiz.answers[question?counter-1]=="B">
                     <option value="B" selected>B</option>
                     <#else>
                     <option value="B" >B</option>
                     </#if>
                     <#if lesson.quiz.answers[question?counter-1]=="C">
                     <option value="C" selected>C</option>
                     <#else>
                     <option value="C" >C</option>
                     </#if>
                     <#if lesson.quiz.answers[question?counter-1]=="D">
                     <option value="D" selected>D</option>
                     <#else>
                     <option value="D" >D</option>
                     </#if>
                  </select>
               </div>
            </div>
            </#list>
      </section>
      <!-- Footer Section -->
      <footer>
      <button type="submit" class="button">Submit</button>
      </form >
      </footer>
   </body>
</html>