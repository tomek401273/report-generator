package com.tgrajkowski.service;

import com.tgrajkowski.MultipartFilesForTests;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.document.DocumentFormat;
import org.jodconverter.office.OfficeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConverterServiceTest {
    @Autowired
    private ConverterService converterService;

    @Test
    public void doConvert() throws IOException, OfficeException {
        final DocumentFormat targetFormat = DefaultDocumentFormatRegistry.getFormatByExtension("pdf");
        MultipartFile file = MultipartFilesForTests.docx();


        ByteArrayOutputStream byteArrayOutputStream = converterService.doConvert(targetFormat, file.getInputStream(), file.getOriginalFilename());
        OutputStream outputStream = new FileOutputStream(new File("src/test/java/resources/converted-pdf.pdf"));
        byteArrayOutputStream.writeTo(outputStream);
        byteArrayOutputStream.close();
        outputStream.close();

// /home/tomek/Documents/samples2/raport-generator/converted-pdf.pdf
// /home/tomek/Documents/samples2/raport-generator/src/test/java/resources/converted-pdf.pdf
    }
}
