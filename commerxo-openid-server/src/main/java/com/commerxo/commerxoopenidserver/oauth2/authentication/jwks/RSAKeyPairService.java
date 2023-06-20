package com.commerxo.commerxoopenidserver.oauth2.authentication.jwks;

import java.util.List;

public interface RSAKeyPairService {

    List<RSAKeyPair> getKeyPairs();

    void delete(RSAKeyPair rsaKeyPair);

    void save(RSAKeyPair rsaKeyPair);

}