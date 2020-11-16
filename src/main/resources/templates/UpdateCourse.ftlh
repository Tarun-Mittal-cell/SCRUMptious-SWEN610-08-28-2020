<!DOCTYPE html>
<html>
   <head>
      <title>MyPLS Update Course</title>
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
            background-color: #1ABC9C;
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
            background-color: #1ABC9C;
            padding: 9px 10px;
        }

        h2{
            font-family: 'Courier New';
            font-weight: bold;
        }

        #a1 {
            background-color: #1ABC9C;
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
            background-color: #1ABC9C;
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
            <h2>Update Course</h2>
         </div>
         <div class="container">
            <form action= "/HomepageAdmin/UpdateCourse" method ="post">
               <#if blankSpaces??>
               <p style="color:red;">Invalid Input! Please re-enter values properly.</p>
               </#if>

               <div class="form-group">
                  <label for="courseName">Course Name</label>
                  <input id="txtbox" type ="text" name="courseName" class="form-control" value="${course.name} "required>
               </div>

               <div class="form-group">
                  <label for="requirement"> Requirements</label>
                  <textarea style="font-size: 15px" name="requirement" class="form-control"  rows="10" cols="500"> ${course.requirements} </textarea>
               </div>

               <div class="form-group">
                  <label for="objectives"> Learning Objectives</label>
                  <textarea style="font-size: 15px" name="objectives" class="form-control" rows="10" cols="500" >${course.objectives} </textarea>
               </div>

               <div class="form-group">
                  <label for="outcomes"> Learning Outcomes</label>
                  <textarea style="font-size: 15px" name="outcomes" class="form-control" rows="10" cols="500" > ${course.outcomes}</textarea>
               </div>

               <div class="form-group">
                  <label>Assign Pre-requisite</label>
                  <select class="form-control" name="prerequisite" id="txtbox">
                     <#list courses as preReqCourse>
                     <#if course.courseID != preReqCourse.courseID>
                        <#if course.prerequisiteCourseId==preReqCourse.courseID>
                             <option selected>${preReqCourse.name} Course ID: ${preReqCourse.courseID?replace(",","")} </option>
                        <#else>
                             <option>${preReqCourse.name} Course ID: ${preReqCourse.courseID?replace(",","")} </option>
                        </#if>
                     </#if>
                     </#list>

                     <#if course.prerequisiteCourseId == 0>
                             <option selected>None</option>
                       <#else>
                               <option>None</option>
                      </#if>
                  </select>
               </div>

               <div class="form-group">
                  <label>Assign Professor</label>
                  <select class="form-control" name="professor" id="txtbox" >
                     <#list professors as professor>
                     <#if course.assignedProfessorId==professor.professorID>
                     <option selected>${professor.firstName} ${professor.lastName} Professor ID: ${professor.professorID}</option>
                     <#else>
                     <option>${professor.firstName} ${professor.lastName} Professor ID: ${professor.professorID}</option>
                     </#if>
                     </#list>
                  </select>
               </div>

               <button id="buttonx" class="btn btn-success" type = "submit" name ="reg_user"> Update </button>

            </form>
         </div>
      </div>

      <div id="footerx">
        <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
        Conducted in the Academic Year 2020</p>
      </div>

   </body>
</html>