package org.zrx.springframework.samples.mvc.convert;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

/**
 * Function:    MaskFormatAnnotationFormatterFactory 实现控制反转， 对{@link MaskFormat} 进行解析
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 17:43
 */
public class MaskFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<MaskFormat> {

    /**
     * 被注解的字段类型，字段的类型。
     * @return
     */
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> fieldTypes = new HashSet<Class<?>>(1, 1);
        fieldTypes.add(String.class);
        return fieldTypes;
    }

    /**
     * 解析器解析提交的值的字段, annotation(自定义的注解 eg MaskFormat, 字段属性名称：fieldType)
     * @param annotation
     * @param fieldType
     * @return
     */
    public Parser<?> getParser(MaskFormat annotation, Class<?> fieldType) {
        return new MaskFormatter(annotation.value());
    }

    /**
     *
     * @param annotation
     * @param fieldType
     * @return
     */
    public Printer<?> getPrinter(MaskFormat annotation, Class<?> fieldType) {
        return new MaskFormatter(annotation.value());
    }

    private static class MaskFormatter implements Formatter<String> {

        private javax.swing.text.MaskFormatter delegate;

        public MaskFormatter(String mask) {
            try {
                this.delegate = new javax.swing.text.MaskFormatter(mask);
                this.delegate.setValueContainsLiteralCharacters(false);
            } catch (ParseException e) {
                throw new IllegalStateException("Mask could not be parsed " + mask, e);
            }
        }

        public String print(String object, Locale locale) {
            try {
                return delegate.valueToString(object);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Unable to print using mask " + delegate.getMask(), e);
            }
        }

        public String parse(String text, Locale locale) throws ParseException {
            return (String) delegate.stringToValue(text);
        }

    }
}
