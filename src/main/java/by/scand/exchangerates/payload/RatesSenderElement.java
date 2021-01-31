package by.scand.exchangerates.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class RatesSenderElement {

    @XmlElement
    private String name;
}
