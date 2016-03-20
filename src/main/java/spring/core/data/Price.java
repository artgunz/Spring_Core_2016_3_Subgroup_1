package spring.core.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Embeddable
@Access(AccessType.FIELD)
public class Price {
    @Enumerated(value = EnumType.STRING)
    @Column(name = "price_currency")
    Currency currency;
    @Column(name = "price_value")
    Double value;

    public Price() {
    }

    public Price(final Currency currency, final Double value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("currency", currency)
                .append("value", value)
                .toString();
    }
}
