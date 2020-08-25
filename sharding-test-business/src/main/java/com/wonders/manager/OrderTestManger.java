package com.wonders.manager;

import com.wonders.domain.entity.OrderItemTest;
import com.wonders.domain.entity.OrderTest;
import com.wonders.domain.entity.UserTest;
import com.wonders.domain.result.OrderInsertResult;
import com.wonders.service.IOrderItemTestService;
import com.wonders.service.IOrderTestService;
import com.wonders.service.IUserTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YuChen
 * @since 2020-08-18
 */
@Slf4j
@Service
public class OrderTestManger {

    @Autowired
    private IOrderTestService orderTestService;

    @Autowired
    private IOrderItemTestService orderItemTestService;

    @Autowired
    private IUserTestService userTestService;

    @Transactional(rollbackFor = Exception.class)
    public OrderInsertResult testInsertOrderTransaction(OrderTest orderTest){
        orderTestService.save(orderTest);
        List<OrderItemTest> itemTestList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OrderItemTest orderItemTest = new OrderItemTest();
            orderItemTest.setOrderId(orderTest.getOrderId());
            orderItemTest.setName("测测测"+i);
            orderItemTest.setUnitCount(i+1);
            orderItemTest.setUserId(orderTest.getUserId());
            itemTestList.add(orderItemTest);
        }
        Long userId = orderTest.getUserId();
        orderItemTestService.saveBatch(itemTestList);
        OrderInsertResult orderInsertResult = new OrderInsertResult();
        orderInsertResult.setOrder(orderTest);
        orderInsertResult.setItemList(itemTestList);
        if(userId == 404){
            throw new RuntimeException("ceshi");
        }
        return orderInsertResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public OrderInsertResult testInsertOrderTransaction2(OrderTest orderTest){
        orderTestService.save(orderTest);
        List<OrderItemTest> itemTestList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OrderItemTest orderItemTest = new OrderItemTest();
            orderItemTest.setOrderId(orderTest.getOrderId());
            orderItemTest.setName("测测测"+i);
            orderItemTest.setUnitCount(i+1);
            orderItemTest.setUserId(orderTest.getUserId());
            itemTestList.add(orderItemTest);
        }
        Long userId = orderTest.getUserId();
        orderItemTestService.saveBatch(itemTestList);
        OrderInsertResult orderInsertResult = new OrderInsertResult();
        orderInsertResult.setOrder(orderTest);
        orderInsertResult.setItemList(itemTestList);
        UserTest byId = userTestService.getById(orderTest.getUserId());
        byId.setName(byId.getName()+"|修改|");
        if(userId == 404){
            throw new RuntimeException("ceshi");
        }
        userTestService.updateById(byId);
        return orderInsertResult;
    }

}
