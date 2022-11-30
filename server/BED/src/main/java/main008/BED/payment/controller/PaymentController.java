package main008.BED.payment.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.payment.dto.PaymentDetailDto;
import main008.BED.payment.mapper.PaymentDetailMapper;
import main008.BED.payment.mapper.PaymentMapper;
import main008.BED.payment.service.PaymentService;
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.security.Principal;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentDetailMapper paymentDetailMapper;
    private final UsersService usersService;

    /*
    컨텐츠 결제
    A 유저가 B 유저의 컨텐츠 결제 시 A 유저의 코인을 B 유저에게 전송하는 방식
    */
    @PostMapping("/auth/{contents-id}")
    public ResponseEntity payContents(Principal principal,
                                      @PathVariable("contents-id") @Positive Long contentsId,
                                      @RequestParam("payed") Boolean payed) {

        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        PaymentDetailDto.PayPost payPost = new PaymentDetailDto.PayPost(payed);

        paymentService.payContent(paymentDetailMapper.payPostToEntity(payPost), users.getUsersId(), contentsId);

        return new ResponseEntity<>("Payment successful.", HttpStatus.OK);
    }
}
