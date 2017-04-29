package com.cxdai.console.security.custody;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.utils.resolver.ResourceResolverException;
import org.apache.xml.security.utils.resolver.ResourceResolverSpi;
import org.apache.xml.utils.URI;
import org.w3c.dom.Attr;

/**
 * This class helps us home users to resolve http URIs without a network
 * connection
 *
 * @author $Author: wjm $
 */
public class OfflineResolver extends ResourceResolverSpi {
    /** {@link org.apache.commons.logging} logging facility */
    static org.apache.commons.logging.Log log      = org.apache.commons.logging.LogFactory
                                                       .getLog(OfflineResolver.class.getName());

/*    *//** Field uriMap *//*
    static Map<String, String>            uriMap  = null;

    *//** Field mimeMap *//*
    static Map<String, String>            mimeMap = null;*/
    static HashMap uriMap=null;
    static HashMap mimeMap=null;
    static {
        org.apache.xml.security.Init.init();

/*        uriMap = new HashMap<String, String>();
        mimeMap = new HashMap<String, String>();*/
         uriMap = new HashMap();
         mimeMap = new HashMap();

        OfflineResolver.register("http://www.w3.org/TR/xml-stylesheet",
            "data/org/w3c/www/TR/xml-stylesheet.html", "text/html");
        OfflineResolver.register("http://www.w3.org/TR/2000/REC-xml-20001006",
            "data/org/w3c/www/TR/2000/REC-xml-20001006", "text/xml");
        OfflineResolver.register("http://www.nue.et-inf.uni-siegen.de/index.html",
            "data/org/apache/xml/security/temp/nuehomepage", "text/html");
        OfflineResolver.register("http://www.nue.et-inf.uni-siegen.de/~geuer-pollmann/id2.xml",
            "data/org/apache/xml/security/temp/id2.xml", "text/xml");
        OfflineResolver.register("http://xmldsig.pothole.com/xml-stylesheet.txt",
            "data/com/pothole/xmldsig/xml-stylesheet.txt", "text/xml");
        OfflineResolver.register("http://www.w3.org/Signature/2002/04/xml-stylesheet.b64",
            "data/ie/baltimore/merlin-examples/merlin-xmldsig-twenty-three/xml-stylesheet.b64",
            "text/plain");
    }

    /**
     * Method engineResolve
     *
     * @param uri
     * @param BaseURI
     *
     * @throws ResourceResolverException
     */
    public XMLSignatureInput engineResolve(Attr uri, String BaseURI)
                                                                    throws ResourceResolverException {

        try {
            String URI = uri.getNodeValue();

            if (OfflineResolver.uriMap.containsKey(URI)) {
                String newURI = (String) OfflineResolver.uriMap.get(URI);

                log.debug("Mapped " + URI + " to " + newURI);

                InputStream is = new FileInputStream(newURI);

                log.debug("Available bytes = " + is.available());

                XMLSignatureInput result = new XMLSignatureInput(is);

                // XMLSignatureInput result = new XMLSignatureInput(inputStream);
                result.setSourceURI(URI);
                result.setMIMEType((String) OfflineResolver.mimeMap.get(URI));

                return result;
            } else {
                Object exArgs[] = { "The URI " + URI + " is not configured for offline work" };

                throw new ResourceResolverException("generic.EmptyMessage", exArgs, uri, BaseURI);
            }
        } catch (IOException ex) {
            throw new ResourceResolverException("generic.EmptyMessage", ex, uri, BaseURI);
        }
    }

    /**
     * We resolve http URIs <I>without</I> fragment...
     *
     * @param uri
     * @param BaseURI
     *
     */
    public boolean engineCanResolve(Attr uri, String BaseURI) {

        String uriNodeValue = uri.getNodeValue();

        if (uriNodeValue.equals("") || uriNodeValue.startsWith("#")) {
            return false;
        }

        try {
            URI uriNew = new URI(new URI(BaseURI), uri.getNodeValue());

            if (uriNew.getScheme().equals("http")) {
                log.debug("I state that I can resolve " + uriNew.toString());

                return true;
            }

            log.debug("I state that I can't resolve " + uriNew.toString());
        } catch (URI.MalformedURIException ex) {
        }

        return false;
    }

    /**
     * Method register
     *
     * @param URI
     * @param filename
     * @param MIME
     */
    private static void register(String URI, String filename, String MIME) {
        uriMap.put(URI, filename);
        mimeMap.put(URI, MIME);
    }

}
