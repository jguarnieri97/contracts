package ar.edu.unlam.tpi.contracts.util;

import java.util.Base64;

import com.lowagie.text.Image;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class FileImageUtil {
    
    public static Image buildImage(String signatureBase64, Integer width, Integer height) throws Exception {
        byte[] signatureBytes = Base64.getDecoder().decode(signatureBase64);
        Image signatureImage = Image.getInstance(signatureBytes);
        signatureImage.scaleToFit(width, height); // Ajustar el tamaño de la imagen si es necesario
        return signatureImage;
    }
    
}
