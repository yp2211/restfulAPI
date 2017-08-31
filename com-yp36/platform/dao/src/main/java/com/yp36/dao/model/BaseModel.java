/**
 *
 */
package com.yp36.dao.model;

import java.sql.Date;

import com.yp36.common.custom.Pagination;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yangpeng 2017/06/15
 *
 */
@Getter
@Setter
public class BaseModel extends Pagination {
    private Date updated_time;

    private Date created_time;

    private String created_by;

    private String updated_by;

    private int version;

}
