/**
 *
 */
package com.yp36.dao.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yangpeng 2017/06/15
 *
 */
@Getter
@Setter
public class FilterFactor extends BaseModel {

    private int id;

    private String factor_name;

    private String description;

    private String factor_code;

    private long group_id;

    private String group_name;

    private String remark;
}
