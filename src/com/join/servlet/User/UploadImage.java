package com.join.servlet.User;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadImage {
    public static void convertStringtoImage(String encodedImageStr, String fileName) {

        try {
            // Base64解码图片
            byte[] imageByteArray = Base64.decodeBase64(encodedImageStr);

            //
            FileOutputStream imageOutFile = new FileOutputStream("D:\\JoinService\\web\\Proflie" + fileName+".jpg");
            imageOutFile.write(imageByteArray);

            imageOutFile.close();

            System.out.println("Image Successfully Stored");
        } catch (FileNotFoundException fnfe) {
            System.out.println("Image Path not found" + fnfe);
        } catch (IOException ioe) {
            System.out.println("Exception while converting the Image " + ioe);
        }
    }
}
