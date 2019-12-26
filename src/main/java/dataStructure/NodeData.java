package dataStructure;

import utils.Point3D;

public class NodeData implements node_data {
    private int k;
    private double weight;
    private String Information;
    private int Tag;
    private Point3D location;


    public NodeData(){
        this.k = 0;
        this.weight = 0;
        this.Information = null;
        this.location = null;
        this.Tag = 0;
    }

    public NodeData(int k, double weight, int Tag, Point3D point, String Information){
        this.k = k;
        this.Tag = Tag;
        this.location = new Point3D(point);
        this.Information = Information;
        this.weight = weight;
    }

    public NodeData(NodeData n){
        this.k = n.k;
        this.Tag = n.Tag;
        this.location = new Point3D(n.location);
        this.Information = n.Information;
        this.weight = n.weight;
    }


    @Override
    public int getKey(){
        return this.k;
    }

    @Override
    public Point3D getLocation(){
        return this.location;
    }

    @Override
    public void setLocation(Point3D p){
        this.location = new Point3D(p);
    }

    @Override
    public double getWeight(){
        return this.weight;
    }

    public void setWeight(double w){
        this.weight = w;
    }

    @Override
    public String getInfo(){
        return this.Information;
    }

    @Override
    public void setInfo(String s){
        this.Information = s;
    }

    @Override
    public int getTag(){
        return this.Tag;
    }

    @Override
    public void setTag(int t){
        this.Tag = t;
    }
}