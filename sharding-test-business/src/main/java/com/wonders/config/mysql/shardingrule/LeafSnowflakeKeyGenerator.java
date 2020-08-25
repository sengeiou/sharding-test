package com.wonders.config.mysql.shardingrule;

import com.wonders.config.leaf.SnowflakeService;
import com.wonders.util.SpringContextUtils;
import com.wonders.util.leaf.common.Result;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;

/**
 * 
 *  
 * @author YuChen
 * @date 2020/8/25 10:05
 **/
 
public class LeafSnowflakeKeyGenerator implements ShardingKeyGenerator {

    private SnowflakeService snowflakeService;

    /**
     * Generate key.
     *
     * @return generated key
     */
    @Override
    public Comparable<?> generateKey() {
        if(snowflakeService == null){
            getService();
        }
        Result res = snowflakeService.getId(null);
        return res.getId();
    }

    private synchronized void getService() {
        if(snowflakeService == null){
            snowflakeService = (SnowflakeService)SpringContextUtils.getApplicationContext().getBean("SnowflakeService");
        }
    }

    /**
     * Get algorithm type.
     *
     * @return type
     */
    @Override
    public String getType() {
        return "LEFT-SNOWFLAKE";
    }

    /**
     * Get properties.
     *
     * @return properties of algorithm
     */
    @Override
    public Properties getProperties() {
        return null;
    }

    /**
     * Set properties.
     *
     * @param properties properties of algorithm
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
