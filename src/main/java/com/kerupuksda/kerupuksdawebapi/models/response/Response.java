package com.kerupuksda.kerupuksdawebapi.models.response;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

	@JsonProperty("status")
	private HttpStatus httpStatus;
	@JsonProperty("code")
	private int statusCode;
	@JsonProperty("message")
	private String message;
	@JsonProperty("data")
	private T body;

	public static <T> Response<T> success(@Nullable T body, String message) {
		return Response.<T>builder()
				.body(body)
				.message(message)
				.httpStatus(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build();
	}

}
