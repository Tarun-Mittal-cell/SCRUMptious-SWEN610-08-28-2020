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
                position: relative;
                left: 10px;
                background-color: #EC7063;
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
                 background-color: #F8C471;
                 padding: 9px 10px;
             }

             h2, h3, h4 {
                font-weight: bold;
             }

             #a1 {
                 background-color: #F8C471;
                 font-family: 'Lucida Sans Unicode';
                 color: black;
             }

             #txtbox {
                font-size: 16px;
                height: 42px;
                width : 100px;
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
               background-color: #FEF9E7;
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
               background-color: #F4D03F;
               border-style: groove;
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
                background-color: #F8C471;
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
      <div class="well" style="background-color: #F8C471; width:100%">
         <h2 class="text-center"> Review Course </h2>
      </div>

      <div class="row">
         <div id="div_align">

                <form action="/HomepageLearner/ViewCourse/ReviewCourse" method="post">
                <h3><u>Rate Professor: ${professor.firstName} ${professor.lastName}</u></h3>
                            <select id="txtbox" name="profRating" autofocus>
                                <option disabled="disabled" selected="selected">Rating</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                             </select>

               <h3><u>Rate Lessons</u></h3>
             <table>
               <thead class="thead-dark">
                  <tr>
                     <th>Lessson Title</th>
                     <th>Rating</th>
                  </tr>
               </thead>
              <tbody>
                  <#list course.lessons as lesson>
                       <tr>
                          <td>${lesson.title}</td>
                          <td>
                            <select id="txtbox" name="LessonRating${lesson?counter}" >
                                <option disabled="disabled" selected="selected">Rating</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                             </select>
                          </td>

                  </#list>
             </table>

             <h3><u>Rate Course: ${course.name}</u></h3>
              <select id="txtbox" name="courseRating">
                <option disabled="disabled" selected="selected">Rating</option>
                 <option value="1">1</option>
                 <option value="2">2</option>
                 <option value="3">3</option>
                 <option value="4">4</option>
                 <option value="5">5</option>
              </select>

              <br>
              <br>

              <button id="buttonx" type = "submit" > Submit </button>

              <#if blanks??>
              <br>
                <p style="color:red;"> Please fill out all review items!</p>
              </#if>
             </form>
            </div>
         </div>
      </div>

      <div id="footerx">
        <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
        Conducted in the Academic Year 2020</p>
      </div>

   </body>
</html>