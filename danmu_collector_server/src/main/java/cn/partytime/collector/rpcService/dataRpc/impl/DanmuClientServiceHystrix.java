package cn.partytime.collector.rpcService.dataRpc.impl;

import cn.partytime.collector.rpcService.dataRpc.DanmuClientService;
import cn.partytime.collector.model.DanmuClient;
import org.springframework.stereotype.Component;

/**
 * Created by dm on 2017/7/4.
 */
@Component
public class DanmuClientServiceHystrix implements DanmuClientService {

    @Override
    public DanmuClient findByRegistCode(String registCode) {
        return null;
    }
}