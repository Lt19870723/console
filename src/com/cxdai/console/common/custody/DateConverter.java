package com.cxdai.console.common.custody;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p>
 * Description: 日期转化<br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016/5/18
 * @title cxdai_interface
 * @package com.cxdai.common.custody
 */
public class DateConverter implements Converter {

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");


    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter,
                        MarshallingContext marshallingContext) {
        hierarchicalStreamWriter.setValue(formatter.format(o));
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader,
                            UnmarshallingContext unmarshallingContext) {
        try {
            return formatter.parse(hierarchicalStreamReader.getValue());
        } catch (ParseException e) {
            throw new ConversionException(e.getMessage(), e);
        }
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.isAssignableFrom(Date.class);
    }
}
