package com.example.saolei.CreatMap;


import java.util.ArrayList;
import java.util.Random;

public class Units{

    //雷的数量
    public int mineNum;
    //长、宽
    public int width;
    public int height;
    //存储小单元格的二维数组
    public Unit[][] units;

    public int click(int heightIndex, int widthIndex) {
        Unit unit;
        unit=units[heightIndex][widthIndex];
        System.out.println(unit);
        if (!unit.isShow) {
            boolean b = unit.mark();
            return !b ? 3 : 2;
        } else {
            return unit.showAround();
        }
    }

    //标记指定位置的格子，如果格子已经被标记则取消标记，如果格子被打开则返回false.
    public boolean mark(int heightIndex, int widthIndex) {
        return units[heightIndex][widthIndex].mark();
    }

    //打开指定位置的格子,是雷会返回false
    public boolean show(int heightIndex, int widthIndex) {
        return units[heightIndex][widthIndex].show();
    }

    //打开指定格子周围的格子，无法打开返回0，成功打开返回2，打开之后出现了雷返回3.
    public int showAround(int heightIndex, int widthIndex) {
        return units[heightIndex][widthIndex].showAround();
    }

    /**
     * 随机一个指定大小和雷数的地图
     * @param mineNum 雷数
     * @param width 地图横向长度
     * @param height 地图高度
     */
    public Units(int mineNum, int width, int height) {
        this.mineNum = mineNum;
        this.width = width;
        this.height = height;
        this.units = new Unit[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                units[i][j] = new Unit(new int[]{i, j});
            }
        }
        crateMine();
        crateMap();
    }

    //遍历，根据一个格子的四周有多少个雷为格子设置数字
    public void crateMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //统计各自四周的雷数，并把格子与四周的格子联系。
                int unitNum=0;
                units[i][j].setLT(getUnit(i - 1, j - 1));
                if (getUnit(i - 1, j - 1) != null&&getUnit(i - 1, j - 1).num==9) {
                    unitNum++;
                }
                units[i][j].setT(getUnit(i - 1, j ));
                if (getUnit(i - 1, j) != null&&getUnit(i - 1, j).num==9) {
                    unitNum++;
                }
                units[i][j].setRT(getUnit(i - 1, j + 1));
                if (getUnit(i - 1, j + 1) != null&&getUnit(i - 1, j + 1).num==9) {
                    unitNum++;
                }
                units[i][j].setL(getUnit(i, j - 1));
                if (getUnit(i, j - 1) != null&&getUnit(i, j - 1).num==9) {
                    unitNum++;
                }
                units[i][j].setR(getUnit(i , j + 1));
                if (getUnit(i, j + 1) != null&&getUnit(i, j + 1).num==9) {
                    unitNum++;
                }
                units[i][j].setLB(getUnit(i + 1, j - 1));
                if (getUnit(i + 1, j - 1) != null&&getUnit(i + 1, j - 1).num==9) {
                    unitNum++;
                }
                units[i][j].setB(getUnit(i + 1, j));
                if (getUnit(i + 1, j) != null && getUnit(i + 1, j).num == 9) {
                    unitNum++;
                }
                units[i][j].setRB(getUnit(i + 1, j + 1));
                if (getUnit(i + 1, j + 1) != null && getUnit(i + 1, j + 1).num == 9) {
                    unitNum++;
                }
                //如果不是雷格子，设置数字
                if (units[i][j].num != 9) {
                    getUnit(i,j).num=unitNum;
                }
            }
        }
    }

    //随机生成地雷
    public void crateMine() {
        ArrayList<Integer> theUnitThatIsMine = new ArrayList<>();
        for (int i = 0; i < this.mineNum; i++) {
            //存储雷格子的位置，防止重复生成雷在同一个格子上
            int theRandomInt = randomInt((height)*(width));
            while (theUnitThatIsMine.contains(theRandomInt)) {
                theRandomInt=randomInt((height)*(width));
            }
            theUnitThatIsMine.add(theRandomInt);
            units[theRandomInt/ width][theRandomInt% width].num=9;
        }
        //统计雷格子四周的雷数，雷数大于6会重新生成雷
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //如果不是雷格子，跳过检测
                if (units[i][j].num != 9) {
                    continue;
                }
                //统计各自四周的雷数
                int unitNum=0;
                if (getUnit(i - 1, j - 1) != null&&getUnit(i - 1, j - 1).num==9) {
                    unitNum++;
                }
                if (getUnit(i - 1, j) != null&&getUnit(i - 1, j).num==9) {
                    unitNum++;
                }
                if (getUnit(i - 1, j + 1) != null&&getUnit(i - 1, j + 1).num==9) {
                    unitNum++;
                }
                if (getUnit(i, j - 1) != null&&getUnit(i, j - 1).num==9) {
                    unitNum++;
                }
                if (getUnit(i, j + 1) != null&&getUnit(i, j + 1).num==9) {
                    unitNum++;
                }
                if (getUnit(i + 1, j - 1) != null&&getUnit(i + 1, j - 1).num==9) {
                    unitNum++;
                }
                if (getUnit(i + 1, j) != null && getUnit(i + 1, j).num == 9) {
                    unitNum++;
                }
                if (getUnit(i + 1, j + 1) != null && getUnit(i + 1, j + 1).num == 9) {
                    unitNum++;
                }
                if (unitNum >= 6) {
                    for (int ii = 0; ii < height; ii++) {
                        for (int jj = 0; jj < width; jj++) {
                            units[ii][jj] = new Unit(new int[]{ii, jj});
                        }
                    }
                    crateMine();
                }
            }
        }
    }
    public int randomInt(int bound) {
        Random random=new Random();
        return random.nextInt(bound);
    }

    public Unit getUnit(int w,int l) {
        if (l < 0 || l >= width || w < 0 || w >= height) {
            return null;
        }
        return units[w][l];
    }

    //重写toString方法，遍历Units产生地图
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                buffer.append(units[i][j]).append(" ");
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
}