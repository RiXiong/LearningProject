package org.zrx.springframework.samples.mvc.convert;

/**
 * Function:    SocialSecurityNumber
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 17:44
 */
public final class SocialSecurityNumber {

    private final String value;

    public SocialSecurityNumber(String value) {
        this.value = value;
    }

    @MaskFormat("###-##-####")
    public String getValue() {
        return value;
    }

    public static SocialSecurityNumber valueOf(@MaskFormat("###-##-####") String value) {
        return new SocialSecurityNumber(value);
    }

    @Override
    public String toString() {
        return super.toString();
        //return "Hello World!" + this.getValue();
    }
}
