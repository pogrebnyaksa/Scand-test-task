package by.scand.exchangerates.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Envelope", namespace = "http://www.gesmes.org/xml/2002-08-01")
@XmlType(propOrder = {"subject", "ratesSenderElement", "ratesCubeElement"})
@XmlAccessorType(XmlAccessType.NONE)
public class RatesRootElement {

    @XmlElement
    private String subject;

    @XmlElement(name = "Sender")
    private RatesSenderElement ratesSenderElement;

    @XmlElement(name = "Cube", namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
    private RatesCubeElement ratesCubeElement;
 }
