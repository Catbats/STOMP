package com.stomp;


import java.net.InetAddress;

/**
 * Created by Catbat on 24.02.2017.
 */
public class Transmitter {
    String codec;
    InetAddress dest;
    InetAddress src;

    public Transmitter (String code, InetAddress destination, InetAddress source){
        this.codec = code;
        this.dest = destination;
        this.src = source;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public InetAddress getDest() {
        return dest;
    }

    public void setDest(InetAddress dest) {
        this.dest = dest;
    }

    public InetAddress getSrc() {
        return src;
    }

    public void setSrc(InetAddress src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "Transmitter{" +
                "codec='" + codec + '\'' +
                ", dest=" + dest +
                ", src=" + src +
                '}';
    }

    public void fromString(String input){
       String output = new StringBuilder(input).reverse().toString();
    }
}
