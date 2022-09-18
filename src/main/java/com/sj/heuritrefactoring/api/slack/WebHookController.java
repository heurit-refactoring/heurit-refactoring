package com.sj.heuritrefactoring.api.slack;

import com.sj.heuritrefactoring.api.slack.dto.WebHookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/webhook")
@RequiredArgsConstructor
@RestController
public class WebHookController {

    @PostMapping()
    public ResponseEntity<String> slackWebHook(@RequestBody WebHookDto webHookDto) {
        System.out.println(webHookDto.getText());
        return ResponseEntity.status(HttpStatus.OK).body(webHookDto.getText());
    }

}
