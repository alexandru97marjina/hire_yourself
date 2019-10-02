package com.marjina.hire_yourself.common.util.translates;

import com.google.common.collect.ImmutableMap;
import com.marjina.hire_yourself.common.config.MainConfig;

import java.util.Map;

/**
 * Translates for all validation
 */
public class ValidTransl {

    /**
     * Get translation for specific language
     *
     * @param id Identifier of enum
     * @return String
     */
    public static String translate(String id) {
        try {
            return Words.valueOf(id).translates.get(MainConfig.LANG);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return id;
        }
    }

    /**
     * Enum of words
     */
    public enum Words {
        UNAUTHORIZED_TOKEN(ImmutableMap.of(
                "en", "You don't have access to this API. Please provide a valid token"
        )),
        USER_NOT_FOUND(ImmutableMap.of(
                "en", "User with a such email or id is not found!"
        )),
        EDUCATION_NOT_FOUND(ImmutableMap.of(
                "en", "Education entity with this id does not exits please first create one!"
        )),
        ACTIVITY_FIELD_NOT_FOUND(ImmutableMap.of(
                "en", "Activity entity with this id does not exits please first create one!"
        ));

        private final Map<String, String> translates;

        Words(Map<String, String> translates) {
            this.translates = translates;
        }
    }

}
