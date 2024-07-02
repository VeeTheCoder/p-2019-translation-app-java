package com.translatetxml.models;

import com.translatetxml.utils.SegmentHelper;

public class Translatable {

		private int id;
		private int blockId;
		private int segmentId;
		private int sourceWordCount;
		private int targetWordCount;
		private int uniqueSourceWordCount;
		private int uniqueTargetWordCount;
		private String source;
		private String target;
		
		public Translatable(int id, int segmentId, int blockId, int sourceWordCount, 
								String source, String target) {
			
			this.id = id;
			this.blockId = blockId;
			this.segmentId = segmentId;
			this.source = source;
			this.target = target;
			this.sourceWordCount = sourceWordCount;
	        setUniqueSourceWordCount(source);
	        setUniqueTargetWordCount(target);
		}

	    public int getId() {
	        return id;
	    }
	    
	    public void setSetId(int id) {
	        this.id = id;
	    }
	    
	    public int getBlockId() {
	        return blockId;
	    }
	    
	    public void setBlockId(int blockId) {
	        this.blockId = blockId;
	    }
	    
	    public int getSegmentId() {
	        return segmentId;
	    }
	    
	    public void setSegmentId(int segmentId) {
	        this.segmentId = segmentId;
	    }
	    
	    public int getUniqueSourceWordCount() {
	        return uniqueSourceWordCount;
	    }
	    
	    private void setUniqueSourceWordCount(String segmentSource) {
	        this.uniqueSourceWordCount = new SegmentHelper(segmentSource).getUniqueWordCount();
	    }
	    
	    public int getUniqueTargetWordCount() {
	        return uniqueTargetWordCount;
	    }
	    
	    private void setUniqueTargetWordCount(String segmentTarget) {
	        this.uniqueTargetWordCount = new SegmentHelper(segmentTarget).getUniqueWordCount();
	    }
	    
	    public int getSourceWordCount() {
	        return sourceWordCount;
	    }
	    
	    public void setSourceWordCount(int sourceWordCount) {
	        this.sourceWordCount = sourceWordCount;
	    }
	      
	    public int getTargetWordCount() {
	        return targetWordCount;
	    }
	    
	    private void setTargetWordCount(String segmentTarget) {
	        this.targetWordCount = new SegmentHelper(segmentTarget).getWordCount();
	    }
	    
	    public String getSource() {
	        return source;
	    }
	    
	    public void setSource(String source) {
	        this.source = source;
	        setUniqueSourceWordCount(source);
	    }
	    
	    public String getTarget() {
	        return target;
	    }
	    
	    public void setTarget(String target) {
	        this.target = target;
	        setTargetWordCount(target);
	        setUniqueTargetWordCount(target);
	    }
	    
	    @Override
	    public String toString() {

	        StringBuilder builder = new StringBuilder();
	        builder.append("Translatable{").append("blockId=").append(blockId)
	                .append(", segmentId=").append(segmentId)
	                .append(", wordcount=").append(sourceWordCount)
	                .append(", wordcount=").append(targetWordCount)
	                .append(", uniqueSourceWordcount=").append(uniqueSourceWordCount)
	                .append(", uniqueTargetWordcount=").append(uniqueTargetWordCount)
	                .append(", source=").append(source)
	                .append(", target=").append(target)
	                .append("}");

	        return builder.toString();
	    }
}
