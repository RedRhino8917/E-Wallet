package com.saurabh.WalletService.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saurabh.Utils.CommonConstants;
import com.saurabh.Utils.UserIdentifier;
import com.saurabh.WalletService.model.Wallet;
import com.saurabh.WalletService.repository.WalletRepository;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WalletRepository walletRepository;


    @KafkaListener(topics = CommonConstants.USER_CREATION_TOPIC, groupId = "wallet-group")
    public void createWallet(String msg) throws JsonProcessingException {
        JSONObject obj = objectMapper.readValue(msg, JSONObject.class);
//        String name = (String) obj.get(CommonConstants.USER_CREATION_TOPIC_NAME);
//        String email =(String) obj.get(CommonConstants.USER_CREATION_TOPIC_EMAIL);
        String contact = (String) obj.get(CommonConstants.USER_CREATION_TOPIC_PHONE_NO);
        Integer userId = (Integer) obj.get(CommonConstants.USER_CREATION_TOPIC_ID);
        String userIdentifier = (String) obj.get(CommonConstants.USER_CREATION_TOPIC_USERIDENTIFIER);
        String userIdentifierValue = (String) obj.get(CommonConstants.USER_CREATION_TOPIC_USERIDENTIFIER_VALUE);

        Wallet wallet = Wallet.builder().
                userId(userId).
                contact(contact).
                userIdentifier(UserIdentifier.valueOf(userIdentifier)).
                userIdentifierValue(userIdentifierValue).
                balance(50.0).
                build();

        walletRepository.save(wallet);

    }
}
