package com.kakao.pay;

public class AsciiCharRandomGenerator{

    public static ReceivableToken generate(int keySize) {
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
