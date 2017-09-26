package com.example;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sahara on 2017/1/11.
 */

public class MD5Demo {
    public static void main(String[] args){
        try {
            System.out.print(getMD5("d:/photos.png")+"\n");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:/IMG_0170_copy.JPG"));
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("d:/IMG_0170.JPG")));
            byte[] byteArray=new byte[1024];
            int tmp=0;
            int current = 0;
            int total = bis.available();
            System.out.print("md5之前文件大小："+total+"\n");
            while ((tmp = bis.read(byteArray))!=-1) {
                current = current+tmp;
                bos.write(byteArray, 0, tmp);
            }
            bis.close();
            bos.flush();
            bos.close();
            BufferedInputStream bis2 = new BufferedInputStream(new FileInputStream(new File("d:/IMG_0170_copy.JPG")));
            System.out.print("md5之后文件大小："+bis2.available()+"\n");
            System.out.print(getMD5("d:/IMG_0170_copy.JPG")+"\n");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMD5(String imagePath) throws NoSuchAlgorithmException, IOException {

        InputStream in = new FileInputStream(new File(imagePath));

        StringBuffer md5 = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] dataBytes = new byte[1024];

        int nread = 0;
        while ((nread = in.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }

        byte[] mdbytes = md.digest();

        // convert the byte to hex format
        for (int i = 0; i < mdbytes.length; i++) {
            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return md5.toString().toLowerCase();
    }
}
