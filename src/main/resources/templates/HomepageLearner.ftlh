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

            #hrefbutton {
                position: absolute;
                background-color: #2E86C1;
                border: none;
                right: 10px;
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

             h2{
                font-family: 'Courier New';
                font-weight: bold;
             }

             #a1 {
                 background-color: #F8C471;
                 font-family: 'Lucida Sans Unicode';
                 color: black;
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
                <a id="a1" class="navbar-brand" href="/Signout">Sign Out</a>
            </div>
        </div>
      </nav>

      <div class="container">
      <div style="background-color: #F8C471; width:100%" class="well">
         <h2 class="text-center"> Welcome ${userData.firstName}  ! </h2>
      </div>

      <br>

      <div class="row">
         <div class="col-md-12" id="div_align">

            <div>
                <form action="/HomepageLearner/Search" method="post">
                    <input id="txtbox" type ="number" name="search">
                    <button id="buttonx" class="btn btn-success" type = "submit" > Search </button>
                    <a id="hrefbutton" class="btn btn-success pull-right" href='/HomepageLearner/ViewAllCourses'>View All Courses</a>
                </form>
             </div>

             <br>

             <table style="width:100%"  class="table table-hover table-striped table-bordered" >
               <thead class="thead-dark">
                  <tr>
                     <th>Course Name Title</th>
                     <th>Course ID</th>
                     <th>Course Status</th>
                     <th>Course Rating</th>
                     <th>Professor Name</th>
                     <th>Professor Rating</th>
                     <th>Options</th>
                  </tr>
               </thead>
              <tbody>
                <#if registeredCourses??>
                  <#list registeredCourses as course>
                       <tr>
                          <td>${course.name}</td>
                          <td>${course.courseID?replace(",", "")}</td>
                          <td>${course.status}</td>
                          <td>${course.rating}</td>
                          <td>Prof. ${courseProfessors[course?index].firstName} ${courseProfessors[course?index].lastName}</td>
                          <td>${courseProfessors[course?index].rating}</td>
                          <td>
                               <a href='/HomepageLearner/ViewCourse/${course.courseID?replace(",", "")}'>View Course</a>
                               <#if course.status =="Completed" && course.reviewed ==false>
                                    | <a href='/HomepageLearner/ViewCourse/${course.courseID?replace(",", "")}/Review'>Review Course</a>
                               </#if>
                          </td>
                       </tr>
                  </#list>
                </#if>
              </tbody>
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