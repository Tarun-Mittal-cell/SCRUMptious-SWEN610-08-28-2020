<!DOCTYPE html>
<html>
   <head>
      <title> Discussion Group </title>
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
            background-color: #6C3483;
            border: none;
            color: white;
            text-align: center;
            display: inline-block;
            font-size: 18px;
            cursor: pointer;
            height: 45px;
            width : 250px;
            padding: 10px;
            margin: auto;
        }

        #nav1 {
            height: 70px;
            width: 100%;
            border-style: none;
            background-color: #6C3483;
            padding: 9px 10px;
        }

        h2{
            font-family: 'Courier New';
            font-weight: bold;
        }

        #a1 {
            background-color: #6C3483;
            font-family: 'Lucida Sans Unicode';
            color: white;
        }

        #txtbox {
            font-size: 15px;
            height: 42px;
            width : 100%;
        }

         #div_align {
            background-color: #6C3483;
            overflow: scroll;
            position: relative;
            padding: 7px;
            flex-direction: column-reverse;
            border: 5px;
            display: flex;
            border-style: thin;
            border-color: black;
            margin: auto;
         }

        #footerx {
            position: fixed;
            left: 0;
            bottom: 0;
            height: 40px;
            width: 100%;
            background-color: #6C3483;
            color: white;
            font-size: 12px;
            text-align: center;
        }

      </style>
   </head>
   <style>
      hr
      {
      margin: 0.3em auto;
      }
   </style>
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

      <div class="container">
         <div class="well">
            <h2 class="text-center">Discussion Group</h2>
         </div>

         <div class="row overflow-auto">
            <div class="col-md-12 text-right pull-right" style="background-color: white; border: 1px solid; padding: 10px; height:160px; overflow: scroll; display: flex;
                flex-direction: column-reverse;">
               <h5 id="chatBoxValue" style="margin:0px; padding: 0px;" autofocus>
                  <#if textFromUser??>
                  ${textFromUser?no_esc}
                  </#if>
               </h5>
            </div>

         </div>
         <div class="row">
            <div class="col-md-12" style="border: 1px solid; padding: 20px; margin: 5px 0px;">
               <form action="/DiscussionBoard" method="Post">
                  <input type="text" class="form-control" id="txtbox" name="textFromUser" autofocus>
                  <br>
                  <button id="buttonx" class="btn btn-primary pull-right" value="Submit" >Send</button>
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