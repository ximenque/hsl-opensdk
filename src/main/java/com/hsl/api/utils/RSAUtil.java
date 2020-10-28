package com.hsl.api.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;

public class RSAUtil {

    /** 指定加密算法为RSA */
    private static final String ALGORITHM = "RSA";
    /** 指定公钥存放文件 */
    private static String PUBLIC_KEY_FILE = "SRAKey/PublicKey";
    /** 指定私钥存放文件 */
    private static String PRIVATE_KEY_FILE = "SRAKey/PrivateKey";

    public static void main(String[] args) throws Exception {

//        String source = "brandId2479channel1channelDescANDROID收银终端pickUpCode0001productStatus1shopId247900002timestamp1600844076168tradeId123456789";// 要加密的字符串
//        System.out.println("准备用公钥加密的字符串为：" + source);
//
//        String cryptograph = encrypt(source);// 生成的密文
//        System.out.print("用公钥加密后的结果为:" + cryptograph);
//        System.out.println();

        String target = decrypt("I3ulOcMlC9ed2xP060hpssKtRrTya/+axlsuQw7MvLuhRVlkaXtjk6Tzrr2OertkJgEJqEIHwyPiR2hrn1CY7Z8RMdvxcXOwwfoAFrfiAwfltFlwTaW9s1WrzplFKzbgZsNUvyBDT6OaK5a05v1YrrR0SOyDHLObkFyhG6H0o/xOEHnU9fFsfaLISQFTreqxvLnrZkQbAq0e98eJxSJJPjOdFhz2mjXcPpZPqCVBNOFMQW2K3wFATbeN4MTE4u5n0gUdhs8wpBxFo9FyBxD4PfO3/4e1HeciJcrkMPYaDHNr+FBcHhkARFw91QFCglGSJLZ041ioyMvea7xJNcPEWA==");// 解密密文
        System.out.println("用私钥解密后的字符串为：" + target);
        System.out.println();
    }

    /**
     * 加密方法
     * @param source 源数据
     * @return
     * @throws Exception
     */
    public static String encrypt(String source) throws Exception {

        Key publicKey = getKey(PUBLIC_KEY_FILE);

        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b1);
    }

    /**
     * 解密算法
     * @param cryptograph    密文
     * @return
     * @throws Exception
     */
    public static String decrypt(String cryptograph) throws Exception {

        Key privateKey = getKey(PRIVATE_KEY_FILE);

        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);

        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    private static Key getKey(String fileName) throws Exception, IOException {
        Key key;
        ObjectInputStream ois = null;
        try {
            /** 将文件中的私钥对象读出 */
            Resource resource = new ClassPathResource(fileName);
            ois = new ObjectInputStream(resource.getInputStream());
            key = (Key) ois.readObject();
        } catch (Exception e) {
            throw e;
        } finally {
            ois.close();
        }
        return key;
    }
}