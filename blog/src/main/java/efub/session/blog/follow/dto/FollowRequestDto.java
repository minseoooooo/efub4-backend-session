package efub.session.blog.follow.dto;

import efub.session.blog.account.domain.Account;
import efub.session.blog.follow.domain.Follow;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowRequestDto {
    private Long followingId;

    public FollowRequestDto(Long followingId) {
        this.followingId = followingId;
    }

    public Follow toEntity(Account follower, Account following){
        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }
}