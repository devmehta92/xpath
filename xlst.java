/**
 * Created by Dev M on 02-04-2016.
 */
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.Scanner;
import java.net.URL;

public class xlst {
    static void print ( Node e ) {
        if (e instanceof Text)
            System.out.print(((Text) e).getData());
        else {
            NodeList c = e.getChildNodes();
            System.out.println("<"+e.getNodeName()+">");
            for (int k = 0; k < c.getLength(); k++)
                print(c.item(k));
            System.out.println("</"+e.getNodeName()+">");
        }
    }
    static void eval ( String query, Document document ) throws Exception {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        NodeList result = (NodeList) xpath.evaluate(query,document, XPathConstants.NODESET);
        System.out.println("XPath query: "+query);
        for (int i = 0; i < result.getLength(); i++)
            print(result.item(i));
        System.out.println();
    }
        public static void main ( String argv[] ) throws Exception {
            String keywords;
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter the search terms : \n");
            keywords = input.next();
            String url = "http://sandbox.api.shopping.com/publisher/3.0/rest/GeneralSearch?apiKey=--------------&visitorUserAgent&visitorIPAddress&trackingId=7000610&keyword="+keywords+"&numItems=20";
            File stylesheet = new File("xslt.xsl");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse((new URL(url)).openStream());
            StreamSource stylesource = new StreamSource(stylesheet);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer(stylesource);
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("output.html"));
            transformer.transform(source,result);
        }
    }

