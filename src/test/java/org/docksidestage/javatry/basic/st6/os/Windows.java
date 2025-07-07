package org.docksidestage.javatry.basic.st6.os;

/**
 * Windowsオブジェクト
 * @author tahasega
 */
public class Windows extends St6OperationSystem {
    public Windows(String loginId) {
        super(loginId);
    }

    @Override
    public String getFileSeparator() {
        return "\\";
    }

    @Override
    public String getUserDirectory() {
        return "/Users/" + getLoginId();
    }
}
