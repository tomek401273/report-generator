package com.tgrajkowski.controller;

import com.tgrajkowski.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.http.HttpResponse;

@RestController
@CrossOrigin(origins = "*")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public void generateReport(HttpServletResponse response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream =reportService.createRepart();
        response.setContentType("application/pdf");
        response.setContentLength(byteArrayOutputStream.toByteArray().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" +"outputfreemarker.pdf" +"\"");
        FileCopyUtils.copy(byteArrayOutputStream.toByteArray(), response.getOutputStream() );

    }

//    @RequestMapping(value="/get", method=RequestMethod.GET)
//    public ResponseEntity<byte[]> getPDF() {
//        // convert JSON to Employee
//
//
//        // generate the file
//
//        // retrieve contents of "C:/tmp/report.pdf" that were written in showHelp
////        byte[] contents = (...);
//        OutputStream byteArrayOutputStream =reportService.createRepart();
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        // Here you have to set the actual filename of your pdf
//        String filename = "output.pdf";
//        headers.setContentDispositionFormData(filename, filename);
//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        ResponseEntity<byte[]> response = new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);
//        return response;
//    }
}
