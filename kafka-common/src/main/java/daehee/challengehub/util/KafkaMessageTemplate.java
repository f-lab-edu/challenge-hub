package daehee.challengehub.util;

public class KafkaMessageTemplate {

    public static String chatMessageNotification(String username, String messageContent, String challengeName) {
        return username + "님이 '" + messageContent + "' - 챌린지 " + challengeName;
    }

    public static String reviewAndRatingNotification(String username, String challengeName) {
        return username + "님이 챌린지 " + challengeName + "에 새로운 후기와 별점을 남겼습니다:";
    }

    public static String challengeEventNotification(String challengeName, String event) {
        return switch (event) {
            case "추가" -> "새로운 챌린지 " + challengeName + "가 시작되었습니다! 관심 있으시면 지금 바로 확인해보세요!";
            case "수정" -> "참여하신 챌린지 " + challengeName + "에 중요한 변경사항이 있습니다. 확인해주세요!";
            case "삭제" -> "아쉽게도 참여하신 챌린지 " + challengeName + "가 종료되었습니다.";
            default -> throw new IllegalArgumentException("알 수 없는 이벤트 유형: " + event);
        };
    }

    public static String transactionNotification(String username, Double transactionAmount, String transactionType) {
        String transactionAction = transactionType.equals("deposit") ? "충전" : "인출";
        return String.format("%s님의 계정에서 거래가 성공적으로 처리되었습니다. 거래 내용: %.2f %s",
                username, transactionAmount, transactionAction);
    }

    public static String certificationEventNotification(String username, String challengeName, String event) {
        return switch (event) {
            case "추가" -> username + "님이 챌린지 " + challengeName + "에 새로운 인증 사진을 업로드했습니다. 확인이 필요합니다.";
            case "수정" -> username + "님이 제출한 인증 내용이 수정되었습니다. 챌린지 " + challengeName + " 인증을 다시 확인해주세요.";
            case "삭제" -> username + "님이 제출한 챌린지 " + challengeName + " 인증이 삭제되었습니다. 관련 내용을 확인해주세요.";
            default -> throw new IllegalArgumentException("알 수 없는 인증 알림 유형: " + event);
        };
    }
}
