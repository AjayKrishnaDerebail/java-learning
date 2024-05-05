package com.mapping.onetoone;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AnswerOneToOne {
    @Id
    private int answerId;
    private String answer;
    @OneToOne(mappedBy = "answer")
    private QuestionOneToOne questionOneToOne;

    public AnswerOneToOne () {
    }

    public AnswerOneToOne (int answerId, String answer) {
        this.answerId = answerId;
        this.answer = answer;
    }

    public AnswerOneToOne (int answerId, String answer, QuestionOneToOne questionOneToOne) {
        this.answerId = answerId;
        this.answer = answer;
        this.questionOneToOne = questionOneToOne;
    }

    public int getAnswerId () {
        return answerId;
    }

    public void setAnswerId (int answerId) {
        this.answerId = answerId;
    }

    public String getAnswer () {
        return answer;
    }

    public void setAnswer (String answer) {
        this.answer = answer;
    }

    public QuestionOneToOne getQuestionOneToOne () {
        return questionOneToOne;
    }

    public void setQuestionOneToOne (QuestionOneToOne questionOneToOne) {
        this.questionOneToOne = questionOneToOne;
    }
}
