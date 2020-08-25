package com.wonders.domain.result;

import com.wonders.domain.entity.OrderItemTest;
import com.wonders.domain.entity.OrderTest;
import lombok.Data;

import java.util.List;

/**
 * 
 *  
 * @author YuChen
 * @date 2020/8/24 16:37
 **/

@Data
public class OrderInsertResult {

    private OrderTest order;

    private List<OrderItemTest> itemList;
}
