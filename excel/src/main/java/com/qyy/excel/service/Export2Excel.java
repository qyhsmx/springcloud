package com.qyy.excel.service;


import org.apache.poi.hssf.usermodel.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 1.HSSFWorkbook------》描述内存中的Excel文档的对象
 * 2.HSSFSheet---------》描述内存中的【小页】对象
 * 3.HSSFCell-----------》描述内存中的【单元格】对象
 * 4.HSSFRow------------》描述内存中的【行】对象
 */
@Service
public class Export2Excel {


    public static void main(String[] args) throws Exception{

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("测试表");

        HSSFCellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setRightBorderColor(new Short("333"));

        //表头
        HSSFRow firstRow = sheet.createRow(0);

//        sheet.setDefaultColumnStyle();



        HSSFCell cell_0 = firstRow.createCell(0);
        HSSFCell cell_1 = firstRow.createCell(1);
        HSSFCell cell_2 = firstRow.createCell(2);
        HSSFCell cell_3 = firstRow.createCell(3);
        cell_0.setCellValue("username");
        cell_1.setCellValue("password");
        cell_2.setCellValue("hobbies");
        cell_3.setCellValue("address");



        for(int x = 0;x < 10;x++){
            HSSFRow row = sheet.createRow(x+1);

            for(int j=0;j<4;j++){

                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(cellStyle);

                switch (j){
                    case 0:
                        cell.setCellValue("cell1");
                        break;
                    case 1:
                        cell.setCellValue("cell2");
                        break;
                    case 2:
                        cell.setCellValue("cell3");
                        break;
                    case 3:
                        cell.setCellValue("cell3");
                        break;
                    default:
                        cell.setCellValue("default");
                        break;
                }
            }
        }



        FileOutputStream ou = new FileOutputStream(new File("D:\\workbook.xls"));

        workbook.write(ou);

        ou.close();


    }

}
