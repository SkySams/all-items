package org.example;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/11/12
 */
public class PdfMain {

    private PDDocument pdDocument;


    /**
     * 创建
     * @throws IOException
     */
    @Test
    public void pdfOne() throws IOException {
        this.createdPDF();
        this.pdPage(10);

        System.out.println("PDF created");
        pdDocument.save("nice.pdf");
        pdDocument.close();
    }

    /**
     * 加载
     * @throws Exception
     */
    @Test
    public void pdfLoad()throws Exception{
        load("nice.pdf");
        this.pdPage(11);
        pdDocument.save("load.pdf");
    }

    /**
     * 删除
     */
    @Test
    public void removePage()throws Exception{
        load("load.pdf");
        int pages = this.getNumberOfNumber();
        this.removePage(pages-1);
        pdDocument.save("remove.pdf");
        pdDocument.close();
    }

    /**
     * pdf 属性
     */
    @Test
    public void pdDucumentInformation()throws  Exception{
        this.createdPDF();
        this.pdPage(10);
        this.setPDDocumentInformation();
        pdDocument.save("ducumentInformation.pdf");
        pdDocument.close();
    }


    /**
     * pdf添加文本
     */
    @Test
    public void pDPageContentStream() throws Exception{
        this.load("ducumentInformation.pdf");
        this.setPDPageContentStream(0);
        pdDocument.save("stream.pdf");
        pdDocument.close();
    }


    /**
     * pdf添加文本
     */
    @Test
    public void pDPageContentStreamLine() throws Exception{
        this.load("ducumentInformation.pdf");
        this.setPDPageContentStreamManyLine(1);
        pdDocument.save("line.pdf");
        pdDocument.close();
    }

    @Test
    public void writer() throws Exception{
        this.load("line.pdf");
        getPDFTextStripper();
    }

    /**
     * 插入图片
     * @throws Exception
     */
    @Test
    public void picture() throws Exception{
        this.load("nice.pdf");
        this.insertPicture();
        pdDocument.save("sample.pdf");
        pdDocument.close();
    }


    @Test
    public void securrity() throws Exception{
        this.load("nice.pdf");
        this.encryption();
        pdDocument.save("securrity.pdf");
        pdDocument.close();
    }



    // 创建PDF
    public void createdPDF(){
        pdDocument = new PDDocument();
    }

    // PD分页
    public void pdPage(int page){
        for (int i=0; i<page;i++){
            PDPage pdPage = new PDPage();
            pdDocument.addPage(pdPage);
        }
    }


    /**
     * pdf加载
     * @param path
     */
    public void load (String path) {
        try {
            pdDocument = PDDocument.load(new File(path));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取页数
     * @return
     */
    public int getNumberOfNumber(){
       int pages = pdDocument.getNumberOfPages();
        System.out.println(pages);
        return pages;
    }

    /**
     * 删除页数
     * @param page
     */
    public void removePage(int page){
        pdDocument.removePage(page);
    }

    /**
     * PDDocumentInformation
     * 此示例演示如何向PDF文档添加诸如作者，标题，日期和主题的属性。
     * @return
     */
    public PDDocumentInformation setPDDocumentInformation(){
        PDDocumentInformation pdDocumentInformation = pdDocument.getDocumentInformation();
        pdDocumentInformation.setAuthor("author：作者");
        pdDocumentInformation.setTitle("title: 标题");
        pdDocumentInformation.setCreator("PDF Examples");
        pdDocumentInformation.setSubject("Example document");

        Calendar date = new GregorianCalendar();
        date.set(2015, 11, 5);
        date.set(2016, 6, 5);
        pdDocumentInformation.setModificationDate(date);
        pdDocumentInformation.setKeywords("sample, first example, my pdf");
        return pdDocumentInformation;
    }

    /**
     * 设置文本
     * @param page
     * @throws Exception
     */
    public void setPDPageContentStream(int page) throws Exception{
        PDPageContentStream stream = new PDPageContentStream(pdDocument,pdDocument.getPage(page));
        stream.beginText();
        stream.setFont(PDType1Font.TIMES_ROMAN,12);
        stream.newLineAtOffset(25, 500);
        String text = "This is the sample document and we are adding content to it.";
        stream.showText(text);
        stream.endText();
        System.out.println("Content added");
        stream.close();
    }


    /**
     * 设置多行文本
     */
    public void setPDPageContentStreamManyLine(int page) throws Exception{
        PDPageContentStream stream = new PDPageContentStream(pdDocument,pdDocument.getPage(page));
        stream.beginText();
        stream.setFont( PDType1Font.TIMES_ROMAN, 16 );

        stream.setLeading(14.5f);

        stream.newLineAtOffset(25, 725);

        String text = "This is the sample document and we are adding content to it.";

        String text1 = "This is an example of adding text to a page in the pdf document. we can add as many lines";
        String text2 = "as we want like this using the ShowText()  method of the ContentStream class";

        stream.showText(text);
        stream.newLine();
        stream.showText(text1);
        stream.newLine();
        stream.showText(text2);

        stream.endText();
        System.out.println("Content added");
        stream.close();
    }

    /**
     * PDF阅读文档
     */
    public void getPDFTextStripper() throws IOException {
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(pdDocument);
        System.out.println(text);
        pdDocument.close();
    }

    /**
     * 插入图片
     */
    public void insertPicture() throws IOException{
        PDPage page = pdDocument.getPage(0);
        PDImageXObject pdImage = PDImageXObject.createFromFile("logo.png", pdDocument);
        PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page);
        contentStream.drawImage(pdImage, 70, 250);
        System.out.println("Image inserted");
        contentStream.close();
    }
    /**
     您可以使用StandardProtectionPolicy和AccessPermission类提供的方法加密PDF文档。
     AccessPermission类用于通过分配访问权限，以保护PDF文档
     */
    public void encryption() throws IOException{
        // 创建访问权限对象
        AccessPermission ap = new AccessPermission();
        //创建StandardProtectionPolicy对象
        StandardProtectionPolicy spp = new StandardProtectionPolicy("147258","123456",ap);
        spp.setEncryptionKeyLength(128);
        spp.setPermissions(ap);
        pdDocument.protect(spp);
        System.out.println("Document encrypted");
    }

    /**
     您可以使用PDActionJavaScript类将JavaScript操作添加到PDF文档。这代表一个JavaScript操作
     */
    @Test
    public void script() throws IOException {
        File file = new File("load.pdf");
        PDDocument document = PDDocument.load(file);
        String javaScript = "app.alert( {cMsg: \"this is an example\",nIcon: 3, "
                + "nType: 0, cTitle: PDFBox Javascript example });";
        PDActionJavaScript PDAjavascript = new PDActionJavaScript(javaScript);
        document.getDocumentCatalog().setOpenAction(PDAjavascript);
        document.save( new File("script.pdf") );
        System.out.println("Data added to the given PDF");
        document.close();
    }

    /**
     * 拆分PDF文档
     * @throws IOException
     */
    @Test
    public void splitPages()throws IOException{
        File file = new File("load.pdf");
        PDDocument document = PDDocument.load(file);

        //Instantiating Splitter class
        Splitter splitter = new Splitter();

        //splitting the pages of a PDF document
        List<PDDocument> Pages = splitter.split(document);

        //Creating an iterator
        Iterator<PDDocument> iterator = Pages.listIterator();

        //Saving each page as an individual document
        int i = 1;
        while(iterator.hasNext()) {
            PDDocument pd = iterator.next();
            pd.save("splitPages"+ i++ +".pdf");
        }
        System.out.println("Multiple PDF’s created");
        document.close();
    }

    /**
     *  合并多个PDF文档
     * @throws IOException
     */
    @Test
    public void merge()throws IOException{
        File file1 = new File("load.pdf");
        PDDocument doc1 = PDDocument.load(file1);

        File file2 = new File("nice.pdf");
        PDDocument doc2 = PDDocument.load(file2);

        //Instantiating PDFMergerUtility class
        PDFMergerUtility PDFmerger = new PDFMergerUtility();

        //Setting the destination file
        PDFmerger.setDestinationFileName("merged.pdf");

        //adding the source files
        PDFmerger.addSource(file1);
        PDFmerger.addSource(file2);

        //Merging the two documents
        PDFmerger.mergeDocuments();

        System.out.println("Documents merged");
        //Closing the documents
        doc1.close();
        doc2.close();
    }

    /**
     * 提取图像
     */
    @Test
    public void getImage() throws Exception{
        File file = new File("sample.pdf");
        PDDocument document = PDDocument.load(file);

        //Instantiating the PDFRenderer class
        PDFRenderer renderer = new PDFRenderer(document);

        //Rendering an image from the PDF document
        BufferedImage image = renderer.renderImage(0);

        //Writing the image to a file
        ImageIO.write(image, "JPEG", new File("myimage.jpg"));
        System.out.println("Image created");
        //Closing the document
        document.close();
    }
    /**
     *  添加矩形
     */
    @Test
    public void rect() throws Exception{
        File file = new File("load.pdf");
        PDDocument document = PDDocument.load(file);

        //Retrieving a page of the PDF Document
        PDPage page = document.getPage(0);

        //Instantiating the PDPageContentStream class
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        //Setting the non stroking color
        contentStream.setNonStrokingColor(Color.DARK_GRAY);

        //Drawing a rectangle
        contentStream.addRect(200, 650, 100, 100);

        //Drawing a rectangle
        contentStream.fill();

        System.out.println("rectangle added");

        //Closing the ContentStream object
        contentStream.close();
        //Saving the document
        File file1 = new File("colorbox.pdf");
        document.save(file1);
        //Closing the document
        document.close();
    }
}
