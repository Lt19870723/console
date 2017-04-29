/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2007 All Rights Reserved.
 */
package com.cxdai.console.security.custody;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;


/**
 * 
 * ��ȡ֤�鹫˽Կ��
 *
 */
public class KeyReader {

    public boolean isPublicKeyValid(String key, String algorithmName) {
        try {
            readPublicKey(key, true, algorithmName);
        } catch (InvalidKeySpecException e) {
            return false;
        }

        return true;
    }

    public boolean isPrivateKeyValid(String key, String algorithmName) {
        try {
            readPrivateKey(key, true, algorithmName);
        } catch (InvalidKeySpecException e) {
            return false;
        }

        return true;
    }

    /**
     * ��ȡ˽Կ
     * @param keyStr
     * @param base64Encoded
     * @param algorithmName
     * @return
     * @throws InvalidKeySpecException
     */
    public PrivateKey readPrivateKey(String keyStr, boolean base64Encoded, String algorithmName)
                                                                                                throws InvalidKeySpecException {
        return (PrivateKey) readKey(keyStr, false, base64Encoded, algorithmName);
    }

    /**
     * ��ȡ��Կ
     * @param keyStr
     * @param base64Encoded
     * @param algorithmName
     * @return
     * @throws InvalidKeySpecException
     */
    public PublicKey readPublicKey(String keyStr, boolean base64Encoded, String algorithmName)
         throws InvalidKeySpecException {
        return (PublicKey) readKey(keyStr, true, base64Encoded, algorithmName);
    }

    /**
     * ��ȡ��Կ��X509EncodedKeySpec�Ĺ�Կ��PKCS8EncodedKeySpec�����Զ�ȡ����Կ���ݿ���Ϊ��base64������ġ�
     * @param keyStr
     * @param isPublicKey
     * @param base64Encoded
     * @param algorithmName
     * @return
     * @throws InvalidKeySpecException
     */
    private Key readKey(String keyStr, boolean isPublicKey, boolean base64Encoded,
             String algorithmName) throws InvalidKeySpecException {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithmName);

            byte[] encodedKey = keyStr.getBytes("UTF-8");

            if (base64Encoded) {
                encodedKey = Base64.decodeBase64(encodedKey);
            }

            if (isPublicKey) {
                EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedKey);

                return keyFactory.generatePublic(keySpec);
            } else {
                EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);

                return keyFactory.generatePrivate(keySpec);
            }
        } catch (NoSuchAlgorithmException e) {
            // �����ܷ���
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * ��PKCS12��׼�洢��ʽ�ж�ȡ˽ԿԿ����׺Ϊ.pfx�ļ������ļ��а���˽Կ
     * @param resourceName
     * @return
     * @throws Exception
     */
    public PrivateKey readPrivateKeyfromPKCS12StoredFile(String resourceName, String password)
                                                                                              throws Exception {
        InputStream istream = null;

        istream = new FileInputStream(resourceName);
        //ʹ��Ĭ�ϵ�keyprovider�����ܻ������⡣
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        try{
            keystore.load(istream, password.toCharArray());
        }catch(IOException e){
        	System.out.println("��˽Կ��Ϣʧ��,������Ϣ:"+e.getMessage());
        }
        Enumeration enumeration = keystore.aliases();
        String alias = null;
        for (int i = 0; enumeration.hasMoreElements(); i++) {
            alias = enumeration.nextElement().toString();
            if( keystore.isKeyEntry(alias)) break;
        }
        return (PrivateKey) keystore.getKey(alias, password.toCharArray());

    }

    /**
     * ��X509�ı�׼�洢��ʽ�ж�ȡ��Կ
     * @param resourceName ��Կ�ļ�
     * @param base64Encoded ���ļ��洢ǰ�Ƿ�ʹ��base64���루ת�����ɼ��ַ���
     * @return
     * @throws Exception
     */
    public Key fromX509StoredFile(String resourceName, boolean base64Encoded) throws Exception {

        byte[] encodedKeyByte = readByteFromFile(resourceName);
        if (base64Encoded) {
            encodedKeyByte = Base64.decodeBase64(encodedKeyByte);
        }

        //return fromByte(encodedKeyByte);
        return null;

    }

    /**
     * Base64����X.509��ʽ֤���ļ��ж�ȡ��Կ
     * @param resourceName
     * @return
     * @throws Exception
     */
    public static Key fromCerStoredFile(String resourceName) throws Exception {
        FileInputStream inputStream = new FileInputStream(resourceName);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate certificate = cf.generateCertificate(inputStream);
        return (Key) (certificate != null ? certificate.getPublicKey() : null);

    }

    /**
     * ��PKCS12��׼�洢��ʽ�ж�ȡ��Կ����׺Ϊ.pfx�ļ������ļ��а���˽Կ
     * @param resourceName
     * @return
     * @throws Exception
     */
    public Key fromPKCS12StoredFile(String resourceName, String password) throws Exception {
        InputStream istream = null;

        istream = new FileInputStream(resourceName);
        //ʹ��Ĭ�ϵ�keyprovider�����ܻ������⡣
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(istream, password.toCharArray());
        Enumeration enumeration = keystore.aliases();
        String alias = null;
        for (int i = 0; enumeration.hasMoreElements(); i++) {
            alias = enumeration.nextElement().toString();
            if( keystore.isKeyEntry(alias)) break;
        }
 
        Certificate certificate = keystore.getCertificate(alias);
        return certificate.getPublicKey();

    }

    /**
     * ���ļ��ж�ȡ�ֽ�
     * @param resourceName
     * @return
     * @throws Exception
     */
    public byte[] readByteFromFile(String resourceName) throws Exception {
        InputStream istream = null;
        ByteArrayOutputStream baos = null;

        try {
            istream = new FileInputStream(resourceName);
            baos = new ByteArrayOutputStream();

            IOUtils.copy(istream, baos);
        } catch (IOException e) {
            throw new Exception("Failed to read key file: " + resourceName, e);
        } finally {
            if (istream != null) {
                try {
                    istream.close();
                } catch (IOException e) {
                }
            }
        }
        return baos.toByteArray();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
