package kr.co.wizclass.wizhealth;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class javaTest {
    @Test
    void basic() {
        int length = "jwt-yoonseon-jwt-yoonseon-jwt-yoonseon-jwt-yoonseon-jwt-yoonseon-jwt-yoonseon-jwt-yoonseon-jwt-yoonseon-jwt-yoonseon-jwt-yoonseon-jwt-yoonseon-jwt-yoonseon".length();

        System.out.println(length);


        long now = (new Date()).getTime();
        long now2 = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        System.out.println(now+" + "+now2);
    }

    @Test
    void 숫자생성() {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<26; i++) {
            for(int j=1; j<4; j++) {
                if (i%2==0 && j==3) {
                    break;
                }
                sb.append("("+i+", "+j+"), ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    @Test
    void 숫자생성2() {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<26; i++) {
            sb.append("("+i+", 1)");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
