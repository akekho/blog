package cn.liangjiateng.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public final class FileUtil {

    public static final float DEFAULT_DPI = 296;

    /**
     * pdf转image，只生成封面图(第一页)
     *
     * @param pdfPath pdf文件路径
     * @return 流
     * @throws IOException
     */
    public static InputStream pdf2Image(String pdfPath) throws IOException {
        if (pdfPath == null || "".equals(pdfPath) || !pdfPath.endsWith(".pdf"))
            throw new IllegalArgumentException("pdf路径不正确或文件格式不正确");
        PDDocument pdDocument = PDDocument.load(new File(pdfPath));
        PDFRenderer renderer = new PDFRenderer(pdDocument);
        BufferedImage image = renderer.renderImageWithDPI(0, DEFAULT_DPI);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", os);
        return new ByteArrayInputStream(os.toByteArray());
    }
}
