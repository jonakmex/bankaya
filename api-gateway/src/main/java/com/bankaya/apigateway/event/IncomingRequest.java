package com.bankaya.apigateway.event;

import lombok.Data;

import java.util.Date;
@Data
public class IncomingRequest {
    private String id;
    private String originHost;
    private Date date;
    private String method;
}
