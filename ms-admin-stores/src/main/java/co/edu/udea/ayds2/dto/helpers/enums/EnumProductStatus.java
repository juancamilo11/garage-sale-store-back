package co.edu.udea.ayds2.dto.helpers.enums;

import lombok.Getter;

@Getter
public enum EnumProductStatus {
    STATUS_1("Regular"),
    STATUS_2("Bueno"),
    STATUS_3("Muy bueno"),
    STATUS_4("Perfecto estado"),
    STATUS_5("Nuevo (Sin uso)");

    private final String statusValue;

    EnumProductStatus(String statusValue) {
        this.statusValue = statusValue;
    }

}
