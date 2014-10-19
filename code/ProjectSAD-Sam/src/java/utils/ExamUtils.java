/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.TblAnswer;
import entity.TblQuestion;
import java.util.ArrayList;
import java.util.List;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author computer
 */
public class ExamUtils {
    public static List<String> checkTypeQuestion(List<TblQuestion> lst) {
      List<String> lstTypeQuestion = new ArrayList<String>();
        for (TblQuestion question : lst) {
            boolean isCheckBox = true;
            for(TblAnswer answer: question.getTblAnswerList()) {
                if(answer.getPoint() == 100) {
                   isCheckBox = false;
                    break;
                }
            }
            if (isCheckBox) {
                lstTypeQuestion.add("checkBox");
            } else {
                lstTypeQuestion.add("radioButton");
            }
        }
        return lstTypeQuestion;
    }
}
