<!DOCTYPE html>
<html>
   <head>
      <title>MyPLS Registration</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

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
          width : 900px;
      }

      #password_1 {
          font-size: 14pt;
          height: 42px;
          width : 900px;
      }

      #password_2 {
          font-size: 14pt;
          height: 42px;
          width : 900px;
      }

      #buttonx {
          background-color: #C0392B;
          border: none;
          color: white;
          text-align: center;
          display: inline-block;
          font-size: 18px;
          cursor: pointer;
          height: 42px;
          width : 900px;
      }

      #id1{
          font-size: 18px;
      }

      #nav1 {
          border-style: none;
          background-color: #C0392B;
      }

      #a1 {
          background-color: #C0392B;
          font-family: 'Lucida Sans Unicode';
          color: white;
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
          background-color: #C0392B;
          color: white;
          font-size: 12px;
          text-align: center;
      }

      </style>
   </head>
   <body>
      <nav id="nav1" class="navbar navbar-inverse">
         <div class="container-fluid">
            <div class="navbar-header">
               <a id="a1" class="navbar-brand" href="#">MyPLS</a>
            </div>
            <ul id="a1" class="nav navbar-nav">
               <li id="a1" class="active"><a href="#">Home</a></li>
            </ul>
            <div class="navbar-header pull-right">
                <a id="a1" class="navbar-brand" href="/">Back to login</a>
            </div>
         </div>
      </nav>
      <div class= "container">
         <div class= "header">
            <h2>Register</h2>
         </div>

         <div class="container">
            <form action= "/Registration" method ="post">
               <div class="form-group">
                  <#if blankSpaces??>
                  <p style="color:red;">Invalid Input! Please re-enter values properly.</p>
                  </#if>

                  <input id="txtbox" type ="text" name="fname" class="form-control" placeholder="First Name" required autofocus>
               </div>

                <br>

               <div class="form-group">
                  <input id="txtbox" type ="text" name="lname" class="form-control" placeholder="Last Name" required>
               </div>

               <br>

               <div class="form-group">
                  <input id="txtbox" type ="email" name="email" class="form-control" placeholder="Email ID" required>
                  <#if !isEmailUnique>
                  <p> Email already assigned to an account!
                     </#if>
               </div>

               <br>

               <div id="txtbox" class="form-group">
                  <input id="password_1" type ="password" name="password_1" class="form-control" placeholder="Password" required>
               </div>

               <br>

               <div id="txtbox" class="form-group">
                  <input id="password_2" type ="password" name="password_2" class="form-control" placeholder="Confirm Password" required>
                  <span id='message'></span>
               </div>

               <br>

               <div class="form-group">
                  <label>Role</label>
                  <select class="form-control" name="type" id="txtbox">
                     <option>Learner</option>
                     <option>Professor</option>
                  </select>
               </div>

               <br>

               <button id="buttonx" class="btn btn-success" type = "submit" name ="reg_user"> Submit </button>
               <script>
                  $(document).ready(function(){
                  $("button").hide();
                    $("#password_1,#password_2").on("keyup", function () {
                      if ($("#password_1").val() == $("#password_2").val())
                      {
                          $('#message').html('Matching').css('color', 'green');
                          $("button").show();
                      }
                      else
                      {
                          $('#message').html('Not Matching').css('color', 'red');
                          $("button").hide();
                          }

                    });
                  });

               </script>
            </form>
         </div>
      </div>

      <div id="footerx">
        <p>SWEN 610 Project by Kemar, Manikantan, Tarun and Sanjana<br>
        Conducted in the Academic Year 2020</p>
      </div>
   </body>
</html>