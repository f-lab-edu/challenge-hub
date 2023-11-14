package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileDto {
    private final String username;
    private final String email;
    // TODO: 유저 프로필로 받을 다른 정보들에 대해서 더 생각해볼 것
    private final String bio;       // 사용자 소개
    private final String location;  // 사용자 위치
}
