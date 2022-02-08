package biz.brioschi.lcmgenerator.literatemap.directives;

import lombok.Value;

@Value
public class LiterateMapInvoke implements Directive {

    Integer progressiveNumber;
    String targetBox;
    String description;

}
