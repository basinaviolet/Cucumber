package ui.utils;

import java.util.List;

public class MenuCheckObject {
    private boolean exist;
    private List<String> foundList;

    public MenuCheckObject(boolean exist, List<String> foundList) {
        this.exist = exist;
        this.foundList = foundList;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public List<String> getFoundList() {
        return foundList;
    }

    public void setFoundList(List<String> foundList) {
        this.foundList = foundList;
    }

    @Override
    public String toString() {
        return "" +
                "exist=" + exist +
                " [" + foundList +
                ']';
    }
}
