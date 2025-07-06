package br.com.brdonsb.literatura.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
