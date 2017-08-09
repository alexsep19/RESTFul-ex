@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type = LocalDate.class, 
                        value = LocalDateXmlConverter.class)
})
package ru.rb.data;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import ru.rb.data.util.LocalDateXmlConverter;
