package org.docksidestage.bizfw.basic.supercar.exception;

/**
 * ステアリングホイールが作れなかった時の例外クラス
 * @author tahasega
 */
public class SteeringWheelCannotMakeException extends RuntimeException {

  public SteeringWheelCannotMakeException(String message) {
    super(message);
  }
}
