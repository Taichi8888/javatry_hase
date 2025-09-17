/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.supercar;

import org.docksidestage.bizfw.basic.supercar.SupercarSteeringWheelManufacturer.SteeringWheel;
import org.docksidestage.bizfw.basic.supercar.exception.SteeringWheelCannotMakeException;
import org.docksidestage.bizfw.basic.supercar.exception.SupercarCannotMakeException;

/**
 * The manufacturer(製造業者) of supercar.
 * @author jflute
 */
public class SupercarManufacturer {

    private final SupercarEasyCatalog catalog = new SupercarEasyCatalog();

    public Supercar makeSupercar(String catalogKey) {
        Integer steeringWheelId = catalog.findSteeringWheelSpecId(catalogKey); // 3, has many shop

        SupercarSteeringWheelManufacturer wheelManufacturer = createSupercarSteeringWheelManufacturer();
        try {
            wheelManufacturer.makeSteeringWheel(steeringWheelId);
        } catch (SteeringWheelCannotMakeException e) {
//            throw new SupercarCannotMakeException("SupercarSteeringWheelManufacture> " + e.getMessage()); // おもいで：Caused by使おう by hase (2025/07/11)
            throw new SupercarCannotMakeException("Could not make supercar. catalogKey: " + catalogKey, e);
        }
        SteeringWheel steeringWheel = wheelManufacturer.makeSteeringWheel(steeringWheelId);

        return new Supercar(steeringWheel);
    }

    protected SupercarSteeringWheelManufacturer createSupercarSteeringWheelManufacturer() {
        return new SupercarSteeringWheelManufacturer();
    }

    public static class Supercar {

        public Supercar(SteeringWheel steeringWheel) {
            // dummy
        }
    }
}
