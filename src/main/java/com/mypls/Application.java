package com.mypls;
import freemarker.template.*;

import java.io.File;
import java.io.IOException;

import static spark.Spark.*;

public class Application {


    public static void main(String[] args)
    {
        TemplateGenerator.setUpConfig();


        get("/hello", (req, res) ->"helloWorld");
    }


}
