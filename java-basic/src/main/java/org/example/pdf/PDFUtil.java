package org.example.pdf;

import cn.hutool.core.io.resource.ClassPathResource;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author: zyh
 * @date: 2022/11/12
 */
public class PDFUtil {

    private PDFUtil(){}


    /**
     * @Description 为pdf文件添加水印
     * @Date 2022/6/6
     * @Param [tempFile, waterMark, fontSize, color, rowSpace, colSpace]
     * tempFile     需要添加水印的文件
     * watermark    水印文字
     * fontSize     水印字体大小
     * color        字体颜色{r,g,b}
     * rowSpace     行间距，大中小分别对应150/100/50
     * colSpace     列间距，大中小分别对应150/100/50
     * @return void
     **/

    public byte[] pdfWaterMark(byte[] tempFile, String waterMark, float fontSize, int[] color, int rowSpace, int colSpace) throws Exception{
        PDDocument document = PDDocument.load(tempFile);
        document.setAllSecurityToBeRemoved(true);
        // 加载水印字体
//        ClassPathResource classPathResource = new ClassPathResource("/templates/站酷酷黑.ttf");
//        InputStream inputStream =classPathResource.getInputStream();
//        PDFont font = PDType0Font.load(document, inputStream);

        // 遍历PDF文件，在每一页加上水印
        for (PDPage page : document.getPages()) {
            PDPageContentStream stream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
            PDExtendedGraphicsState r = new PDExtendedGraphicsState();

            // 设置透明度
            r.setNonStrokingAlphaConstant(0.1f);
            r.setAlphaSourceFlag(true);
            stream.setGraphicsStateParameters(r);

            // 设置水印字体颜色
            if (color.length == 3) {
                stream.setNonStrokingColor(color[0], color[1], color[2]);
            }
            stream.beginText();
//            stream.setFont(font, fontSize);
            stream.setFont(PDType1Font.TIMES_ROMAN,12);
            stream.newLineAtOffset(0, -15);

            // 获取PDF页面大小
            float pageHeight = page.getMediaBox().getHeight();
            float pageWidth = page.getMediaBox().getWidth();

            // 根据纸张大小添加水印，30度倾斜
            for (int h = 10; h < pageHeight; h = h + rowSpace) {
                for (int w = - 10; w < pageWidth; w = w + colSpace) {
                    stream.setTextMatrix(Matrix.getRotateInstance(0.3, w, h));
                    stream.showText(waterMark);
                }
            }

            // 结束渲染，关闭流
            stream.endText();
            stream.restoreGraphicsState();
            stream.close();
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        document.save(os);
        document.close();
        return os.toByteArray();
    }

}
