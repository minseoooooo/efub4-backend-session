package efub.session.blog.follow.service;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.service.AccountService;
import efub.session.blog.follow.domain.Follow;
import efub.session.blog.follow.dto.FollowRequestDto;
import efub.session.blog.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    public final AccountService accountService;

    public Long add(Long accountId, FollowRequestDto followRequestDto){
        log.info("팔로우를 추가합니다. accountId: " + accountId);
        Account follower = accountService.findAccountById(accountId); // 본인 (팔로우 보내는)
        Account following = accountService.findAccountById(followRequestDto.getFollowingId()); // 상대 (팔로우 당하는)
        Follow follow = followRepository.save(followRequestDto.toEntity(follower, following));
        return follow.getFollowId();
    }

    @Transactional(readOnly = true)
    public boolean isFollowing(Long follwerId, Long followingId){
        Account follower = accountService.findAccountById(follwerId);
        Account following = accountService.findAccountById(followingId);
        return followRepository.existsByFollowerAndFollowing(follower, following);
    }

    @Transactional(readOnly = true)
    public List<Follow> findAllByFollowerId(Long accountId) {
        Account findAccount = accountService.findAccountById(accountId);
        return followRepository.findAllByFollower(findAccount);
    }

    @Transactional(readOnly = true)
    public List<Follow> findAllByFollowingId(Long accountId) {
        Account findAccount = accountService.findAccountById(accountId);
        return followRepository.findAllByFollowing(findAccount);
    }

    @Transactional(readOnly = true)
    public Follow findByFollowerIdAndFollowingId(Long followerId, Long followingId) {
        Account follower = accountService.findAccountById(followerId);
        Account following = accountService.findAccountById(followingId);
        return followRepository.findByFollowerAndFollowing(follower, following);
    }

    public void delete(Long accountId, Long followingId) {

        Follow findFollow = findByFollowerIdAndFollowingId(accountId, followingId);
        followRepository.deleteByFollowId(findFollow.getFollowId());

    }
}