package daehee.challengehub.social.network.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;

@Repository
public class NetworkRepository {
    private final Map<Long, Set<Long>> followings = new HashMap<>(); // 팔로우하는 사용자 매핑
    private final Map<Long, Set<Long>> followers = new HashMap<>(); // 팔로워 매핑

    public NetworkRepository() {
        // 초기 팔로우 관계 설정
        followings.put(123L, new HashSet<>(Arrays.asList(456L))); // 사용자 123이 456을 팔로우
        followers.put(456L, new HashSet<>(Arrays.asList(123L, 789L))); // 사용자 456의 팔로워 123, 789
    }

    public void followUser(Long followerId, Long followingId) {
        followings.computeIfAbsent(followerId, k -> new HashSet<>()).add(followingId);
        followers.computeIfAbsent(followingId, k -> new HashSet<>()).add(followerId);
    }

    public void unfollowUser(Long followerId, Long followingId) {
        followings.getOrDefault(followerId, new HashSet<>()).remove(followingId);
        followers.getOrDefault(followingId, new HashSet<>()).remove(followerId);
    }

    public Set<Long> getFollowings(Long userId) {
        return followings.getOrDefault(userId, Collections.emptySet());
    }

    public Set<Long> getFollowers(Long userId) {
        return followers.getOrDefault(userId, Collections.emptySet());
    }

}
