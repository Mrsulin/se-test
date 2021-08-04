package com.slc.design.creational.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author slc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Cloneable{
    private String id;
    private String area;
    private String city;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
