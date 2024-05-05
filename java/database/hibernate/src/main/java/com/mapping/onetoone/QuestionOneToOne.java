package com.mapping.onetoone;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class QuestionOneToOne {
    @Id
    private int questionId;
    private String question;
    @OneToOne
    private AnswerOneToOne answer;

    public QuestionOneToOne () {
    }

    public int getQuestionId () {
        return questionId;
    }

    public void setQuestionId (int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion () {
        return question;
    }

    public void setQuestion (String question) {
        this.question = question;
    }

    public AnswerOneToOne getAnswer () {
        return answer;
    }

    public void setAnswer (AnswerOneToOne answer) {
        this.answer = answer;
    }

    public QuestionOneToOne (int questionId, String question, AnswerOneToOne answer) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
    }
}