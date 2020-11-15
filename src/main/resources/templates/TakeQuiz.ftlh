<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Take Quiz</title>
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
      <#if !score??>
            <#if (lesson.quiz.grade > -1) >
                <form action="/HomepageProf/ViewCourse/RetakeQuiz" method="Post">
            <#else>
                  <form action="/HomepageProf/ViewCourse/TakeQuiz" method="Post">
            </#if>
               <input type ="hidden" name="lessonID" value="${lesson.lessonID?replace(",", "")}">
               <#list lesson.quiz.questions as question>
               <div class="mcq">
                  <label for="question" class="question">Question ${question?counter} :<textarea name="question${question?counter}" rows="10" cols="65" disabled>${question}</textarea></label>
                  <div class="block">
                     <label for="answer${question?counter}">Answer: </label>
                     <select name="answer${question?counter}" id="answer" class="answer">
                        <option value="" hidden  disabled selected></option>
                        <option value="A" >A</option>
                        <option value="B" >B</option>
                        <option value="C" >C</option>
                        <option value="D" >D</option>
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
      <#else>
            <form action="/HomepageProf/ViewCourse/TakeQuiz" method="Post">
            <input type ="hidden" name="lessonID" value="${lesson.lessonID?replace(",", "")}">
            <#list lesson.quiz.questions as question>
            <div class="mcq">
               <label for="question" class="question">Question ${question?counter} :<textarea name="question${question?counter}" rows="10" cols="65" disabled>${question}</textarea></label>
               <div class="block">
                  <label for="answer${question?counter}">Answer: </label>
                  <select name="answer${question?counter}" id="answer" class="answer">
                     <option value="" hidden  disabled selected></option>
                     <option value="A" >A</option>
                     <option value="B" >B</option>
                     <option value="C" >C</option>
                     <option value="D" >D</option>
                  </select>
               </div>
            </div>
            </#list>
            </section>
            <!-- Footer Section -->
            <footer>

                    <h3>${score?string["0.##"]}% <br>
                        <a href='/HomepageLearner/ViewCourse/${course.courseID?replace(",", "")}'>Back to course home</a>
                    </h3>

            </form >
            </footer>
      </#if>
   </body>
</html>