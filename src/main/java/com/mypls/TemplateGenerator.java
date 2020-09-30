package com.mypls;

import freemarker.template.*;
import java.io.*;
import java.util.Map;

public class TemplateGenerator {


    public static Object setUpConfig(Map model, String page)
    {
        Configuration config = new Configuration(Configuration.VERSION_2_3_30);
        try {
            config.setClassForTemplateLoading(TemplateGenerator.class, "/templates");

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        config.setDefaultEncoding("UTF-8");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        config.setLogTemplateExceptions(false);
        config.setWrapUncheckedExceptions(true);
        config.setFallbackOnNullLoopVariable(false);

        Template temp = null;
        try {
            temp = config.getTemplate(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer out = new StringWriter();
        try {
            temp.process(model, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;
    }


}
