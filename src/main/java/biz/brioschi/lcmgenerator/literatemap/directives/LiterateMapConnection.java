package biz.brioschi.lcmgenerator.literatemap.directives;

import lombok.Value;

@Value
public class LiterateMapConnection implements Directive {

    String targetBox;
    String description;

}
