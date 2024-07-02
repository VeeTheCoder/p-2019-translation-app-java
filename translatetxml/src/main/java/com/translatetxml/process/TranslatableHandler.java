package com.translatetxml.process;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.translatetxml.models.Translatable;

public class TranslatableHandler extends DefaultHandler{
    
    private HashMap<Integer,Translatable> hTabletranslatable;
    private Translatable translatable;
    
    private int id = 0;
	private int blockId = Integer.parseInt("0");
	private int segmentId = Integer.parseInt("0");
	private int sourceWordCount = Integer.parseInt("0");
	private StringBuilder source = new StringBuilder();
	
	private boolean bSource = false;

    public TranslatableHandler() {
    	hTabletranslatable = new HashMap<>();
    }
    
    public HashMap<Integer, Translatable> getHashMapTranslatable(){
        return hTabletranslatable;
    }
    
    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException{

        if(qName.equalsIgnoreCase("translatable")){
            
            if(attributes.getValue("blockID") != null){
            	blockId = Integer.parseInt(attributes.getValue("blockId"));
            }
            
            if(attributes.getValue("wordcount") != null){
            	sourceWordCount = Integer.parseInt(attributes.getValue("wordcount"));
            }
                        
        } else if (qName.equalsIgnoreCase("segment")) {
            if(attributes.getValue("segmentId") != null){
            	segmentId = Integer.parseInt(attributes.getValue("segmentId"));
            }
        } else if (qName.equalsIgnoreCase("source")) {
        	bSource = true;
        }   
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        if(qName.equalsIgnoreCase("translatable")){
        	String sourceSegment = source.toString().trim();
            translatable = new Translatable(id, segmentId, blockId, sourceWordCount, sourceSegment, "");
            hTabletranslatable.put(id, translatable);
            id++;
            resetElements();
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
    	if(bSource) {
    		source.append(ch, start, length);
    		bSource = false;
    	}
    }

    private void resetElements() {
		blockId = Integer.parseInt("0");
		segmentId = Integer.parseInt("0");
		sourceWordCount = Integer.parseInt("0");
		source.setLength(0);
    }

}
