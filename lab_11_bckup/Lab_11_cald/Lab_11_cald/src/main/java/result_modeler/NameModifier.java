package result_modeler;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NameModifier {
    @XmlElement public int id;
    @XmlElement public String newName;
}
