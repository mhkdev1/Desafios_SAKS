package com.br.saks.clienteservice.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
    public static String criptografar(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest criptografia = MessageDigest.getInstance("SHA-256");
        byte senhaCriptografada[] = criptografia.digest(senha.getBytes("UTF-8"));
        String senhaEmString = new String(senhaCriptografada, "UTF-8");

        return senhaEmString;
    }
}
