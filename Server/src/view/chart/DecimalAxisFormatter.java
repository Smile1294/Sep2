/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart;

import javafx.util.StringConverter;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *
 * @author RobTerpilowski
 */
public class DecimalAxisFormatter extends StringConverter<Number>{

    protected DecimalFormat decimalFormat;
    
    public DecimalAxisFormatter( String format ) {
        decimalFormat = new DecimalFormat(format);
    }
    
    public DecimalAxisFormatter( DecimalFormat decimalFormat ) {
        this.decimalFormat = decimalFormat;
    }
    
    
    @Override
    public String toString(Number object) {
        return decimalFormat.format(object.doubleValue());
    }

    @Override
    public Number fromString(String string) {
        try {
            return decimalFormat.parse(string);
        } catch (ParseException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    
    
}
