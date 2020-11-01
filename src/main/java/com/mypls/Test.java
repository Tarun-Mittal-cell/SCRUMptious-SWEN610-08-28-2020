package com.mypls;

import spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

public class Test {

    public static void main(String[] args) {
        enableDebugScreen();



        //File uploadDir = new File("src/main/resources/public/media");
       // uploadDir.mkdir(); // create the upload directory if it doesn't exist
      //  staticFiles.location("/public/");

        staticFiles.externalLocation("src/main/resources/public/");

        get("/", (req, res) ->
                "<form method='post' enctype='multipart/form-data'>" // note the enctype
                        + "    <input type='file' name='uploaded_file' accept='.mp4'>" // make sure to call getPart using the same "name" in the post
                        + "    <button>Upload picture</button>"
                        + "</form>"
                        + "<h1>You uploaded this image:<h1><img src='media/cool.png'>"
        );

        post("/", (req, res) -> {

                    Part uploadfile=null;

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            String fileName=getFileName(req.raw().getPart("uploaded_file"));
            //fileName=fileName.substring(0,fileName.indexOf("."));
           // System.out.println("Name :"+fileName);
            File uploadDir = new File("src/main/resources/public/media/"+fileName);

           // Path tempFile = Files.createTempFile(uploadDir.toPath(), fileName, ".pdf");
            try {
                uploadfile = req.raw().getPart("uploaded_file");
                System.out.println(uploadfile.getSize()+""+uploadfile.getName()+" "+uploadfile.getHeader("Content-Disposition") );
                InputStream input = uploadfile.getInputStream() ;// getPart needs to use same "name" as input field in form
                Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            //logInfo(req, uploadDir.toPath());

          //  return "<h1>You uploaded this image:<h1><iframe  src='media/"+ uploadDir.toPath().getFileName() +"' width=\"100%\" height=\"500px\" > </iframe>";

            return "<video width=\"320\" height=\"240\" controls>"
                    +" <source src=\"media/"+uploadDir.toPath().getFileName()+"\" type=\"video/mp4\">"
                    +"Your browser does not support the video tag."
                   +" </video>";
        });

    }

    // methods used for logging
    private static void logInfo(Request req, Path tempFile) throws IOException, ServletException {
        System.out.println("Uploaded file '" + getFileName(req.raw().getPart("uploaded_file")) + "' saved as '" + tempFile.toAbsolutePath() + "'");
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
