package org.example.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfPage01 {
    /**
     * 基础配置
     */
    private static final String PDF_SITE = "E:\\pdf\\PDF页面.pdf" ;
    private static final String FONT = "C:\\Windows\\Fonts\\simhei.ttf";
    private static final String PAGE_TITLE = "PDF数据导出报告" ;
    /**
     * 基础样式
     */
    private static final Font TITLE_FONT = FontFactory.getFont(FONT, BaseFont.IDENTITY_H,20, Font.BOLD);
    private static final Font NODE_FONT = FontFactory.getFont(FONT, BaseFont.IDENTITY_H,15, Font.BOLD);
    private static final Font BLOCK_FONT = FontFactory.getFont(FONT, BaseFont.IDENTITY_H,13, Font.BOLD, BaseColor.BLACK);
    private static final Font INFO_FONT = FontFactory.getFont(FONT, BaseFont.IDENTITY_H,12, Font.NORMAL, BaseColor.BLACK);
    private static final Font CONTENT_FONT = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

    private static void createPdfPage () throws Exception {
        // 创建文档
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF_SITE));
        document.open();
        // 报告标题
        document.add(PdfFontUtil.getParagraph(PAGE_TITLE,TITLE_FONT,1)) ;
        document.add(PdfFontUtil.getParagraph("\n商户名称：XXX科技有限公司",INFO_FONT,-1)) ;
        document.add(PdfFontUtil.getParagraph("\n生成时间：2021-09-02\n\n",INFO_FONT,-1)) ;
        // 报告内容
        // 段落标题 + 报表图
        document.add(PdfFontUtil.getParagraph("城市数据分布统计",NODE_FONT,-1)) ;
        document.add(PdfFontUtil.getParagraph("\n· 可视化图表\n\n",BLOCK_FONT,-1)) ;
        // 设置图片宽高
        float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
        float documentHeight = documentWidth / 580 * 320;
        document.add(PdfFontUtil.getImage("D:\\图片\\雷克萨斯.jpg",documentWidth-80,documentHeight-80)) ;
        // 数据表格
        document.add(PdfFontUtil.getParagraph("\n· 数据详情\n\n",BLOCK_FONT,-1)) ;
        PdfPTable dataTable = PdfFontUtil.getPdfPTable01(6,500) ;
        // 设置表格
        List<String> tableHeadList = tableHead () ;
        List<List<String>> tableDataList = getTableData () ;
        PdfFontUtil.addTableCell(dataTable,CONTENT_FONT,tableHeadList);
        for (List<String> tableData : tableDataList) {
            PdfFontUtil.addTableCell(dataTable,CONTENT_FONT,tableData);
        }
        document.add(dataTable);
        document.add(PdfFontUtil.getParagraph("\n· 报表描述\n\n",BLOCK_FONT,-1)) ;
        document.add(PdfFontUtil.getParagraph("数据报告可以监控每天的推广情况，" +
                "可以针对不同的数据表现进行分析，以提升推广效果。",CONTENT_FONT,-1)) ;
        document.newPage() ;
        document.close();
        writer.close();
    }
    private static List<List<String>> getTableData (){
        List<List<String>> tableDataList = new ArrayList<>() ;
        for (int i = 0 ; i < 3 ; i++){
            List<String> tableData = new ArrayList<>() ;
            tableData.add("浙江"+i) ;
            tableData.add("杭州"+i) ;
            tableData.add("276"+i) ;
            tableData.add("33.3%") ;
            tableData.add("33.3%") ;
            tableData.add("33.3%") ;
            tableData.add("33.3%") ;
            tableData.add("33.3%") ;
            tableDataList.add(tableData) ;
        }
        return tableDataList ;
    }
    private static List<String> tableHead (){
        List<String> tableHeadList = new ArrayList<>() ;
        tableHeadList.add("省份") ;
        tableHeadList.add("城市") ;
        tableHeadList.add("数量") ;
        tableHeadList.add("百分比") ;
        tableHeadList.add("百分比") ;
        tableHeadList.add("百分比") ;
        tableHeadList.add("百分比2") ;
        tableHeadList.add("百分比1") ;
        return tableHeadList ;
    }

    @Test
    public void main() throws Exception {
        createPdfPage () ;
    }
}

