package com.kakao.pay.divvy.controller;

import com.kakao.pay.divvy.model.domain.ChattingRoom;
import com.kakao.pay.divvy.model.domain.User;
import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import com.kakao.pay.divvy.model.dto.*;
import com.kakao.pay.divvy.service.MoneyDivvyReportService;
import com.kakao.pay.divvy.service.MoneyDivvyService;
import com.kakao.pay.divvy.service.MoneyReceiveService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class MoneyDivvyController {

    private MoneyDivvyService moneyDivvyService;

    private MoneyReceiveService moneyReceiveService;

    private MoneyDivvyReportService moneyDivvyReportService;

    @PostMapping("/money/divvy")
    public ReceivableToken divvyMoney(
            @RequestHeader("X-USER-ID") int userId,
            @RequestHeader("X-ROOM-ID") String roomId,
            @RequestBody MoneyDivvyRequestCdo moneyDivvyRequestCdo
    ) {

        MoneyDivvyRequestDto requestDto = new MoneyDivvyRequestDto(
                new User(String.valueOf(userId)),
                new ChattingRoom(roomId),
                moneyDivvyRequestCdo.getAmountOfMoney(),
                moneyDivvyRequestCdo.getNumOfReceivers()
        );

        return moneyDivvyService.register(requestDto);
    }

    @PutMapping("/money/divvy/{token}")
    public MoneyReceiveResponseDto receiveMoney(
            @RequestHeader("X-USER-ID") int userId,
            @RequestHeader("X-ROOM-ID") String roomId,
            @PathVariable String token
    ) {
        MoneyReceiveRequestDto requestDto = new MoneyReceiveRequestDto(
                new User(String.valueOf(userId)),
                new ChattingRoom(roomId),
                new ReceivableToken(token),
                System.currentTimeMillis()
        );
        return moneyReceiveService.receive(requestDto);
    }

    @GetMapping("/money/divvy/{token}/report")
    public MoneyDivvyReportResponseDto reportMoneyDivvy(
            @RequestHeader("X-USER-ID") int userId,
            @RequestHeader("X-ROOM-ID") String roomId,
            @PathVariable String token) {
        MoneyDivvyReportRequestDto requestDto = new MoneyDivvyReportRequestDto(
                new User(String.valueOf(userId)),
                new ChattingRoom(roomId),
                new ReceivableToken(token),
                System.currentTimeMillis()
        );
        return moneyDivvyReportService.report(requestDto);
    }
}
