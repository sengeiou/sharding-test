package com.wonders.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author YuChen
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop_test")
public class ShopTest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;


}
