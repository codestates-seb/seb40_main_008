package main008.BED.payment.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.payment.dto.PaymentDetailDto;
import main008.BED.payment.mapper.PaymentDetailMapper;
import main008.BED.payment.mapper.PaymentMapper;
import main008.BED.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;
    private final PaymentDetailMapper paymentDetailMapper;

    /*
    컨텐츠 결제
    A 유저가 B 유저의 컨텐츠 결제 시 A 유저의 코인을 B 유저에게 전송하는 방식
    */
    @PostMapping("/auth/{users-id}/{contents-id}")
    public ResponseEntity payContents(@PathVariable("users-id") @Positive Long usersId,
                                      @PathVariable("contents-id") @Positive Long contentsId,
                                      @RequestParam("payed") Boolean payed) {

        PaymentDetailDto.PayPost payPost = new PaymentDetailDto.PayPost(payed);

        paymentService.payContent(paymentDetailMapper.payPostToEntity(payPost), usersId, contentsId);

        return new ResponseEntity<>("Payment successful.", HttpStatus.OK);
    }
}
