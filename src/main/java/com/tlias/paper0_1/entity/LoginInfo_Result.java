package com.tlias.paper0_1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo_Result {

        // 对应前端 result.data.data.token
        private String token;

        // 对应前端 result.data.data.user
        private User user;
}
