package org.ceeker.web.sbootm.common.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 二维码工具
 * @author zhangxiaoling01
 * @date  2016年1月20日 下午3:42:43
 * @see
 */
public class QrcodeUtil {

    private final static Logger log = LoggerFactory.getLogger(QrcodeUtil.class);

    private static final String CHARSET = "UTF-8";

    /**
     * 生成二维码
     * @param content
     * @param width
     * @param height
     * @param out
     * @return
     */
    public static BufferedImage generateQr(String content, int width, int height) {
        try {
            return MatrixToImageWriter.toBufferedImage(createBitMatrix(content, width, height));
        } catch (Exception e) {
            log.error("generateQr exception:" + e);
        }
        return null;
    }

    /**
     * 生成带logo的二维码图片
     * @param content
     * @param logoPath
     * @return
     * @throws Exception
     */
    public static BufferedImage getQrWithLogo(String content,int width,int heigth, String logoPath) throws Exception {
        return mergeLogo(createBitMatrix(content, width, heigth), width,heigth, logoPath);
    }

    /**
     * 输出二维码到输出流中
     * @param content
     * @param width
     * @param height
     * @param format
     * @param out
     */
    public static void outputQr(String content, int width, int height, ImgFormat format, OutputStream out) {
        try {
            MatrixToImageWriter.writeToStream(createBitMatrix(content, width, height), format.getValue(), out);
        } catch (Exception e) {
            log.error("generateQr exception:" + e);
        }
    }

    /**
     * 输出二维码到文件
     * @param content
     * @param width
     * @param height
     * @param format
     * @param out
     */
    public static void saveQr(BufferedImage img, String savePath, ImgFormat imgFormat) {
        try {
            ImageIO.write(img, imgFormat.getValue(), new File(savePath));
        } catch (IOException e) {
            log.error("saveQr exception:" + e);
        }
    }

    /**
     * 生成二维码矩阵
     * @param content
     * @param width
     * @param height
     * @return
     * @throws WriterException
     */
    private static BitMatrix createBitMatrix(String content, int width, int height) throws WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        //设置边框
        hints.put(EncodeHintType.MARGIN, 0);
        return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
    }

    /**
     * 将logo合并到二维码中
     * 微信二维码中二位码尺寸为：430*430
     * logo尺寸为：90*90
     * @param matrix 二维码矩阵相关
     * @param logoPath logo路径
     * @throws IOException
     * @throws IOException
     */
    private static BufferedImage mergeLogo(BitMatrix matrix, int width, int height, String logoPath) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        Graphics2D gs = image.createGraphics();
        //载入logo
        Image img = ImageIO.read(new File(logoPath));
//        gs.drawImage(img, 125, 125, null);
        gs.drawImage(img,17*width/43,17*width/43, null);
        gs.dispose();
        img.flush();
        return image;
    }
    
    

    /**
     * 将二维码矩阵转换为图片
     * @param matrix
     * @return
     */
    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? MatrixToImageConfig.BLACK : MatrixToImageConfig.WHITE);
            }
        }
        return image;
    }

    public static void main(String[] args) {
        try {
            //            BufferedImage img = getCodeWithLogo("http://www.baidu.com", "d:\\test\\logo.jpg");
            BufferedImage img = generateQr("http://www.baidu.com", 430, 430);
            saveQr(img, "d:\\test\\" + img.hashCode() + ".jpg", ImgFormat.JPG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }

    /**
     * 图片格式枚举
     * @author zhangxiaoling01
     * @date  2016年1月20日 下午3:50:54
     * @see
     */
    public enum ImgFormat {
        JPG("jpg"), JPEG("jpeg"), PNG("png");

        private String value;

        ImgFormat(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

    }

}
