package com.toolbox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toolbox.entity.MetaInfo;
import com.toolbox.mapper.MetaInfoMapper;
import com.toolbox.service.IMetaInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tianYang
 * @since 2022-02-10
 */
@Service
public class MetaInfoServiceImpl extends ServiceImpl<MetaInfoMapper, MetaInfo> implements IMetaInfoService {

}
