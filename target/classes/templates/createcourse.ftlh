<!DOCTYPE html>
<html>
   <head>
      <title>MyPLS Create Course</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

      <style>
        body {
            background-color: white;
            font-family: 'Lucida Sans Unicode';
            font-size: 18px;
            margin-bottom: 60px;
        }

        #buttonx {
            background-color: #EC7063;
            border: none;
            color: white;
            text-align: center;
            display: inline-block;
            font-size: 18px;
            cursor: pointer;
            height: 45px;
            width : 1145px;
            padding: 10px;
            margin: auto;
        }

        #nav1 {
            height: 70px;
            width: 100%;
            border-style: none;
            background-color: #EC7063;
            padding: 9px 10px;
        }

        h2{
            font-family: 'Courier New';
            font-weight: bold;
        }

        #a1 {
            background-color: #EC7063;
            font-family: 'Lucida Sans Unicode';
            color: black;
        }

        #txtbox {
            font-size: 15px;
            height: 42px;
            width : 1145px;
        }

        #footerx {
            position: fixed;
            left: 0;
            bottom: 0;
            height: 40px;
            width: 100%;
            background-color: #EC7063;
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
                <li id="a1" class="active"><a href="#">Home</a></li>
            </ul>
            <div class="navbar-header pull-right">
                <a id="a1" class="navbar-brand" href="/HomepageAdmin">Go Back</a>
            </div>
        </div>
      </nav>

      <div class= "container">
         <div class= "header">
            <h2>Create Course</h2>
         </div>

         <div class="container">
            <form action= "/HomepageAdmin/CreateCourse" method ="post">
               <#if blankSpaces??>
               <p style="color:red;">Invalid Input! Please re-enter values properly.</p>
               </#if>

               <div class="form-group">
                  <label for="courseName">Course Name</label>
                  <input id="txtbox" type ="text" name="courseName" class="form-control" required autofocus>
               </div>

               <div class="form-group">
                  <label for="requirement"> Requirements</label>
                  <textarea style="font-size: 15px" name="requirement" class="form-control" rows="10" cols="500"> </textarea>
               </div>

               <div class="form-group">
                  <label for="objectives"> Learning Objectives</label>
                  <textarea style="font-size: 15px" name="objectives" class="form-control" rows="10" cols="500" > </textarea>
               </div>

               <div class="form-group">
                  <label for="outcomes"> Learning Outcomes</label>
                  <textarea style="font-size: 15px" name="outcomes" class="form-control" rows="10" cols="500" > </textarea>
               </div>

               <div class="form-group">
                  <label>Assign Pre-requisite</label>
                  <select id="txtbox" class="form-control" name="prerequisite">
                     <option>None</option>
                     <#list courses as course>
                     <option>${course.name} Course ID: ${course.courseID?replace(",","")} </option>
                     </#list>
                  </select>
               </div>

               <div class="form-group">
                  <label>Assign Professor</label>
                  <select class="form-control" name="professor" id="txtbox">
                     <#list professors as professor>
                     <option>${professor.firstName} ${professor.lastName} Professor ID: ${professor.professorID}</option>
                     </#list>
                  </select>
               </div>

               <button id="buttonx" class="btn btn-block" type = "submit" name ="reg_user"> Submit </button>
            </form>
         </div>
      </div>

      <div id="footerx">
        <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
        Conducted in the Academic Year 2020</p>
      </div>

   </body>
</html>