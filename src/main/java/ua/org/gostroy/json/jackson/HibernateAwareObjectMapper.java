package ua.org.gostroy.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * Created by panser on 5/19/14.
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

    public HibernateAwareObjectMapper() {
        registerModule(new Hibernate4Module());
    }
}
