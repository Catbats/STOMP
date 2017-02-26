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
            if (dest != "null" && dest != null) {
                this.dest.getByName(dest);
            }
            if (src != "null" && src != null) {
                this.src.getByName(src);
            }


        } catch (UnknownHostException e) {
            // e.printStackTrace();
            Logger log = Logger.getLogger("transmitter");
            log.warning("UnkownHostException : " + e.getMessage());
        }

        this.msg = msg;
    }


    //Special Methods

    public static Transmitter fromString(String input) {
        Pattern pattern = Pattern.compile("'(.*?)'");

        Matcher matcher = pattern.matcher(input);
        Transmitter out = null;
        int count = 0;
        String[] found = new String[4];
        while (matcher.find()) {
            found[count] = matcher.group(1);
            count++;
        }
        out = new Transmitter(found[0], found[1], found[2], found[3]);

        return out;
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
