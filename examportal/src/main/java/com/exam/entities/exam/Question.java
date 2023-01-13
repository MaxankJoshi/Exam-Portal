package com.exam.entities.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @Column(name = "content", nullable = false, length = 400)
    private String questionContent;

    @Column(name = "image", nullable = false)
    private String questionImage;

    @Column(name = "option_1", nullable = false, length = 30)
    private String questionOption1;

    @Column(name = "option_2", nullable = false, length = 30)
    private String questionOption2;

    @Column(name = "option_3", nullable = false, length = 30)
    private String questionOption3;

    @Column(name = "option_4", nullable = false, length = 30)
    private String questionOption4;

    @Column(name = "answer", nullable = false, length = 30)
    private String questionAnswer;

    @Transient
    private String questionGivenAnswer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
