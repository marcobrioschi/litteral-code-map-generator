package biz.brioschi.lcmgenerator.literatemap;

import lombok.NonNull;
import lombok.Value;

@Value
public class BoxBlock {

    @NonNull
    String title;

    @NonNull
    String content;

}
