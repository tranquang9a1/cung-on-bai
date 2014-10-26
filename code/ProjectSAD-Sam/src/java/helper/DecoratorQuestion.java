/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import entity.TblAnswer;
import entity.TblQuestion;

/**
 *
 * @author computer
 */
public class DecoratorQuestion {

    private String type;
    private TblQuestion question;
    private TblAnswer answer;

    public DecoratorQuestion(String type, TblQuestion question) {
        this.type = type;
        this.question = question;
    }

    public DecoratorQuestion(String type, TblQuestion question, TblAnswer answer) {
        this.type = type;
        this.question = question;
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TblQuestion getQuestion() {
        return question;
    }

    public void setQuestion(TblQuestion question) {
        this.question = question;
    }

    public TblAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(TblAnswer answer) {
        this.answer = answer;
    }
}
