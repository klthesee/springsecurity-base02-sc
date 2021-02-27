package com.zk.security.security;

import com.zk.security.utils.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultPasswordEncoder implements PasswordEncoder {
    public DefaultPasswordEncoder() {
        this(-1);
    }
    public DefaultPasswordEncoder(int strength) {
    }

    // 进行MD5加密
    @Override
    public String encode(CharSequence charSequence) {
        String encrypt = MD5.encrypt(charSequence.toString());
        return encrypt;
    }

    /**
     * 进行密码比对
     * @param charSequence 前端传入的密码
     * @param encodePassword 后端保存的加密的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String encodePassword) {
        return MD5.encrypt(charSequence.toString()).equals(encodePassword);
    }
}
