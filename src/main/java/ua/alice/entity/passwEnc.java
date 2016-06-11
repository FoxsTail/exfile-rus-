package ua.alice.entity;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * Created by Лис on 09.06.2016.
 */
public class passwEnc {
    public static void main(String[] args) {
        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
        String salt = "salt";
        System.out.println(passwordEncoder.encodePassword("SertyMin", null));
        System.out.println(passwordEncoder.isPasswordValid(passwordEncoder.encodePassword("SertyMin", null), "SertyMin", salt));
    }
}
