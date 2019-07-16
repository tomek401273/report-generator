package com.tgrajkowski.service;

import com.tgrajkowski.model.job.JobDaoProxy;
import com.tgrajkowski.model.job.active.title.ActiveTitle;
import com.tgrajkowski.model.job.active.user.ActiveUser;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PdfService {

    @Autowired
    private JobDaoProxy jobDaoProxy;

    public ByteArrayOutputStream generate(ByteArrayOutputStream byteArrayOutputStream) {
        try {

            InputStream in = new FileInputStream(new File("template.odt"));

            IXDocReport report =
                    XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

            FieldsMetadata metadata = new FieldsMetadata();
            metadata.addFieldAsList("developers.Name");
            metadata.addFieldAsList("developers.LastName");
            metadata.addFieldAsList("developers.Mail");
            metadata.addFieldAsImage("logo");
            metadata.addFieldAsList("activeUser.login");
            metadata.addFieldAsList("activeUser.count");
            metadata.addFieldAsList("activeTitle.title");
            metadata.addFieldAsList("activeTitle.count");
            report.setFieldsMetadata(metadata);

            IContext context = report.createContext();
            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            IImageProvider iImageProvider = new ByteArrayImageProvider(inputStream);


            context.put("Report", getProjectMap());
            context.put("logo", iImageProvider);
            context.put("activeUser", getActiveUsers());
            context.put("activeTitle", getActiveTitle());

            OutputStream pdfOut = new FileOutputStream(new File("outputfreemarker.pdf"));
            ByteArrayOutputStream byteArrayOutputStreamPdf = new ByteArrayOutputStream();
            Options options =
                    Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);
            report.convert(context, options, pdfOut);
            report.convert(context, options, byteArrayOutputStreamPdf);
            return  byteArrayOutputStreamPdf;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> getProjectMap() {
        Map<String, String> project = new HashMap<String, String>();
        project.put("Title", "Bibliography Monthly Raport Usage");
        project.put("Table1Title", "The most 10 active users in this moth");
        project.put("Table2Title", "The most 10 active title in this moth");
        project.put("Table1Header1", "Login");
        project.put("Table1Header2", "Count of Verified Diploma Works");
        project.put("Table2Header1", "Title");
        project.put("Table2Header2", "Count of Verified Diploma Works");
        return project;
    }

    private List<Map<String, String>> getActiveUsers() {
        List<Map<String, String>> activeUsers = new ArrayList<>();
        List<ActiveUser> activeUsersFound = jobDaoProxy.findTheMostActiveUsers();
        for (ActiveUser activeUser : activeUsersFound) {
            Map<String, String> user = new HashMap<>();
            user.put("login", activeUser.getLogin());
            user.put("count", String.valueOf(activeUser.getCount()));
            activeUsers.add(user);
        }

        return activeUsers;
    }

    private List<Map<String, String>> getActiveTitle() {
        List<Map<String, String>> activeUsers = new ArrayList<>();
        List<ActiveTitle> theMostActiveTitle = jobDaoProxy.findTheMostActiveTitle();
        for (ActiveTitle activeUser : theMostActiveTitle) {
            Map<String, String> title = new HashMap<>();
            title.put("title", activeUser.getTitle());
            title.put("count", String.valueOf(activeUser.getCount()));
            activeUsers.add(title);
        }
        return activeUsers;
    }
}
