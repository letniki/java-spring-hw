package org.example.javaspring.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.javaspring.dto.ReviewDTO;
import org.example.javaspring.service.CarService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Slf4j
public class SendLatestReviewsJob {
    private final MailSender mailSender;
    private final CarService carService;
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS )
    public void sendLatestReviews(){
       log.info("Sending latest reviews job is running");
       List<ReviewDTO> latestReviews = this.carService
               .getLatestReviews(LocalDateTime.now().minusSeconds(10));
       if(latestReviews.isEmpty()){
           return;
       }
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.mailUsername);
        message.setTo(this.mailUsername);
        message.setSubject("Latest reviews");
        final String text = latestReviews
                .stream().map(ReviewDTO::toString)
                .collect(Collectors.joining(", \n", "Here is the latest reviews: \n", ""));
        message.setText(text);
        log.info("sending mail about latest reviews");
        mailSender.send(message);
    }
}
