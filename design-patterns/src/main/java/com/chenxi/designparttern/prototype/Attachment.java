package com.chenxi.designparttern.prototype;

import java.io.Serializable;

/**
 * Created by chenxi on 2017/7/26.
 *
 * @author chenxi
 */
public class Attachment implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void download() {
        System.out.println("下载附件，文件名为" + name);
    }
}
