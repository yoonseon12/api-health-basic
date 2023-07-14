package kr.co.wizclass.wizhealth;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
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
        for (int i = 1; i<101; i++) {
            if (i%2==0) {
                for (int j=1; j<6; j++) {
                    sb.append("("+i+", "+j+"), ");

                }
            }
            else {
                for (int j=1; j<3; j++) {
                    sb.append("("+i+", "+j+"), ");

                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    @Test
    void 숫자생성3() {
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<101; i++) {
            sb.append("테스트운동시설").append(i).append("\n");
        }
        System.out.println(sb);
    }
}
