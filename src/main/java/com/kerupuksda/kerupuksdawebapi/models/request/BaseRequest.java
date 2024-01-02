package com.kerupuksda.kerupuksdawebapi.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = -987654321L;

}
