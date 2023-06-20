package com.commerxo.commerxoopenidserver.oauth2.authentication.jwks;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.UUID;

public class RSAKeyPair {

    private final String id;
    private final Instant createdAt;
    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    public RSAKeyPair(RSAPublicKey publicKey, RSAPrivateKey privateKey){
        this(UUID.randomUUID().toString(), Instant.now(), publicKey, privateKey);
    }

    public RSAKeyPair(Instant createdAt, RSAPublicKey publicKey, RSAPrivateKey privateKey){
        this(UUID.randomUUID().toString(), createdAt, publicKey, privateKey);
    }

    public RSAKeyPair(String id,Instant createdAt, RSAPublicKey publicKey, RSAPrivateKey privateKey){
        this.id = id;
        this.createdAt = createdAt;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public String getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }
}
