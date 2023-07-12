package com.manageTeam.global.util;

public class GlobalUtil {
    /**
     * @descreption: 문자열의 줄바꿈 공백을 전부 제거해준다
     * @Request: String str (제거할 문자열)
     * @author skhan
     * */
    public static String cleanString(String str) {
    	return str.replace("\n", "").replace("\r", "").replace(" ", "");
    }
}
