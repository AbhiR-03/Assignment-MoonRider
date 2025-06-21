package com.zamazon.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IdentifyResponse {
    private Long primaryContactId;
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<Long> secondaryContactIds;
}
