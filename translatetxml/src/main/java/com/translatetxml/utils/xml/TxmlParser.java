package com.translatetxml.utils.xml;

import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import java.io.IOException;

import com.translatetxml.models.Translatable;
import com.translatetxml.process.TranslatableHandler;

public class TxmlParser {
	
	public HashMap<Integer, Translatable> translatableParser(String path) throws ParserConfigurationException, SAXException{
		HashMap<Integer,Translatable> translatable = new HashMap<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try{            
            SAXParser saxParser =  factory.newSAXParser();
            
            TranslatableHandler translatableHandler = new TranslatableHandler();
 
            saxParser.parse(path, translatableHandler);
            translatable = translatableHandler.getHashMapTranslatable();

            
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
        }
        
        return translatable;
    }

}
