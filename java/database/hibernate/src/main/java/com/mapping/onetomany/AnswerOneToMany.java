package com.mapping.onetomany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AnswerOneToMany {
    @Id
    private int answerId;
    private String answer;
    @ManyToOne
    private QuestionOneToMany question;


    public AnswerOneToMany () {
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

    public QuestionOneToMany getQuestion () {
        return question;
    }

    public void setQuestionOneToMany (QuestionOneToMany question) {
        this.question = question;
    }

    public AnswerOneToMany (int answerId, String answer, QuestionOneToMany question) {
        this.answerId = answerId;
        this.answer = answer;
        this.question = question;
    }
}
