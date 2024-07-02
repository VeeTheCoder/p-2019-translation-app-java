package com.translatetxml.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SegmentHelper {
	
	private String segment;
	
	public SegmentHelper(String segment) {
		this.segment = segment;
	}
	
	
	public Integer getUniqueWordCount() {
        List<String> list = Arrays.asList(removePunctuation(segment).split(" "));
        Set<String> wordHS = new HashSet<String>(list);
        int uniqueWordCount = 0;
        
        for(String word : wordHS) {
        	if(Collections.frequency(list, word) == 1){
        		uniqueWordCount++;
            }
        }
        
		return uniqueWordCount;
	}
	
	public Integer getWordCount() {
        List<String> list = Arrays.asList(removePunctuation(segment).split(" "));
		return list.size();
	}
	
	private String removePunctuation(String segment) {
		return segment.replaceAll("\\p{Punct}", " ").replaceAll("\\s+", " ").trim();   

	}

}
