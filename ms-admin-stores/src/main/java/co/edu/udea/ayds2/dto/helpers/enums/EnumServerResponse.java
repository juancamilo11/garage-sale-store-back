package co.edu.udea.ayds2.dto.helpers.enums;

public enum EnumServerResponse {
    SUCCESS_RESPONSE("Success"),
    ERROR_RESPONSE("Error");

    private String description;

    EnumServerResponse(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
