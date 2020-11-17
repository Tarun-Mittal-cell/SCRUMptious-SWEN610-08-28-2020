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
                background-color: #52BE80;
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

             #nav1 {
                 height: 70px;
                 width: 100%;
                 border-style: none;
                 background-color: #512E5F;
                 padding: 9px 10px;
             }

             h2, h3{
                font-family: 'Courier New';
                font-weight: bold;
             }

             #a1 {
                 background-color: #512E5F;
                 font-family: 'Lucida Sans Unicode';
                 color: white;
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
                 padding: 7px;
             }

             td {
               background-color: #E8DAEF;
               font-size: 15px;
               border-style: groove;
               border-color: black;
               border-width: thin;
               text-align: left;
               height: 50px;
               width: 50px;
               padding: 7px;
             }

             th {
               background-color: #884EA0;
               border-style: groove;
               color: white;
               font-size: 16px;
               border-width: thin;
               border-color: black;
               text-align: center;
               height: 30px;
               width: 50px;
               padding: 7px;
             }

             #footerx {
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
                background-color: #512E5F;
                color: white;
                font-size: 12px;
                text-align: center;
             }

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
      <nav id="nav1" class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header w3-hover-green">
                <a id="a1" class="navbar-brand" href="#">MyPLS</a>
            </div>
            <ul id="a1" class="nav navbar-nav">
                <li id="a1" class="active"><a href="/HomepageProf">Home</a></li>
            </ul>
            <div class="navbar-header pull-right">
                <a id="a1" class="navbar-brand" href="/HomepageLearner/Search">Go Back</a>
            </div>
        </div>
      </nav>

      <div class="container">
      <div class="well" style="color: white; background-color: #512E5F; width:100%">
         <h2 class="text-center"> ${course.courseID?replace(",", "")} <br>
         ${course.name} </h2>
      </div>

      <div class="row">
      <div id="div_align">
         <div class="text-center">
            <div>

               <center>
                  <h3><u>Objectives</u></h3>
               </center>
               <p>${course.objectives}</p>

               <hr>

               <center>
                  <h3><u>Outcomes</u></h3>
               </center>
               <p>${course.outcomes}</p>

               <hr>

               <center>
                  <h3><u>Requirements</u></h3>
               </center>
               <p>${course.requirements}</p>

               <hr>

               <center>
                  <h3><u>Lessons</u></h3>
               </center>

               <hr>

               <br>

               <table  class="table table-hover table-striped table-bordered" >
                  <thead class="thead-dark">
                     <tr>
                        <th scope="col">Lesson Title</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Requirements</th>
                     </tr>
                  </thead>

                  <#list course.lessons as lesson>
                  <tr>
                     <td>${lesson.title}</td>
                     <td>${lesson.rating}</td>
                     <td>${lesson.requirements}</td>
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