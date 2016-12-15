package com.example.perhe.myapplication;

import android.view.ViewGroup;
import android.widget.TextView;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sasukauppinen on 11.12.2016.
 */
class GeneratePasswords {
    private String allChars;
    private int pswdLen;

    GeneratePasswords(String allChars, int pswdLen) {
        this.allChars = allChars;
        this.pswdLen = pswdLen;
        if(pswdLen == 0) this.pswdLen = 12;
    }

    List<String> generate() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            List<String> pwords = new ArrayList<>(20);

            for (int j = 0; j < 10; j++) {

                StringBuilder buf = new StringBuilder();

                for (int i = 0; i < pswdLen; i++) {
                    Integer val = sr.nextInt(allChars.length());
                    buf.append(allChars.charAt(val));
                }
                String str = buf.toString();
                pwords.add(str);
            }

            return pwords;

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // Use normal random or something
        }

        return null;
    }
}
