package com.slc.reflect.introspector;

import lombok.*;

/**
 * @author slc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Human {

    private String id;
    private String userName;
    private String password;

}
