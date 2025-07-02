package org.docksidestage.javatry.basic.st6.os;

/**
 * OldWindowsオブジェクト
 * @author tahasega
 */
public class OldWindows extends St6OperationSystem {
    public OldWindows(String loginId) {
        super("Windows", loginId);
    }

    @Override
    public String getFileSeparator() {
        return "\\";
    }

    @Override
    public String getUserDirectory() {
        return "/Documents and Settings/" + loginId;
    }
}
