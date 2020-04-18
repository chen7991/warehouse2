package com.casic.warehouse2.common;

import com.casic.warehouse2.bean.Customer;
import com.casic.warehouse2.bean.Provider;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExportProviderUtils {
    /**
     * 导出供应商数据
     * @param providers
     * @param sheetName
     * @return
     */
    //@SuppressWarnings("deprecation")
    public static ByteArrayOutputStream exportProvider(List<Provider> providers, String sheetName) {
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
        CellRangeAddress region1 = new CellRangeAddress(0,0,0,11);
        CellRangeAddress region2 = new CellRangeAddress(1,1,0,11);
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
        row1_cell.setCellValue("供应商数据");

        //创建第二行
        index++;
        HSSFRow row2 = sheet.createRow(index);
        HSSFCell row2_cell = row2.createCell(0);
        //设置标题样式
        row2_cell.setCellStyle(subTitleStyle);
        //设置单元格内容
        row2_cell.setCellValue("总条数"+providers.size()+"  导出时间："+new Date().toLocaleString());

        //第三行
        index++;
        String[] titles = {"ID", "供应商名称", "邮编" ,"供应商地址", "供应商电话", "联系人", "联系人电话",
                "开户行", "账号", "邮箱" ,"传真" ,"是否有效"};
        HSSFRow row3 = sheet.createRow(index);
        for (int i = 0; i <titles.length ; i++) {
            HSSFCell row3_cell=row3.createCell(i);
            row3_cell.setCellStyle(tableTitleStyle);
            row3_cell.setCellValue(titles[i]);
        }

        //第四行
        for (int i = 0; i <providers.size() ; i++) {
            Provider provider= providers.get(i);
            index++;
            HSSFRow row = sheet.createRow(index);
            //创建列ID
            HSSFCell row_id=row.createCell(0);
            row_id.setCellStyle(baseStyle);
            row_id.setCellValue(provider.getId());
            //创建列供应商名称
            HSSFCell row_providername=row.createCell(1);
            row_providername.setCellStyle(baseStyle);
            row_providername.setCellValue(provider.getProvidername());
            //创建列邮编
            HSSFCell row_zip=row.createCell(2);
            row_zip.setCellStyle(baseStyle);
            row_zip.setCellValue(provider.getZip());
            //创建列供应商地址
            HSSFCell row_Address=row.createCell(3);
            row_Address.setCellStyle(baseStyle);
            row_Address.setCellValue(provider.getAddress());
            //创建列供应商电话
            HSSFCell row_Telephone=row.createCell(4);
            row_Telephone.setCellStyle(baseStyle);
            row_Telephone.setCellValue(provider.getTelephone());
            //创建列联系人
            HSSFCell row_Connectionperson=row.createCell(5);
            row_Connectionperson.setCellStyle(baseStyle);
            row_Connectionperson.setCellValue(provider.getConnectionperson());
            //创建列联系电话
            HSSFCell row_Phone=row.createCell(6);
            row_Phone.setCellStyle(baseStyle);
            row_Phone.setCellValue(provider.getPhone());
            //创建列开户行
            HSSFCell row_Bank=row.createCell(7);
            row_Bank.setCellStyle(baseStyle);
            row_Bank.setCellValue(provider.getBank());
            //创建列账户
            HSSFCell row_Account=row.createCell(8);
            row_Account.setCellStyle(baseStyle);
            row_Account.setCellValue(provider.getAccount());
            //创建列邮箱
            HSSFCell row_Email=row.createCell(9);
            row_Email.setCellStyle(baseStyle);
            row_Email.setCellValue(provider.getEmail());
            //创建列传真
            HSSFCell row_Fax=row.createCell(10);
            row_Fax.setCellStyle(baseStyle);
            row_Fax.setCellValue(provider.getFax());
            //创建列是否有效
            HSSFCell row_Available=row.createCell(11);
            row_Available.setCellStyle(baseStyle);
            row_Available.setCellValue(provider.getAvailable());
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
