package com.changgou.oauth;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

public class ParseJwtTest {
    /***
     * 校验令牌
     */
    @Test
    public void testParseToken(){
        //令牌
        String token ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6IlJPTEVfQURNSU4iLCJuYW1lIjoiV2FuZ0NoZW55YW5nIiwiaWQiOiIxIn0.T2W3QiaAqs-76SISW-72StRiUFJt8leqvQSn61QS0A_CfJCgoM6ACJU2X68MGKWskyRJJJCq4O3Z4SFcQA4mztrD09hjQh8-QmtBkLbZDVRrhGYa_7d6WVQxHk1_jZ0WfSHaKuKBAJX0TArVS62KrYBBTZt2tSuQbdzxo4ulJcx73G-dCV6UOGX7-x9D1jHh-7LrZFACy3yJBpIUFkYihBedNW0lMvah5joqoVMSGxQdogN1d0eBGjnd0zoBNXuiAC9bevuOb8qUbgOd9gMjqLcQ_1cGLUiW0D9vTQnaaKeyN4D0DTKxIbMrklJyHnZz4Y5MBA9MXiZwTFV0UrhgLA";
        //公钥
        String publickey ="-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvFsEiaLvij9C1Mz+oyAmt47whAaRkRu/8kePM+X8760UGU0RMwGti6Z9y3LQ0RvK6I0brXmbGB/RsN38PVnhcP8ZfxGUH26kX0RK+tlrxcrG+HkPYOH4XPAL8Q1lu1n9x3tLcIPxq8ZZtuIyKYEmoLKyMsvTviG5flTpDprT25unWgE4md1kthRWXOnfWHATVY7Y/r4obiOL1mS5bEa/iNKotQNnvIAKtjBM4RlIDWMa6dmz+lHtLtqDD2LF1qwoiSIHI75LQZ/CNYaHCfZSxtOydpNKq8eb1/PGiLNolD4La2zf0/1dlcr5mkesV570NxRmU1tFm8Zd3MZlZmyv9QIDAQAB-----END PUBLIC KEY-----";
        //校验JWT
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
        System.out.println("------------------------------------------------");
        //jWT令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);

    }
}
