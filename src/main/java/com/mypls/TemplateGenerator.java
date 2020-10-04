package com.mypls;

import freemarker.cache.TemplateCache;
import freemarker.template.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TemplateGenerator {


    private Map model;
    private Configuration config;

    public TemplateGenerator()
    {
        this.model = new HashMap<>();
        this.config = new Configuration(Configuration.VERSION_2_3_30);

    }

    public void setUpConfig()
    {


        try {
            this.config.setClassForTemplateLoading(this.getClass(), "/templates");

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        config.setDefaultEncoding("UTF-8");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        config.setLogTemplateExceptions(false);
        config.setWrapUncheckedExceptions(true);
        config.setFallbackOnNullLoopVariable(false);

    }

    public Object render(String page)
    {
        Template  temp= null;
        try
        {
            temp = this.config.getTemplate(page);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Writer out = new StringWriter();
        try
        {
            temp.process(this.model, out);
        }
        catch (TemplateException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;
    }

    public void setModel(String tag,Object model) {
        this.model.put(tag,model);
    }

    public void setModelNull() {
        this.model=null;
    }

    public void removeModel(String tag,Object model) {
        this.model.remove(tag);
    }


}
