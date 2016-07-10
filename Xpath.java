/**
 * Created by Dev M on 24-03-2016.
 */
import javax.xml.xpath.*;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.w3c.dom.*;
public class Xpath {
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

    private void fetchResults(String keywords)throws Exception{

        String url = "http://sandbox.api.ebaycommercenetwork.com/publisher/3.0/rest/GeneralSearch?apiKey=---------------------&trackingId=7000610&categoryId=72&keyword="+java.net.URLEncoder.encode(keywords, "UTF-8")+"&numItems=20";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse((new URL(url)).openStream());
        //Node root = doc.getDocumentElement();
        //print(root);
        System.out.println("Query 1-");
        System.out.println("-----------------------------------------------------------------------------------------------");
        eval("//items/product[rating/rating>4.50]/fullDescription",doc);
        System.out.println("Query 2-");
        System.out.println("-----------------------------------------------------------------------------------------------");
        eval("//items/product[name[text()[contains(.,'Sony')]]]/name | //items/product[name[text()[contains(.,'Sony')]]]/minPrice",doc);
        System.out.println("Query 3-");
        System.out.println("-----------------------------------------------------------------------------------------------");
        eval("//items/product[name[text()[contains(.,'Sony')]] and minPrice>=1000.00 and minPrice<=2000.00]/name",doc);
    }
    static void eval ( String query, Document document ) throws Exception {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        NodeList result = (NodeList) xpath.evaluate(query,document,XPathConstants.NODESET);
        System.out.println("XPath query: "+query);
        for (int i = 0; i < result.getLength(); i++)
            print(result.item(i));
        System.out.println();
    }
    public static void main(String arg[]){
        String keywords;
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the search terms : \n");
        keywords = input.next();
        Xpath instance = new Xpath();
        try {
            instance.fetchResults(keywords);
        }catch (Exception e){
            System.out.print("An error Occurred : \n" + e);
        }
    }
}
