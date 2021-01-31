package by.scand.exchangerates.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlType(namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
@XmlAccessorType(XmlAccessType.NONE)
public class RatesCubeElement {

    @XmlAttribute
    private Date time;

    @XmlAttribute
    private String currency;

    @XmlAttribute
    private BigDecimal rate;

    @XmlElement(name = "Cube")
    private List<RatesCubeElement> ratesCubeElements;
}
