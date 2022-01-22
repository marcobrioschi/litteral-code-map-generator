package biz.brioschi.lcmgenerator.diagram;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BoxesFilter {

    private List<String> filters;

    public BoxesFilter(List<String> filters) {
        this.filters = filters;
    }

    public List<LiterateCodeMapBox> filter(List<LiterateCodeMapBox> boxList) {
        if ((filters != null) && (filters.size() > 0)) {
            String composedRegEx = "^(" + filters.stream().collect(Collectors.joining(")|(")) + ")$";
            Pattern filterPattern = Pattern.compile(composedRegEx);
            return boxList.stream()
                    .filter(box -> filterPattern.matcher(box.getName()).matches())
                    .collect(Collectors.toList());
        } else {
            return boxList;
        }
    }
}
