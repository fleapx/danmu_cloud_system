package cn.partytime.dataRpc;

import cn.partytime.dataRpc.impl.RpcTimerDanmuServiceHystrix;
import cn.partytime.model.TimerDanmuFileModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by dm on 2017/7/11.
 */

@FeignClient(value = "${dataRpcServer}",fallback = RpcTimerDanmuServiceHystrix.class)
public interface RpcTimerDanmuService {


    @RequestMapping(value = "/rpcTimerDanmu/findTimerDanmuFileList" ,method = RequestMethod.GET)
    public List<TimerDanmuFileModel> findTimerDanmuFileList(@RequestParam(value = "partyIdList") List<String> partyIdList);


    @RequestMapping(value = "/rpcTimerDanmu/countByPartyIdAndBeginTimeGreaterThan" ,method = RequestMethod.GET)
    public boolean countByPartyIdAndBeginTimeGreaterThan(@RequestParam(value = "partyId") String partyId,@RequestParam(value = "time") long time);


}
