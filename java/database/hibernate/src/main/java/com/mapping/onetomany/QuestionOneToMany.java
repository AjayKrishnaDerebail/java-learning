package com.mapping.onetomany;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class QuestionOneToMany {
    @Id
    private int questionId;
    private String question;
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AnswerOneToMany> answers;

    public QuestionOneToMany () {
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

    public List<AnswerOneToMany> getAnswers () {
        return answers;
    }

    public void setAnswers (List<AnswerOneToMany> answers) {
        this.answers = answers;
    }

    public QuestionOneToMany (int questionId, String question, List<AnswerOneToMany> answers) {
        this.questionId = questionId;
        this.question = question;
        this.answers = answers;
    }

}