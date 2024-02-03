package daehee.challengehub.challenge.management.model;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChallengeDto {
    private String challengeId;
    private String title;
    private String frequency;
    private String duration;
    private Instant startTime;
    private Instant endTime;
    private Instant startDate;
    private String verificationMethod;
    private List<String> verificationExampleUrls;
    private Boolean isCameraOnly;
    private String description;
    private String category;
    private String coverImageUrl;
    private List<String> keywords;
    private Boolean isPublic;
    private String createdBy;
    private Instant createdAt;
    private Instant lastModified;

    private String error;
    private long processingTime; // 요청 처리 시간을 위한 필드

    public boolean validate() throws ValidationException, InterruptedException, ExecutionException {
        if (title == null || title.trim().isEmpty()) {
            throw new ValidationException("제목은 비워둘 수 없습니다");
        }2
        if (frequency == null || frequency.trim().isEmpty()) {
            throw new ValidationException("빈도는 비워둘 수 없습니다");
        }
        if (startTime != null && endTime != null && startTime.isAfter(endTime)) {
            throw new ValidationException("시작 시간은 종료 시간 이전이어야 합니다");
        }
        if (startDate != null && !startDate.isAfter(Instant.now())) {
            throw new ValidationException("시작 날짜는 미래여야 합니다");
        }

        if (verificationExampleUrls != null) {
            CompletableFuture[] futures = verificationExampleUrls.stream()
                    .map(url -> CompletableFuture.supplyAsync(() -> isValidImageUrl(url)))
                    .toArray(CompletableFuture[]::new); // 수정된 부분

            CompletableFuture.allOf(futures).join(); // 모든 비동기 작업 완료 대기

            for (CompletableFuture<Boolean> future : futures) {
                if (!future.get()) { // 수정된 부분
                    throw new ValidationException("검증 예시 URL 중 잘못된 URL이 있습니다");
                }
            }
        }

        return true;
    }

    private boolean isValidImageUrl(String url) {
        try {
            URL imageUrl = new URL(url);
            URLConnection connection = imageUrl.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            BufferedImage image = ImageIO.read(connection.getInputStream());
            return image != null;
        } catch (IOException e) {
            return false;
        }
    }

}
