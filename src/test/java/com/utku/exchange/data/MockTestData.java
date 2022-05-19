package com.utku.exchange.data;

import com.utku.exchange.data.entity.ExchangeHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Utku APAYDIN
 * @created 18/05/2022 - 19:08
 */

public class MockTestData {

    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public static Map<Date,Integer> getExchangeHistoryDateCountMap() throws ParseException {
        Map<Date,Integer> data = new HashMap<>();
        data.put(formatter.parse("2022-01-10"),4);
        data.put(formatter.parse("2022-02-15"),3);
        data.put(formatter.parse("2022-03-20"),2);
        data.put(formatter.parse("2022-04-10"),1);
        data.put(formatter.parse("2022-05-11"),0);
        return data;
    }
    public static List<ExchangeHistory> getExchangeHistoryData() throws ParseException {
        List<ExchangeHistory> data = new ArrayList<>();
        data.add(new ExchangeHistory("1","transaction-1","JEP","AMD", 1.5,100.1, formatter.parse("2022-01-10")));
        data.add(new ExchangeHistory("2","transaction-2","BRL","BDT",100.2,2.5,  formatter.parse("2022-02-15")));
        data.add(new ExchangeHistory("3","transaction-3","GTQ","BMD",100.3,1.1,  formatter.parse("2022-03-20")));
        data.add(new ExchangeHistory("4","transaction-4","GTQ","CVE",100.4,5.0,  formatter.parse("2022-04-10")));
        data.add(new ExchangeHistory("5","transaction-5","JEP","BMD",100.5,1.2,  formatter.parse("2022-05-11")));
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

    public static Map<String,Double> getRateForSymbol(String symbol){
        return getRates().get(symbol);
    }
    public static  Map<String, Map<String,Double>> getRates(){
        Map<String, Map<String,Double>> rateData = new HashMap<>();

        Map<String,Double> AEDrate = new HashMap<>();
        AEDrate.put("AFN",24.774983);
        AEDrate.put("ALL",31.185907);
        AEDrate.put("AMD",124.553271);
        AEDrate.put("ANG",0.490728);
        AEDrate.put("AOA",112.099682);
        AEDrate.put("AED",1.0);
        rateData.put("AED",AEDrate);

        Map<String,Double> AFNrate = new HashMap<>();
        AFNrate.put("AED",0.040363);
        AFNrate.put("ALL",1.258764);
        AFNrate.put("AMD",5.027368);
        AFNrate.put("ANG",0.019807);
        AFNrate.put("AOA",4.524705);
        AFNrate.put("AFN",1.0);
        rateData.put("AFN",AFNrate);

        Map<String,Double> ALLrate = new HashMap<>();
        ALLrate.put("AED",0.032065);
        ALLrate.put("AFN",0.79443);
        ALLrate.put("AMD",3.993893);
        ALLrate.put("ANG",0.015736);
        ALLrate.put("AOA",3.594562);
        ALLrate.put("ALL",1.0);
        rateData.put("ALL",ALLrate);


        Map<String,Double> AMDrate = new HashMap<>();
        AMDrate.put("AED",0.008029);
        AMDrate.put("AFN",0.198912);
        AMDrate.put("ALL",0.250382);
        AMDrate.put("ANG",0.00394);
        AMDrate.put("AOA",0.900014);
        AMDrate.put("AMD",1.0);
        rateData.put("AMD",AMDrate);

        Map<String,Double> ANGrate = new HashMap<>();
        ANGrate.put("AED", 2.037759);
        ANGrate.put("AFN", 50.486199);
        ANGrate.put("ALL", 63.414802);
        ANGrate.put("AMD", 253.813137);
        ANGrate.put("AOA", 228.43548);
        ANGrate.put("ANG",1.0);
        rateData.put("ANG",ANGrate);

        Map<String,Double> AOArate = new HashMap<>();
        AOArate.put("AED", 0.008921);
        AOArate.put("AFN", 0.22101);
        AOArate.put("ALL", 0.277616);
        AOArate.put("AMD", 1.111094);
        AOArate.put("ANG", 0.004378);
        AOArate.put("AOA",1.0);
        rateData.put("AOA",AOArate);

        return rateData;
    }
}
