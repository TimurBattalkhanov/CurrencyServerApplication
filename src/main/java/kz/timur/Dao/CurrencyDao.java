package kz.timur.Dao;

import kz.timur.Entity.Currency;

import java.util.Collection;

public interface CurrencyDao {

    void WriteUSDtoDB(Currency currency);

    void WriteEUROtoDB(Currency currency);

    Collection<Currency> readLastRecordfromUsdDB();

    Collection<Currency> readLastRecordfromEuroDB();
}
