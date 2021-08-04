package com.slc.design.creational.prototype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author slc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Cloneable{

    private String name;
    private String password;

    private int asd1;
    private int asd2;
    private int asd3;
    private Address address;
    @Override
    protected Object clone() throws CloneNotSupportedException {

        User clone = (User) super.clone();
        clone.setAddress((Address) this.getAddress().clone());
        return clone;
    }
}
