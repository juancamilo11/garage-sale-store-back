package co.edu.udea.ayds2.dto.helpers.response;

public enum EnumResponseStatus {
    OK("La peitición ha sido llevada a cabo con éxito"),
    ERROR("Error en la petición");

    private final String statusText;

    EnumResponseStatus(String statusText) {
        this.statusText = statusText;
    }
}
