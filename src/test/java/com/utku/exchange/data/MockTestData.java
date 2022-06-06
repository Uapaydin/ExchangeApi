package com.utku.exchange.data;

import com.utku.exchange.data.entity.ExchangeHistory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Utku APAYDIN
 * @created 18/05/2022 - 19:08
 */

public class MockTestData {


    public static final SimpleDateFormat FORMATTER;

    static{
        FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
        FORMATTER.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static Map<Date,Integer> getExchangeHistoryDateCountMap() throws ParseException {
        Map<Date,Integer> data = new HashMap<>();
        data.put(FORMATTER.parse("2022-01-10"),4);
        data.put(FORMATTER.parse("2022-02-15"),3);
        data.put(FORMATTER.parse("2022-03-20"),2);
        data.put(FORMATTER.parse("2022-04-10"),1);
        data.put(FORMATTER.parse("2022-05-11"),0);
        return data;
    }
    public static List<ExchangeHistory> getExchangeHistoryData() throws ParseException {
        List<ExchangeHistory> data = new ArrayList<>();
        data.add(new ExchangeHistory("1","transaction-1","JEP","AMD", BigDecimal.valueOf(1.5), BigDecimal.valueOf(100.1), FORMATTER.parse("2022-01-10")));
        data.add(new ExchangeHistory("2","transaction-2","BRL","BDT", BigDecimal.valueOf(100.2), BigDecimal.valueOf(2.5),  FORMATTER.parse("2022-02-15")));
        data.add(new ExchangeHistory("3","transaction-3","GTQ","BMD", BigDecimal.valueOf(100.3), BigDecimal.valueOf(1.1),  FORMATTER.parse("2022-03-20")));
        data.add(new ExchangeHistory("4","transaction-4","GTQ","CVE", BigDecimal.valueOf(100.4), BigDecimal.valueOf(5.0),  FORMATTER.parse("2022-04-10")));
        data.add(new ExchangeHistory("5","transaction-5","JEP","BMD", BigDecimal.valueOf(100.5), BigDecimal.valueOf(1.2),  FORMATTER.parse("2022-05-11")));
        return data;
    }

    public static Map<String,String> getSymbolListData(){
        Map<String,String> data = new HashMap<>();
        data.put("AED","United Arab Emirates Dirham");
        data.put("AFN","Afghan Afghani");
        data.put("ALL","Albanian Lek");
        data.put("AMD","Armenian Dram");
        data.put("ANG","Netherlands Antillean Guilder");
        data.put("AOA","Angolan Kwanza");
        return data;
    }

    public static Map<String, BigDecimal> getRateForSymbol(String symbol){
        return getRates().get(symbol);
    }
    public static  Map<String, Map<String,BigDecimal>> getRates(){
        Map<String, Map<String,BigDecimal>> rateData = new HashMap<>();

        Map<String,BigDecimal> AEDrate = new HashMap<>();
        AEDrate.put("AFN", BigDecimal.valueOf(24.774983));
        AEDrate.put("ALL", BigDecimal.valueOf(31.185907));
        AEDrate.put("AMD", BigDecimal.valueOf(124.553271));
        AEDrate.put("ANG", BigDecimal.valueOf(0.490728));
        AEDrate.put("AOA", BigDecimal.valueOf(112.099682));
        AEDrate.put("AED", BigDecimal.valueOf(1.0));
        rateData.put("AED",AEDrate);

        Map<String,BigDecimal> AFNrate = new HashMap<>();
        AFNrate.put("AED", BigDecimal.valueOf(0.040363));
        AFNrate.put("ALL", BigDecimal.valueOf(1.258764));
        AFNrate.put("AMD", BigDecimal.valueOf(5.027368));
        AFNrate.put("ANG", BigDecimal.valueOf(0.019807));
        AFNrate.put("AOA", BigDecimal.valueOf(4.524705));
        AFNrate.put("AFN", BigDecimal.valueOf(1.0));
        rateData.put("AFN",AFNrate);

        Map<String,BigDecimal> ALLrate = new HashMap<>();
        ALLrate.put("AED", BigDecimal.valueOf(0.032065));
        ALLrate.put("AFN", BigDecimal.valueOf(0.79443));
        ALLrate.put("AMD", BigDecimal.valueOf(3.993893));
        ALLrate.put("ANG", BigDecimal.valueOf(0.015736));
        ALLrate.put("AOA", BigDecimal.valueOf(3.594562));
        ALLrate.put("ALL", BigDecimal.valueOf(1.0));
        rateData.put("ALL",ALLrate);


        Map<String,BigDecimal> AMDrate = new HashMap<>();
        AMDrate.put("AED", BigDecimal.valueOf(0.008029));
        AMDrate.put("AFN", BigDecimal.valueOf(0.198912));
        AMDrate.put("ALL", BigDecimal.valueOf(0.250382));
        AMDrate.put("ANG", BigDecimal.valueOf(0.00394));
        AMDrate.put("AOA", BigDecimal.valueOf(0.900014));
        AMDrate.put("AMD", BigDecimal.valueOf(1.0));
        rateData.put("AMD",AMDrate);

        Map<String,BigDecimal> ANGrate = new HashMap<>();
        ANGrate.put("AED", BigDecimal.valueOf(2.037759));
        ANGrate.put("AFN", BigDecimal.valueOf(50.486199));
        ANGrate.put("ALL", BigDecimal.valueOf(63.414802));
        ANGrate.put("AMD", BigDecimal.valueOf(253.813137));
        ANGrate.put("AOA", BigDecimal.valueOf(228.43548));
        ANGrate.put("ANG", BigDecimal.valueOf(1.0));
        rateData.put("ANG",ANGrate);

        Map<String,BigDecimal> AOArate = new HashMap<>();
        AOArate.put("AED", BigDecimal.valueOf(0.008921));
        AOArate.put("AFN", BigDecimal.valueOf(0.22101));
        AOArate.put("ALL", BigDecimal.valueOf(0.277616));
        AOArate.put("AMD", BigDecimal.valueOf(1.111094));
        AOArate.put("ANG", BigDecimal.valueOf(0.004378));
        AOArate.put("AOA", BigDecimal.valueOf(1.0));
        rateData.put("AOA",AOArate);

        return rateData;
    }
}
