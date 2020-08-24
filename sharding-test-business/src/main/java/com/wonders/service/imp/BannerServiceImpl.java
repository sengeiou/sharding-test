package com.wonders.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.BannerMapper;
import com.wonders.domain.entity.Banner;
import com.wonders.service.IBannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * store首页banner表 服务实现类
 * </p>
 *
 * @author YuChen
 * @since 2020-08-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {

}
