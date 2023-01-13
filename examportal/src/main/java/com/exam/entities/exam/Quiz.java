package com.exam.entities.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quiz")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizId;

    @Column(name = "title", nullable = false, length = 50)
    private String quizTitle;

    @Column(name = "description", nullable = false, length = 300)
    private String quizDescription;

    @Column(name = "enabled", nullable = false)
    private Boolean quizEnabled = false;

    @Column(name = "max_marks", nullable = false, length = 4)
    private String quizMaxMarks;

    @Column(name = "number_of_questions", nullable = false, length = 4)
    private String quizNumberOfQuestions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quiz")
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();
}
