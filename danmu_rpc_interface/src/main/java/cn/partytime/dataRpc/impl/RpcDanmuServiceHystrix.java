package cn.partytime.dataRpc.impl;

import cn.partytime.dataRpc.RpcDanmuService;
import cn.partytime.model.DanmuLogModel;
import cn.partytime.model.DanmuModel;

import java.util.List;
import java.util.Map;

public class RpcDanmuServiceHystrix implements RpcDanmuService {

    @Override
    public DanmuLogModel save(DanmuLogModel danmuLogModel) {
        return null;
    }

    @Override
    public DanmuLogModel findDanmuLogById(String id) {
        return null;
    }

    @Override
    public DanmuModel save(DanmuModel danmuModel) {
        return null;
    }

    @Override
    public DanmuModel findById(String id) {
        return null;
    }

    @Override
    public List<DanmuModel> findDanmuByIsBlocked(int page, int size, boolean isBlocked) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findHistoryDanmu(String partyId, int count, String id) {
        return null;
    }


}
