
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a “de facto” standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 



The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/





import java.io.*;
import java.text.ParseException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParser4BestDealProducts extends DefaultHandler {
    Product product;
    List<Product> products;
    String productXmlFileName;
    String elementValueRead;
    public HashMap<String,ArrayList<String>> dataStoreMap ; 
public HashMap<String,Product> dataStoreProd ; 
	
    
    public SaxParser4BestDealProducts(String productXmlFileName) {
	dataStoreMap = new  HashMap<String,ArrayList<String>>();
dataStoreProd = new HashMap<String,Product>();
        this.productXmlFileName = productXmlFileName;
        products = new ArrayList<Product>();
        parseDocument();
      	prettyPrint();
   	
    }


    public void parseDocument() {
		
		
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
             File f = new File(productXmlFileName);
			 parser.parse(f, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }


   public HashMap<String,Product> prettyPrint() {
	
	ArrayList<String> prod = null;  

    for (Product product: products) {
		 prod = new ArrayList<String>();
		
            //	System.out.println("Product #"+ product.id +":");
		//prod.add(product.retailer);
	//	System.out.println("\t\t retailer: " + product.retailer);
		prod.add(product.name);
		prod.add(product.image);
	//	System.out.println("\t\t name: " + product.name);
		prod.add(product.shortDescription);
	//	System.out.println("\t\t shortDescription: " + product.shortDescription);
		String pr = Integer.toString(product.price);
		prod.add(pr);
		dataStoreProd.put(product.id,product);
		for (String accessory: product.accessories) {
		prod.add(accessory);
//			System.out.println("\t\t accessory: " + accessory);

		
		}
		dataStoreMap.put(product.id,prod);
        }
	return dataStoreProd;
    }





////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////

    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("product")) {
            product = new Product();
            product.setId(attributes.getValue("id"));
            product.setRetailer(attributes.getValue("retailer"));
        }

    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("product")) {
            products.add(product);
	    return;
        }
        if (element.equalsIgnoreCase("image")) {
            product.setImage(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("name")) {
            product.setName(elementValueRead);
	    return;
        }
	 if (element.equalsIgnoreCase("shortDescription")) {
            product.setShortDescription(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("accessory")){
           product.getAccessories().add(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("price")){
            product.setPrice(Integer.parseInt(elementValueRead));
	    return;
        }

    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
/*
  public static void main(String[] args) {
     SaxParser4BestDealXMLdataStore sx = new SaxParser4BestDealXMLdataStore("ProductCatalog.xml");
	sx.loadDataStore();
    }

*/
/*
public  HashMap<String,ArrayList<String>> loadDataStore(){
return dataStoreMap;
  }
*/
}
