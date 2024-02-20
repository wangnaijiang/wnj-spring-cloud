package com.wnj.mybatis;

import org.springframework.stereotype.Component;
import com.wnj.util.DateUtil;

import java.util.Date;

@Component
public class IdGenerator {
    public String id32(TableEnum tableEnum){
        String formatTightTime = DateUtil.formatTightTime(new Date());
        String tableNo;
        if(tableEnum == TableEnum.USER){
            tableNo = "00001";
        }else{
            tableNo = "00002";
        }
        return formatTightTime + tableNo + System.currentTimeMillis();
    }
    public String id32(String tableCode){
        return id32(TableEnum.codeOf(tableCode));
    }
}
