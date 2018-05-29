package cn.partytime.rpc;


import cn.partytime.common.util.DateUtils;
import cn.partytime.logicService.PartyLogicService;
import cn.partytime.model.MovieScheduleModel;
import cn.partytime.model.PageResultModel;
import cn.partytime.model.PartyLogicModel;
import cn.partytime.model.manager.MovieSchedule;
import cn.partytime.service.MovieScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rpcMovieSchedule")
@Slf4j
public class RpcMovieScheduleService {

    @Autowired
    private MovieScheduleService movieScheduleService;

    @Autowired
    private PartyLogicService partyLogicService;

    @RequestMapping(value = "/findByCurrentMoviePastTime" ,method = RequestMethod.GET)
    public long findByCurrentMoviePastTime(@RequestParam String partyId, @RequestParam String addressId){
        Page<MovieSchedule> movieSchedulePage = movieScheduleService.findPageByPartyIdAndAddressIsAndCreateTimeDesc(partyId,addressId,1,0);

        PartyLogicModel partyLogicModel = partyLogicService.findFilmParty(addressId);
        long movieTime = partyLogicModel.getMovieTime();

        long result = 0;

        //第一场
        if(movieSchedulePage.getTotalElements()==0){
            return 0;
        }

        //获取最后一条数据
        MovieSchedule movieSchedule = movieSchedulePage.getContent().get(0);
        if(movieSchedule.getEndTime()!=null){
            //电影结束了
            return 0;
        }
        Date startDate = movieSchedule.getStartTime();
        Date currentDate = DateUtils.getCurrentDate();
        long subTime = (currentDate.getTime() - startDate.getTime())/1000;

        return subTime;
    }

    @RequestMapping(value = "/findByCurrentMovieLastTime" ,method = RequestMethod.GET)
    public long findByCurrentMovieLastTime(@RequestParam String partyId, @RequestParam String addressId){
        Page<MovieSchedule> movieSchedulePage = movieScheduleService.findPageByPartyIdAndAddressIsAndCreateTimeDesc(partyId,addressId,1,0);

        PartyLogicModel partyLogicModel = partyLogicService.findFilmParty(addressId);
        long movieTime = partyLogicModel.getMovieTime();

        long result = 0;

        //第一场
        if(movieSchedulePage.getTotalElements()==0){
            return 0;
        }

        //获取最后一条数据
        MovieSchedule movieSchedule = movieSchedulePage.getContent().get(0);
        if(movieSchedule.getEndTime()!=null){
            //电影结束了
            return 0;
        }
        Date startDate = movieSchedule.getStartTime();
        Date currentDate = DateUtils.getCurrentDate();

        long subTime = (currentDate.getTime() - startDate.getTime())/1000/60;
        if(movieTime==0){

            return Math.abs(180 - subTime);
        }
        return  Math.abs(movieTime/1000/60 - subTime);
    }

    @RequestMapping(value = "/findByPartyIdAndAddressId" ,method = RequestMethod.GET)
    public List<MovieSchedule> findByPartyIdAndAddressId(@RequestParam String partyId, @RequestParam String addressId){
        return movieScheduleService.findByPartyIdAndAddressId(partyId,addressId);
    }

    @RequestMapping(value = "/insertMovieSchedule" ,method = RequestMethod.POST)
    public MovieSchedule insertMovieSchedule(@RequestBody MovieSchedule movieSchedule){
       return movieScheduleService.insertMovieSchedule(movieSchedule);
    }

    @RequestMapping(value = "/updateMovieSchedule" ,method = RequestMethod.POST)
    public MovieSchedule updateMovieSchedule(@RequestBody MovieSchedule movieSchedule){
       return movieScheduleService.updateMovieSchedule(movieSchedule);
    }

    @RequestMapping(value = "/findCurrentMovie" ,method = RequestMethod.GET)
    public MovieSchedule findCurrentMovie(String partyId,String addressId){
        Page<MovieSchedule> movieSchedulePage = movieScheduleService.findPageByPartyIdAndAddressIs(partyId,addressId,1,0);
        if(movieSchedulePage.getTotalElements()==0){
            return null;
        }
        return movieSchedulePage.getContent().get(0);
    }

    @RequestMapping(value = "/findLastMovieByAddressId" ,method = RequestMethod.GET)
    public MovieSchedule findLastMovieByAddressId(String addressId){
        Page<MovieSchedule> movieSchedulePage = movieScheduleService.findAllByAddressId(addressId,1,0);
        if(movieSchedulePage.getTotalElements()==0){
            return null;
        }
        return movieSchedulePage.getContent().get(0);
    }

    @RequestMapping(value = "/countByCreateTimeGreaterThanSeven" ,method = RequestMethod.GET)
    public long countByCreateTimeGreaterThanSeven(String addressId){
        Date sevenTime = DateUtils.getSpecifiedTime(7);
        log.info("createTime:"+sevenTime);
        return movieScheduleService.countByCreateTimeGreaterThan(sevenTime,addressId);
    }

    @RequestMapping(value = "/findLastMovieListByAddressId" ,method = RequestMethod.GET)
    public List<MovieSchedule> findLastMovieListByAddressId(String addressId,int pageSize,int pageNo){
        Page<MovieSchedule> movieSchedulePage = movieScheduleService.findAllByAddressId(addressId,pageSize,pageNo);
        if(movieSchedulePage.getTotalElements()==0){
            return null;
        }
        return movieSchedulePage.getContent();
    }
}
