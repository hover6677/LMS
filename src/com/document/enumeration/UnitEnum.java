/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.document.enumeration;

import java.util.Arrays;

/**
 *
 * @author admin1
 */
public enum  UnitEnum {
    kg,g,mg,L,ml,pack,undefined;
    
    public static String[] names() {
    return Arrays.toString(UnitEnum.values()).replaceAll("^.|.$", "").split(", ");
}

}


