package dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XStreamAlias("Valute")
public class Valute {
    @XStreamAlias("ID")
    @XStreamAsAttribute
    private Integer id;

    @XStreamAlias("NumCode")
    private Integer numCode;

    @XStreamAlias("CharCode")
    private String charCode;

    @XStreamAlias("Nominal")
    private Integer nominal;

    @XStreamAlias("Name")
    private String name;

    @XStreamAlias("Value")
    private Double value;
}
