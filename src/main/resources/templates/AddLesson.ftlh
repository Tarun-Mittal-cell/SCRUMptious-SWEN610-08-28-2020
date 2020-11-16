<!DOCTYPE html>
<html>
   <head>
      <title>MyPLS Add Lesson</title>
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
            margin-bottom: 60x;
        }

        #txtbox {
            font-size: 14pt;
            height: 42px;
            width : 1145px;
        }

        #buttonx {
            background-color: #5DADE2;
            border: none;
            color: white;
            text-align: center;
            display: inline-block;
            font-size: 18px;
            cursor: pointer;
            height: 42px;
            width : 1145px;
        }

      #nav1 {
        height: 70px;
        width: 100%;
        border-style: none;
        background-color: #5DADE2;
        padding: 9px 10px;
      }

        #a1 {
            background-color: #5DADE2;
            font-family: 'Lucida Sans Unicode';
            color: black;
        }

        h2{
            font-family: 'Courier New';
            font-weight: bold;
        }

        #footerx {
            position: fixed;
            left: 0;
            height: 40px;
            bottom: 0;
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
            <div class="navbar-header w3-hover-green">
                <a id="a1" class="navbar-brand" href="#">MyPLS</a>
            </div>
            <ul id="a1" class="nav navbar-nav">
                <li id="a1" class="active"><a href="#">Home</a></li>
            </ul>
            <div class="navbar-header pull-right">
                <a id="a1" class="navbar-brand" href="/HomepageProf">Go Back</a>
            </div>
        </div>
      </nav>

      <div class= "container">
         <div class= "header">
            <h2>Add Lesson</h2>
         </div>

         <div class="container">
            <form action= "/AddLesson" method ="Post" enctype='multipart/form-data' >
               <label>Course Name: ${course.name}<br>Course ID:${course.courseID?replace(",", "")}  </label>
               <input type ="hidden" name="courseID" value="${course.courseID?replace(",", "")}">

               <div class="form-group">
                  <#if blankSpaces??>
                  <p style="color:red;">Invalid Input! Please re-enter values properly.</p>
                  </#if>
                  <label for="title">Lesson Title</label>
                  <input id="txtbox" type ="text" name="title" class="form-control" required>
               </div>

               <div class="form-group">
                  <label for="requirement"> Requirements</label>
                  <textarea style="font-size: 15px;" name="requirement" class="form-control"  rows="10" cols="500"> </textarea>
               </div>

               <div class="form-group">
                    <label for="document"> Add Lesson Slides &emsp;</label>
                    <input type ="file" name="uploaded_pdf"  accept='.pdf'>
               </div>

               <div class="form-group">
                    <label for="media"> Add Lesson Video &emsp;</label>
                    <input type ="file" name="uploaded_media"  accept='.mp4'>
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