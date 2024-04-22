package pl.jorgX.utils.jwt;

import lombok.Getter;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    @Getter
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}