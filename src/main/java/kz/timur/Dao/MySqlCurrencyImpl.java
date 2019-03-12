package kz.timur.Dao;

import kz.timur.Entity.Currency;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class MySqlCurrencyImpl implements CurrencyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static ResourceBundle bundle = ResourceBundle.getBundle("botconfig");
    private final static String USD_KZT_URL = bundle.getString("USD_Currency_URL");
    private final static String EURO_KZT_URL = bundle.getString("EURO_Currency_URL");

    public void getCurrencyFromUrl(URL url, Currency currency) throws IOException {
        //Читаем данные JSON
        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";

        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }
        JSONObject object = new JSONObject(result);
        currency.setDate(object.getLong("timestamp"));
        JSONObject rates = object.getJSONObject("rates");
        currency.setValue(rates.getDouble("KZT"));
    }

    private static class CurrencyRowMapper implements RowMapper<Currency> {

        @Override
        public Currency mapRow(ResultSet resultSet, int i) throws SQLException {
            Currency currency = new Currency();
            currency.setId(resultSet.getInt("id"));
            currency.setDate(resultSet.getLong("timestamp"));
            currency.setValue(resultSet.getDouble("value"));
            return currency;
        }
    }

    @Override
    public void WriteUSDtoDB(Currency currency) {
        try {
            getCurrencyFromUrl(new URL(USD_KZT_URL), currency);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        final String sql = "INSERT INTO currencyDB.currencyUSD (timestamp, value) VALUES (? , ?)";
        long timestamp = currency.getDate();
        double value = currency.getValue();
        jdbcTemplate.update(sql, timestamp, value);
    }

    @Override
    public void WriteEUROtoDB(Currency currency) {
        try {
            getCurrencyFromUrl(new URL(EURO_KZT_URL), currency);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        final String sql = "INSERT INTO currencyDB.currencyEURO (timestamp, value) VALUES (? , ?)";
        final long timestamp = currency.getDate();
        final double value = currency.getValue();
        jdbcTemplate.update(sql, timestamp, value);
    }

    @Override
    public Collection<Currency> readLastRecordfromUsdDB() {
        final String sql = "SELECT id, timestamp, value FROM currencyDB.currencyUSD";
        List<Currency> currencies = jdbcTemplate.query(sql, new CurrencyRowMapper());
        return currencies;
    }

    @Override
    public Collection<Currency> readLastRecordfromEuroDB() {
        final String sql = "SELECT id, timestamp, value FROM currencyDB.currencyEURO";
        List<Currency> currencies = jdbcTemplate.query(sql, new CurrencyRowMapper());
        return currencies;
    }
}
