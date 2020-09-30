<!DOCTYPE html>
<html>
    <title>MYPLS</title>

    <head>
         <link rel="stylesheet" href="CSS/index_styles.css">

    </head>
    <body>

    <div>
        <form method="POST" action="/registration">
            <label for="fname">First name:</label><br>
            <input type="text" id="fname" name="fname"><br>
            <label for="lname">Last name:</label><br>
            <input type="text" id="lname" name="lname"><br>
            <label for="email">Email :</label><br>
            <input type="text" id="email" name="email"><br>
            <label for="username">Username :</label><br>
            <input type="text" id="username" name="username"><br>
            <label for="password">Password :</label><br>
            <input type="text" id="password" name="password"><br>
            <label for="roles">Role:</label>
              <select name="roles" id="roles">
                <option value="Staff">Staff</option>
                <option value="Alumni">Alumni</option>
                <option value="Student">Student</option>
                <option value="Faculty">Faculty</option>
            	<option value="Professor">Professor</option>
            	<option value="Administrator">Administrator</option>
              </select>
               <br><button type="submit" class="btn btn-default">Submit</button>
            </form>
    </div>

    </body>
</html>