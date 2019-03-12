package kz.timur;

import kz.timur.Entity.Currency;
import kz.timur.Service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    CurrencyService currencyService;

    @Scheduled(fixedRate = 1000*60*60)
    public void requestDataFromExternalSource(){
        currencyService.WriteUSDtoDB(new Currency());
        currencyService.WriteEUROtoDB(new Currency());

    }
}
