package com.mypls;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

public class TemplateGenerator {


    public static void setUpConfig()
    {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        try {
            cfg.setDirectoryForTemplateLoading(new File("C:/Users/kj031313/TestSpark/src/main/java/System"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
    }
}
