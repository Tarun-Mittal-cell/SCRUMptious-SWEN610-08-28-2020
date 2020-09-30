package com.mypls;
import static spark.Spark.*;

public class Application {
    static final String INDEX = "index.ftlh";
    static final String HOME = "homepage.ftlh";
    static final String REGISTRATION = "registration.ftlh";
    static final String LOGIN = "login.ftlh";

    public static void main(String[] args)
    {
        staticFileLocation("/public");

        get("/", (req, res) -> TemplateGenerator.setUpConfig(null,INDEX));
        get("/homepage", (req, res) -> TemplateGenerator.setUpConfig(null,HOME));
        get("/registration", (req, res) -> TemplateGenerator.setUpConfig(null,REGISTRATION));
        get("/login", (req, res) -> TemplateGenerator.setUpConfig(null,LOGIN));
        post("/registration", (req, res) -> { System.out.println(req.queryParams());
        return "REGISTERED"; });
    }


}
