package biz.brioschi.lcmgenerator.diagram;

import java.util.List;

public class BoxesFilter {

    private List<String> filters;

    public BoxesFilter(List<String> filters) {
        this.filters = filters;
    }

    public List<LiterateCodeMapBox> filter(List<LiterateCodeMapBox> boxList) {
        return boxList;
    }
}
