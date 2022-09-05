package com.sj.heuritrefactoring.domain.worry;

import com.sj.heuritrefactoring.domain.category.Category;
import com.sj.heuritrefactoring.dto.user.User;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Table(name = "WORRY")
@Entity
public class Worry {

    @Id
    @GeneratedValue
    private Long worryId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @Column(name = "worryText", nullable = false)
    @NotNull
    private String worryText;

    @Column(name = "isFinished")
    private boolean isFinished;

    @Column(name = "isRealized")
    private boolean isRealized;

    @Column(name = "isLocked")
    private boolean isLocked;

    @Column(name = "worryStartDate", nullable = false)
    private LocalDateTime worryStartDate;

    @Column(name = "worryExpiryDate", nullable = false)
    private LocalDateTime worryExpiryDate;

    @Column(name = "worryReview")
    private String worryReview;

    @Builder
    public Worry(User user, Category category, String worryText, LocalDateTime worryStartDate, LocalDateTime worryExpiryDate, boolean isLocked) {
        this.user = user;
        this.category = category;
        this.worryText = worryText;
        this.worryStartDate = worryStartDate;
        this.worryExpiryDate = worryExpiryDate;
        this.isLocked = isLocked;
    }
}
