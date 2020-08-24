package com.wonders.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.VoiceMapper;
import com.wonders.domain.entity.Voice;
import com.wonders.service.IVoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 语音 服务实现类
 * </p>
 *
 * @author YuChen
 * @since 2020-08-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class VoiceServiceImpl extends ServiceImpl<VoiceMapper, Voice> implements IVoiceService {

}
