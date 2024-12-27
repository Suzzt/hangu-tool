package com.hangu.tool.mybatis.secret.bo;

import java.lang.reflect.Field;

/**
 * @author wuzhenhong
 * @date 2024/12/27 9:48
 */
public class FieldEncryptSnapshotBo {

    /**
     * 加密字段所属的主对象
     */
    private Object containBean;
    /**
     * 加密字段信息
     */
    private Field field;
    /**
     * 加密字段未加密时的值
     */
    private Object origin;
    /**
     * 加密后的值
     */
    private Object encrypt;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Object getOrigin() {
        return origin;
    }

    public void setOrigin(Object origin) {
        this.origin = origin;
    }

    public Object getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Object encrypt) {
        this.encrypt = encrypt;
    }

    public Object getContainBean() {
        return containBean;
    }

    public void setContainBean(Object containBean) {
        this.containBean = containBean;
    }
}
