package com.zamazon.controller;

import com.zamazon.dto.IdentifyRequest;
import com.zamazon.dto.IdentifyResponse;
import com.zamazon.service.IdentityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/identify")
@RequiredArgsConstructor
public class IdentityController {

    private final IdentityService identityService;

    @PostMapping
    public ResponseEntity<IdentifyResponse> identify(@Valid @RequestBody IdentifyRequest request) {
        IdentifyResponse response = identityService.identify(request);
        return ResponseEntity.ok(response);
    }
}
