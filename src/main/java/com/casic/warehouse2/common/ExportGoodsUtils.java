package com.casic.warehouse2.common;

import com.casic.warehouse2.bean.Goods;
import com.casic.warehouse2.bean.Provider;
import com.casic.warehouse2.controller.GoodsController;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.http.ResponseEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class ExportGoodsUtils {
    /**
     * 导出商品数据
     * @param goodss
     * @param sheetName
     * @return
     */
    //@SuppressWarnings("deprecation")
    public static ByteArrayOutputStream exportGoods(List<Goods> goodss, String sheetName) {
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
        CellRangeAddress region1 = new CellRangeAddress(0,0,0,13);
        CellRangeAddress region2 = new CellRangeAddress(1,1,0,13);
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
        row1_cell.setCellValue("商品数据");

        //创建第二行
        index++;
        HSSFRow row2 = sheet.createRow(index);
        HSSFCell row2_cell = row2.createCell(0);
        //设置标题样式
        row2_cell.setCellStyle(subTitleStyle);
        //设置单元格内容
        row2_cell.setCellValue("总条数"+goodss.size()+"  导出时间："+new Date().toLocaleString());

        //第三行
        index++;
        String[] titles = {"ID","商品名称","供应商","产地","商品规格","商品包装","生产批号",
                "批准文号","商品描述","商品价格","库存量","预警库存","商品图片","是否可用"};

        HSSFRow row3 = sheet.createRow(index);
        for (int i = 0; i <titles.length ; i++) {
            HSSFCell row3_cell=row3.createCell(i);

            row3_cell.setCellStyle(tableTitleStyle);
            row3_cell.setCellValue(titles[i]);
        }

        //第四行
        for (int i = 0; i <goodss.size() ; i++) {
            Goods goods= goodss.get(i);
            index++;
            HSSFRow row = sheet.createRow(index);
            row.setHeightInPoints(150);
            //创建列ID
            HSSFCell row_id=row.createCell(0);
            row_id.setCellStyle(baseStyle);
            row_id.setCellValue(goods.getId());
            //创建列商品名称
            HSSFCell row_Goodsname=row.createCell(1);
            row_Goodsname.setCellStyle(baseStyle);
            row_Goodsname.setCellValue(goods.getGoodsname());
            //创建列供应商
            HSSFCell row_providername=row.createCell(2);
            row_providername.setCellStyle(baseStyle);
            row_providername.setCellValue(goods.getProvidername());
            //创建列产地
            HSSFCell row_produceplace=row.createCell(3);
            row_produceplace.setCellStyle(baseStyle);
            row_produceplace.setCellValue(goods.getProduceplace());
            //创建列供商品规格
            HSSFCell row_size=row.createCell(4);
            row_size.setCellStyle(baseStyle);
            row_size.setCellValue(goods.getSize());
            //创建列商品包装
            HSSFCell row_goodspackage=row.createCell(5);
            row_goodspackage.setCellStyle(baseStyle);
            row_goodspackage.setCellValue(goods.getGoodspackage());
            //创建列生产批号
            HSSFCell row_productcode=row.createCell(6);
            row_productcode.setCellStyle(baseStyle);
            row_productcode.setCellValue(goods.getProductcode());
            //创建列批准文号
            HSSFCell row_promitcode=row.createCell(7);
            row_promitcode.setCellStyle(baseStyle);
            row_promitcode.setCellValue(goods.getPromitcode());
            //创建列商品描述
            HSSFCell row_description=row.createCell(8);
            row_description.setCellStyle(baseStyle);
            row_description.setCellValue(goods.getDescription());
            //创建列商品价格
            HSSFCell row_price=row.createCell(9);
            row_price.setCellStyle(baseStyle);
            row_price.setCellValue(goods.getPrice());
            //创建列库存量
            HSSFCell row_number=row.createCell(10);
            row_number.setCellStyle(baseStyle);
            row_number.setCellValue(goods.getNumber());
            //创建列预警库存
            HSSFCell row_dangernum=row.createCell(11);
            row_dangernum.setCellStyle(baseStyle);
            row_dangernum.setCellValue(goods.getDangernum());
            //创建列商品图片
            HSSFCell row_goodsimg=row.createCell(12);
            row_goodsimg.setCellStyle(baseStyle);
            row_goodsimg.setCellValue("");

            //图片

            ByteArrayOutputStream bos= new ByteArrayOutputStream();

            try {
                BufferedImage image = ImageIO.read(new FileInputStream(AppFileUtils.UPLOADPATH + goods.getGoodsimg()));
                ImageIO.write(image,"JPEG",bos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //画图片的顶级管理器，一个sheet只能获取一个！！！
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            /**
             * 参数4设置图片的平铺程度 最大值255  255代表铺满当前单元格，小于255就不铺满
             * 参数5列的开始坐标
             * 参数6行的开始坐标
             * 参数7列的结束坐标
             * 参数8行的结束坐标
             */
            HSSFClientAnchor anchor = new HSSFClientAnchor(0,0,0,255,(short)12,3+i,(short)13,3+i);
            anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
            patriarch.createPicture(anchor,workbook.addPicture(bos.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));

            //创建列是否有效
            HSSFCell row_Available=row.createCell(13);
            row_Available.setCellStyle(baseStyle);
            row_Available.setCellValue(goods.getAvailable());
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
