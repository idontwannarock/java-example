package encryption;

import org.apache.commons.net.util.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaEncryption {

    //私鑰加密、公鑰解密——加密
    public String encryptWithPrivateKey(String source, String privateKeyString) {
        String result = null;
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] resultBytes = cipher.doFinal(source.getBytes());
            result = Base64.encodeBase64String(resultBytes);
            System.out.println("私鑰加密、公鑰解密——加密 : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //私鑰加密、公鑰解密——解密
    public String decryptWithPublicKey(String source, String publicKeyString) {
        String result = null;
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] resultBytes = cipher.doFinal(Base64.decodeBase64(source));
            result = new String(resultBytes);
            System.out.println("私鑰加密、公鑰解密——解密：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //公鑰加密、私鑰解密——加密
    public String encryptWithPublicKey(String source, String publicKeyString) {
        String result = null;
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] resultBytes = cipher.doFinal(source.getBytes());
            result = Base64.encodeBase64String(resultBytes);
            System.out.println("公鑰加密、私鑰解密——加密 : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //公鑰加密、私鑰解密——解密
    public String decryptWithPrivateKey(String source, String privateKeyString) {
        String result = null;
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] resultBytes = cipher.doFinal(Base64.decodeBase64(source));
            result = new String(resultBytes);
            System.out.println("公鑰加密、私鑰解密——解密：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
