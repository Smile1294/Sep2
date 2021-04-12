package mediator;

public class JsonTest {

    public static String getJsonRequest(){
        return "{" +
                "    \"Meta Data\": {" +
                "        \"1. Information\": \"Intraday (5min) open, high, low, close prices and volume\"," +
                "        \"2. Symbol\": \"IBM\"," +
                "        \"3. Last Refreshed\": \"2021-04-06 20:00:00\"," +
                "        \"4. Interval\": \"5min\"," +
                "        \"5. Output Size\": \"Compact\"," +
                "        \"6. Time Zone\": \"US/Eastern\"" +
                "    }," +
                "    \"Time Series (5min)\": {" +
                "        \"2021-04-06 20:00:00\": {" +
                "            \"1. open\": \"134.4100\"," +
                "            \"2. high\": \"134.4100\"," +
                "            \"3. low\": \"134.4100\"," +
                "            \"4. close\": \"134.4100\"," +
                "            \"5. volume\": \"185\"" +
                "        }," +
                "        \"2021-04-06 19:25:00\": {" +
                "            \"1. open\": \"134.4000\"," +
                "            \"2. high\": \"134.4000\"," +
                "            \"3. low\": \"134.4000\"," +
                "            \"4. close\": \"134.4000\"," +
                "            \"5. volume\": \"123\"" +
                "        }," +
                "        \"2021-04-06 19:20:00\": {" +
                "            \"1. open\": \"134.5900\"," +
                "            \"2. high\": \"134.5900\"," +
                "            \"3. low\": \"134.5900\"," +
                "            \"4. close\": \"134.5900\"," +
                "            \"5. volume\": \"500\"" +
                "        }," +
                "        \"2021-04-06 18:45:00\": {" +
                "            \"1. open\": \"134.5000\"," +
                "            \"2. high\": \"134.5000\"," +
                "            \"3. low\": \"134.5000\"," +
                "            \"4. close\": \"134.5000\"," +
                "            \"5. volume\": \"1393\"" +
                "        }," +
                "        \"2021-04-06 17:00:00\": {" +
                "            \"1. open\": \"134.5000\"," +
                "            \"2. high\": \"134.5000\"," +
                "            \"3. low\": \"134.5000\"," +
                "            \"4. close\": \"134.5000\"," +
                "            \"5. volume\": \"100\"" +
                "        }," +
                "        \"2021-04-06 16:50:00\": {" +
                "            \"1. open\": \"134.5000\"," +
                "            \"2. high\": \"134.5000\"," +
                "            \"3. low\": \"134.5000\"," +
                "            \"4. close\": \"134.5000\"," +
                "            \"5. volume\": \"107\"" +
                "        }," +
                "        \"2021-04-06 16:45:00\": {" +
                "            \"1. open\": \"134.3900\"," +
                "            \"2. high\": \"134.4900\"," +
                "            \"3. low\": \"134.3900\"," +
                "            \"4. close\": \"134.4900\"," +
                "            \"5. volume\": \"593\"" +
                "        }," +
                "        \"2021-04-06 16:15:00\": {" +
                "            \"1. open\": \"134.1200\"," +
                "            \"2. high\": \"134.1200\"," +
                "            \"3. low\": \"134.1100\"," +
                "            \"4. close\": \"134.1100\"," +
                "            \"5. volume\": \"351\"" +
                "        }," +
                "        \"2021-04-06 16:10:00\": {" +
                "            \"1. open\": \"134.4200\"," +
                "            \"2. high\": \"134.5200\"," +
                "            \"3. low\": \"134.2200\"," +
                "            \"4. close\": \"134.2200\"," +
                "            \"5. volume\": \"3184\"" +
                "        }," +
                "        \"2021-04-06 16:05:00\": {" +
                "            \"1. open\": \"134.2200\"," +
                "            \"2. high\": \"134.2200\"," +
                "            \"3. low\": \"134.2200\"," +
                "            \"4. close\": \"134.2200\"," +
                "            \"5. volume\": \"71548\"" +
                "        }," +
                "        \"2021-04-06 16:00:00\": {" +
                "            \"1. open\": \"134.1200\"," +
                "            \"2. high\": \"134.2400\"," +
                "            \"3. low\": \"134.0900\"," +
                "            \"4. close\": \"134.2300\"," +
                "            \"5. volume\": \"174017\"" +
                "        }," +
                "        \"2021-04-06 15:55:00\": {" +
                "            \"1. open\": \"134.1700\"," +
                "            \"2. high\": \"134.2300\"," +
                "            \"3. low\": \"134.1000\"," +
                "            \"4. close\": \"134.1150\"," +
                "            \"5. volume\": \"96030\"" +
                "        }," +
                "        \"2021-04-06 15:50:00\": {" +
                "            \"1. open\": \"134.1100\"," +
                "            \"2. high\": \"134.2000\"," +
                "            \"3. low\": \"134.1100\"," +
                "            \"4. close\": \"134.1650\"," +
                "            \"5. volume\": \"45895\"" +
                "        }," +
                "        \"2021-04-06 15:45:00\": {" +
                "            \"1. open\": \"134.1500\"," +
                "            \"2. high\": \"134.2250\"," +
                "            \"3. low\": \"134.0900\"," +
                "            \"4. close\": \"134.1000\"," +
                "            \"5. volume\": \"50833\"" +
                "        }," +
                "        \"2021-04-06 15:40:00\": {" +
                "            \"1. open\": \"134.1700\"," +
                "            \"2. high\": \"134.2200\"," +
                "            \"3. low\": \"134.1450\"," +
                "            \"4. close\": \"134.1600\"," +
                "            \"5. volume\": \"54069\"" +
                "        }," +
                "        \"2021-04-06 15:35:00\": {" +
                "            \"1. open\": \"134.3250\"," +
                "            \"2. high\": \"134.3600\"," +
                "            \"3. low\": \"134.1700\"," +
                "            \"4. close\": \"134.1700\"," +
                "            \"5. volume\": \"48096\"" +
                "        }," +
                "        \"2021-04-06 15:30:00\": {" +
                "            \"1. open\": \"134.2900\"," +
                "            \"2. high\": \"134.3500\"," +
                "            \"3. low\": \"134.2800\"," +
                "            \"4. close\": \"134.3300\"," +
                "            \"5. volume\": \"37260\"" +
                "        }," +
                "        \"2021-04-06 15:25:00\": {" +
                "            \"1. open\": \"134.3000\"," +
                "            \"2. high\": \"134.3500\"," +
                "            \"3. low\": \"134.2700\"," +
                "            \"4. close\": \"134.3000\"," +
                "            \"5. volume\": \"44687\"" +
                "        }," +
                "        \"2021-04-06 15:20:00\": {" +
                "            \"1. open\": \"134.2779\"," +
                "            \"2. high\": \"134.3200\"," +
                "            \"3. low\": \"134.2450\"," +
                "            \"4. close\": \"134.2950\"," +
                "            \"5. volume\": \"39172\"" +
                "        }," +
                "        \"2021-04-06 15:15:00\": {" +
                "            \"1. open\": \"134.2400\"," +
                "            \"2. high\": \"134.3100\"," +
                "            \"3. low\": \"134.1900\"," +
                "            \"4. close\": \"134.2800\"," +
                "            \"5. volume\": \"38684\"" +
                "        }," +
                "        \"2021-04-06 15:10:00\": {" +
                "            \"1. open\": \"134.1900\"," +
                "            \"2. high\": \"134.2800\"," +
                "            \"3. low\": \"134.1700\"," +
                "            \"4. close\": \"134.2400\"," +
                "            \"5. volume\": \"42224\"" +
                "        }," +
                "        \"2021-04-06 15:05:00\": {" +
                "            \"1. open\": \"134.1900\"," +
                "            \"2. high\": \"134.3000\"," +
                "            \"3. low\": \"134.1600\"," +
                "            \"4. close\": \"134.2000\"," +
                "            \"5. volume\": \"45876\"" +
                "        }," +
                "        \"2021-04-06 15:00:00\": {" +
                "            \"1. open\": \"134.2400\"," +
                "            \"2. high\": \"134.2800\"," +
                "            \"3. low\": \"134.1700\"," +
                "            \"4. close\": \"134.1932\"," +
                "            \"5. volume\": \"32179\"" +
                "        }," +
                "        \"2021-04-06 14:55:00\": {" +
                "            \"1. open\": \"134.3200\"," +
                "            \"2. high\": \"134.3200\"," +
                "            \"3. low\": \"134.2200\"," +
                "            \"4. close\": \"134.2300\"," +
                "            \"5. volume\": \"32428\"" +
                "        }," +
                "        \"2021-04-06 14:50:00\": {" +
                "            \"1. open\": \"134.2600\"," +
                "            \"2. high\": \"134.3700\"," +
                "            \"3. low\": \"134.2600\"," +
                "            \"4. close\": \"134.3300\"," +
                "            \"5. volume\": \"34307\"" +
                "        }," +
                "        \"2021-04-06 14:45:00\": {" +
                "            \"1. open\": \"134.1900\"," +
                "            \"2. high\": \"134.2800\"," +
                "            \"3. low\": \"134.1400\"," +
                "            \"4. close\": \"134.2800\"," +
                "            \"5. volume\": \"33826\"" +
                "        }," +
                "        \"2021-04-06 14:40:00\": {" +
                "            \"1. open\": \"134.2700\"," +
                "            \"2. high\": \"134.2958\"," +
                "            \"3. low\": \"134.1600\"," +
                "            \"4. close\": \"134.1900\"," +
                "            \"5. volume\": \"32898\"" +
                "        }," +
                "        \"2021-04-06 14:35:00\": {" +
                "            \"1. open\": \"134.2700\"," +
                "            \"2. high\": \"134.3150\"," +
                "            \"3. low\": \"134.2100\"," +
                "            \"4. close\": \"134.2800\"," +
                "            \"5. volume\": \"31444\"" +
                "        }," +
                "        \"2021-04-06 14:30:00\": {" +
                "            \"1. open\": \"134.3100\"," +
                "            \"2. high\": \"134.3500\"," +
                "            \"3. low\": \"134.2700\"," +
                "            \"4. close\": \"134.2800\"," +
                "            \"5. volume\": \"25709\"" +
                "        }," +
                "        \"2021-04-06 14:25:00\": {" +
                "            \"1. open\": \"134.3350\"," +
                "            \"2. high\": \"134.3700\"," +
                "            \"3. low\": \"134.2950\"," +
                "            \"4. close\": \"134.3200\"," +
                "            \"5. volume\": \"25005\"" +
                "        }," +
                "        \"2021-04-06 14:20:00\": {" +
                "            \"1. open\": \"134.4575\"," +
                "            \"2. high\": \"134.4600\"," +
                "            \"3. low\": \"134.3200\"," +
                "            \"4. close\": \"134.3350\"," +
                "            \"5. volume\": \"23979\"" +
                "        }," +
                "        \"2021-04-06 14:15:00\": {" +
                "            \"1. open\": \"134.3950\"," +
                "            \"2. high\": \"134.5099\"," +
                "            \"3. low\": \"134.3600\"," +
                "            \"4. close\": \"134.4600\"," +
                "            \"5. volume\": \"26544\"" +
                "        }," +
                "        \"2021-04-06 14:10:00\": {" +
                "            \"1. open\": \"134.3200\"," +
                "            \"2. high\": \"134.4000\"," +
                "            \"3. low\": \"134.2900\"," +
                "            \"4. close\": \"134.4000\"," +
                "            \"5. volume\": \"20326\"" +
                "        }," +
                "        \"2021-04-06 14:05:00\": {" +
                "            \"1. open\": \"134.3900\"," +
                "            \"2. high\": \"134.4300\"," +
                "            \"3. low\": \"134.3150\"," +
                "            \"4. close\": \"134.3400\"," +
                "            \"5. volume\": \"40907\"" +
                "        }," +
                "        \"2021-04-06 14:00:00\": {" +
                "            \"1. open\": \"134.2400\"," +
                "            \"2. high\": \"134.4800\"," +
                "            \"3. low\": \"134.2150\"," +
                "            \"4. close\": \"134.4034\"," +
                "            \"5. volume\": \"57447\"" +
                "        }," +
                "        \"2021-04-06 13:55:00\": {" +
                "            \"1. open\": \"134.2029\"," +
                "            \"2. high\": \"134.2600\"," +
                "            \"3. low\": \"134.1802\"," +
                "            \"4. close\": \"134.2500\"," +
                "            \"5. volume\": \"59912\"" +
                "        }," +
                "        \"2021-04-06 13:50:00\": {" +
                "            \"1. open\": \"134.2000\"," +
                "            \"2. high\": \"134.2600\"," +
                "            \"3. low\": \"134.1700\"," +
                "            \"4. close\": \"134.2050\"," +
                "            \"5. volume\": \"29903\"" +
                "        }," +
                "        \"2021-04-06 13:45:00\": {" +
                "            \"1. open\": \"134.2281\"," +
                "            \"2. high\": \"134.2300\"," +
                "            \"3. low\": \"134.1550\"," +
                "            \"4. close\": \"134.1850\"," +
                "            \"5. volume\": \"32502\"" +
                "        }," +
                "        \"2021-04-06 13:40:00\": {" +
                "            \"1. open\": \"134.2500\"," +
                "            \"2. high\": \"134.2500\"," +
                "            \"3. low\": \"134.1650\"," +
                "            \"4. close\": \"134.2200\"," +
                "            \"5. volume\": \"24708\"" +
                "        }," +
                "        \"2021-04-06 13:35:00\": {" +
                "            \"1. open\": \"134.2450\"," +
                "            \"2. high\": \"134.2600\"," +
                "            \"3. low\": \"134.1500\"," +
                "            \"4. close\": \"134.2300\"," +
                "            \"5. volume\": \"36053\"" +
                "        }," +
                "        \"2021-04-06 13:30:00\": {" +
                "            \"1. open\": \"134.2250\"," +
                "            \"2. high\": \"134.2900\"," +
                "            \"3. low\": \"134.1800\"," +
                "            \"4. close\": \"134.2450\"," +
                "            \"5. volume\": \"82773\"" +
                "        }," +
                "        \"2021-04-06 13:25:00\": {" +
                "            \"1. open\": \"134.3700\"," +
                "            \"2. high\": \"134.4200\"," +
                "            \"3. low\": \"134.2000\"," +
                "            \"4. close\": \"134.2200\"," +
                "            \"5. volume\": \"41999\"" +
                "        }," +
                "        \"2021-04-06 13:20:00\": {" +
                "            \"1. open\": \"134.3700\"," +
                "            \"2. high\": \"134.4219\"," +
                "            \"3. low\": \"134.3400\"," +
                "            \"4. close\": \"134.3600\"," +
                "            \"5. volume\": \"32325\"" +
                "        }," +
                "        \"2021-04-06 13:15:00\": {" +
                "            \"1. open\": \"134.4600\"," +
                "            \"2. high\": \"134.4600\"," +
                "            \"3. low\": \"134.3600\"," +
                "            \"4. close\": \"134.3600\"," +
                "            \"5. volume\": \"27042\"" +
                "        }," +
                "        \"2021-04-06 13:10:00\": {" +
                "            \"1. open\": \"134.5350\"," +
                "            \"2. high\": \"134.5427\"," +
                "            \"3. low\": \"134.4600\"," +
                "            \"4. close\": \"134.4700\"," +
                "            \"5. volume\": \"19721\"" +
                "        }," +
                "        \"2021-04-06 13:05:00\": {" +
                "            \"1. open\": \"134.5600\"," +
                "            \"2. high\": \"134.6000\"," +
                "            \"3. low\": \"134.5300\"," +
                "            \"4. close\": \"134.5300\"," +
                "            \"5. volume\": \"17406\"" +
                "        }," +
                "        \"2021-04-06 13:00:00\": {" +
                "            \"1. open\": \"134.5100\"," +
                "            \"2. high\": \"134.5800\"," +
                "            \"3. low\": \"134.4900\"," +
                "            \"4. close\": \"134.5500\"," +
                "            \"5. volume\": \"16087\"" +
                "        }," +
                "        \"2021-04-06 12:55:00\": {" +
                "            \"1. open\": \"134.5700\"," +
                "            \"2. high\": \"134.6100\"," +
                "            \"3. low\": \"134.4900\"," +
                "            \"4. close\": \"134.5100\"," +
                "            \"5. volume\": \"25839\"" +
                "        }," +
                "        \"2021-04-06 12:50:00\": {" +
                "            \"1. open\": \"134.5844\"," +
                "            \"2. high\": \"134.6100\"," +
                "            \"3. low\": \"134.5350\"," +
                "            \"4. close\": \"134.5650\"," +
                "            \"5. volume\": \"11517\"" +
                "        }," +
                "        \"2021-04-06 12:45:00\": {" +
                "            \"1. open\": \"134.5900\"," +
                "            \"2. high\": \"134.6800\"," +
                "            \"3. low\": \"134.5600\"," +
                "            \"4. close\": \"134.5803\"," +
                "            \"5. volume\": \"14936\"" +
                "        }," +
                "        \"2021-04-06 12:40:00\": {" +
                "            \"1. open\": \"134.7400\"," +
                "            \"2. high\": \"134.7400\"," +
                "            \"3. low\": \"134.5600\"," +
                "            \"4. close\": \"134.5900\"," +
                "            \"5. volume\": \"28836\"" +
                "        }," +
                "        \"2021-04-06 12:35:00\": {" +
                "            \"1. open\": \"134.8252\"," +
                "            \"2. high\": \"134.8300\"," +
                "            \"3. low\": \"134.7200\"," +
                "            \"4. close\": \"134.7500\"," +
                "            \"5. volume\": \"19700\"" +
                "        }," +
                "        \"2021-04-06 12:30:00\": {" +
                "            \"1. open\": \"134.7800\"," +
                "            \"2. high\": \"134.8600\"," +
                "            \"3. low\": \"134.7650\"," +
                "            \"4. close\": \"134.8175\"," +
                "            \"5. volume\": \"16452\"" +
                "        }," +
                "        \"2021-04-06 12:25:00\": {" +
                "            \"1. open\": \"134.7100\"," +
                "            \"2. high\": \"134.7776\"," +
                "            \"3. low\": \"134.6900\"," +
                "            \"4. close\": \"134.7776\"," +
                "            \"5. volume\": \"16812\"" +
                "        }," +
                "        \"2021-04-06 12:20:00\": {" +
                "            \"1. open\": \"134.6800\"," +
                "            \"2. high\": \"134.7800\"," +
                "            \"3. low\": \"134.6800\"," +
                "            \"4. close\": \"134.7170\"," +
                "            \"5. volume\": \"24364\"" +
                "        }," +
                "        \"2021-04-06 12:15:00\": {" +
                "            \"1. open\": \"134.6400\"," +
                "            \"2. high\": \"134.6500\"," +
                "            \"3. low\": \"134.5500\"," +
                "            \"4. close\": \"134.6400\"," +
                "            \"5. volume\": \"39409\"" +
                "        }," +
                "        \"2021-04-06 12:10:00\": {" +
                "            \"1. open\": \"134.7200\"," +
                "            \"2. high\": \"134.7450\"," +
                "            \"3. low\": \"134.6400\"," +
                "            \"4. close\": \"134.6600\"," +
                "            \"5. volume\": \"23065\"" +
                "        }," +
                "        \"2021-04-06 12:05:00\": {" +
                "            \"1. open\": \"134.7100\"," +
                "            \"2. high\": \"134.8000\"," +
                "            \"3. low\": \"134.6650\"," +
                "            \"4. close\": \"134.7100\"," +
                "            \"5. volume\": \"30356\"" +
                "        }," +
                "        \"2021-04-06 12:00:00\": {" +
                "            \"1. open\": \"134.5800\"," +
                "            \"2. high\": \"134.7500\"," +
                "            \"3. low\": \"134.5750\"," +
                "            \"4. close\": \"134.7100\"," +
                "            \"5. volume\": \"27703\"" +
                "        }," +
                "        \"2021-04-06 11:55:00\": {" +
                "            \"1. open\": \"134.6900\"," +
                "            \"2. high\": \"134.7100\"," +
                "            \"3. low\": \"134.5700\"," +
                "            \"4. close\": \"134.5900\"," +
                "            \"5. volume\": \"28017\"" +
                "        }," +
                "        \"2021-04-06 11:50:00\": {" +
                "            \"1. open\": \"134.6400\"," +
                "            \"2. high\": \"134.7450\"," +
                "            \"3. low\": \"134.6300\"," +
                "            \"4. close\": \"134.7100\"," +
                "            \"5. volume\": \"30156\"" +
                "        }," +
                "        \"2021-04-06 11:45:00\": {" +
                "            \"1. open\": \"134.6750\"," +
                "            \"2. high\": \"134.7200\"," +
                "            \"3. low\": \"134.6400\"," +
                "            \"4. close\": \"134.6400\"," +
                "            \"5. volume\": \"24907\"" +
                "        }," +
                "        \"2021-04-06 11:40:00\": {" +
                "            \"1. open\": \"134.7300\"," +
                "            \"2. high\": \"134.7500\"," +
                "            \"3. low\": \"134.6600\"," +
                "            \"4. close\": \"134.6800\"," +
                "            \"5. volume\": \"24337\"" +
                "        }," +
                "        \"2021-04-06 11:35:00\": {" +
                "            \"1. open\": \"134.7736\"," +
                "            \"2. high\": \"134.8300\"," +
                "            \"3. low\": \"134.6600\"," +
                "            \"4. close\": \"134.7200\"," +
                "            \"5. volume\": \"27512\"" +
                "        }," +
                "        \"2021-04-06 11:30:00\": {" +
                "            \"1. open\": \"134.5678\"," +
                "            \"2. high\": \"134.7700\"," +
                "            \"3. low\": \"134.5413\"," +
                "            \"4. close\": \"134.7700\"," +
                "            \"5. volume\": \"24089\"" +
                "        }," +
                "        \"2021-04-06 11:25:00\": {" +
                "            \"1. open\": \"134.5100\"," +
                "            \"2. high\": \"134.6500\"," +
                "            \"3. low\": \"134.4500\"," +
                "            \"4. close\": \"134.5400\"," +
                "            \"5. volume\": \"56990\"" +
                "        }," +
                "        \"2021-04-06 11:20:00\": {" +
                "            \"1. open\": \"134.4950\"," +
                "            \"2. high\": \"134.6800\"," +
                "            \"3. low\": \"134.4900\"," +
                "            \"4. close\": \"134.5100\"," +
                "            \"5. volume\": \"35463\"" +
                "        }," +
                "        \"2021-04-06 11:15:00\": {" +
                "            \"1. open\": \"134.6300\"," +
                "            \"2. high\": \"134.7100\"," +
                "            \"3. low\": \"134.5000\"," +
                "            \"4. close\": \"134.5001\"," +
                "            \"5. volume\": \"34681\"" +
                "        }," +
                "        \"2021-04-06 11:10:00\": {" +
                "            \"1. open\": \"134.6200\"," +
                "            \"2. high\": \"134.6700\"," +
                "            \"3. low\": \"134.5550\"," +
                "            \"4. close\": \"134.6250\"," +
                "            \"5. volume\": \"31092\"" +
                "        }," +
                "        \"2021-04-06 11:05:00\": {" +
                "            \"1. open\": \"134.6600\"," +
                "            \"2. high\": \"134.6700\"," +
                "            \"3. low\": \"134.5050\"," +
                "            \"4. close\": \"134.5900\"," +
                "            \"5. volume\": \"32962\"" +
                "        }," +
                "        \"2021-04-06 11:00:00\": {" +
                "            \"1. open\": \"134.7200\"," +
                "            \"2. high\": \"134.7900\"," +
                "            \"3. low\": \"134.6738\"," +
                "            \"4. close\": \"134.6738\"," +
                "            \"5. volume\": \"25952\"" +
                "        }," +
                "        \"2021-04-06 10:55:00\": {" +
                "            \"1. open\": \"134.4300\"," +
                "            \"2. high\": \"134.7520\"," +
                "            \"3. low\": \"134.4200\"," +
                "            \"4. close\": \"134.7100\"," +
                "            \"5. volume\": \"24861\"" +
                "        }," +
                "        \"2021-04-06 10:50:00\": {" +
                "            \"1. open\": \"134.5000\"," +
                "            \"2. high\": \"134.5700\"," +
                "            \"3. low\": \"134.3700\"," +
                "            \"4. close\": \"134.4250\"," +
                "            \"5. volume\": \"134470\"" +
                "        }," +
                "        \"2021-04-06 10:45:00\": {" +
                "            \"1. open\": \"134.5800\"," +
                "            \"2. high\": \"134.7190\"," +
                "            \"3. low\": \"134.4300\"," +
                "            \"4. close\": \"134.4900\"," +
                "            \"5. volume\": \"54249\"" +
                "        }," +
                "        \"2021-04-06 10:40:00\": {" +
                "            \"1. open\": \"134.6800\"," +
                "            \"2. high\": \"134.6800\"," +
                "            \"3. low\": \"134.5300\"," +
                "            \"4. close\": \"134.5820\"," +
                "            \"5. volume\": \"29226\"" +
                "        }," +
                "        \"2021-04-06 10:35:00\": {" +
                "            \"1. open\": \"134.6500\"," +
                "            \"2. high\": \"134.7400\"," +
                "            \"3. low\": \"134.5900\"," +
                "            \"4. close\": \"134.7031\"," +
                "            \"5. volume\": \"34906\"" +
                "        }," +
                "        \"2021-04-06 10:30:00\": {" +
                "            \"1. open\": \"134.7600\"," +
                "            \"2. high\": \"134.8900\"," +
                "            \"3. low\": \"134.6500\"," +
                "            \"4. close\": \"134.6500\"," +
                "            \"5. volume\": \"34389\"" +
                "        }," +
                "        \"2021-04-06 10:25:00\": {" +
                "            \"1. open\": \"134.8900\"," +
                "            \"2. high\": \"134.9396\"," +
                "            \"3. low\": \"134.7400\"," +
                "            \"4. close\": \"134.7400\"," +
                "            \"5. volume\": \"34417\"" +
                "        }," +
                "        \"2021-04-06 10:20:00\": {" +
                "            \"1. open\": \"134.9050\"," +
                "            \"2. high\": \"134.9490\"," +
                "            \"3. low\": \"134.7600\"," +
                "            \"4. close\": \"134.9200\"," +
                "            \"5. volume\": \"33191\"" +
                "        }," +
                "        \"2021-04-06 10:15:00\": {" +
                "            \"1. open\": \"134.8200\"," +
                "            \"2. high\": \"134.9900\"," +
                "            \"3. low\": \"134.8100\"," +
                "            \"4. close\": \"134.8800\"," +
                "            \"5. volume\": \"29036\"" +
                "        }," +
                "        \"2021-04-06 10:10:00\": {" +
                "            \"1. open\": \"134.9500\"," +
                "            \"2. high\": \"135.0599\"," +
                "            \"3. low\": \"134.7300\"," +
                "            \"4. close\": \"134.8000\"," +
                "            \"5. volume\": \"32688\"" +
                "        }," +
                "        \"2021-04-06 10:05:00\": {" +
                "            \"1. open\": \"134.7700\"," +
                "            \"2. high\": \"134.9700\"," +
                "            \"3. low\": \"134.7700\"," +
                "            \"4. close\": \"134.9400\"," +
                "            \"5. volume\": \"36506\"" +
                "        }," +
                "        \"2021-04-06 10:00:00\": {" +
                "            \"1. open\": \"134.6900\"," +
                "            \"2. high\": \"134.9200\"," +
                "            \"3. low\": \"134.6357\"," +
                "            \"4. close\": \"134.7792\"," +
                "            \"5. volume\": \"41183\"" +
                "        }," +
                "        \"2021-04-06 09:55:00\": {" +
                "            \"1. open\": \"134.7800\"," +
                "            \"2. high\": \"134.9800\"," +
                "            \"3. low\": \"134.6800\"," +
                "            \"4. close\": \"134.6800\"," +
                "            \"5. volume\": \"42306\"" +
                "        }," +
                "        \"2021-04-06 09:50:00\": {" +
                "            \"1. open\": \"134.7300\"," +
                "            \"2. high\": \"134.9450\"," +
                "            \"3. low\": \"134.6850\"," +
                "            \"4. close\": \"134.8100\"," +
                "            \"5. volume\": \"51223\"" +
                "        }," +
                "        \"2021-04-06 09:45:00\": {" +
                "            \"1. open\": \"135.1500\"," +
                "            \"2. high\": \"135.1750\"," +
                "            \"3. low\": \"134.5900\"," +
                "            \"4. close\": \"134.7300\"," +
                "            \"5. volume\": \"52931\"" +
                "        }," +
                "        \"2021-04-06 09:40:00\": {" +
                "            \"1. open\": \"135.1200\"," +
                "            \"2. high\": \"135.2592\"," +
                "            \"3. low\": \"135.0300\"," +
                "            \"4. close\": \"135.1127\"," +
                "            \"5. volume\": \"62064\"" +
                "        }," +
                "        \"2021-04-06 09:35:00\": {" +
                "            \"1. open\": \"135.5800\"," +
                "            \"2. high\": \"135.6400\"," +
                "            \"3. low\": \"134.9000\"," +
                "            \"4. close\": \"135.2500\"," +
                "            \"5. volume\": \"227988\"" +
                "        }," +
                "        \"2021-04-06 09:30:00\": {" +
                "            \"1. open\": \"135.5500\"," +
                "            \"2. high\": \"135.5600\"," +
                "            \"3. low\": \"135.2600\"," +
                "            \"4. close\": \"135.2800\"," +
                "            \"5. volume\": \"1706\"" +
                "        }," +
                "        \"2021-04-06 09:25:00\": {" +
                "            \"1. open\": \"135.5400\"," +
                "            \"2. high\": \"135.5400\"," +
                "            \"3. low\": \"135.5300\"," +
                "            \"4. close\": \"135.5300\"," +
                "            \"5. volume\": \"609\"" +
                "        }," +
                "        \"2021-04-06 08:50:00\": {" +
                "            \"1. open\": \"135.6600\"," +
                "            \"2. high\": \"135.6600\"," +
                "            \"3. low\": \"135.6600\"," +
                "            \"4. close\": \"135.6600\"," +
                "            \"5. volume\": \"319\"" +
                "        }," +
                "        \"2021-04-06 08:45:00\": {" +
                "            \"1. open\": \"135.6300\"," +
                "            \"2. high\": \"135.6300\"," +
                "            \"3. low\": \"135.4907\"," +
                "            \"4. close\": \"135.4907\"," +
                "            \"5. volume\": \"942\"" +
                "        }," +
                "        \"2021-04-06 08:40:00\": {" +
                "            \"1. open\": \"135.6400\"," +
                "            \"2. high\": \"135.6400\"," +
                "            \"3. low\": \"135.6400\"," +
                "            \"4. close\": \"135.6400\"," +
                "            \"5. volume\": \"352\"" +
                "        }," +
                "        \"2021-04-06 08:35:00\": {" +
                "            \"1. open\": \"135.6500\"," +
                "            \"2. high\": \"135.6500\"," +
                "            \"3. low\": \"135.6500\"," +
                "            \"4. close\": \"135.6500\"," +
                "            \"5. volume\": \"402\"" +
                "        }," +
                "        \"2021-04-06 08:30:00\": {" +
                "            \"1. open\": \"135.5400\"," +
                "            \"2. high\": \"135.5400\"," +
                "            \"3. low\": \"135.5400\"," +
                "            \"4. close\": \"135.5400\"," +
                "            \"5. volume\": \"1419\"" +
                "        }," +
                "        \"2021-04-06 08:10:00\": {" +
                "            \"1. open\": \"135.6799\"," +
                "            \"2. high\": \"135.6799\"," +
                "            \"3. low\": \"135.6799\"," +
                "            \"4. close\": \"135.6799\"," +
                "            \"5. volume\": \"300\"" +
                "        }," +
                "        \"2021-04-06 07:30:00\": {" +
                "            \"1. open\": \"135.6000\"," +
                "            \"2. high\": \"135.6000\"," +
                "            \"3. low\": \"135.6000\"," +
                "            \"4. close\": \"135.6000\"," +
                "            \"5. volume\": \"100\"" +
                "        }," +
                "        \"2021-04-06 07:20:00\": {" +
                "            \"1. open\": \"135.4100\"," +
                "            \"2. high\": \"135.4100\"," +
                "            \"3. low\": \"135.4100\"," +
                "            \"4. close\": \"135.4100\"," +
                "            \"5. volume\": \"298\"" +
                "        }," +
                "        \"2021-04-06 07:15:00\": {" +
                "            \"1. open\": \"135.6500\"," +
                "            \"2. high\": \"135.6500\"," +
                "            \"3. low\": \"135.6500\"," +
                "            \"4. close\": \"135.6500\"," +
                "            \"5. volume\": \"262\"" +
                "        }," +
                "        \"2021-04-06 07:05:00\": {" +
                "            \"1. open\": \"135.7400\"," +
                "            \"2. high\": \"135.9000\"," +
                "            \"3. low\": \"135.7400\"," +
                "            \"4. close\": \"135.9000\"," +
                "            \"5. volume\": \"1500\"" +
                "        }" +
                "    }" +
                "}";
    }
}