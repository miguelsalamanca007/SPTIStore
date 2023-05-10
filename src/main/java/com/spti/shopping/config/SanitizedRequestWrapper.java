package com.spti.shopping.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SanitizedRequestWrapper extends HttpServletRequestWrapper {
    public SanitizedRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value != null) {
            value = sanitize(value);
        }
        return value;
    }

    private String sanitize(String value) {
        // Elimina los caracteres o patrones que podrían ser utilizados en un ataque de inyección SQL
        // En este ejemplo, simplemente eliminamos los puntos y comas, pero puedes agregar más patrones de acuerdo a tus necesidades
        return value.replaceAll(";", "");
    }
}

