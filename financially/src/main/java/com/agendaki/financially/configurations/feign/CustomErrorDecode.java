package com.agendaki.financially.configurations.feign;

import com.agendaki.financially.exceptions.InternalRequestFailedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomErrorDecode implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if (HttpStatus.valueOf(response.status()).is5xxServerError()){
            throw new InternalRequestFailedException("Api for end-point: " + response.request().url()+ " is failed.");
        }
        return null;
    }
}
