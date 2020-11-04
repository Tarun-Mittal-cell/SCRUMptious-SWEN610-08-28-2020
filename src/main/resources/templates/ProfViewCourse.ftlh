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
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">Page 1</a></li>
          <li><a href="#">Page 2</a></li>
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
                <center><h2>Lessons</h2></center>
                <br>
               <table style="width:100%"  class="table table-hover table-striped table-bordered" >
                  <thead class="thead-dark">
                  <tr>
                     <th scope="col">Lesson Title</th>
                     <th scope="col">Rating</th>
                     <th scope="col">Options</th>
                  </tr>
                  </thead>
                  <#list course.lessons as lesson>
                  <tr>
                     <td style="width:50%">${lesson.title}</td>
                     <td style="width:10%">${lesson.rating}</td>
                     <td style="width:30%" >
                        <a href='/HomepageProf/ViewCourse/ViewLesson/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}'>View Lesson</a> |
                        <a href='UpdateLesson/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}'>Update Lesson</a> |
                        <a href='/HomepageProf/ViewCourse/${course.courseID?replace(",", "")}/${lesson.lessonID?replace(",", "")}/AddQuiz'>Add Quiz</a> |
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

</body>
</html>
