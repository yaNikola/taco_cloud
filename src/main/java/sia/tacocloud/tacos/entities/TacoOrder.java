package sia.tacocloud.tacos.entities;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import sia.tacocloud.tacos.entities.udt.TacoUDT;
import sia.tacocloud.tacos.utils.TacoOrderUDRUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("orders")
public class TacoOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @PrimaryKey
    private UUID id = Uuids.timeBased();
    private Date placedAt = new Date();
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    @NotBlank(message = "City is required")
    private String deliveryCity;
    @NotBlank(message = "State is required")
    private String deliveryState;
    @NotBlank(message = "Zip is required")
    @Size(max = 10, message = "Длинна не больше 10")
    private String deliveryZip;
/*    @CreditCardNumber(message = "Not a valid credit card number")*/
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
    message = "Must be formatted  MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction=0, message = "Invalid CVV")
    private String ccCVV;

    @Column("tacos")
    private List<TacoUDT> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        tacos.add(TacoOrderUDRUtils.toTacoUDT(taco));
    }
}
