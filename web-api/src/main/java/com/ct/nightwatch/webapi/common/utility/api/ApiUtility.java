package com.ct.nightwatch.webapi.common.utility.api;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ApiUtility {

    public static URI createLocationUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

}
