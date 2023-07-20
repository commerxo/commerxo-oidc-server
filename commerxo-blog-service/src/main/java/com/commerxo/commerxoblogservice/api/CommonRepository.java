package com.commerxo.commerxoblogservice.api;

public interface CommonRepository <T>{

        T save(T t);

        void delete(T t);

        T update(T t);

}
