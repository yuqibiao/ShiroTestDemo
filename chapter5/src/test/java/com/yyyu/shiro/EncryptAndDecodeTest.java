package com.yyyu.shiro;

import junit.framework.Assert;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

import java.security.Key;

/**
 * 功能：加密和解密测试
 *
 * @author yu
 * @date 2017/9/22.
 */

public class EncryptAndDecodeTest extends BaseTest{

    @Test
    public void testBase64(){
        String str = "hello 123";
        String base64Str = Base64.encodeToString(str.getBytes());
        log("base64Str=="+base64Str);
        String decodeStr = Base64.decodeToString(base64Str.getBytes());
        log("decodeStr=="+decodeStr);
    }

    @Test
    public void testHex(){
        String str = "hello 123";
        String encodeStr= Hex.encodeToString(str.getBytes());
        log("encodeStr=="+encodeStr);
        String decodeStr = new String(Hex.decode(encodeStr));
        log("decodeStr=="+decodeStr);
    }

    @Test
    public void testCodecSupport() {
        String str = "hello 中文";
        byte[] bytes = CodecSupport.toBytes(str, "utf-8");
        String str2 = CodecSupport.toString(bytes, "utf-8");
        log(str2);
    }

    @Test
    public void testRandom() {
        //生成随机数
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("123".getBytes());
        log("生成的安全随机数："+randomNumberGenerator.nextBytes().toHex());
    }

    @Test
    public void testMd5(){
        String str = "hello";
        String salt = "123";
        String md5Str = new Md5Hash(salt , salt).toString();
        log("md5Str=="+md5Str);
    }

    @Test
    public void testSha1(){
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha1Hash(str, salt).toString();
        log("sha1=="+sha1);
    }

    @Test
    public void testSha256() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha256Hash(str, salt).toString();
        System.out.println(sha1);

    }

    @Test
    public void testSha384() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha384Hash(str, salt).toString();
        System.out.println(sha1);

    }

    @Test
    public void testSha512() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha512Hash(str, salt).toString();
        System.out.println(sha1);

    }

    @Test
    public void testSimpleHash(){
        String str = "hello";
        String salt = "123";
        //MessageDigest
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
        log("simpleHash="+simpleHash);
    }

    @Test
    public void testHashService(){
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("1231"));
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        hashService.setHashIterations(1);//生成hash值的迭代次数

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5")
                .setSource(ByteSource.Util.bytes("hello"))
               .setSalt(ByteSource.Util.bytes("1231"))
                .setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        log("hex="+hex);
    }

    @Test
    public void testAesCipherService(){
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);//设置key长度
        //生成key
        Key key = aesCipherService.generateNewKey();
        String keyStr = key.getEncoded().toString();
        log("keyStr=="+keyStr);
        String text = "hello";
        String encryptStr = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        log("encryptStr=="+encryptStr);
        ByteSource decrypt = aesCipherService.decrypt(Hex.decode(encryptStr), key.getEncoded());
        String decryptStr = new String(decrypt.getBytes());
        log("decryptStr="+decryptStr);
    }

}
