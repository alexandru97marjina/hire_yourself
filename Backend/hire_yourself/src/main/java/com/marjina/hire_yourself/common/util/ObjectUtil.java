package com.marjina.hire_yourself.common.util;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.CURR_TIME_FIELD;
import static com.marjina.hire_yourself.common.util.consts.GlobalConst.SECRET_FIELD;

public class ObjectUtil {

    /**
     * Decrypt given access token using Base64 decoder
     *
     * @param token Encrypted token
     * @return Map with decrypted params: secret && time | EMPTY_MAP
     */
    public static Map<String, String> decryptAccessToken(String token) {
        try {
            Map<String, String> decryptedToken = new HashMap<>();
            token = decodeString(token);
            String[] parts = token.split("_");

            if (parts.length == 2) {
                decryptedToken.put(SECRET_FIELD, decodeString(parts[0]));
                decryptedToken.put(CURR_TIME_FIELD, decodeString(parts[1]));

                return decryptedToken;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        return new HashMap<>();
    }

    /**
     * Decode given string using Base64 decoder
     *
     * @param str Encoded string
     * @return String decoded string
     */
    public static String decodeString(String str) {
        return new String(Base64.getDecoder().decode(str));
    }


    /**
     * Filter given list to keep only unique elements
     *
     * @param list List with duplicates (or without)
     * @param <T>  Elements type
     * @return List with unique elements
     */
    public static <T> List<T> getListOfUniqueElements(List<T> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

}
