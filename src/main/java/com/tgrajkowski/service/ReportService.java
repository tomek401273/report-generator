package com.tgrajkowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ReportService {
    @Autowired
    private JFreeChartServiceTime jFreeChartServiceTime;

    @Autowired
    private PdfService pdfService;


    public ByteArrayOutputStream createRepart() {
        ByteArrayOutputStream byteArrayOutputStream = jFreeChartServiceTime.create();
        return pdfService.generate(byteArrayOutputStream);
    }
}
