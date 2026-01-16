package com.example.nyangapay.service;

import org.springframework.stereotype.Component;

@Component
public class ProviderBAdapter implements ISmsProvider {
    private final ProviderBSoapService soapService = new ProviderBSoapService();

    @Override
    public void sendSms(String phoneNumber, String message) {
        String xml = "<sms><dest>" + phoneNumber + "</dest><body>" + message + "</body></sms>";
        soapService.sendRequest(xml);
    }
}

class ProviderBSoapService {
    public void sendRequest(String xml) {
        System.out.println("API Fournisseur B (SOAP) : " + xml);
    }
}
