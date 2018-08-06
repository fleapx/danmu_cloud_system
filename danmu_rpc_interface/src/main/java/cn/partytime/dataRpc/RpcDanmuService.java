package cn.partytime.dataRpc;

import cn.partytime.dataRpc.impl.RpcDanmuServiceHystrix;
import cn.partytime.model.DanmuLogModel;
import cn.partytime.model.DanmuModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@FeignClient(value = "${dataRpcServer}",fallback = RpcDanmuServiceHystrix.class)
public interface RpcDanmuService {


    @RequestMapping(value = "/rpcDanmu/setSendUserTime" ,method = RequestMethod.GET)
    public void setSendUserTime(@RequestParam(value = "danmuId") String danmuId);

    @RequestMapping(value = "/rpcDanmu/setAdminAccepetTime" ,method = RequestMethod.GET)
    public void setAdminAccepetTime(@RequestParam(value = "danmuLogId") String danmuLogId);

    @RequestMapping(value = "/rpcDanmu/updateDanmuStatus" ,method = RequestMethod.GET)
    public void updateDanmuStatus(@RequestParam(value = "id") String id,@RequestParam(value = "sendStatus") int sendStatus);

    @RequestMapping(value = "/rpcDanmu/danmuLogSave" ,method = RequestMethod.POST)
    public DanmuLogModel save(DanmuLogModel danmuLogModel);

    @RequestMapping(value = "/rpcDanmu/findDanmuLogById" ,method = RequestMethod.GET)
    public DanmuLogModel findDanmuLogById(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/rpcDanmu/danmuSave" ,method = RequestMethod.POST)
    public DanmuModel save(DanmuModel danmuModel);

    @RequestMapping(value = "/rpcDanmu/findDanmuById" ,method = RequestMethod.GET)
    public DanmuModel findById(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/rpcDanmu/findDanmuByIsBlocked" ,method = RequestMethod.GET)
    public List<DanmuModel> findDanmuByIsBlocked(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, @RequestParam(value = "isBlocked") boolean isBlocked);

    @RequestMapping(value = "/rpcDanmu/findHistoryDanmu" ,method = RequestMethod.GET)
    public List<Map<String,Object>> findHistoryDanmu(@RequestParam(value = "partyId") String partyId, @RequestParam(value = "count") int count, @RequestParam(value = "id") String  id);


    @RequestMapping(value = "/rpcDanmu/findDanmuPoolIdListByPartyIdAndAddressId" ,method = RequestMethod.GET)
    public List<String> findDanmuPoolIdListByPartyIdAndAddressId(@RequestParam(value = "partyId") String partyId,@RequestParam(value = "addressId") String addressId);

    @RequestMapping(value = "/rpcDanmu/countByDanmuPoolIdInAndDanmuSrcAndIsBlockedAndViewFlgAndTimeGreaterThan" ,method = RequestMethod.GET)
    public long countByDanmuPoolIdInAndDanmuSrcAndIsBlockedAndViewFlgAndTimeGreaterThan(@RequestParam(value = "danmuPoolIdList") List<String> danmuPoolIdList,@RequestParam(value = "danmuSrc") int danmuSrc,@RequestParam(value = "isBlocked") boolean isBlocked,@RequestParam(value = "time") long time);

}
