package com.kakao.pay.divvy.model.domain.token;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class AsciiCharRandomTokenGenerator implements TokenGenerator {

    @Value("3")
    private int keySize;

    public ReceivableToken generate() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digit = "0123456789";
        String special = "!@$%^&*()<>,.?/[]{}-=_+";

        String characters = new StringBuilder()
                .append(upper)
                .append(lower)
                .append(digit)
                .append(special).toString();

        StringBuilder result = new StringBuilder();
        for (int i = 0 ; i < keySize ; i++)
        {
            int pos = (int)Math.floor(Math.random() * (characters.length() - 1) - 0 + 1);
            result.append(characters.charAt(pos));
        }
        return new ReceivableToken(result.toString());
    }
}
