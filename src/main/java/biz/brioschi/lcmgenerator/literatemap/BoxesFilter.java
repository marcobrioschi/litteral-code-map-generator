package biz.brioschi.lcmgenerator.literatemap;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BoxesFilter {

    // TODO refactoring to simplify, move the patter object at class level

    private List<String> filters;

    public BoxesFilter(List<String> filters) {
        this.filters = filters;
    }

    public List<LiterateCodeMapBox> filter(List<LiterateCodeMapBox> boxList) {
        if (filtersAreValid()) {
            Pattern filterPattern = composeNameMatchRegularExpression();
            return boxList.stream()
                    .filter(box -> filterPattern.matcher(box.getName()).matches())
                    .map(box -> new LiterateCodeMapBox(
                            box.getType(),
                            box.getName(),
                            box.getConnections().stream()
                                    .filter(boxConnection -> filterPattern.matcher(boxConnection.getTargetBoxName()).matches())
                                    .collect(Collectors.toList())
                    ))
                    .collect(Collectors.toList());
        } else {
            return boxList;
        }
    }

    private boolean filtersAreValid() {
        return (filters != null) && (filters.size() > 0);
    }

    private Pattern composeNameMatchRegularExpression() {
        return Pattern.compile("^(" + filters.stream().collect(Collectors.joining(")|(")) + ")$");
    }

}
