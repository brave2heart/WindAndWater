package com.living.bean.mingli.layout;

/**
 * Created by weihao on 2018/1/9.
 */
/*栏目数据，热门测算，放生池，大师推荐
* */
public class Column {

    private int image;
    private String columnName;
    private String moreName;
    private int rightImage;

    public Column(int image, String columnName, String moreName, int rightImage) {
        this.image = image;
        this.columnName = columnName;
        this.moreName = moreName;
        this.rightImage = rightImage;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getMoreName() {
        return moreName;
    }

    public void setMoreName(String moreName) {
        this.moreName = moreName;
    }

    public int getRightImage() {
        return rightImage;
    }

    public void setRightImage(int rightImage) {
        this.rightImage = rightImage;
    }
}
