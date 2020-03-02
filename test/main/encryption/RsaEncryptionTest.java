package main.encryption;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

public class RsaEncryptionTest {

    private RsaEncryption rsaEncryption;
    private String privateKeyString;
    private String publicKeyString;
    private String seed;

    @Before
    public void init() throws IOException {
        this.rsaEncryption = new RsaEncryption();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("rsa.properties");
        Properties properties = new Properties();
        properties.load(stream);
        privateKeyString = properties.getProperty("rsa.key.private");
        publicKeyString = properties.getProperty("rsa.key.public");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 2);
        System.out.println("time: " + new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        seed = String.valueOf(calendar.getTime().getTime());
    }

    @Test
    public void shouldReturnPrivateKeyEncryptedString() {
        // arrange
        String source = seed;

        // action
        String actual = rsaEncryption.encryptWithPrivateKey(source, privateKeyString);

        // assert
        assertNotNull(actual);
        System.out.println("RSA private key encrypted string: " + actual);
    }

    @Test
    public void shouldReturnPublicDecryptedString() {
        // arrange
        String source = rsaEncryption.encryptWithPrivateKey(seed, privateKeyString);

        // action
        String actual = rsaEncryption.decryptWithPublicKey(source, publicKeyString);

        // assert
        assertNotNull(actual);
        System.out.println("RSA public key decrypted string: " + actual);
    }

    @Test
    public void shouldReturnPublicEncryptedString() {
        // arrange
        String source = seed;

        // action
        String actual = rsaEncryption.encryptWithPublicKey(source, publicKeyString);

        // assert
        assertNotNull(actual);
        System.out.println("RSA public key encrypted string: " + actual);
    }

    @Test
    public void shouldReturnPrivateDecryptedString() {
        // arrange
        String source = rsaEncryption.encryptWithPublicKey(seed, publicKeyString);

        // action
        String actual = rsaEncryption.decryptWithPrivateKey(source, privateKeyString);

        // assert
        assertNotNull(actual);
        System.out.println("RSA private key decrypted string: " + actual);
    }
}
