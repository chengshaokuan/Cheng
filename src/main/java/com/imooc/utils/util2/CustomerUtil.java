package com.imooc.utils.util2.utils;

import com.creditease.panda.engine.interfaces.DictionaryEngine;
import com.creditease.panda.facade.constant.*;
import com.creditease.panda.facade.dataTransfer.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mengshuai on 2017/7/6.
 */
@Component
public class CustomerUtil {

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CustomerUtil.class);
    @Autowired
    private DictionaryEngine dictionaryEngine;
    private static Map<String, Map<String, Object>> dictionaries;

    @PostConstruct
    public void initDic () {
        dictionaries = new HashMap<String, Map<String, Object>>();
        EnumSet<DicEnumConst.DicType> enumSet = EnumSet.allOf(DicEnumConst.DicType.class);
        for(DicEnumConst.DicType dicType : enumSet)
            dictionaries.put(dicType.name(), initHashMap(dicType));
    }

    private Map<String, Object> initHashMap(DicEnumConst.DicType type) {
        Map<String, Object> result = new HashMap<String, Object>();

        DictionaryDTO dictionaryDTO = new DictionaryDTO();
        dictionaryDTO.setLevel(0);
        dictionaryDTO.setType(type.getCode());
        for(DictionaryResultItemDTO dictionaryResultItemDTO: dictionaryEngine.queryDictionaryBySingleItem(dictionaryDTO))
            result.put(dictionaryResultItemDTO.getCode(), dictionaryResultItemDTO.getContent());
        return result;
    }

    /**
     *
     */
    public static CustomerRespDTO checkParams(CustomerDTO customerDTO) {
        Logger.info("参数校验入口");
        CustomerRespDTO customerRespDTO = new CustomerRespDTO();
        JSONObject jsonObject = new JSONObject();
        boolean res = validateMain(customerDTO, jsonObject) &
                validateIdentities(customerDTO, jsonObject) &
                validateSystemSource(customerDTO, jsonObject) &
                validateContactWays(customerDTO, jsonObject) &
                validateContacts(customerDTO, jsonObject) &
                validateJobs(customerDTO, jsonObject) &
                validateOrgStaff(customerDTO, jsonObject) &
                validateCards(customerDTO, jsonObject) &
                validateCars(customerDTO, jsonObject) &
                validateEstate(customerDTO, jsonObject) &
                validateAddress(customerDTO, jsonObject) &
                validateEducation(customerDTO, jsonObject);

        if(!res) {
            customerRespDTO.setCode(InteractConst.PARAM_INVALID_CODE);
            customerRespDTO.setMsg(InteractConst.PARAM_INVALID_MSG);
        } else {
            customerRespDTO.setCode(InteractConst.REQUEST_SUCC_CODE);
            customerRespDTO.setMsg("check params pass");
        }
        Logger.info("参数校验结果为:{}",res);
        customerRespDTO.setContent(jsonObject);
        return customerRespDTO;
    }

    /**
     *
     */
    public static CustomerIdentityDTO getMainIden(CustomerDTO customerDTO) {
        if(customerDTO.getIdentities() == null)
            return null;

        CustomerIdentityDTO result = null;
        for(CustomerIdentityDTO customerIdentityDTO : customerDTO.getIdentities()) {
            //根据需求，查询以及开户之前，先把姓名和证件号码转换成大写
            customerIdentityDTO.setCustName(customerIdentityDTO.getCustName().toUpperCase());
            customerIdentityDTO.setIdNumber(customerIdentityDTO.getIdNumber().toUpperCase());

            if(customerDTO.getPersonalMsg() != null && customerDTO.getPersonalMsg().getBirthday() == null)
                customerDTO.getPersonalMsg().setBirthday(IdentityUtil.getBirthday(customerIdentityDTO.getIdNumber()));

            if(customerIdentityDTO.getIsMajorCard() == DicEnumConst.IsMajorCard.YES.getContext())
                result = customerIdentityDTO;
        }

        return result;
    }

    /**
     *
     */
    private static boolean validateMain(CustomerDTO customerDTO, JSONObject content) {
        boolean res = false;

        if (customerDTO.getCustomerType() == null) {
            content.put("customerType", "客户类型为空;");
        } else if(DicEnumConst.OpenAccountType.ORG.getContext() != customerDTO.getCustomerType() &&
                DicEnumConst.OpenAccountType.PERSONAL.getContext() != customerDTO.getCustomerType()) {
            content.put("customerType", "客户类型不存在;");
        } else if (StringUtils.isBlank(customerDTO.getOperator())) {
            content.put("operator", "操作人编号为空;");
        }else if(!StringUtils.isNumeric(customerDTO.getOperator())){
            content.put("operator","操作人格式错误，必须为数字型字符串");
        }else if(customerDTO.getOperatorType() == null || customerDTO.getOperatorType() != DicEnumConst.OperatorType.OPEN_ACCOUNT.getContext() &&
                customerDTO.getOperatorType() != DicEnumConst.OperatorType.UPDATE.getContext()) {  //todo 不需要校验,给一个默认值
            content.put("operatorType", "操作类型为空或不正确，开户操作是0;");
        } else {
            res = true;
        }
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }

    /**
     * 三要素必填以及有效性校验。
     *
     * @param customerDTO
     * @param content
     * @return 唯一的主证件
     */
    private static boolean validateIdentities(CustomerDTO customerDTO, JSONObject content) {
        boolean res = true;
        StringBuilder resultMsg = new StringBuilder();

        if(customerDTO.getIdentities() == null || customerDTO.getIdentities().size() == 0) {
            resultMsg.append("证件列表为空;");
            res = false;
        } else {
            int count = 0;
            Set<String> deduplicatedIdTypes = new HashSet<String>();
            boolean flag;

            for (CustomerIdentityDTO customerIdentityDTO : customerDTO.getIdentities()) {
                deduplicatedIdTypes.add(customerIdentityDTO.getIdType());//Set不能存放重复元素
                //如果设置了主证件标志，计数加1
                if (customerIdentityDTO.getIsMajorCard() != null && DicEnumConst.IsMajorCard.YES.getContext() == customerIdentityDTO.getIsMajorCard())
                    count++;

                flag = false;
                if (StringUtils.isBlank(customerIdentityDTO.getCustName())) {
                    resultMsg.append("名称为空;");
                } else if (StringUtils.isBlank(customerIdentityDTO.getIdNumber())) {
                    resultMsg.append("证件号码为空;");
                } else if (StringUtils.isBlank(customerIdentityDTO.getIdType())) {
                    resultMsg.append("证件类型为空;");
                } else if (!validateCode(DicEnumConst.DicType.IDENTITY_TYPE, customerIdentityDTO.getIdType())) {
                    resultMsg.append("证件类型不存在;");
                } else if (InteractConst.ID_TYPE_MAIN.equals(customerIdentityDTO.getIdType()) &&
                        !IdentityUtil.IdCardValid(customerIdentityDTO.getIdNumber())) {
                    resultMsg.append("身份证格式不符;");
                } else {
                    flag = true;
                }
                res = res && flag;
            }

            if (count != 1) {
                resultMsg.append("主证件有且只能有一个;");
                res = false;
            } else if(deduplicatedIdTypes.size() < customerDTO.getIdentities().size()) {
                resultMsg.append("证件列表中不能有相同的证件类型;");
                res = false;
            }
        }

        if(!res)
            content.put("identities", resultMsg.toString());
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }

    /**
     * 来源系统检验
     *
     * @param customerDTO
     * @param content
     * @return 所有字段符合标准返回true；否则false。
     */
    public static boolean validateSystemSource(CustomerDTO customerDTO, JSONObject content) {
        boolean res = false;
        StringBuilder resultMsg = new StringBuilder();

        if(customerDTO.getGenerals() == null || customerDTO.getGenerals().size() == 0) {
            resultMsg.append("来源信息为空;");
        } else if (customerDTO.getGenerals().get(0) == null
                || StringUtils.isBlank(customerDTO.getGenerals().get(0).getSystemSource())) {
            resultMsg.append("来源系统代码必填;");
        } else if (!validateCode(DicEnumConst.DicType.SYSTEM, customerDTO.getGenerals().get(0).getSystemSource())) {
            resultMsg.append("来源系统代码不存在;");
        } else if (StringUtils.isBlank(customerDTO.getGenerals().get(0).getSourceUid())) {
            resultMsg.append("来源系统客户编号必填;");
        } else
            if(null == customerDTO.getGenerals().get(0).getBothEnds() || "".equals(customerDTO.getGenerals().get(0).getBothEnds())){
            resultMsg.append("generals模块中两端标识bothEnds必填:普惠(1)、财富(2)、普惠&财富(3)、其他(4)");
        }else
            res = true;

        if(!res)
            content.put("generals", resultMsg.toString());
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }

    private static boolean validateCode(DicEnumConst.DicType type , String val) {
        return dictionaries.get(type.name()).keySet().contains(val);
    }

    /**
     *
     */
    private static boolean validateContactWays(CustomerDTO customerDTO, JSONObject content) {
        boolean res = true;
        StringBuilder msg = new StringBuilder();

        if (!checkContacts(customerDTO.getContactWays(), msg)) {
            content.put("contactWays", msg.toString());
            res = false;
        }
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }

    private static boolean validateOrgStaff(CustomerDTO customerDTO, JSONObject content) {
        boolean res = true;

        if (customerDTO.getOrgStaffCompositions() != null) {
            StringBuilder msg = new StringBuilder();
            boolean flag;
            for (CustomerOrgStaffCompositionsDTO orgStaffCompositionsDTO : customerDTO.getOrgStaffCompositions()) {
                flag = checkContacts(orgStaffCompositionsDTO.getContactWays(), msg);
                res = res && flag;
            }
            content.put("orgStaff", msg.toString());
        }
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }
    /**
     *
     * @param customerDTO
     * @param content
     * @return
     */
    private static boolean validateContacts(CustomerDTO customerDTO, JSONObject content) {
        boolean res = true;

        if (customerDTO.getContacts() != null) {
            StringBuilder msg = new StringBuilder();
            boolean flag,flags;
            for (CustomerContactDTO customerContactDTO : customerDTO.getContacts()) {
                flag = checkContacts(customerContactDTO.getContactWays(), msg);
                flags = checkContactsAdds(customerContactDTO.getAddress(), msg);
                if(StringUtils.isBlank(customerContactDTO.getIdNumber()) && StringUtils.isBlank(customerContactDTO.getContactName())) {
                    flag = flag && false;
                    msg.append("联系人的姓名和证件号码不能同时为空;");
                }
                res = res && flag && flags;
            }
            content.put("contacts", msg.toString());
        }
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }

    private static boolean checkContactsAdds(List<CustomerAddressDTO> address, StringBuilder msg) {
        if (address == null || address.size() == 0)
            return true;

        boolean res = true;
        for (CustomerAddressDTO customerAddressDTO : address) {
            String type = customerAddressDTO.getContactType();  //todo 缺少非空判断
            String addr = customerAddressDTO.getDetail();

            if (StringUtils.isBlank(addr)) {
                msg.append("地址不能为空;");
                res = false;
            } else if (type == null || !StringUtils.isNumeric(type)){
                msg.append("联系方式地址类型不能为空或者类型不正确");
                res = false;
            }
            else if(!validateCode(DicEnumConst.DicType.CONTACT_TYPE, type)) {
                msg.append("联系类型不存在;");
                res = false;
            }
        }
        return res;
    }

    /**
     *
     */
    private static boolean validateJobs(CustomerDTO customerDTO, JSONObject content) {
        boolean res = true;

        if (customerDTO.getJobs() != null) {
            StringBuilder msg = new StringBuilder();
            boolean flag = true;
            for (CustomerJobsDTO customerJobsDTO : customerDTO.getJobs()) {
                if(StringUtils.isBlank(customerJobsDTO.getOrgName())) {
                    flag = false;
                    msg.append("公司名称必填;");
                }else if(customerJobsDTO.getAddress() != null){
                String typeJob = customerJobsDTO.getAddress().get(0).getContactType();
                String detail = customerJobsDTO.getAddress().get(0).getDetail();
//                flag = checkContacts(customerJobsDTO.getContactWays(), msg);
                if (detail == null || detail.equals("")){
                    flag = false;
                    msg.append("联系方式不能为空");
                }else if (typeJob == null || !StringUtils.isNumeric(typeJob) || typeJob.equals("")){
                    flag = false;
                    msg.append("联系方式类型不能为空");
                }
                res = res && flag;
            }
            content.put("jobs", msg.toString());
            }
        }
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }

    /**
     *
     */
    private static boolean validateAddress(CustomerDTO customerDTO, JSONObject content) {
        boolean res = true;

        if(customerDTO.getAddress() != null) {
            StringBuilder msg = new StringBuilder();
            for(CustomerAddressDTO customerAddressDTO : customerDTO.getAddress()) {
                if(StringUtils.isBlank(customerAddressDTO.getContactType())) {
                    res = false;
                    msg.append("地址类型必填;");
                }
                content.put("address", msg.toString());
            }
        }
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }

    /**
     *
     */
    private static boolean validateCars(CustomerDTO customerDTO, JSONObject content) {
        boolean res = true;

        if(customerDTO.getCars() != null) {
            StringBuilder msg = new StringBuilder();
            for(CustomerCarsDTO customerCarsDTO : customerDTO.getCars()) {
                if(StringUtils.isBlank(customerCarsDTO.getAssetNo())) {
                    res = false;
                    msg.append("车辆号码必填;");
                }
            }
            content.put("cars", msg.toString());
        }
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }

    /**
     *
     */
    private static boolean validateEstate(CustomerDTO customerDTO, JSONObject content) {
        boolean res = true;

        if(customerDTO.getHouses() != null) {
            StringBuilder msg = new StringBuilder();
            for(CustomerHousesDTO customerHousesDTO : customerDTO.getHouses()) {
                if(StringUtils.isBlank(customerHousesDTO.getAssetNo())) {
                    res = false;
                    msg.append("房屋产权证号必填;");
                }
            }
            content.put("houses", msg.toString());
        }
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }

    /**
     *
     */
    private static boolean validateCards(CustomerDTO customerDTO, JSONObject content) {
        boolean res = true;

        if(customerDTO.getBankCards() != null) {
            StringBuilder msg = new StringBuilder();
            for(CustomerBankCardsDTO customerBankCardsDTO : customerDTO.getBankCards()) {
                if(StringUtils.isBlank(customerBankCardsDTO.getCardNo())) {
                    res = false;
                    msg.append("银行卡号必填;");
                }
            }
            content.put("bankCars", msg.toString());
        }
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }

    /**
     *
     */
    private static boolean validateEducation(CustomerDTO customerDTO, JSONObject content) {
        boolean res = true;

        if (customerDTO.getEducations() != null) {
            StringBuilder msg = new StringBuilder();
            boolean flag;
            for (CustomerEducationDTO customerEducationDTO : customerDTO.getEducations()) {
                flag = checkContacts(customerEducationDTO.getContactWays(), msg);
                if(StringUtils.isBlank(customerEducationDTO.getOrgName())) {
                    flag = false;
                    msg.append("学校名称必填;");
                }
                res = res && flag;
            }
            content.put("education", msg.toString());
        }
        Logger.info("参数校验返回结果:{}",content);
        return res;
    }

    /**
     * 校验具体联系方式
     *
     * @param contactWaysDTOList
     * @param stringBuilder
     * @return
     */
    private static boolean checkContacts(List<CustomerContactWaysDTO> contactWaysDTOList, StringBuilder stringBuilder) {
        if (contactWaysDTOList == null || contactWaysDTOList.size() == 0)
            return true;

        boolean res = true;
        for (CustomerContactWaysDTO customerContactWaysDTO : contactWaysDTOList) {
            String type = customerContactWaysDTO.getContactType();  //todo 缺少非空判断
            String number = customerContactWaysDTO.getContact();

            if (StringUtils.isBlank(number)) {
                stringBuilder.append("联系方式不能为空;");
                res = false;
            } else if (type == null || !StringUtils.isNumeric(type)){
                stringBuilder.append("联系方式类型不能为空或者类型不正确");
                res = false;
            }
            else if(!validateCode(DicEnumConst.DicType.CONTACT_TYPE, type)) {
                stringBuilder.append("联系类型不存在;");
                res = false;
            } else {
                //Map的key是字典表中的code，value是content,见initHashMap方法
                Map<String, Object> contacts = dictionaries.get(DicEnumConst.DicType.CONTACT_TYPE.name());
                if(contacts != null) {
                    JSONObject jsonObject = JSONObject.fromObject(contacts.get(type));
                    String regex = jsonObject.getString("regex");
                    if(StringUtils.isNotBlank(regex)) {
                        Matcher matcher = Pattern.compile(regex).matcher(customerContactWaysDTO.getContact());  //todo 正则表达式性能问题
                        if(!matcher.matches()) {
                            stringBuilder.append(jsonObject.getString("name") + "格式有误;");
                            res = false;
                        }
                    } else
                        Logger.warn("dic lack contents：type{},code{},content{}", DicEnumConst.DicType.CONTACT_TYPE.getCode(),
                                type, jsonObject);
                }
            }
        }
        return res;
    }
}
