package com.ben.traffic.exceptions;

import org.apache.log4j.Logger;

/**
 * Created by Ben on 10/13/2014.
 */
public class UninitializedConverterException extends Throwable {
    final static Logger LOG = Logger.getLogger(UninitializedConverterException.class);

    public UninitializedConverterException(){
        super();
    }
}
