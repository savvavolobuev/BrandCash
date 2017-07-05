package com.brandcash.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SuperscriptSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by savva.volobuev on 07.06.2017.
 */

public class PriceView extends TextView {
    private Currency currency;
    private String fallbackCurrency;
    private float price;
    private float lower;
    private float upper;

    public enum Currency{
        RUB("\u00c6", false),
        KZT("\u20b8", false),
        GEL("GEL", false),
        BYR("р.", false),
        EUR("\u20ac", true),
        USD("$", true),
        CZK("Kč", false),
        PLN("zł", false),
        HUF("Ft", false);

        private String character;
        private boolean toLeft;

        Currency(String character, boolean toLeft) {
            this.character = character;
            this.toLeft = toLeft;
        }

        public String getCharacter() {
            return character;
        }

        public boolean isToLeft() {
            return toLeft;
        }
    }

    public PriceView(Context context) {
        super(context);
        init();
    }

    public PriceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PriceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            setTypeface(FontUtils.getRobotoCondensed());
            setCurrency("RUB");
        }
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        try {
            if (TextUtils.isEmpty(currency)) {
                throw new IllegalArgumentException("Empty currency");
            }
            this.currency = Currency.valueOf(currency);
        } catch (IllegalArgumentException e) {
            this.currency = null;
            fallbackCurrency = currency;
        }
        updatePrice();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        this.lower = this.upper = 0.0f;
        updatePrice();
    }

    public void setPrice(int price) {
        setPrice((float) price);
    }

    public float getLower() {
        return lower;
    }

    public void setLower(float lower) {
        this.lower = lower;
        updatePrice();
    }

    public float getUpper() {
        return upper;
    }

    public void setUpper(float upper) {
        this.upper = upper;
        updatePrice();
    }

    public void setLimits(float lower, float upper) {
        if (Math.abs(upper - lower) < 0.001) {
            this.lower = this.upper = 0.0f;
            setPrice(lower);
        } else {
            this.lower = lower;
            this.upper = upper;
            updatePrice();
        }
    }

    private String getCurrencyCharacter() {
        if (currency != null) {
            return currency.character;
        }
        return TextUtils.isEmpty(fallbackCurrency) ? "" : fallbackCurrency;
    }

    private void updatePrice() {
        SpannableStringBuilder priceRange = new SpannableStringBuilder();

        if (upper > lower && upper > 0) {
            priceRange.append(formatLongNumber(lower)).append(" - ").append(formatLongNumber(upper));
        } else {
            priceRange.append(formatLongNumber(price));
        }

        if (currency != null && currency.isToLeft()) {
            priceRange.insert(0, currency.character);
        } else {
            priceRange.append(" ").append(getCurrencyCharacter());
        }

        setText(priceRange);
    }

    private CharSequence formatLongNumber(float price) {
        DecimalFormat formatter = new DecimalFormat("###,###.##");
        DecimalFormatSymbols symbolsFormatter = formatter.getDecimalFormatSymbols();
        symbolsFormatter.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbolsFormatter);
        if (price > 999999) {
            String formatted = formatter.format(price);
            DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
            SpannableString spannableString = new SpannableString(formatted);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                    0,spannableString.length(), 0);
            spannableString.setSpan(new RelativeSizeSpan(0.6f), formatted.lastIndexOf(symbols.getGroupingSeparator()), formatted.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new SuperscriptSpan(), formatted.lastIndexOf(symbols.getGroupingSeparator()), formatted.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            return spannableString;
        } else {
            String formatted = formatter.format(price);
            SpannableString spannableString = new SpannableString(formatted);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                    0,spannableString.length(), 0);
            return spannableString;
        }
    }
}
