/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.document.enumeration;

/**
 *
 * @author admin1
 */

public enum MessageEnum {
    SampleNotFound("Sample Not Found."),
    NotAllFilled("Please fill up all the fields."),
    NotAllSelected("Please make your selection"),
    RecordSaved("Record Saved Successfully"),
    NumberRequired("Please inpuot an number"),
    UnitMisMatch("Failed to update quantity due to mismatched  unit");
    
    private String msg;

    MessageEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
    
}
