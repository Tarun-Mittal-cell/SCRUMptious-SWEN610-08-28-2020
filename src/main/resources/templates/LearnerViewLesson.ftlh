<!DOCTYPE html>
<html>
   <head>
      <title> MyPLS  </title>
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
                  margin-bottom: 60px;
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

             h1, h2{
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
                <label class="pull-right" for="goback" onclick="goBack()">Go Back</label>
                <input id="goback" style="visibility:hidden;">
            </div>
        </div>
      </div>
   </nav>

      <div class="container">
         <div class="well"  style="background-color: #F8C471; width:100%">
            <h1 class="text-center">  ${course.name}  </h1>
            <h2  class="text-center">${lesson.title}  </h2>
         </div>

         <center>
            <#if lesson.documentPath !=" ">
            <div class="row">
               <iframe src="/${lesson.documentPath}" width="100%" height="1000px">
               </iframe>
            </div>

            <#else>
            <h3>No Document has been added.</h3>
            </#if>

            <br>

            <#if lesson.documentPath !=" ">
            <div class="row">
               <video width="640" height="480" controls>
                  <source src="/${lesson.mediaPath}" type="video/mp4">
               </video>
            </div>

            <#else>
            <h3>No Media has been added.</h3>
            </#if>
         </center>
      </div>

      <div id="footerx">
        <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
        Conducted in the Academic Year 2020</p>
      </div>

   </body>
</html>