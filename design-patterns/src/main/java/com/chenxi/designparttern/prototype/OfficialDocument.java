package com.chenxi.designparttern.prototype;

import java.io.*;

/**
 * Created by chenxi on 2017/7/26.
 *
 * @author chenxi
 */
public abstract class OfficialDocument implements Cloneable, Serializable {

    public OfficialDocument clone() {
        OfficialDocument srs = null;
        try {
            srs = (OfficialDocument) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("不支持复制！");
        }
        return srs;
    }

    //使用序列化技术实现深克隆
    public OfficialDocument deepClone() throws IOException, ClassNotFoundException, OptionalDataException {
        //将对象写入流中
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);

        //将对象从流中取出
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (OfficialDocument) ois.readObject();
    }

    public abstract void display();
}

/**
 * 可行性分析报告(Feasibility Analysis Report)
 */
class FAR extends OfficialDocument {
    @Override
    public void display() {
        System.out.println("《可行性分析报告》");
    }
}

/**
 * 软件需求规格说明书(Software Requirements Specification)
 */
class SRS extends OfficialDocument {
    @Override
    public void display() {
        System.out.println("《软件需求规格说明书》");
    }
}