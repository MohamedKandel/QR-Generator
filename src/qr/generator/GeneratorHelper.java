/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qr.generator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.*;
import java.util.*;

/**
 *
 * @author Mohamed
 */
public class GeneratorHelper {

    private static GeneratorHelper instance;

    private GeneratorHelper() {

    }

    public static GeneratorHelper getInstance() {
        if (instance == null) {
            instance = new GeneratorHelper();
        }
        return instance;
    }

    public void createQR(String data, String path)
            throws WriterException, IOException {

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                ErrorCorrectionLevel.L);

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes("UTF-8"), "UTF-8"),
                BarcodeFormat.QR_CODE, 500, 500);

        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                new File(path));
    }

    private String readFile(String filePath) {
        String data = "";
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line!= null) {
                data+=line;
                line = br.readLine();
                data+= "\n";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    
    public String[] readFileLineByLine(String filePaht) {
        String fileData = readFile(filePaht);
        return fileData.split("\n");
    }
}
