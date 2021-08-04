package com.slc.design.creational.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Cloneable{

    private String name;
    private String password;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
