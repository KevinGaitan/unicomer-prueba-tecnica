package ni.com.kgaitan.unicomerjamaicaclient.resource.request;

import lombok.Data;
import ni.com.kgaitan.unicomerjamaicaclient.entity.Gender;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
public class ClientRequest {
    @NotBlank(message = "first name is mandatory")
    @Length(min = 3, max = 25, message = "first name must have 3 - 25 characters")
    private String firstName;
    @NotBlank(message = "last name is mandatory")
    @Length(min = 3, max = 25, message = "last name must have 3 - 25 characters")
    private String lastName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    @NotNull(message = "gender is mandatory")
    private Gender gender;
    @Pattern(
            regexp = "^(1\\s?)?(\\d{3}|\\(\\d{3}\\))[\\s\\-]?\\d{3}[\\s\\-]?\\d{4}$",
            message = """
                    For cell phone Matches formats such as the following:
                    999-999-9999
                    (999)999-9999
                    (999) 999-9999
                    999 999 9999
                    9999999999
                    1 999 999 9999
                    """)
    @NotNull
    private String cellPhone;
    @Pattern(
            regexp = "^(1\\s?)?(\\d{3}|\\(\\d{3}\\))[\\s\\-]?\\d{3}[\\s\\-]?\\d{4}$|",
            message = """
                    For home phone Matches formats such as the following:
                    999-999-9999
                    (999)999-9999
                    (999) 999-9999
                    999 999 9999
                    9999999999
                    1 999 999 9999
                    """)
    private String homePhone;

    @Length(min = 5, max = 255, message = "Address must have 5 - 255 characters")
    @NotBlank
    private String addressHome;
    @Length(min = 3, max = 25, message = "Profession must have 3 - 25 characters")
    private String profession;

    @DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer = 16, fraction = 2)
    private BigDecimal incomes;
}
