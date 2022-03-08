package co.edu.udea.ayds2.collection.store.product;

import co.edu.udea.ayds2.collection.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductQuestion {

    private LocalDate questionDate;
    private Local answerDate;
    private String question;
    private String response;
    private User customer;
    private User seller;

}
