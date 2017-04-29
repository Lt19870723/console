/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2007 All Rights Reserved.
 */
package com.cxdai.console.security.custody;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xpath.XPathAPI;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * SignManagerDemo的实现类  用于实现签名、验签
 *
 */
public class SignManagerImpl implements SignManager {
//    private static final Logger logger = LoggerFactory.getLogger(SignManagerDemoImpl.class);
    private KeyReader       keyReaderDemo;

    static {
        org.apache.xml.security.Init.init();
        try {
            //设置Signature标签的前缀,这个前缀是不影响签名校验结果的。
            Constants.setSignatureSpecNSprefix("ds");
        } catch (XMLSecurityException e) {
            System.err.println("org.apache.xml.security.utils.Constants初始化出错！");
            throw new RuntimeException(e);
        }
    }    
    
    /**
     * 非对称密钥校验签名
     * @param key
     * @param content
     * @param signature
     * @param algorithmsName
     * @param algorithms
     * @return
     */
    public boolean keyPairCheck(String key, String content, String signature,
                                String algorithmsName, String algorithms) {
        PublicKey pubKey = null;
        try {
            pubKey = keyReaderDemo.readPublicKey(key, true, algorithmsName);

        } catch (Exception e) {
//            logger.error("SignManagerImpl error:", e);
        	System.out.println("SignManagerImpl error:" + e);
        }
        return keyPairCheck(pubKey, content, signature, algorithms);

    }

    /**
     * 非对称密钥验签
     * 
     * @param pubKey   公钥
     * @param content  原数据
     * @param signature 已签名数据
     * @param algorithms 算法
     * @return
     */
    public boolean keyPairCheck(PublicKey pubKey, String content, String signature,
                                String algorithms) {
        try {
            byte[] signed = Base64.decodeBase64(signature.getBytes(DEFAULT_CHARSET));
            Signature signCheck = Signature.getInstance(algorithms);
            signCheck.initVerify(pubKey);
            //对原数据进行签名
            signCheck.update(content.getBytes(DEFAULT_CHARSET));
            //对原数据签名后与签名数据进行比较
            return signCheck.verify(signed);
        } catch (Exception e) {
//            logger.error("SignManagerImpl error:", e);
        	System.out.println("SignManagerImpl error:" + e);
        }
        return false;
    }

    /**
     * 非对称密钥签名
     * @param content	原数据
     * @param keyStr	证书ID
     * @param algorithmsName	
     * @param algorithms
     * @return
     */
    public String keyPairSign(String content, String keyStr, String algorithmsName,
                              String algorithms) {

        PrivateKey priKey = null;
        try {
            priKey = keyReaderDemo.readPrivateKey(keyStr, true, algorithmsName);

        } catch (Exception e) {
        	System.out.println("SignManagerImpl error:" + e);
        }
        return keyPairSign(content, priKey, algorithms);
    }

    /**
     * 非对称密钥签名   
     * @param content	原数据
     * @param priKey	私钥
     * @param algorithms 签名算法  SHA1WithRSA  或  DSA
     * @return
     */
    public String keyPairSign(String content, PrivateKey priKey, String algorithms) {

        try {
            Signature signature = Signature.getInstance(algorithms);
            signature.initSign(priKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));
            return new String(Base64.encodeBase64(signature.sign(), false), DEFAULT_CHARSET);
        } catch (Exception e) {
        	System.out.println("SignManagerImpl error:" + e);
        }

        return null;
    }
    
    /**
     * MD5签名
     * @param content
     * @param keyStr
     * @return
     */
    public String md5Sign(String content, String keyStr) {
        try {
            return DigestUtils.md5Hex((content + keyStr).getBytes(DEFAULT_CHARSET));
        } catch (Exception e) {
        	System.out.println("SignManagerImpl error:" + e);
        }
        return null;
    }

    /**
     * 传入私钥签名
     */
    public String sign(String content, String signType, PrivateKey priKey) {
        if (signType.equals(RSA_ALGORITHM_NAME)) {//RSA算法
            return keyPairSign(content, priKey, RSA_SIGN_ALGORITHMS);
        } else if (signType.equals(DSA_ALGORITHM_NAME)) {//DSA算法
            return keyPairSign(content, priKey, DSA_SIGN_ALGORITHMS);

        }
        return null;
    }
    
    /**
     * 传入证书ID签名
     */
    public String sign(String content, String signType, String key) {
        if (signType.equals(RSA_ALGORITHM_NAME)) {//RSA算法
            return keyPairSign(content, key, RSA_ALGORITHM_NAME, RSA_SIGN_ALGORITHMS);
        } else if (signType.equals(MD5_ALGORITHM_NAME)) {//MD5算法
            md5Sign(content, key);
        } else if (signType.equals(DSA_ALGORITHM_NAME)) {//DSA算法
            return keyPairSign(content, key, DSA_ALGORITHM_NAME, DSA_SIGN_ALGORITHMS);

        }
        return null;
    }

    /**
     * 传入证书ID验签
     */
    public boolean check(String signature, String content, String signType, String key) {
        if (signType.equals(RSA_ALGORITHM_NAME)) {
            return keyPairCheck(key, content, signature, RSA_ALGORITHM_NAME, RSA_SIGN_ALGORITHMS);
        } else if (signType.equals(MD5_ALGORITHM_NAME)) {
            return signature.equals(md5Sign(content, key));
        } else if (signType.equals(DSA_ALGORITHM_NAME)) {
            return keyPairCheck(key, content, signature, DSA_ALGORITHM_NAME, DSA_SIGN_ALGORITHMS);

        }
        return false;
    }

    /**
     * 传入公钥验签
     */
    public boolean check(String signature, String content, String signType, PublicKey pubKey) {
        if (signType.equals(RSA_ALGORITHM_NAME)) {
            return keyPairCheck(pubKey, content, signature, RSA_SIGN_ALGORITHMS);
        } else if (signType.equals(DSA_ALGORITHM_NAME)) {
            return keyPairCheck(pubKey, content, signature, DSA_SIGN_ALGORITHMS);

        }
        return false;
    }

    /**
     * 初始化证书读取类
     * @param keyReaderDemo
     */
    public void setKeyReaderDemo(KeyReader keyReaderDemo) {
        this.keyReaderDemo = keyReaderDemo;
    }
    /**
     * xml报文校验签名
     * @param doc
     * @param pubKey
     * @param keyId
     * @return
     */
    public boolean check(Document doc, PublicKey pubKey) {
        try {
            Element nscontext = XMLUtils.createDSctx(doc, "ds", Constants.SignatureSpecNS);
            Element signElement = (Element) XPathAPI.selectSingleNode(doc, "//ds:Signature[1]",nscontext);

            if (signElement == null) {
                return false;
            }

            //XMLSignature signature = new XMLSignature(signElement, doc.getDocumentURI());
            XMLSignature signature = new XMLSignature(signElement, null);

            return signature.checkSignatureValue(pubKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * xml报文签名
     * @param doc
     * @param privateKey
     * @param msgType 对应报文中需要签名域的ID
     * @return
     */
    public String sign(Document doc, PrivateKey privateKey, String msgType) {
        try {
            // 获得中金签名密钥
            //XMLSignature sig = new XMLSignature(doc, doc.getDocumentURI(),
            		XMLSignature sig = new XMLSignature(doc, null,
                XMLSignature.ALGO_ID_SIGNATURE_RSA);
            sig.getSignedInfo().addResourceResolver(new OfflineResolver());

            Node messageNode = doc.getElementsByTagName("Message").item(0);
            messageNode.appendChild(sig.getElement());

            Transforms transforms = new Transforms(doc);

            transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
//            transforms.addTransform(Transforms.TRANSFORM_C14N_WITH_COMMENTS);

            sig.addDocument("#" + msgType, transforms, Constants.ALGO_ID_DIGEST_SHA1);

            // 签名
            sig.sign(privateKey);

            // 将签名好的XML文档写出
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            XMLUtils.outputDOMc14nWithComments(doc, os);

            return os.toString("utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}