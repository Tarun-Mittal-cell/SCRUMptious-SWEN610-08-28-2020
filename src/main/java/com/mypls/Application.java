package com.mypls;
import static spark.Spark.*;

public class Application {


    public static void main(String[] args)
    {
        TemplateGenerator.setUpConfig();
       // DatabaseController.insertDatabase("");
            DatabaseController.queryDatabase("");
      //  get("/hello", (req, res) ->"helloWorld");
    }


}
