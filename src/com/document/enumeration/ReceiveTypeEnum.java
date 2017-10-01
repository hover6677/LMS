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
public enum  ReceiveTypeEnum {
    Solution,Chemical,General_supply__direct_mtl,general_supply__indirect_mtl;
    
    public static String[] names() {
    return Arrays.toString(ReceiveTypeEnum.values()).replaceAll("^.|.$", "").replaceAll("__", " - ").replaceAll("_", " ").split(", ");
}

}


