package com.ch3schedulerdevelop.global.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

// 이 클래스를 스프링 빈으로 등록하여, 필요한 곳에서 의존성 주입을 받아 사용할 수 있게 합니다.
@Component
public class PasswordEncoder {

    // 사용자가 입력한 평문 비밀번호를 BCrypt 알고리즘을 사용하여 해시값으로 변환합니다.
    public String encode(String rawPassword) {
        // 비밀번호를 가공하여 DB에 저장할 수 있는 문자열로 만듭니다
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    // 로그인 시 입력한 비밀번호(raw)와 DB에 저장된 암호화된 비밀번호(encoded)가 같은지 비교합니다.
    public boolean matches(String rawPassword, String encodedPassword) {
        // 입력값을 동일한 방식으로 검증하여 일치 여부를 결과로 반환합니다.
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}
