/*
SPDX-License-Identifier: Apache-2.0
*/
package org.example.ledgerapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * State class. States have a class, unique key, and a lifecycle current state
 * the current state is determined by the specific subclass
 */
public class State {

    protected String key;

    /**
     * @param {String|Object} class An identifiable class of the instance
     * @param {keyParts[]}    elements to pull together to make a key for the objects
     */
    public State() {

    }

    @JsonProperty("key")
    public String getKey() {
        return this.key;
    }

    @JsonIgnore
    public String[] getSplitKey() {
        System.out.println("Invoke State.getSplitKey(): this.key = " + key);
        return State.splitKey(this.key);
    }

    /**
     * Convert object to buffer containing JSON data serialization Typically used
     * before putState()ledger API
     *
     * @param {Object} JSON object to serialize
     * @return {buffer} buffer with the data to store
     */
    public static byte[] serialize(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Join the keyParts to make a unififed string
     */
    public static String makeKey(String[] keyParts) {
        return String.join(":", keyParts);
    }

    public static String[] splitKey(String key) {
        System.out.println("");
        System.out.println("Splittin gkey " + key + "   " + java.util.Arrays.asList(key.split(":")));
        return key.split(":");
    }

}
