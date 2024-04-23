package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private Integer messageId;
    private Integer storeId;
    private Integer customerId;
    private Integer senderId;
    private String messageContent;
    private String time;
}
