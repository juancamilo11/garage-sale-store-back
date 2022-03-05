package co.edu.udea.ayds2.dto.store.product;

import lombok.Getter;

@Getter
public enum EnumProductStatusDto {
    STATUS_1("Regular"),
    STATUS_2("Bueno"),
    STATUS_3("Muy bueno"),
    STATUS_4("Perfecto estado"),
    STATUS_5("Nuevo (Sin uso)");

    private final String statusValue;

    EnumProductStatusDto(String statusValue) {
        this.statusValue = statusValue;
    }

}
