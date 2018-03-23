package com.chenxi.designparttern.builder;

/**
 * Created by chenxi on 2017/7/26.
 * <p>
 * //游戏角色创建控制器：指挥者
 *
 * @author chenxi
 */
public class ActorController {

    //逐步构建复杂产品对象
    public Actor construct(ActorBuilder ab) {
        Actor actor;
        ab.buildType();
        ab.buildSex();
        ab.buildFace();
        ab.buildCostume();
        ab.buildHairstyle();
        actor = ab.createActor();
        return actor;
    }

    public static void main(String[] args) {
        ActorBuilder ab = new HeroBuilder();

        // ActorController ac = new ActorController();
        // Actor actor = ac.construct(ab); //通过指挥者创建完整的建造者对象
         Actor actor = ab.construct();

        String type = actor.getType();
        System.out.println(type + "的外观：");
        System.out.println("性别：" + actor.getSex());
        System.out.println("面容：" + actor.getFace());
        System.out.println("服装：" + actor.getCostume());
        System.out.println("发型：" + actor.getHairstyle());
    }
}
