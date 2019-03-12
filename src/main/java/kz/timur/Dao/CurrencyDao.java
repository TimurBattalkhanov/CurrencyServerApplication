package kz.timur.Dao;

import kz.timur.Entity.Currency;

public interface CurrencyDao {

    void WriteUSDtoDB(Currency currency);

    void WriteEUROtoDB(Currency currency);
}
