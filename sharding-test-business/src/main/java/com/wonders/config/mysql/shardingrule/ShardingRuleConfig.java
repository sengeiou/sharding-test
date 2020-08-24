package com.wonders.config.mysql.shardingrule;

import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 分库分表配置
 *  
 * @author YuChen
 * @date 2020/8/21 14:21
 **/

@Configuration
public class ShardingRuleConfig {

    @Bean
    @ConditionalOnProperty(prefix = "spring.datasource",name = "mode",havingValue = "sharding")
    public ShardingRuleConfiguration shardingRuleConfiguration(){
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTestRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTestRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getShopTestRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("order_test, order_item_test");
        shardingRuleConfig.getBroadcastTables().add("voice");
        shardingRuleConfig.getBroadcastTables().add("banner");

        StandardShardingStrategyConfiguration orderDatabaseShardingConfiguration
                = new StandardShardingStrategyConfiguration("order_id"
                , new OrderPreciseShardingAlgorithm()
                //, new OrderRangeShardingAlgorithm()
        );

        StandardShardingStrategyConfiguration userDatabaseShardingConfiguration
                = new StandardShardingStrategyConfiguration("user_id"
                , new UserPreciseShardingAlgorithm()
                //, new UserRangeShardingAlgorithm()
        );

        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(userDatabaseShardingConfiguration);
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(orderDatabaseShardingConfiguration);
        return shardingRuleConfig;
    }

    private KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        Properties properties = new Properties();
        properties.setProperty("worker.id","22");
        properties.setProperty("max.vibration.offset","15");
        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration("SNOWFLAKE", "order_id",properties);
        return result;
    }

    public TableRuleConfiguration getOrderTestRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("order_test", "electric_${0..3}.order_test_${0..3}");
        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return result;
    }

    public TableRuleConfiguration getOrderItemTestRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("order_item_test", "electric_${0..3}.order_item_test_${0..3}");
        Properties properties = new Properties();
        properties.setProperty("worker.id","22");
        properties.setProperty("max.vibration.offset","15");
        result.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE","order_item_id",properties));
        return result;
    }

    public TableRuleConfiguration getShopTestRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("shop_test", "electric_${0..3}.shop_test");
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("s_id",new ShopPreciseShardingAlgorithm()));
        Properties properties = new Properties();
        properties.setProperty("worker.id","22");
        properties.setProperty("max.vibration.offset","15");
        result.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE","s_id",properties));
        return result;
    }
}
