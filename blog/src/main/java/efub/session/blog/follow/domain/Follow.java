package efub.session.blog.follow.domain;

import efub.session.blog.account.domain.Account;
import efub.session.blog.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 어노테이션들을 작성해 보아요 (3개)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends BaseTimeEntity {

    // 어노테이션 3개
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id", updatable = false)
    private Long followId;


    // 어노테이션 2개
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private Account follower;


    // 어노테이션 2개
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private Account following;

    // Builder 메소드 패턴으로 작성 (또는 클래스에 @Builder 붙임)
    @Builder
    Follow(Account follower, Account following) {
        this.follower = follower;
        this.following = following;
    }
}