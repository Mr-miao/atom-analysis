package com.nantian.util.str;


import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util {
	
    public static byte[] createChecksum(String filename)
        throws Exception {
        InputStream fis = new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        MessageDigest diggest = MessageDigest.getInstance("MD5");
        int numRead = -1;

        do {
            numRead = fis.read(buffer);

            if (numRead > 0) {
                diggest.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();

        return diggest.digest();
    }

    public static String getMD5Checksum(String filename)
        {
        try {
			byte[] b = createChecksum(filename);
			String result = "";

			for (int i = 0; i < b.length; i++) {
			    result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
			}

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    /**
   	 * 字符串转换为MD5
   	 */
   	public static String strToMD5(String str) {
   		MessageDigest messageDigest = null;

   		try {
   			messageDigest = MessageDigest.getInstance("MD5");

   			messageDigest.reset();

   			messageDigest.update(str.getBytes("UTF-8"));
   		} catch (NoSuchAlgorithmException e) {
   			e.printStackTrace();
   		} catch (UnsupportedEncodingException e) {
   			e.printStackTrace();
   		}

   		byte[] byteArray = messageDigest.digest();

   		StringBuffer md5StrBuff = new StringBuffer();

   		for (int i = 0; i < byteArray.length; i++) {
   			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
   				md5StrBuff.append("0").append(
   						Integer.toHexString(0xFF & byteArray[i]));
   			else
   				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
   		}

   		return md5StrBuff.toString();
   	}
}
