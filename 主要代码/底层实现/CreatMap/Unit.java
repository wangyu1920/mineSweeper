package com.example.saolei.CreatMap;

//基础类之一，单位格子属性功能的实现
public class Unit{

    //是否是第一个出现的雷，根据这个绘制雷的图案。
    public boolean isTheFirstMine=false;
    
    private Unit LT;
    private Unit T;
    private Unit RT;
    private Unit R;
    private Unit RB;
    private Unit B;
    private Unit LB;
    private Unit L;

    //格子的坐标
    protected int[] point;
    //格子上的数字，9代表雷
    protected int num=0;
    //格子是否被打开
    protected boolean isShow=false;
    //格子是否被标记
    protected boolean isMark=false;

    
    //标记格子，如果格子已经被标记则取消标记，如果格子被打开则返回false.
    protected boolean mark() {
        if (isShow) {
            return false;
        }
        isMark=!isMark;
        return true;
    }
    
    //打开这个格子，是雷会返回false
    protected boolean show() {
        if (!isShow) {
            isShow=true;
        }
        return num!=9;
    }
    
    //尝试打开周围的格子，无法打开返回0，成功打开返回1，打开之后出现了雷返回雷格子对象.
    protected Object showAround() {
        if (checkAroundCanShow()) {

            boolean isHaveMine=true;
            if (!LT.isMark) {
                isHaveMine= LT.show();
                if (!isHaveMine) {
                    return LT;
                }
            }
            if (!T.isMark) {
                isHaveMine= T.show();
                if (!isHaveMine) {
                    return T;
                }
            }
            if (!RT.isMark) {
                isHaveMine=((isHaveMine&&RT.show()));
                if (!isHaveMine) {
                    return RT;
                }
            }
            if (!L.isMark) {
                isHaveMine=((isHaveMine&&L.show()));
                if (!isHaveMine) {
                    return L;
                }
            }
            if (!R.isMark) {
                isHaveMine=((isHaveMine&&R.show()));
                if (!isHaveMine) {
                    return R;
                }
            }
            if (!LB.isMark) {
                isHaveMine=((isHaveMine&&LB.show()));
                if (!isHaveMine) {
                    return LB;
                }
            }
            if (!B.isMark) {
                isHaveMine=((isHaveMine&&B.show()));
                if (!isHaveMine) {
                    return B;
                }
            }
            if (!RB.isMark) {
                isHaveMine=((isHaveMine&&RB.show()));
                if (!isHaveMine) {
                    return RB;
                }
            }
            return 1;
        } else {
            return 0;
        } 
    }
    
    //判断这个格子四周的格子可不可以被打开
    protected boolean checkAroundCanShow() {
        int i=0;
        if (LT.isMark) {
            i++;
        }
        if (T.isMark) {
            i++;
        }
        if (RT.isMark) {
            i++;
        }
        if (L.isMark) {
            i++;
        }
        if (R.isMark) {
            i++;
        }
        if (LB.isMark) {
            i++;
        }
        if (B.isMark) {
            i++;
        }
        if (RB.isMark) {
            i++;
        }
        return i == num;
    }

    protected Unit(int[] point) {
        this.point = point;
    }

    protected Unit getLT() {
        return LT;
    }

    //以下所有set方法都设置了防null机制，边界默认为: num=0，isShow=true
    protected void setLT(Unit LT) {
        if (LT == null) {
            LT = new Unit(new int[]{point[0] - 1, point[1] - 1});
            LT.show();
        }
        this.LT = LT;
    }

    protected Unit getT() {
        return T;
    }

    protected void setT(Unit t) {
        if (t == null) {
            t = new Unit(new int[]{point[0] - 1, point[1]});
            t.show();
        }
        T = t;
    }

    protected Unit getRT() {
        return RT;
    }

    protected void setRT(Unit RT) {
        if (RT == null) {
            RT = new Unit(new int[]{point[0] + 1, point[1] - 1});
            RT.show();
        }
        this.RT = RT;
    }

    protected Unit getR() {
        return R;
    }

    protected void setR(Unit r) {
        if (r == null) {
            r = new Unit(new int[]{point[0], point[1] + 1});
            r.show();
        }
        R = r;
    }

    protected Unit getRB() {
        return RB;
    }

    protected void setRB(Unit RB) {
        if (RB == null) {
            RB = new Unit(new int[]{point[0] + 1, point[1] + 1});
            RB.show();
        }
        this.RB = RB;
    }

    protected Unit getB() {
        return B;
    }

    protected void setB(Unit b) {
        if (b == null) {
            b = new Unit(new int[]{point[0], point[1] + 1});
            b.show();
        }
        B = b;
    }

    protected Unit getLB() {
        return LB;
    }

    protected void setLB(Unit LB) {
        if (LB == null) {
            LB = new Unit(new int[]{point[0] - 1, point[1] + 1});
            LB.show();
        }
        this.LB = LB;
    }

    protected Unit getL() {
        return L;
    }

    protected void setL(Unit l) {
        if (l == null) {
            l = new Unit(new int[]{point[0] - 1, point[1]});
            l.show();
        }
        L = l;
    }

    public int[] getPoint() {
        return point;
    }

    public int getNum() {
        return num;
    }

    public boolean isShow() {
        return isShow;
    }

    public boolean isMark() {
        return isMark;
    }

    @Override
    public String toString() {
        return String.valueOf(num)+(isShow?"show:true":"show:false");
    }

}