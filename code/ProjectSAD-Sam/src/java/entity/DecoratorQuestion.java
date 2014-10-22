/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author computer
 */
public class DecoratorQuestion {
    private String type;
    private TblQuestion question;

    public DecoratorQuestion(String type, TblQuestion question) {
        this.type = type;
        this.question = question;
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
    
}
