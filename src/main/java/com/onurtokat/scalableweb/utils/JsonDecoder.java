package com.onurtokat.scalableweb.utils;

import com.onurtokat.scalableweb.exceptions.EncodingException;

import javax.xml.bind.DatatypeConverter;

/**
 * JsonDecoder provides decoding for Base64 encoded JSON string
 *
 * @author onurtokat
 */
public class JsonDecoder {

    /**
     * decode method decodes Base64 encoded JSON string
     *
     * @param encodedJson Base64 encoded JSON string
     * @return decoded JSON data
     */
    public static String decode(String encodedJson) {
        try {
            byte[] decoded = DatatypeConverter.parseBase64Binary(encodedJson);
            return new String(decoded);
        } catch (Exception e) {
            throw new EncodingException("Error occured when data encoding", e);
        }
    }
}
