<!DOCTYPE html>
<html>
   <head>
      <title> MyPLS Homepage </title>
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
            }

            #buttonx {
                 background-color: #F8C471;
                 border: none;
                 color: white;
                 text-align: center;
                 display: inline-block;
                 font-size: 18px;
                 cursor: pointer;
                 height: 42px;
                 width : 200px;
            }

             #nav1 {
                 height: 70px;
                 width: 100%;
                 border-style: none;
                 background-color: #2E86C1;
                 padding: 9px 10px;
             }

             h2{
                font-family: 'Courier New';
                font-weight: bold;
             }

             #a1 {
                 background-color: #2E86C1;
                 font-family: 'Lucida Sans Unicode';
                 color: white;
             }

             #txtbox {
                 font-size: 14pt;
                 height: 40px;
                 width : 900px;
             }

             #div_align {
                 background-color: #2E86C1;
                 width: 100%;
                 padding: 10px;
                 border: 5px;
                 border-style: solid;
                 border-color: black;
                 margin: auto;
             }

             #table1 {
                 width: 100%;
                 border-style: groove;
                 border-width: thin;
                 border-color: black;
                 padding: 7px;
             }

             #td1 {
               background-color: #D6EAF8;
               font-size: 15px;
               padding: 7px;
               border-style: groove;
               border-color: black;
               border-width: thin;
               text-align: left;
               height: 50px;
               width: 50px;
             }

             #th1 {
               background-color: #1B4F72;
               color: white;
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
                      background-color: #2E86C1;
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
      <div style="background-color: #2E86C1; width:100%" class="well">
         <h2 class="text-center"> Course Offering </h2>
      </div>

      <br>

      <div class="row">
       <table id="table1">
                  <thead class="thead-dark">
                     <tr>
                        <th id="th1">Course ID</th>
                        <th id="th1">Course Name</th>
                        <th id="th1">Enrollment</th>
                        <th id="th1">Rating</th>
                     </tr>
                  </thead>
                  <#list courses as course>
                  <tr>
                     <td id="td1">${course.courseID?replace(",", "")}</td>
                     <td id="td1">${course.name}</td>
                     <td id="td1">${course.enrollment}</td>
                     <td id="td1">${course.rating}</td>
                  </tr>
                  </#list>
               </table>
      </div>

      <div id="footerx">
        <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
        Conducted in the Academic Year 2020</p>
      </div>

   </body>
</html>