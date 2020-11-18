/**
 * Freemarker template class to handle the rendering of webpages.
 */
package com.mypls;

import freemarker.cache.TemplateCache;
import freemarker.template.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TemplateGenerator {


    private Map <String, Object> model;//Data model for freemarker
    private Configuration config;//Config for freemarker

    public TemplateGenerator()
    {
        this.model = new HashMap<>();
        this.config = new Configuration(Configuration.VERSION_2_3_30);

    }

    /**
     * Set up freemarker configuration
     */
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

    /**
     * Render webpage
     * @param page name of template used to render pages.
     */
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

    public void setModel(String key,Object data) {

        this.model.put(key, data);
    }


    public  Object getModel(String key) {
        return model.get(key);
    }

    public void removeModel(String tag) {
        this.model.remove(tag);
    }

    public void removeAll() {
        this.model.clear();
    }

}
