<!DOCTYPE html>
<html>
   <head>
      <title>MyPLS Create Discussion Group</title>
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
            background-color: #283747;
            border: none;
            color: white;
            text-align: center;
            display: inline-block;
            font-size: 18px;
            cursor: pointer;
            height: 45px;
            width : 900px;
            padding: 10px;
            margin: auto;
        }

        #nav1 {
            height: 70px;
            width: 100%;
            border-style: none;
            background-color: #283747;
            padding: 9px 10px;
        }

        h2{
            font-family: 'Courier New';
            font-weight: bold;
        }

        #a1 {
            background-color: #283747;
            font-family: 'Lucida Sans Unicode';
            color: white;
        }

        #txtbox {
            font-size: 15px;
            height: 42px;
            width : 900px;
        }

        #footerx {
            position: fixed;
            left: 0;
            bottom: 0;
            height: 40px;
            width: 100%;
            background-color: #283747;
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
            <h2>Create Discussion Group</h2>
         </div>

         <div class="container">
            <form action= "/HomepageAdmin/CreateDiscussionGroup" method ="Post">
               <#if blankSpaces??>
               <p style="color:red;">Invalid Input! Please re-enter values properly.</p>
               </#if>
               <#if topicUnique??>
                 <p style="color:red;">Discussion topic already created.</p>
                 </#if>

               <div class="form-group">
                  <label for="topic">Topic</label>
                  <input id="txtbox" type ="text" name="topic" class="form-control" required>
               </div>

               <div class="form-group">
                  <label>Type</label>
                  <select id="txtbox" class="form-control" name="type" id="type">
                     <option>Public</option>
                     <option>Private</option>
                  </select>
               </div>

               <div class="form-group">
                  <label>Related Course</label>
                  <select id="txtbox" class="form-control" name="relatedCourse" id="relatedCourse">
                     <option>None</option>
                     <#list courses as course>
                     <option>${course.name} Course ID: ${course.courseID?replace(",","")} </option>
                     </#list>
                  </select>

               </div>

               <button id="buttonx" class="btn btn-success" type = "submit" name ="reg_user"> Submit </button>

            </form>
         </div>
      </div>

      <div id="footerx">
        <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
        Conducted in the Academic Year 2020</p>
      </div>

   </body>
</html>