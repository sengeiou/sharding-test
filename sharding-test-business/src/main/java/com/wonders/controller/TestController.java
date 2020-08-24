package com.wonders.controller;

import cn.hutool.core.lang.Snowflake;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wonders.config.leaf.SnowflakeService;
import com.wonders.domain.entity.OrderTest;
import com.wonders.framework.annotion.LoggingFlag;
import com.wonders.framework.annotion.SaveRequestTimeFlag;
import com.wonders.service.IOrderTestService;
import com.wonders.util.leaf.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

/**
 * @author YuChen
 * @date 2020/4/16 17:57
 **/

@Slf4j
@Controller
@RequestMapping(value = "/test/",produces = "application/json;charset=UTF-8")
public class TestController {

    @Autowired
    private IOrderTestService orderTestService;

    @PostMapping("/testInsertOrder")
    @LoggingFlag(logging = true)
    @SaveRequestTimeFlag
    @ResponseBody
    public OrderTest testInsertOrder(@RequestBody OrderTest orderTest){
        orderTestService.save(orderTest);
        return orderTest;
    }

    @GetMapping("/testGetOrderById")
    @LoggingFlag(logging = true)
    @SaveRequestTimeFlag
    @ResponseBody
    public List<OrderTest> testGetOrderById(@RequestBody OrderTest orderTest){
        if(orderTest.getOrderId() == null && orderTest.getUserId() == null){
            return null;
        }
        QueryWrapper<OrderTest> queryWrapper = new QueryWrapper<>();
        Long orderId = orderTest.getOrderId();
        Long userId = orderTest.getUserId();
        if(orderId != null){
            queryWrapper.eq("order_id",orderId);
        }
        if(userId != null){
            queryWrapper.eq("user_id",userId);
        }
        return orderTestService.list(queryWrapper);
    }

    @Autowired
    private SnowflakeService snowflakeService;

    @GetMapping("/getIdBySnowflake")
    @LoggingFlag(logging = true)
    @SaveRequestTimeFlag
    @ResponseBody
    public Long getIdBySnowflake()throws BlockException{
        Result id = snowflakeService.getId(null);
        return id.getId();
    }

    public static void main(String[] args){
        watchSnowflackId();
    }

    public static void watchSnowflackId(){
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        Comparable<?> comparable = snowflakeShardingKeyGenerator.generateKey();
        Properties properties = snowflakeShardingKeyGenerator.getProperties();
        System.out.println("==================================== shardingsphere 自己的实现  workerId = 0 =================================");
        System.out.println("起始时间: Tue Nov 01 00:00:00 CST 2016   1477929600000L");
        System.out.println("id:"+comparable);
        Long s = (Long) comparable;
        String trans = trans(s);
        System.out.println("二进制:"+trans);
        System.out.println("二进制位数:"+trans.length());

        properties.setProperty("worker.id","3");
        snowflakeShardingKeyGenerator.setProperties(properties);
        System.out.println("==================================== shardingsphere 自己的实现  workerId = 3 ==================================");
        System.out.println("起始时间: Tue Nov 01 00:00:00 CST 2016   1477929600000L");
        Comparable<?> comparable1 = snowflakeShardingKeyGenerator.generateKey();
        System.out.println("id:"+comparable1);
        Long s1 = (Long) comparable1;
        String trans1 = trans(s1);
        System.out.println("二进制:"+trans1);
        System.out.println("二进制位数:"+trans1.length());

        System.out.println("====================================== hutool 的实现  workerId = 3 dataCenterId = 3 ============================");
        System.out.println("起始时间: Thu, 04 Nov 2010 01:42:54 GMT   1288834974657L");
        Snowflake snowflake = new Snowflake(3,3);
        long s2 = snowflake.nextId();
        String trans2 = trans(s2);
        System.out.println("id:"+s2);
        System.out.println("二进制:"+trans2);
        System.out.println("二进制位数:"+trans2.length());

        System.out.println("======================================= meituan 的实现  workerId = 0 ============================================");
        System.out.println("起始时间: Thu, 04 Nov 2010 01:42:54 GMT   1288834974657L");
        long s3 = 1296355248486481986L;
        String trans3 = trans(s3);
        System.out.println("id:"+s3);
        System.out.println("二进制:"+trans3);
        System.out.println("二进制位数:"+trans3.length());
    }

    public static String trans(Long l){
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < 64; j++) {
            long r = l & i;
            stringBuilder.append(r);
            l = l >> 1;
        }
        return stringBuilder.reverse().toString();
    }
}
