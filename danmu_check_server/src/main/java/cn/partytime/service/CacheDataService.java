package cn.partytime.service;

import cn.partytime.common.util.IntegerUtils;
import cn.partytime.model.DanmuClientModel;
import cn.partytime.common.cachekey.AdminUserCacheKey;
import cn.partytime.common.cachekey.ClientCacheKey;
import cn.partytime.redis.service.RedisService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by lENOVO on 2016/9/26.
 */

@Service
public class CacheDataService {


    @Autowired
    private RedisService redisService;


    /**
     * 管理员在线人数
     * @param partyType
     * @param count
     */
    public void setAdminOnlineCount(int partyType,int count){
        String key = AdminUserCacheKey.ADMIN_ONLINE_COUNT+partyType;
        if(count==0){
            redisService.set(key,count);
        }else{
            redisService.expire(key,0);
        }
    }

    public void clearAdminAlarmCache(){
        redisService.expire(AdminUserCacheKey.AMIN_OFFLINE_ALARM_COUNT,0);
        redisService.expire(AdminUserCacheKey.AMIN_OFFLINE_TIME,0);
    }

    public void adminOfflineAlarmCount(int count){
        String key = AdminUserCacheKey.AMIN_OFFLINE_ALARM_COUNT;
        redisService.incrKey(key,count);
    }

    public int findadminOfflineAlarmCount(){
        String key = AdminUserCacheKey.AMIN_OFFLINE_ALARM_COUNT;
        return IntegerUtils.objectConvertToInt(redisService.get(key));
    }



    /**
     * 获取缓存中存的客户端
     * @param addressId
     * @return
     */
    public List<DanmuClientModel> findDanmuClientList(String addressId) {
        String clientSortSetKey = ClientCacheKey.CLIENT_CACHE_ID_SORTSET + addressId;
        Set<String> clientSet = redisService.getSortSetByRnage(clientSortSetKey, 0, -1, true);
        List<DanmuClientModel> clientNameList = null;
        if (clientSet != null && !clientSet.isEmpty()) {
            clientNameList = new ArrayList<DanmuClientModel>();
            for (String str : clientSet) {
                DanmuClientModel danmuClientModel = JSON.parseObject(str, DanmuClientModel.class);
                clientNameList.add(danmuClientModel);
            }
        }
        return clientNameList;
    }
}
