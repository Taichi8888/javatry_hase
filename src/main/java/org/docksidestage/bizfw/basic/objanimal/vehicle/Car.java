package org.docksidestage.bizfw.basic.objanimal.vehicle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 車オブジェクト
 * @author jflute
 * @author tahasega
 */
public class Car implements Ridable {

    private static final Logger logger = LoggerFactory.getLogger(Car.class);

    @Override
    public void ride() {
        // dummy implementation
        logger.debug("I love my car");
    }
}
