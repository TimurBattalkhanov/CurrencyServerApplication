package kz.timur.Controller;

import kz.timur.Entity.Currency;
import kz.timur.Service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @RequestMapping(value = "/usd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void WriteUSDtoDB(Currency currency) {
        currencyService.WriteUSDtoDB(currency);
    }

    @RequestMapping(value = "/euro", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void WriteEUROtoDB(Currency currency) {
        currencyService.WriteEUROtoDB(currency);
    }
}
