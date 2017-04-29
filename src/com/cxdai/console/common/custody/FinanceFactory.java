package com.cxdai.console.common.custody;

import com.cxdai.console.security.custody.KeyReader;
import com.cxdai.console.security.custody.SignManager;
import com.cxdai.console.security.custody.SignManagerImpl;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.custody.XML;
import com.cxdai.console.util.custody.https.DataSignDP;
import com.cxdai.console.util.custody.https.HttpClientMessageSender;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.cxdai.console.common.custody.xml.XmlUtil.getFirstElementValue;

/**
 * <p>
 * Description: <br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016/5/18
 * @title cxdai_interface
 * @package com.cxdai.common.custody
 */
public class FinanceFactory {

    private Logger logger = LoggerFactory.getLogger(FinanceFactory.class);

    private PublicKey publicKey;
    private PrivateKey privateKey;
    private SignManager signManager = new SignManagerImpl();

    private FinanceFactory() {
        String basePath = FinanceFactory.class.getResource("/").getPath();
        String prxPath = basePath + PropertiesUtil.getValue("custody_phjf");
        String cerPath = basePath + PropertiesUtil.getValue("custody_czbank");
        String passwd = PropertiesUtil.getValue("clientCert_password");

        KeyReader keyReader = new KeyReader();
        try {
            publicKey = DataSignDP.readCertPublicKey(cerPath);
            privateKey = keyReader.readPrivateKeyfromPKCS12StoredFile(prxPath, passwd);
        } catch (Exception e) {
            logger.error("Read key error", e);
        }
    }

    public String sign(String message, String modelAlias) {
        try {
            XML xml = XML.readFrom(message);
            if (xml != null) {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                docBuilderFactory.setNamespaceAware(true);
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(new InputSource(new StringReader(xml.getXmlStr())));
                return signManager.sign(doc, privateKey, modelAlias);
            }
        } catch (Exception e) {
            logger.error("sign error", e);
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public boolean checkSign(String res) {
        try {
            XML xml = XML.readFrom(res);
            if (xml != null) {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                docBuilderFactory.setNamespaceAware(true);
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document resDoc = docBuilder.parse(new InputSource(new StringReader(xml.getXmlStr())));

                return signManager.check(resDoc, publicKey);
            }
        } catch (Exception e) {
            logger.error("send error", e);
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

    private static class FinanceFactoryHolder {
        private static FinanceFactory instance = new FinanceFactory();
    }

    public static FinanceFactory getInstance() {
        return FinanceFactoryHolder.instance;
    }

    public FinanceProxy create() {
        return new FinanceProxy();
    }

    public class FinanceProxy {

        private Finance finance;
        private Message message;
        private XStream xStream;
        private String modelAlias;
        private HttpClientMessageSender httpClient;
        private FinanceHandler handler;

        public FinanceProxy() {
            xStream = new XStream(new Dom4JDriver());
            xStream.autodetectAnnotations(true);
            xStream.registerConverter(new DateConverter());
            xStream.aliasSystemAttribute(null, "class");

            httpClient = new HttpClientMessageSender();

            finance = new Finance();
            message = new Message();
            message.setId(UUID.randomUUID().toString());
            finance.setMessage(message);
        }

        public FinanceProxy aliasMode(String alias) {
            this.modelAlias = alias;
            xStream.aliasField(alias, Message.class, "Mode");
            return this;
        }

        public XStream getXStream() {
            return this.xStream;
        }

        public FinanceProxy setModel(BaseReq baseReq) {
            message.setMode(baseReq);
            return this;
        }

        public String toXML() {
            return xStream.toXML(finance);
        }

        public String toSignedXML() {
            return sign(toXML(), modelAlias);
        }

        public FinanceResponse send() {
            FinanceResponse response = new FinanceResponse();
            String url = PropertiesUtil.getValue("custody_req_url");
            if (!StringUtils.hasLength(url)) {
                throw new RuntimeException("custody_req_url not found.");
            }
            try {
                String message = toSignedXML();
                logger.debug("signed message: ", message);
                // 请求浙商银行接口
                String res = httpClient.send(message, url);
                // 获取返回结果并验证签名
                XML xml = XML.readFrom(res);
                if (xml != null) {
                    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                    docBuilderFactory.setNamespaceAware(true);
                    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                    Document resDoc = docBuilder.parse(new InputSource(new StringReader(xml.getXmlStr())));
                    response.setResponse(res);
                    response.setResDocument(resDoc);
                    boolean check = signManager.check(resDoc, publicKey);
                    Assert.isTrue(check, "check sign error");

                    NodeList errors = resDoc.getElementsByTagName("Error");
                    if (errors != null && errors.getLength() > 0) {
                        String errorCode = getFirstElementValue(resDoc, "errorCode");
                        String errorMsg = getFirstElementValue(resDoc, "errorMessage");
                        errorMsg = !StringUtils.hasLength(errorMsg) ? errorCode : errorMsg;
                        errorCode = !StringUtils.hasLength(errorCode) ? errorMsg : errorCode;
                        response.setErrorCode(errorCode);
                        response.setErrorMsg(errorMsg);
                        response.setHasError(true);
                    } else {
                        response.setHasError(false);
                    }
                    if (handler != null) {
                        // 错误处理
                        handler.onResponse(response);
                    }
                } else {
                    response.setErrorCode("1000010000");
                    response.setErrorMsg("存管接口请求超时或无应答");
                    response.setHasError(true);
                }
            } catch (Exception e) {
                logger.error("send error", e);
                throw new RuntimeException(e.getMessage());
            }
            return response;
        }

        public FinanceProxy handler(FinanceHandler handler) {
            this.handler = handler;
            return this;
        }

    }

    public class FinanceResponse {
        private String response;
        private Document resDocument;
        private boolean hasError;
        private String errorMsg;
        private String errorCode;
        private Map<String, Object> map = new HashMap<>();

        public void addObject(String key, Object val) {
            this.map.put(key, val);
        }

        public void addAllObject(Map<String, Object> map) {
            this.map.putAll(map);
        }

        public Object getObject(String key) {
            return this.map.get(key);
        }

        public void setResDocument(Document resDoc) {
            this.resDocument = resDoc;
        }

        public void setResponse(String res) {
            this.response = res;
        }

        public boolean isHasError() {
            return hasError;
        }

        public void setHasError(boolean hasError) {
            this.hasError = hasError;
        }

        public String getResponse() {
            return response;
        }

        public Document getResDocument() {
            return resDocument;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
    }

    /**
     * 响应处理类
     */
    public abstract class FinanceHandler {

        /**
         * @param response 响应内容
         */
        protected abstract void onResponse(FinanceResponse response);
    }
}
