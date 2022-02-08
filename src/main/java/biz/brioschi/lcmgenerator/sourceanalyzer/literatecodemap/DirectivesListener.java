package biz.brioschi.lcmgenerator.sourceanalyzer.literatecodemap;

import biz.brioschi.lcmgenerator.antlr.literatecodemap.LiterateCodeMapBaseListener;
import biz.brioschi.lcmgenerator.antlr.literatecodemap.LiterateCodeMapParser;
import biz.brioschi.lcmgenerator.antlr.literatecodemap.LiterateCodeMapParser.ParamContext;
import biz.brioschi.lcmgenerator.antlr.literatecodemap.LiterateCodeMapParser.ParamsContext;
import biz.brioschi.lcmgenerator.literatemap.directives.Directive;

import java.util.ArrayList;
import java.util.List;

public class DirectivesListener extends LiterateCodeMapBaseListener {

    private List<Directive> directives;

    public DirectivesListener() {
        this.directives = new ArrayList<>();
    }

    public List<Directive> getDirectives() {
        return directives;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Parse directives

    @Override
    public void enterLiteratemapinvoke(LiterateCodeMapParser.LiteratemapinvokeContext ctx) {
        List<Object> params = parseParams(ctx.params());
        // TODO build the directive
    }

    private List<Object> parseParams(ParamsContext paramsContext) {
        ArrayList<Object> params = new ArrayList<>();
        for (ParamContext paramContext : paramsContext.param()) {
            if (paramContext.NUMBER() != null) {
                params.add(Integer.parseInt(paramContext.NUMBER().getText()));
            } else if (paramContext.SQSTRING() != null) {
                params.add(removeQuotas(paramContext.SQSTRING().getText()));
            } else if (paramContext.DQSTRING() != null) {
                params.add(removeQuotas(paramContext.DQSTRING().getText()));
            }
        }
        return params;
    }

    private String removeQuotas(String src) {
        if (src.startsWith("'") || src.startsWith("\"")) {
            src = src.substring(1);
        }
        if (src.endsWith("'") || src.endsWith("\"")) {
            src = src.substring(0, src.length() - 1);
        }
        return src;
    }

}
