package com.github.mjdev.libaums;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import ax.p248p5.d;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private static final String l = "c";
    private UsbManager a;
    private UsbDeviceConnection b;
    private UsbDevice c;
    private UsbInterface d;
    private UsbEndpoint e;
    private UsbEndpoint f;
    private ax.p168k5.a g;
    private ax.p248p5.b h;
    private List<ax.p248p5.a> i = new ArrayList();
    private boolean j = false;
    private boolean k = false;

    private c(UsbManager usbManager, UsbDevice usbDevice, UsbInterface usbInterface, UsbEndpoint usbEndpoint, UsbEndpoint usbEndpoint2) {
        this.a = usbManager;
        this.c = usbDevice;
        this.d = usbInterface;
        this.e = usbEndpoint;
        this.f = usbEndpoint2;
    }

    public static c[] b(Context context) {
        UsbManager usbManager = (UsbManager) context.getSystemService("usb");
        ArrayList arrayList = new ArrayList();
        for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
            Log.i(l, "found usb device: " + usbDevice);
            int interfaceCount = usbDevice.getInterfaceCount();
            for (int i = 0; i < interfaceCount; i++) {
                UsbInterface usbInterface = usbDevice.getInterface(i);
                String str = l;
                Log.i(str, "found usb interface: " + usbInterface);
                if (usbInterface.getInterfaceClass() == 8 && usbInterface.getInterfaceSubclass() == 6 && usbInterface.getInterfaceProtocol() == 80) {
                    int endpointCount = usbInterface.getEndpointCount();
                    if (endpointCount != 2) {
                        Log.w(str, "inteface endpoint count != 2");
                    }
                    UsbEndpoint usbEndpoint = null;
                    UsbEndpoint usbEndpoint2 = null;
                    for (int i2 = 0; i2 < endpointCount; i2++) {
                        UsbEndpoint endpoint = usbInterface.getEndpoint(i2);
                        Log.i(l, "found usb endpoint: " + endpoint);
                        if (endpoint.getType() == 2) {
                            if (endpoint.getDirection() == 0) {
                                usbEndpoint = endpoint;
                            } else {
                                usbEndpoint2 = endpoint;
                            }
                        }
                    }
                    if (usbEndpoint == null || usbEndpoint2 == null) {
                        Log.e(l, "Not all needed endpoints found!");
                    } else {
                        arrayList.add(new c(usbManager, usbDevice, usbInterface, usbEndpoint2, usbEndpoint));
                    }
                } else {
                    Log.i(str, "device interface not suitable!");
                }
            }
        }
        return (c[]) arrayList.toArray(new c[0]);
    }

    private void g() throws IOException {
        for (ax.p248p5.c cVar : this.h.a()) {
            ax.p248p5.a aVarB = ax.p248p5.a.b(cVar, this.g);
            if (aVarB != null) {
                this.i.add(aVarB);
            } else if (cVar.b() == 6) {
                this.k = true;
            }
        }
    }

    private void i() throws IOException {
        String str = l;
        Log.d(str, "setup device");
        UsbDeviceConnection usbDeviceConnectionOpenDevice = this.a.openDevice(this.c);
        this.b = usbDeviceConnectionOpenDevice;
        if (usbDeviceConnectionOpenDevice == null) {
            throw new IOException("deviceConnection is null!");
        }
        if (!usbDeviceConnectionOpenDevice.claimInterface(this.d, true)) {
            throw new IOException("could not claim interface!");
        }
        ax.p296s5.b bVarA = ax.p296s5.c.a(this.b, this.f, this.e);
        byte[] bArr = new byte[1];
        this.b.controlTransfer(161, 254, 0, this.d.getId(), bArr, 1, 5000);
        Log.i(str, "MAX LUN " + ((int) bArr[0]));
        ax.p168k5.a aVarA = ax.p168k5.b.a(bVarA);
        this.g = aVarA;
        aVarA.a();
        this.h = d.a(this.g);
        g();
    }

    public void a() {
        String str = l;
        Log.d(str, "close device");
        UsbDeviceConnection usbDeviceConnection = this.b;
        if (usbDeviceConnection == null) {
            return;
        }
        if (!usbDeviceConnection.releaseInterface(this.d)) {
            Log.e(str, "could not release interface!");
        }
        this.b.close();
        this.j = false;
    }

    public List<ax.p248p5.a> c() {
        return this.i;
    }

    public UsbDevice d() {
        return this.c;
    }

    public boolean e() {
        return this.k;
    }

    public void f() throws IOException {
        if (this.a.hasPermission(this.c)) {
            i();
            this.j = true;
        } else {
            throw new IllegalStateException("Missing permission to access usb device: " + this.c);
        }
    }

    public void h() {
        UsbDeviceConnection usbDeviceConnection = this.b;
        if (usbDeviceConnection == null) {
            return;
        }
        usbDeviceConnection.claimInterface(this.d, true);
    }
}
