package com.wonders.config.leaf;


import com.wonders.util.leaf.IDGen;
import com.wonders.util.leaf.common.Result;
import com.wonders.util.leaf.common.ZeroIDGen;
import com.wonders.util.leaf.snowflake.SnowflakeIDGenImpl;
import com.wonders.util.leaf.snowflake.exception.InitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SnowflakeService")
public class SnowflakeService {
    private Logger logger = LoggerFactory.getLogger(SnowflakeService.class);

    private IDGen idGen;

    @Autowired
    public SnowflakeService(LeafProperty property) throws InitException {
        LeafProperty.LeafSnowflakeProperty snowflake = property.getSnowflake();
        boolean flag = snowflake.isEnable();
        if (flag) {
            idGen = new SnowflakeIDGenImpl(property);
            if(idGen.init()) {
                logger.info("Snowflake Service Init Successfully");
            } else {
                throw new InitException("Snowflake Service Init Fail");
            }
        } else {
            idGen = new ZeroIDGen();
            logger.info("Zero ID Gen Service Init Successfully");
        }
    }

    public Result getId(String key) {
        return idGen.get(key);
    }
}
