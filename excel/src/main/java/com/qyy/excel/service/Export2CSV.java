package com.qyy.excel.service;


import com.csvreader.CsvWriter;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;


/**
 * csv导出
 */
@Service
public class Export2CSV {

    public static void main(String[] args) throws Exception{

        Charset forName = Charset.forName("utf-8");
        CsvWriter writer = new CsvWriter("D:\\workbook.csv", ',',forName);


        String[] header = {"1","2","3"};

        String[] footer = {"4","5","6"};

        writer.writeRecord(header);
        writer.writeRecord(footer);

        writer.close();



    }
}
