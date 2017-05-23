package com.example.Diego.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * Created by Diego on 08/05/2017.
 */

@Api(
        name = "u2Api",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Diego.example.com",
                ownerName = "backend.myapplication.Diego.example.com",
                packagePath = ""
        )
)
public class Usuario {
}
