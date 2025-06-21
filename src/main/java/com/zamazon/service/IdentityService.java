package com.zamazon.service;

import com.zamazon.dto.IdentifyRequest;
import com.zamazon.dto.IdentifyResponse;
import com.zamazon.model.Contact;
import com.zamazon.model.Contact.LinkPrecedence;
import com.zamazon.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IdentityService {

    private final ContactRepository contactRepository;

    public IdentifyResponse identify(IdentifyRequest request) {
        String email = request.getEmail();
        String phone = request.getPhoneNumber();

        List<Contact> matched = contactRepository.findByEmailOrPhoneNumber(email, phone);

        if (matched.isEmpty()) {
            Contact newContact = Contact.builder()
                    .email(email)
                    .phoneNumber(phone)
                    .linkPrecedence(LinkPrecedence.PRIMARY)
                    .build();
            contactRepository.save(newContact);
            return buildResponse(newContact, List.of(newContact));
        }

        Contact primary = matched.stream()
                .filter(c -> c.getLinkPrecedence() == LinkPrecedence.PRIMARY)
                .min(Comparator.comparing(Contact::getCreatedAt))
                .orElse(matched.get(0));

        boolean exactMatchExists = matched.stream()
                .anyMatch(c -> Objects.equals(c.getEmail(), email) && Objects.equals(c.getPhoneNumber(), phone));

        if (!exactMatchExists) {
            Contact newSecondary = Contact.builder()
                    .email(email)
                    .phoneNumber(phone)
                    .linkPrecedence(LinkPrecedence.SECONDARY)
                    .linkedId(primary.getId())
                    .build();
            contactRepository.save(newSecondary);
        }

        List<Contact> allLinked = contactRepository.findAll().stream()
                .filter(c -> Objects.equals(c.getId(), primary.getId()) || Objects.equals(c.getLinkedId(), primary.getId()))
                .collect(Collectors.toList());

        return buildResponse(primary, allLinked);
    }

    private IdentifyResponse buildResponse(Contact primary, List<Contact> contacts) {
        Set<String> emails = contacts.stream()
                .map(Contact::getEmail).filter(Objects::nonNull).collect(Collectors.toSet());

        Set<String> phones = contacts.stream()
                .map(Contact::getPhoneNumber).filter(Objects::nonNull).collect(Collectors.toSet());

        List<Long> secondaryIds = contacts.stream()
                .filter(c -> c.getLinkPrecedence() == LinkPrecedence.SECONDARY)
                .map(Contact::getId).collect(Collectors.toList());

        return IdentifyResponse.builder()
                .primaryContactId(primary.getId())
                .emails(new ArrayList<>(emails))
                .phoneNumbers(new ArrayList<>(phones))
                .secondaryContactIds(secondaryIds)
                .build();
    }
}
