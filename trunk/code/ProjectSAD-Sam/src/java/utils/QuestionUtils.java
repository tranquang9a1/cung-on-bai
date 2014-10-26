/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author khangtnse60992
 */
public class QuestionUtils {
    public static final int page = 16;
    public static final String errorPage = "WEB-INF/common/error.jsp";
    
    public static int checkEmpty(StringBuffer questionContent) {
        if (questionContent.toString().equals("")) {
            return 0;
        }
        return 1;
    }
    
    public static int checkEmpty(String[] answerContent) {
        for (int i = 0; i < answerContent.length; i++) {
            if(answerContent[i].equals("")) {
                return 0;
            }
        }
        return 1;
    }
    
}
