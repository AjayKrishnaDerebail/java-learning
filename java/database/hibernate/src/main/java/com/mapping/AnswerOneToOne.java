package com.mapping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AnswerOneToOne {
    @Id
    private int answerId;
    private String answer;

    public AnswerOneToOne () {
    }

    public AnswerOneToOne (int answerId, String answer) {
        this.answerId = answerId;
        this.answer = answer;
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
}
