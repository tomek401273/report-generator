package com.tgrajkowski.service;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public void generate(InputStream inputStream) {
        try {

            InputStream in = new FileInputStream(new File("template.odt"));

            IXDocReport report =
                    XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

            FieldsMetadata metadata = new FieldsMetadata();
            metadata.addFieldAsList("developers.Name");
            metadata.addFieldAsList("developers.LastName");
            metadata.addFieldAsList("developers.Mail");
            metadata.addFieldAsImage("logo");
            report.setFieldsMetadata(metadata);

            IContext context = report.createContext();
            IImageProvider iImageProvider = new ByteArrayImageProvider(inputStream);


            context.put("project", getProjectMap());
            context.put("developers", getDevelopers());
            context.put("logo", iImageProvider);

            OutputStream pdfOut = new FileOutputStream(new File("outputfreemarker.pdf"));
            Options options =
                    Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);
            report.convert(context, options, pdfOut);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getProjectMap() {
        Map<String, String> project = new HashMap<String, String>();
        project.put("Name", "Test");
        project.put("HomePage", "http://www.test.com");
        project.put("Developer", "test@test.com");
        return project;
    }


    public  List<Map<String, String>> getDevelopers() {
        List<Map<String, String>> developers = new ArrayList<>();
        Map<String, String> developer1 = new HashMap<>();
        developer1.put("Name", "Name");
        developer1.put("LastName", "Tester");
        developer1.put("Mail", "tester@test.com");
        developers.add(developer1);

        Map<String, String> developer2 = new HashMap<>();
        developer2.put("Name", "Name2");
        developer2.put("LastName", "Tester2");
        developer2.put("Mail", "tester@test.com2");
        developers.add(developer2);
        return developers;
    }
}
