package com.translatetxml.utils.xml;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.translatetxml.models.Translatable;
import com.translatetxml.utils.ConfigHandler;

public class TxmlWriter {
	
	private String outputFile;
	private String sourceLanguage;
	private String targetLanguage;
	private Boolean isEvaluate;
	private int totalWordCount;
	private HashMap<Integer,Translatable> htTranslatable;

	
	public TxmlWriter(String outputFile, boolean isEvaluate, HashMap<Integer,Translatable> htTranslatable, String sourceLanguage, String targetLanguage) {
		this.outputFile = outputFile;
		this.isEvaluate = isEvaluate;
		this.htTranslatable = htTranslatable;
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
	}
	
	public void writeTxml() {
		
		 try {
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				Document doc = docBuilder.newDocument();
				Element rootElement = createRootElement(doc, "txml");
				doc.setXmlStandalone(true);

				htTranslatable.forEach((key,value) -> {	
					Element translatable = createElement(doc, rootElement, "translatable");
					createAttribute(doc, translatable, "blockId", Integer.toString(htTranslatable.get(key).getBlockId()));
					createAttribute(doc, translatable, "wordcount", Integer.toString(htTranslatable.get(key).getSourceWordCount()));

					Element segment = createElement(doc, translatable, "segment");
					createAttribute(doc, segment, "segmentId", Integer.toString(htTranslatable.get(key).getSegmentId()));
					
					if(isEvaluate) {
						Element source = createElement(doc, segment, "source", htTranslatable.get(key).getSource());
						createAttribute(doc, source, "uniquewordcount", Integer.toString(htTranslatable.get(key).getUniqueSourceWordCount()));
					}
					
					Element target = createElement(doc, segment, "target", htTranslatable.get(key).getTarget());
					
					if(isEvaluate) {
						createAttribute(doc, target, "uniquewordcount", Integer.toString(htTranslatable.get(key).getUniqueTargetWordCount()));
					}

					this.totalWordCount += htTranslatable.get(key).getSourceWordCount();	
					this.totalWordCount += htTranslatable.get(key).getTargetWordCount();		
				});
				
				
				createAttribute(doc, rootElement, "version", new ConfigHandler("TXML_VERSION").getConfigValue());
				createAttribute(doc, rootElement, "segtype", new ConfigHandler("TXML_SEGTYPE").getConfigValue());
				createAttribute(doc, rootElement, "datatype", new ConfigHandler("TXML_DATATYPE").getConfigValue());
				createAttribute(doc, rootElement, "createdby", new ConfigHandler("TXML_CREATEDBY").getConfigValue());
				createAttribute(doc, rootElement, "locale", sourceLanguage);
				createAttribute(doc, rootElement, "targetlocale", targetLanguage);
				createAttribute(doc, rootElement, "wordcount", Integer.toString(totalWordCount));
				createAttribute(doc, rootElement, "file_extension", new ConfigHandler("TXML_FILE_EXTENSION").getConfigValue());
				createAttribute(doc, rootElement, "editedby", new ConfigHandler("TXML_EDITEDBY").getConfigValue());

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(outputFile));

		        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		        transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
				transformer.transform(source, result);

				
			  } catch (ParserConfigurationException e) {
		        System.out.println(e.getMessage());
				e.printStackTrace();
				System.exit(1);
			  } catch (TransformerException e) {
		        System.out.println(e.getMessage());
				e.printStackTrace();
				System.exit(1);
			  }
	}
	
	private Element createElement(Document document, Element parentNode, String elementName, String nodeValue) {
		Element source = document.createElement(elementName);
		source.appendChild(document.createTextNode(nodeValue));
		parentNode.appendChild(source);
		return source;
	}
	
	private Element createElement(Document document, Element parentNode, String elementName) {
		Element source = document.createElement(elementName);
		parentNode.appendChild(source);
		return source;
	}
	
	private Element createRootElement(Document document, String elementName) {
		Element source = document.createElement(elementName);
		document.appendChild(source);
		return source;
		
	}
	
	private void createAttribute(Document document, Element element, String attributeName, String attributeValue) {
		Attr attribute = document.createAttribute(attributeName);
		attribute.setValue(attributeValue);
		element.setAttributeNode(attribute);
	}
	
	

}
