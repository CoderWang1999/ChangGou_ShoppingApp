package com.changgou.oauth;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.JwtHandler;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;

public class CreateJwtTest {
    /***
     * 创建令牌测试
     */
    @Test
    public void testCreateToken(){
        //证书文件路径
        String key_location="changgou.jks";
        //密钥库密码
        String key_pwd="changgou";
        //密钥密码
        String pwd="changgou";
        //密钥别名
        String alias="changgou";
        //访问证书路径
        ClassPathResource resource=new ClassPathResource(key_location);
        //创建密钥工厂
        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(resource, key_pwd.toCharArray());
        //读取密钥对（公钥、私钥）
        KeyPair keyPair = keyFactory.getKeyPair(alias, pwd.toCharArray());
        //获取私钥
        RSAPrivateKey privateKey= (RSAPrivateKey) keyPair.getPrivate();
        //定义载荷（payload)
        HashMap<String, Object> payLoad = new HashMap<>();
        payLoad.put("id","1");
        payLoad.put("name","WangChenyang");
        payLoad.put("roles","ROLE_ADMIN");
        //生成Jwt令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(payLoad), new RsaSigner(privateKey));
        //取出令牌
        String token = jwt.getEncoded();
        System.out.println(token);
    }
}
