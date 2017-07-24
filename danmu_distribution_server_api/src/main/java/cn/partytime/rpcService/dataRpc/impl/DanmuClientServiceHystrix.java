package cn.partytime.rpcService.dataRpc.impl;

import cn.partytime.model.DanmuClient;
import cn.partytime.rpcService.DanmuClientService;
import org.springframework.stereotype.Component;

/**
 * Created by dm on 2017/7/4.
 */
@Component
public class DanmuClientServiceHystrix implements DanmuClientService{

    @Override
    public DanmuClient findByRegistCode(String registCode) {
        return null;
    }
}
