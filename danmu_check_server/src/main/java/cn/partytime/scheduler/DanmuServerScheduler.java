package cn.partytime.scheduler;

import cn.partytime.cache.admin.CheckAdminCacheService;
import cn.partytime.cache.danmu.DanmuCacheService;
import cn.partytime.common.cachekey.danmu.DanmuCacheKey;
import cn.partytime.common.util.ListUtils;
import cn.partytime.common.util.SetUtils;
import cn.partytime.config.DanmuChannelRepository;
import cn.partytime.handlerThread.PartyDanmuPushHandler;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@EnableScheduling
@RefreshScope
@Slf4j
public class DanmuServerScheduler {

    @Autowired
    private DanmuChannelRepository danmuChannelRepository;

    @Autowired
    private CheckAdminCacheService checkAdminCacheService;

    @Autowired
    private PartyDanmuPushHandler partyDanmuPushHandler;

    @Autowired
    private DanmuCacheService danmuCacheService;

    @Scheduled(cron = "0/60 * * * * *")
    public void resetOnlineCheckAdminCount(){
        log.info("重置在线审核管理员的个数");
        int type=1;
        int count = danmuChannelRepository.getAdminCount(type);
        checkAdminCacheService.setCheckAdminCount(type,count);
    }

    /**
     * 将活动缓存中弹幕推送给管理员
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void pushPartyDanmuToCheckManager(){
        log.info("将缓存中弹幕推送给管理员");
        //int partyType = 0;
        Set<String> partySet =  danmuChannelRepository.getPartySet();
        if(SetUtils.checkSetIsNotNull(partySet)){
            for(String partyId:partySet){
                List<Channel> channelList = danmuChannelRepository.findChannelListByPartyId(partyId);
                if(ListUtils.checkListIsNotNull(channelList)){
                    Object object = danmuCacheService.getDanmuFromTempList(partyId);
                    if(object!=null){
                        partyDanmuPushHandler.pushDanmuToManager(object,channelList);
                    }
                }

            }
        }
    }


    //@Scheduled(cron = "0/10 * * * * *")
    /*public void checkAdminOffLineScheduler() {

        logger.info("管理员离线告警监听");
        //获取管理员掉县时间

        List<DanmuAddressModel> danmuAddressModels = danmuAddressService.findByType(0);
        if(ListUtils.checkListIsNotNull(danmuAddressModels)){
            for(DanmuAddressModel danmuAddressModel:danmuAddressModels){
                //rpcPartyService.findPartyAddressId(danmuAddressModel.getId());
                PartyLogicModel partyLogicModel = rpcPartyService.findFilmByAddressId(danmuAddressModel.getId());
                if(partyLogicModel!=null){
                    String partyId = partyLogicModel.getPartyId();
                    String addressId = partyLogicModel.getAddressId();
                    int count = danmuChannelRepository.findFilmCheckAdminCount();
                    if(count==0){
                        //判断管理员离线时间
                        Object object = redisService.get(AdminUserCacheKey.AMIN_OFFLINE_TIME);
                        if(object!=null){
                            long time = Long.parseLong(String.valueOf(object));
                            sendAlaram(time);
                        }else{
                            //管理员从来未登陆过
                            MovieScheduleModel movieScheduleModel = rpcMovieScheduleService.findCurrentMovie(partyId,addressId);
                            Date movieStartTime = movieScheduleModel.getMoviceStartTime();
                            sendAlaram(movieStartTime.getTime());
                        }
                    }
                    break;
                }
            }
        }
    }


    public void sendAlaram(long  time){
        Date date =  DateUtils.getCurrentDate();
        int alarmCount = cacheDataService.findadminOfflineAlarmCount();
        if(alarmCount>0){
            logger.info("告警已经发出");
            return;
        }


        long subTime = date.getTime() - time;
        long minute = subTime/1000/60;
        if(minute>5){
            //告警
            //rpcAdminAlarmService.admiOffLine();
            cacheDataService.adminOfflineAlarmCount(1);
        }
    }*/
}