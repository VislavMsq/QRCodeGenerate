import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QR {
    public static void main(String[] args) {
        String source = "https://github.com/VislavMsq/BankMicroserviceApp/tree/master/src";
        String path = "qrL.png";

        try {
            generateQR(source, path);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

    }

    private static void generateQR(String url, String filePath) throws WriterException {
        int width = 300;
        int height = 300;
        String format = "png";

        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // степень защиты qr

            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage qrImages = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    qrImages.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }
            File file = new File(filePath);
            ImageIO.write(qrImages, format, file);
            System.out.println("***DONE***");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}