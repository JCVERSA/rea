package com.box.androidsdk.content.models;

import java.util.EnumSet;

/* JADX INFO: loaded from: classes.dex */
public class BoxPermission extends BoxJsonObject {
    EnumSet<BoxItem.Permission> D() {
        EnumSet<BoxItem.Permission> enumSetNoneOf = EnumSet.noneOf(BoxItem.Permission.class);
        for (String str : n()) {
            Boolean boolO = o(str);
            if (boolO != null && boolO.booleanValue()) {
                BoxItem.Permission permission = BoxItem.Permission.CAN_DOWNLOAD;
                if (str.equals(permission.toString())) {
                    enumSetNoneOf.add(permission);
                } else {
                    BoxItem.Permission permission2 = BoxItem.Permission.CAN_UPLOAD;
                    if (str.equals(permission2.toString())) {
                        enumSetNoneOf.add(permission2);
                    } else {
                        BoxItem.Permission permission3 = BoxItem.Permission.CAN_RENAME;
                        if (str.equals(permission3.toString())) {
                            enumSetNoneOf.add(permission3);
                        } else {
                            BoxItem.Permission permission4 = BoxItem.Permission.CAN_DELETE;
                            if (str.equals(permission4.toString())) {
                                enumSetNoneOf.add(permission4);
                            } else {
                                BoxItem.Permission permission5 = BoxItem.Permission.CAN_SHARE;
                                if (str.equals(permission5.toString())) {
                                    enumSetNoneOf.add(permission5);
                                } else {
                                    BoxItem.Permission permission6 = BoxItem.Permission.CAN_SET_SHARE_ACCESS;
                                    if (str.equals(permission6.toString())) {
                                        enumSetNoneOf.add(permission6);
                                    } else {
                                        BoxItem.Permission permission7 = BoxItem.Permission.CAN_PREVIEW;
                                        if (str.equals(permission7.toString())) {
                                            enumSetNoneOf.add(permission7);
                                        } else {
                                            BoxItem.Permission permission8 = BoxItem.Permission.CAN_COMMENT;
                                            if (str.equals(permission8.toString())) {
                                                enumSetNoneOf.add(permission8);
                                            } else {
                                                BoxItem.Permission permission9 = BoxItem.Permission.CAN_INVITE_COLLABORATOR;
                                                if (str.equals(permission9.toString())) {
                                                    enumSetNoneOf.add(permission9);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return enumSetNoneOf;
    }
}
