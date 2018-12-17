package com.csk.DesignPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-11-26 18:27
 **/
public class GroupTest {
    public static void main (String[] args) {

        // 生成树根，并为其增加两个叶子节点
        Composite root = new Composite("Root");
        root.Add(new Leaf("Leaf A in Root"));
        root.Add(new Leaf("Leaf B in Root"));

        // 为根增加两个枝节点
        Components branchX = new Composite("Branch X in Root");
        Components branchY = new Composite("Branch Y in Root");
        root.Add(branchX);
        root.Add(branchY);

        // 为BranchX增加页节点
        branchX.Add(new Leaf("Leaf A in Branch X"));

        // 为BranchX增加枝节点
        Components branchZ = new Composite("Branch Z in Branch X");
        branchX.Add(branchZ);

        // 为BranchY增加叶节点
        branchY.Add(new Leaf("Leaf in Branch Y"));

        // 为BranchZ增加叶节点
        branchZ.Add(new Leaf("Leaf in Branch Z"));

        // 显示树
        root.Display(1);
    }
}

abstract class Components{
     String name;

    public Components (String name) {
        this.name = name;
    }
     abstract  void Add (Components component);
     abstract void Remove (Components component);
     abstract void Display (int level);
}

class Leaf extends Components {
    public Leaf (String name) {
        super(name);
    }
    @Override
    public void Add (Components component) {
        System.out.println("Can not add a component to a leaf.");
    }
    @Override
    public void Remove (Components component) {
        System.out.println("Can not remove a component to a leaf.");
    }
    @Override
    public void Display (int level) {
        System.out.println(new String("-"+ level)+name );
    }
}
class Composite extends Components {
    private List<Components> children = new ArrayList<>();
    public Composite (String name) {
        super(name);
    }
    @Override
    public void Add (Components component) {
        children.add(component);
    }
    @Override
    public void Remove (Components component) {
        children.remove(component);
    }
    @Override
    public void Display (int level) {
        System.out.println(new String("-"+level)+name);

        // 遍历其子节点并显示
        for(Components c :children)
        {
            c.Display(level + 2);
        }
    }

}