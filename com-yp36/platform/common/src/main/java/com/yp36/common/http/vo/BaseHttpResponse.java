/**
 *
 */
package com.yp36.common.http.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yangpeng
 *
 */
@Getter
@Setter
public class BaseHttpResponse<D> implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 5605035263916017857L;

	public int status = HttpCommons.Response.SUCCESS.getCode();

    public String message = HttpCommons.Response.SUCCESS.getMessage();

    public int count;

    public D data;

    @JsonProperty("more info")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String more_info;

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
