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
    <!-- Question 1 -->
    <div class="mcq">
      <label for="question" class="question">Question 1 :<textarea name="question1" rows="10" cols="65" >${lesson.quiz.questions[0]}</textarea></label>
       <div class="block">
        <label for="answer1">Answer: </label>
        <select name="answer1" id="answer" class="answer">
        <#if lesson.quiz.answers[0]=="A">
          <option value="A" selected>A</option>
        <#else>
            <option value="A" >A</option>
        </#if>
        <#if lesson.quiz.answers[0]=="B">
          <option value="B" selected>B</option>
        <#else>
            <option value="B" >B</option>
        </#if>
        <#if lesson.quiz.answers[0]=="C">
          <option value="C" selected>C</option>
        <#else>
            <option value="C" >C</option>
        </#if>
        <#if lesson.quiz.answers[0]=="D">
          <option value="D" selected>D</option>
        <#else>
            <option value="D" >D</option>
        </#if>
        </select>
      </div>
    </div>

    <!-- Question 2 -->
    <div class="mcq">
      <label for="question" class="question">Question 2 : <textarea name="question2" rows="10" cols="65" >${lesson.quiz.questions[1]}</textarea></label>
      <div class="block">
        <label for="answer2">Answer: </label>
        <select name="answer2" id="answer" class="answer">
         <#if lesson.quiz.answers[1]=="A">
          <option value="A" selected>A</option>
        <#else>
            <option value="A" >A</option>
        </#if>
        <#if lesson.quiz.answers[1]=="B">
          <option value="B" selected>B</option>
        <#else>
            <option value="B" >B</option>
        </#if>
        <#if lesson.quiz.answers[1]=="C">
          <option value="C" selected>C</option>
        <#else>
            <option value="C" >C</option>
        </#if>
        <#if lesson.quiz.answers[1]=="D">
          <option value="D" selected>D</option>
        <#else>
            <option value="D" >D</option>
        </#if>
        </select>
      </div>
    </div>

    <!-- Question 3 -->
    <div class="mcq">
      <label for="question" class="question">Question 3 : <textarea name="question3" rows="10" cols="65" >${lesson.quiz.questions[2]}</textarea></label>
      <div class="block">
        <label for="answer3">Answer: </label>
        <select name="answer3" id="answer" class="answer">
           <#if lesson.quiz.answers[2]=="A">
          <option value="A" selected>A</option>
        <#else>
            <option value="A" >A</option>
        </#if>
        <#if lesson.quiz.answers[2]=="B">
          <option value="B" selected>B</option>
        <#else>
            <option value="B" >B</option>
        </#if>
        <#if lesson.quiz.answers[2]=="C">
          <option value="C" selected>C</option>
        <#else>
            <option value="C" >C</option>
        </#if>
        <#if lesson.quiz.answers[2]=="D">
          <option value="D" selected>D</option>
        <#else>
            <option value="D" >D</option>
        </#if>
        </select>
      </div>
    </div>

    <!-- Question 4 -->
    <div class="mcq">
      <label for="question" class="question">Question 4 : <textarea name="question4" rows="10" cols="65" >${lesson.quiz.questions[3]}</textarea></label>
      <div class="block">
        <label for="answer4">Answer: </label>
        <select name="answer4" id="answer" class="answer">
           <#if lesson.quiz.answers[3]=="A">
          <option value="A" selected>A</option>
        <#else>
            <option value="A" >A</option>
        </#if>
        <#if lesson.quiz.answers[3]=="B">
          <option value="B" selected>B</option>
        <#else>
            <option value="B" >B</option>
        </#if>
        <#if lesson.quiz.answers[3]=="C">
          <option value="C" selected>C</option>
        <#else>
            <option value="C" >C</option>
        </#if>
        <#if lesson.quiz.answers[3]=="D">
          <option value="D" selected>D</option>
        <#else>
            <option value="D" >D</option>
        </#if>
        </select>
      </div>
    </div>

    <!-- Question 5 -->
    <div class="mcq">
      <label for="question" class="question">Question 5 : <textarea name="question5" rows="10" cols="65" >${lesson.quiz.questions[4]}</textarea></label>
      <div class="block">
        <label for="answer5">Answer: </label>
        <select name="answer5" id="answer" class="answer">
          < <#if lesson.quiz.answers[4]=="A">
          <option value="A" selected>A</option>
        <#else>
            <option value="A" >A</option>
        </#if>
        <#if lesson.quiz.answers[4]=="B">
          <option value="B" selected>B</option>
        <#else>
            <option value="B" >B</option>
        </#if>
        <#if lesson.quiz.answers[4]=="C">
          <option value="C" selected>C</option>
        <#else>
            <option value="C" >C</option>
        </#if>
        <#if lesson.quiz.answers[4]=="D">
          <option value="D" selected>D</option>
        <#else>
            <option value="D" >D</option>
        </#if>
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