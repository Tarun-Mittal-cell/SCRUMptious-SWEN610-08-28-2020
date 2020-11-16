<!DOCTYPE html>
<html>
   <head>
      <title> MyPLS Login </title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

      <style>
      body {
        background-color: white;
        font-family: 'Lucida Sans Unicode';
        font-size: 18px;
      }

      #div1 {
        background-color: #C0392B;
      }

      h1 {
        font-family: 'Courier New';
        color: #FEF9E7;
        font-weight: bold;
      }

      #body2{
        color: red;
      }

      #txtbox {
          margin: 0 auto;
          font-size: 14pt;
          height: 42px;
          width : 300px;
      }

      #buttonx {
        background-color: #C0392B;
        border: none;
        color: white;
        text-align: center;
        display: inline-block;
        font-size: 18px;
        margin: 0 auto;
        cursor: pointer;
        height: 42px;
        width : 300px;
      }

      #footerx {
         position: fixed;
         left: 0;
         bottom: 0;
         width: 100%;
         background-color: #C0392B;
         color: white;
         font-size: 12px;
         text-align: center;
      }

      </style>
   </head>
   <body class = "text-center">
      <div class= "container">
         <div id="div1" class= "well">
           <h1 class="text-center" >W e l c o m e &emsp;&ensp; t o &emsp;&ensp; M y P L S</h1>
         </div>

         <form action= "/Login" method ="post">
            <div class="form-group">
                <input id="txtbox" type ="text" name="email" class="form-control" placeholder="Email address" required autofocus>
                <#if loginStatus??>
                <#if loginStatus== "EMAIL_INVALID">
                <#assign loginStatus= "None">
                <p id="body2"> Email is not registered!
                    </#if >
                    </#if >
                </div>

            <div class="form-group">
               <input id="txtbox" type ="password" name="password" class="form-control" placeholder="Password" required>
               <#if loginStatus??>
               <#if loginStatus== "PASSWORD_INVALID">
               <p id="body2"> Invalid Password!
                  <#assign loginStatus= "None">
                  </#if >
                  </#if >
            </div>
            <button id="buttonx" class="btn-primary" type="submit">Log in</button>
            <br>
            <br>
            <p>New User? Register yourself<a href="/Registration"><b> here</b></a>!</p>
         </form>

         <div id="footerx">
           <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
           Conducted in the Academic Year 2020</p>
         </div>

      </div>
   </body>
</html>