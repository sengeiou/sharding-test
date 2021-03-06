package com.wonders.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.CityMapper;
import com.wonders.domain.entity.City;
import com.wonders.service.ICityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 城市表 服务实现类
 * </p>
 *
 * @author YuChen
 * @since 2020-08-26
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

}
