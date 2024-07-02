package com.translatetxml.http;

public  enum  ResponseCodeEnum{
    SUCCESSFUL(0,"Successful"),
    INCORRECT_PASSWORD(26,"Incorrect Password"),
    SOURCE_LANGUAGE_UNSUB(28,"Source language not subscribed to"),
    TARGET_LANGUAGE_UNSUB(29,"Target language not subscribed to"),
    INVALID_LANG_PAIR(176,"Invalid Language Pair"),
    NO_INPUT_DATA(177,"No input data"),
    INVALID_MIME(502,"Invalid Mime-type"),
    CONN_TIME_OUT(1176,"Translation timed out"),
    TE_ENGINE_ERROR(1181,"TEEngineErrorException. "
    				+ "Occurs when single words "
    				+ "or short phrases in isolation "
    				+ "cause the translation engine to fail.");

   private  int  code;
   private String desc;
   
   ResponseCodeEnum(int code,String desc){
       this.code=code;
       this.desc=desc;
   }

   public int getCode() {
       return code;
   }

   public void setCode(int code) {
       this.code = code;
   }

   public String getDesc() {
       return desc;
   }

   public void setDesc(String desc) {
       this.desc = desc;
   }
   
   public static ResponseCodeEnum getResponseCodeEnum(int code) {
       for (ResponseCodeEnum ResponseCodeEnum : ResponseCodeEnum.values()) {
           if (ResponseCodeEnum.code == code) {
               return ResponseCodeEnum;
           }
       }
       return null;
   }
}
