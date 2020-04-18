package com.casic.warehouse2.common;

import com.casic.warehouse2.bean.Customer;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 客户数据导出
 */
public class ExportCustomerUtils {

    /**
     * 导出客户数据
     * @param customers
     * @param sheetName
     * @return
     */
    //@SuppressWarnings("deprecation")
    public static ByteArrayOutputStream exportCustomer(List<Customer> customers,  String sheetName) {
        //组装Excel文档
        //创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建样式
        HSSFCellStyle baseStyle = ExprotStyle.createBaseStyle(workbook);
        HSSFCellStyle subTitleStyle = ExprotStyle.createSubTitleStyle(workbook);
        HSSFCellStyle tableTitleStyle = ExprotStyle.createTableTitleStyle(workbook);
        HSSFCellStyle titleStyle = ExprotStyle.createTitleStyle(workbook);
        //在工作簿创建sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //设置sheet
        sheet.setDefaultColumnWidth(25);
        //合并10列
        CellRangeAddress region1 = new CellRangeAddress(0,0,0,9);
        CellRangeAddress region2 = new CellRangeAddress(1,1,0,9);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);

        //创建第一行
        int index=0;
        HSSFRow row1 = sheet.createRow(index);
        //在第一行创建一个单元格
        HSSFCell row1_cell = row1.createCell(0);
        //设置标题样式
        row1_cell.setCellStyle(titleStyle);
        //设置单元格内容
        row1_cell.setCellValue("客户数据列表");

        //创建第二行
        index++;
        HSSFRow row2 = sheet.createRow(index);
        HSSFCell row2_cell = row2.createCell(0);
        //设置标题样式
        row2_cell.setCellStyle(subTitleStyle);
        //设置单元格内容
        row2_cell.setCellValue("总条数"+customers.size()+"  导出时间："+new Date().toLocaleString());

        //第三行
        index++;
        String[] titles = {"ID", "客户名称" ,"客户邮编", "客户地址" ,"客户电话", "联系人" ,"联系电话", "开户行" ,"账户" ,"邮箱"};
        HSSFRow row3 = sheet.createRow(index);
        for (int i = 0; i <titles.length ; i++) {
            HSSFCell row3_cell=row3.createCell(i);
            row3_cell.setCellStyle(tableTitleStyle);
            row3_cell.setCellValue(titles[i]);
        }

        //第四行
        for (int i = 0; i <customers.size() ; i++) {
            Customer customer= customers.get(i);
            index++;
            HSSFRow row = sheet.createRow(index);
            //创建列ID
            HSSFCell row_id=row.createCell(0);
            row_id.setCellStyle(baseStyle);
            row_id.setCellValue(customer.getId());
            //创建列客户名称
            HSSFCell row_customername=row.createCell(1);
            row_customername.setCellStyle(baseStyle);
            row_customername.setCellValue(customer.getCustomername());
            //创建列客户邮编
            HSSFCell row_zip=row.createCell(2);
            row_zip.setCellStyle(baseStyle);
            row_zip.setCellValue(customer.getZip());
            //创建列客户地址
            HSSFCell row_Address=row.createCell(3);
            row_Address.setCellStyle(baseStyle);
            row_Address.setCellValue(customer.getAddress());
            //创建列客户电话
            HSSFCell row_Telephone=row.createCell(4);
            row_Telephone.setCellStyle(baseStyle);
            row_Telephone.setCellValue(customer.getTelephone());
            //创建列联系人
            HSSFCell row_Connectionperson=row.createCell(5);
            row_Connectionperson.setCellStyle(baseStyle);
            row_Connectionperson.setCellValue(customer.getConnectionperson());
            //创建列联系电话
            HSSFCell row_Phone=row.createCell(6);
            row_Phone.setCellStyle(baseStyle);
            row_Phone.setCellValue(customer.getPhone());
            //创建列开户行
            HSSFCell row_Bank=row.createCell(7);
            row_Bank.setCellStyle(baseStyle);
            row_Bank.setCellValue(customer.getBank());
            //创建列账户
            HSSFCell row_Account=row.createCell(8);
            row_Account.setCellStyle(baseStyle);
            row_Account.setCellValue(customer.getAccount());
            //创建列邮箱
            HSSFCell row_Email=row.createCell(9);
            row_Email.setCellStyle(baseStyle);
            row_Email.setCellValue(customer.getEmail());
        }
        //前面Excel组装完成

        ByteArrayOutputStream outputStream =new ByteArrayOutputStream();//内存流
        //把workbook 里面的数据写到outputStream
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream;
    }
}
