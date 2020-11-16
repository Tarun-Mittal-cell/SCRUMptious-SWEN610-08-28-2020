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

      #button1 {
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

      #button2 {
        position: absolute;
        background-color: #3498DB;
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

      #button3 {
        background-color: #6C3483;
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
            <div class="navbar-header">
                <a id="a1" class="navbar-brand" href="#">MyPLS</a>
            </div>
            <ul id="a1" class="nav navbar-nav">
                <li id="a1" class="active"><a href="#">Home</a></li>
            </ul>
            <div class="navbar-header pull-right">
                <a id="a1" class="navbar-brand" href="/Signout">Sign Out</a>
            </div>
        </div>
    </nav>

      <div class="container">
      <div class="well" style="background-color: #5DADE2">
         <h2 class="text-center"> Welcome Professor ${userData.lastName}! </h2>
      </div>

      <div class="row">
      <div id="div_align">
         <div><input class="pull-right" type="form-control" placeholder="Search" name=""></div>
         <div class="text-center">
            <div>
               <#if courses??>
               <center>
                  <h2 class="text-center">Courses</h2>
               </center>

               <br>

               <table id="table1">
                  <thead class="thead-dark">
                     <tr>
                        <th id="th1">Course Name</th>
                        <th id="th1">Enrollment</th>
                        <th id="th1">Rating</th>
                        <th id="th1">Options</th>
                     </tr>
                  </thead>

                  <#list courses as course>
                  <tr>
                     <td id="td1">${course.name}</td>
                     <td id="td1">${course.enrollment}</td>
                     <td id="td1">${course.rating}</td>
                     <td id="td1">
                        <a href='HomepageProf/ViewCourse/${course.courseID?replace(",", "")}'>View Course</a> |
                        <a href='HomepageProf/AddLesson/${course.courseID?replace(",", "")}'>Add Lesson</a>
                     </td>
                  </tr>
                  </#list>
               </table>
               <#else>
                        <h3> No Assigned Courses</h3>
                 </#if>
            </div>
         </div>

         <br>

         <div class="row" id="div_align">
            <div class="col-md-6">
               <div class="row">
                  <div style="border: 1px solid; padding: 20px; height: 80px; margin: 5px 0px;">
                     <button id="button1" class="btn btn-primary pull-left">
                        Assessments</>
                  </div>
               </div>

               <div class="row">
               <div style="border: 1px solid; padding: 20px; height: 80px; margin: 5px 0px;">
               <button id="button2" class="btn btn-primary pull-left">Feedback</button></div>
               </div>
            </div>

            <div class="col-md-6">
            <div style="border: 1px solid; padding: 60px; height: 160px; margin: 5px 0px;">
            <button id="button3" class="btn btn-primary pull-right">Discussion Group</button></div>
            </div>
         </div>
      </div>

      <div id="footerx">
        <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
        Conducted in the Academic Year 2020</p>
      </div>

   </body>
</html>