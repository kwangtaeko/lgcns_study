package com.example.myproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.text.NumberFormat;
import java.util.Locale;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer price;

    @Column
    private Double rate;

    @Column
    private Integer volume;

    @Column
    private Integer market_cap;

    @Column
    private String sector;

    @Transient
    private String colorClass;

    public void setRate(Double rate) {
        this.rate = rate;
        if (rate != null) this.colorClass = rate >= 0 ? "text-primary" : "text-danger";
        else this.colorClass = "text-muted";
    }

    public String getColorClass() {
        if (colorClass == null && rate != null) this.colorClass = rate >= 0 ? "text-primary" : "text-danger";

        return colorClass;
    }

    @Transient
    public String getFormattedPrice() {
        if (price == null) return "-";
        return NumberFormat.getNumberInstance(Locale.KOREA).format(price);
    }

    @Transient
    public String getFormattedMarketCap() {
        if (market_cap == null) return "-";
        return NumberFormat.getNumberInstance(Locale.KOREA).format(market_cap);
    }

    @Transient
    public String getFormattedVolume() {
        if (volume == null) return "-";
        return NumberFormat.getNumberInstance(Locale.KOREA).format(volume);
    }

    @Transient
    private Boolean isRise;

    public Boolean getIsRise() {
        return rate != null && rate >= 0;
    }

    public void patch(Stock stockEntity) {
        if (stockEntity.code != null) this.code = stockEntity.code;
        if (stockEntity.name != null) this.name = stockEntity.name;
        if (stockEntity.price != null) this.price = stockEntity.price;
        if (stockEntity.rate != null) setRate(stockEntity.rate);
        if (stockEntity.volume != null) this.volume = stockEntity.volume;
        if (stockEntity.market_cap != null) this.market_cap = stockEntity.market_cap;
        if (stockEntity.sector != null) this.sector = stockEntity.sector;
    }
}
