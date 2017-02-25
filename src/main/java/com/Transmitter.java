package com;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Catbat on 24.02.2017.
 */
public class Transmitter {
    private String codec;
    private InetAddress dest;
    private InetAddress src;
    private String msg;

    //TODO JavaDoc

    //Constructors

    public Transmitter() {

    }

    public Transmitter(String code, InetAddress destination, InetAddress source, String msg) {
        this.codec = code;
        this.dest = destination;
        this.src = source;
        this.msg = msg;
    }

    public Transmitter(String codec, String dest, String src, String msg) {
        this.codec = codec;
        try {
            this.dest.getByName(dest);
            this.src.getByName(src);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        this.msg = msg;
    }


    //Special Methods

    public static Transmitter fromString(String input) {
        Logger log;
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            Transmitter out = new Transmitter(matcher.group(1),
                    matcher.group(2),
                    matcher.group(3),
                    matcher.group(4));
            return out;
        } else {
            return null;
        }


    }

    @Override
    public String toString() {
        return "Transmitter{" +
                "codec='" + codec + '\'' +
                ", dest='" + dest + '\'' +
                ", src='" + src + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    //gets & sets

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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


}
