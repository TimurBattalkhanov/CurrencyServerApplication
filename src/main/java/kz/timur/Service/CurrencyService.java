package kz.timur.Service;

import kz.timur.Dao.MySqlCurrencyImpl;
import kz.timur.Entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Collection;

@Service
public class CurrencyService {

    @Autowired
    private MySqlCurrencyImpl mySqlCurrency;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Currency> readLastRecordfromUsdDB() {
        return mySqlCurrency.readLastRecordfromUsdDB();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Currency> readLastRecordfromEuroDB() {
        return mySqlCurrency.readLastRecordfromEuroDB();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void WriteUSDtoDB(Currency currency) {
        mySqlCurrency.WriteUSDtoDB(currency);
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void WriteEUROtoDB(Currency currency) {
        mySqlCurrency.WriteEUROtoDB(currency);
    }
}
