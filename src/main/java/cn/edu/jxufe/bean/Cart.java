package cn.edu.jxufe.bean;


public class Cart{
    private Integer goodsId;
    private Integer num;

    public Cart(Integer goodsId, Integer num) {
        this.goodsId = goodsId;
        this.num = num;
    }

    public Cart(){

    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}