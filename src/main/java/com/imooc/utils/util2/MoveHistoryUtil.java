package com.imooc.utils.util2.utils;

import com.creditease.panda.dao.entity.ClueHistoryDO;
import com.creditease.panda.dao.entity.ClueMainDO;
import com.creditease.panda.facade.constant.EnumConst;
import com.creditease.panda.facade.dataTransfer.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiecui1 on 2017/7/12.
 */
public class MoveHistoryUtil {
    private static final Logger LOG = LoggerFactory.getLogger(MoveHistoryUtil.class);

    public static final String MOVE_CLUE_HISTORY = "MOVE_CLUE_HISTORY";
    public static final String EMPTY_STR = "";

    private static String transformCode(Map<String, String> codeMap, Object code){
        if(code != null && StringUtils.isNotEmpty((String)code)){
            String codeStr = code.toString().trim();
            if(codeMap.containsKey(codeStr)){
                return codeMap.get(codeStr);
            } else {
                return EMPTY_STR;
            }
        }else{
            return EMPTY_STR;
        }
    }

    public static ClueInfoDTO historyTrans2ClueInfo(ClueHistoryDO history){
        ClueInfoDTO clueInfo = new ClueInfoDTO();
        ClueBasicDTO basic = new ClueBasicDTO();
        basic.setPhone(history.getPhone());
        basic.setMarketChannelLev1(Integer.valueOf(history.getMarketChannelLev1()));
        basic.setMarketChannelLev2(history.getMarketChannelLev2());
        basic.setMarketChannelLev3(history.getMarketChannelLev3());
        basic.setName(history.getName());
        basic.setIdentityNumber(history.getIdentityNumber());
        clueInfo.setBasic(basic);

        ClueOtherDTO other = new ClueOtherDTO();
        other.setBirthDate(history.getBirthDate());
        Map<String, String> educationCodeMap = new HashMap<String, String>();
        educationCodeMap.put("1","9");
        educationCodeMap.put("2","9");
        educationCodeMap.put("3","2");
        educationCodeMap.put("4","3");
        educationCodeMap.put("5","8");
        educationCodeMap.put("6","8");
        other.setEducation(history.getEducation());
        other.setEmail(history.getEmail());
        try{
            other.setGender(history.getGender() != null ? Integer.valueOf(history.getGender()) : 2);
        }catch (NumberFormatException nfe) {
            other.setGender(2);
        }
        other.setLivingAddr(history.getLivingAddr());

        Map<String,String> maritalStatusCodeMap = new HashMap<String, String>();
        maritalStatusCodeMap.put("1", "2");
        maritalStatusCodeMap.put("2", "1");
        maritalStatusCodeMap.put("4", "7");
        String maritalStatus = transformCode(maritalStatusCodeMap, history.getMaritalStatus());
        try{
            other.setMaritalStatus(StringUtils.isNotEmpty(maritalStatus.trim()) ? Integer.valueOf(maritalStatus.trim()) : 6);
        }catch (Exception e){
            LOG.info("MaritalStatus format error! clueId : {}", history.getClueId());
        }

        other.setProvince(history.getProvince());
        other.setQq(history.getQq());
        other.setRegisteredAddr(history.getRegisteredAddr());
        other.setCity(history.getCity());
        clueInfo.setOther(other);

        ClueSourceDTO source = new ClueSourceDTO();
        source.setConsultType(EMPTY_STR);
        source.setCustomerSearchId(EMPTY_STR);
        source.setMessageSource(EMPTY_STR);
        source.setSaleEmpid(history.getSaleEmpid());
        source.setCustomerSource(history.getCustomerSource());
        source.setSaleDepart(history.getSaleDepart());
        source.setSaleEmpid(history.getSaleEmpid());
        source.setSaleManagerEmpid(history.getSaleManagerEmpid());
        source.setSourceId(history.getSourceId());
        clueInfo.setSource(source);

        ClueAnayzeDTO anayze = new ClueAnayzeDTO();
        anayze.setCarBuyTime(history.getCarBuyTime());
        anayze.setCarCity(history.getCarCity());
        anayze.setCarDriveMiles(history.getCarDriveMiles());
        anayze.setCarModel(history.getCarModel());
        anayze.setCarNumber(history.getCarNumber());
        anayze.setCarPrice(history.getCarPrice() != null ? history.getCarPrice().toString() : EMPTY_STR);
        anayze.setCarProvince(history.getCarProvince());
        anayze.setIsCarEntirepay(history.getIsCarEntirepay());
        anayze.setIsCarMortgagePayOff(history.getIsCarMortgagePayOff());
        anayze.setIsLoanFromUs(history.getIsLoanFromUs());
        anayze.setIsNineSeats(history.getIsNineSeats());
        anayze.setCareerLength(history.getCareerLength());
        anayze.setCareerStartDate(history.getCareerStartDate());
        anayze.setCareerType(history.getCareerType());
        anayze.setCareerTypeOther(history.getCareerTypeOther());
        anayze.setCompanyAddr(history.getCompanyAddr());
        anayze.setCompanyName(history.getCompanyName());
        anayze.setCompanyTele(history.getCompanyTele());
        anayze.setJobContract(history.getJobContract() != null ? history.getJobContract().toString() : EMPTY_STR);
        anayze.setJobProof(history.getJobProof());
        anayze.setJobTitle(history.getJobTitle());
        anayze.setWorkingDepartment(history.getWorkingDepartment());
        anayze.setCurJobDuringTime(history.getCurJobDuringTime());
        anayze.setIsEcommerceOperator(history.getIsEcommerceOperator() != null ? history.getIsEcommerceOperator().toString() : EMPTY_STR);
        anayze.setEcommerceOp(history.getEcommerceOp() != null ? history.getEcommerceOp().toString() : EMPTY_STR);
        anayze.setEcommercePlatform(history.getEcommercePlatform());
        anayze.setEcommerceType(history.getEcommerceType());
        anayze.setManageYear(history.getManageYear());
        anayze.setBusinessLicense(history.getBusinessLicense() != null ? history.getBusinessLicense().toString() : EMPTY_STR);
        anayze.setBusinessProof(history.getBusinessProof() != null ? history.getBusinessProof().toString() : EMPTY_STR);
        anayze.setEstateBuiltDate(history.getEstateBuiltDate());
        anayze.setEstateBuyDate(history.getEstateBuyDate());
        anayze.setEstateBuyPrice(history.getEstateBuyPrice());
        anayze.setEstateCity(history.getEstateCity());
        anayze.setEstateProvince(history.getEstateProvince());
        anayze.setHouseSelfEvaluatePrice(history.getHouseSelfEvaluatePrice());
        anayze.setIsEstateEntirePay(history.getIsEstateEntirePay() != null ? history.getIsEstateEntirePay().toString() : EMPTY_STR);
        anayze.setIsEstatePaidOff(history.getIsEstatePaidOff() != null ? history.getIsEstatePaidOff().toString() : EMPTY_STR);
        anayze.setHasEstateLoan(history.getHasEstateLoan());
        anayze.setSalary(history.getSalary());
        anayze.setSalaryByCard(history.getSalaryByCard());
        anayze.setSalaryByCash(history.getSalaryByCash());
        anayze.setSalaryPayType(history.getSalaryPayType());
        anayze.setSocialInsurance(history.getSocialInsurance() != null ? history.getSocialInsurance().toString() : EMPTY_STR);
        Map<String, String> hasCarCodeMap = new HashMap<String, String>();
        hasCarCodeMap.put("2", "4");
        anayze.setHasCar(transformCode(hasCarCodeMap, history.getHasCar()));

        Map<String, String> hasEstateCodeMap = new HashMap<String, String>();
        hasEstateCodeMap.put("2", "4");
        anayze.setHasEstate(transformCode(hasEstateCodeMap, history.getHasEstate()));

        anayze.setWorkingDepartment(history.getWorkingDepartment());
        anayze.setHaveCreditCard(history.getHaveCreditCard());
        anayze.setHouseFund(history.getHouseFund() != null ? history.getHouseFund().toString() : EMPTY_STR);
        anayze.setCreditCardLine(history.getCreditCardLine());
        anayze.setCreditcardExpireStatus(history.getCreditcardExpireStatus());
        anayze.setStockOver20(history.getStockOver20() != null ? history.getStockOver20().toString() : EMPTY_STR);
        anayze.setLenders(history.getLenders());
        anayze.setBankStatement(history.getBankStatement() != null ? history.getBankStatement().toString() : EMPTY_STR);
        anayze.setEstateValuation(history.getEstateValuation());
        anayze.setPaidMorethanHalf(history.getPaidMorethanHalf());
        anayze.setOriProductTypeid(history.getOriProductTypeid());
        anayze.setOrigin(history.getOrigin());
        anayze.setOriginExtrainfo(history.getOriginExtrainfo());
        anayze.setOs(history.getOs());
        anayze.setOtherEcommercePlatform(history.getOtherEcommercePlatform());
        anayze.setOtherLenders(history.getOtherLenders());
        anayze.setOtherMarketChannel(history.getOtherMarketChannel());
        anayze.setIp(history.getIp());
        anayze.setUtmCampaign(history.getUtmCampaign());
        anayze.setUtmContent(history.getUtmContent());
        anayze.setUtmKeyword(history.getUtmKeyword());
        anayze.setUtmSource(history.getUtmSource());
        anayze.setUtmTerm(history.getUtmTerm());
        anayze.setUtmMedia(history.getUtmMedia());
        anayze.setUtmMedium(history.getUtmMedium());
        anayze.setSourceTypeId(history.getSourceTypeId());
        anayze.setCreatorId(history.getCreatorId());
        anayze.setLoanDesc(history.getLoanDesc());
        anayze.setLoanDueTime(history.getLoanDueTime() != null ? history.getLoanDueTime().toString() : EMPTY_STR);
        anayze.setLoanExpireStatus(history.getLoanExpireStatus());
        anayze.setLoanUseTime(history.getLoanUseTime());
        anayze.setProductType(history.getProductType() != null ? history.getProductType().toString() : EMPTY_STR);
        anayze.setProductTypeId(history.getProductTypeId() != null ? history.getProductTypeId().toString() : EMPTY_STR);
        anayze.setQuantity(history.getQuantity());
        anayze.setUsagePurpose(history.getUsagePurpose());
        anayze.setMaxRepayment(history.getMaxRepayment() != null ? history.getMaxRepayment().toString() : EMPTY_STR);
        anayze.setUseDate(history.getUseDate());
        anayze.setUserLevel(history.getUserLevel());
        anayze.setUserScore(history.getUserScore() != null ? history.getUserScore().toString() : EMPTY_STR);
        anayze.setYrdDiscardReason(history.getYrdDiscardReason());
        anayze.setFirstContactType(history.getFirstContactType());
        anayze.setDiscardReasonOther(history.getDiscardReasonOther());
        anayze.setAddressProof(history.getAddressProof());
        anayze.setEmergentType(history.getEmergentType());
        anayze.setExt1(history.getExt1());
        anayze.setExt2(history.getExt2());
        anayze.setFeature(history.getFeature());
        anayze.setGiveUp(history.getGiveUp());
        anayze.setGiveUpReason(history.getGiveUpReason());
        anayze.setIsReloan(history.getIsReloan());
        anayze.setLevel(history.getLevel());
        anayze.setMarketChannelId(history.getMarketChannelId());
        anayze.setOperationLength(history.getOperationLength());
        anayze.setRawDataId(history.getRawDataId());

        clueInfo.setAnayze(anayze);

        return clueInfo;
    }

    public static ClueMainDO historyTrans2ClueMainDo(Integer systemFlag, ClueHistoryDO history, ClueInfoDTO clueInfo){
        ObjectMapper mapper = new ObjectMapper();
        ClueMainDO clueMain = new ClueMainDO();

        clueMain.setClueId(history.getClueId().toString());
        clueMain.setPhone(history.getPhone());
        clueMain.setName(history.getName());
        clueMain.setStatus(convertStatus(history.getStatus(), history.getMarketChannelLev1()));

        try{
            clueMain.setMarketChannelLev1(Integer.valueOf(history.getMarketChannelLev1()));
        }catch (Exception e){
            clueMain.setMarketChannelLev1(111);
            LOG.info("MarketChannelLev1 format error! clueId : {}", history.getClueId());
        }

        clueMain.setMarketChannelLev2(history.getMarketChannelLev2());
        clueMain.setMarketChannelLev3(history.getMarketChannelLev3());
        clueMain.setIdentityNumber(history.getIdentityNumber());
        clueMain.setDispatchTarget(convertDispatchTarget(history.getDispatchTarget()));
        String data = EMPTY_STR;
        try {
            data = mapper.writeValueAsString(clueInfo);
        } catch (Exception e) {
            LOG.debug("trans json error!",e.getMessage());
        }
        clueMain.setData(data);


        ClueStatusEnum status = ClueStatusEnum.valueOf(history.getStatus());
        clueMain.setIsDuplicate(status.equals(ClueStatusEnum.dup_24hours) ? true : false);
        clueMain.setIsFraud(status.equals(ClueStatusEnum.fraud) ? true : false);
        clueMain.setIsIllegality(false);

        clueMain.setSystemFlag(systemFlag);
        clueMain.setDataType(EnumConst.ClueDataType.NEW_CLUE.getContext());
        clueMain.setUpdatedTime(history.getUpdateTime());
        clueMain.setCreatedTime(history.getCreateTime());
        return clueMain;
    }

    private static final String GRAB_AUNT_3_STR = "抢小姨_3";
    private static final String GRAB_AUNT_6_STR = "抢小姨_6";

    public static String convertDispatchTarget(String historyDispatchTarget){
        if(historyDispatchTarget.equals(GRAB_AUNT_3_STR)){
            return EnumConst.ClueChannel.GRAB_AUNT_CODE_3.getContext().toString();
        }else if(historyDispatchTarget.equals(GRAB_AUNT_6_STR)){
            return EnumConst.ClueChannel.GRAB_AUNT_CODE_6.getContext().toString();
        }else{
            return historyDispatchTarget;
        }
    }

    public static Integer convertStatus(String historyStatus,String marketChannelLev1){
        ClueStatusEnum status = ClueStatusEnum.valueOf(historyStatus);
        if(status.equals(ClueStatusEnum.dup_24hours) || status.equals(ClueStatusEnum.fraud)){
            if(marketChannelLev1.equals(EnumConst.ClueChannel.CONSULTANT_CODE) || marketChannelLev1.equals(EnumConst.ClueChannel.FINGER_CODE)){
                return EnumConst.ClueStatus.DISTRIBUTE_SUCCESS.getContext();
            }else{
                return EnumConst.ClueStatus.DISTRIBUTE_FAILURE.getContext();
            }
        }else{
            return status.getContext();
        }
    }

    public enum ClueStatusEnum {
        created(0),    //创建
        ready(1),      //准备
        dispatching(4),    //分发中
        dispatched(3), //分发完成
        dispatch_failed(4),    //分发失败
        recycled(4),   //回收
        dup_24hours(6),    //24小时内重复
        fraud(7);       //欺诈

        private Integer context;
        ClueStatusEnum(Integer context) {
            this.context = context;
        }

        public Integer getContext() {
            return context;
        }
    }
}
