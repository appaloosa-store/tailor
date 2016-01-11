package com.sleekbyte.tailor.listeners.whitespace;

import com.sleekbyte.tailor.antlr.SwiftBaseListener;
import com.sleekbyte.tailor.antlr.SwiftParser;
import com.sleekbyte.tailor.antlr.SwiftParser.AvailabilityArgumentsContext;
import com.sleekbyte.tailor.antlr.SwiftParser.ConditionClauseContext;
import com.sleekbyte.tailor.antlr.SwiftParser.GenericArgumentListContext;
import com.sleekbyte.tailor.antlr.SwiftParser.OptionalBindingConditionContext;
import com.sleekbyte.tailor.antlr.SwiftParser.ParameterListContext;
import com.sleekbyte.tailor.antlr.SwiftParser.PatternInitializerListContext;
import com.sleekbyte.tailor.antlr.SwiftParser.RawValueStyleEnumCaseListContext;
import com.sleekbyte.tailor.antlr.SwiftParser.UnionStyleEnumCaseListContext;
import com.sleekbyte.tailor.common.Messages;
import com.sleekbyte.tailor.common.Rules;
import com.sleekbyte.tailor.output.Printer;
import com.sleekbyte.tailor.utils.ParseTreeUtil;
import com.sleekbyte.tailor.utils.WhitespaceVerifier;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

/**
 * Flags commas that are not left associated.
 */
public final class CommaWhitespaceListener extends SwiftBaseListener {

    private WhitespaceVerifier verifier;

    public CommaWhitespaceListener(Printer printer) {
        this.verifier = new WhitespaceVerifier(printer, Rules.COMMA_WHITESPACE);
    }

    @Override
    public void enterTypeInheritanceClause(SwiftParser.TypeInheritanceClauseContext ctx) {
        if (ctx.classRequirement() != null && ctx.typeInheritanceList() != null) {
            Token left = ParseTreeUtil.getStopTokenForNode(ctx.classRequirement());
            Token right = ParseTreeUtil.getStartTokenForNode(ctx.typeInheritanceList());
            Token comma = ((TerminalNodeImpl) ctx.getChild(2)).getSymbol();

            verifyCommaLeftAssociation(left, right, comma);
        }

        if (ctx.typeInheritanceList() != null) {
            checkWhitespaceAroundCommaSeparatedList(ctx.typeInheritanceList());
        }
    }

    @Override
    public void enterGenericParameterList(SwiftParser.GenericParameterListContext ctx) {
        checkWhitespaceAroundCommaSeparatedList(ctx);
    }

    @Override
    public void enterRequirementList(SwiftParser.RequirementListContext ctx) {
        checkWhitespaceAroundCommaSeparatedList(ctx);
    }


    @Override
    public void enterConditionClause(ConditionClauseContext ctx) {
        if (
            // conditionClause -> expression ',' conditionList
            (ctx.expression() != null && ctx.conditionList() != null)
            // conditionClause -> availabilityCondition ',' expression
                || (ctx.availabilityCondition() != null && ctx.expression() != null)) {

            Token left = ParseTreeUtil.getStopTokenForNode(ctx.getChild(0));
            Token right = ParseTreeUtil.getStartTokenForNode(ctx.getChild(2));
            Token comma = ParseTreeUtil.getStartTokenForNode(ctx.getChild(1));

            verifyCommaLeftAssociation(left, right, comma);
        }
        if (ctx.conditionList() != null) {
            checkWhitespaceAroundCommaSeparatedList(ctx.conditionList());
        }
    }

    @Override
    public void enterOptionalBindingCondition(OptionalBindingConditionContext ctx) {
        if (ctx.optionalBindingContinuationList() != null) {
            Token left = ParseTreeUtil.getStopTokenForNode(ctx.optionalBindingHead());
            Token right = ParseTreeUtil.getStartTokenForNode(ctx.optionalBindingContinuationList());
            Token comma = ParseTreeUtil.getStartTokenForNode(ctx.getChild(1));

            verifyCommaLeftAssociation(left, right, comma);
            checkWhitespaceAroundCommaSeparatedList(ctx.optionalBindingContinuationList());
        }
    }

    @Override
    public void enterAvailabilityArguments(AvailabilityArgumentsContext ctx) {
        checkWhitespaceAroundCommaSeparatedList(ctx);
    }

    @Override
    public void enterGenericArgumentList(GenericArgumentListContext ctx) {
        checkWhitespaceAroundCommaSeparatedList(ctx);
    }

    @Override
    public void enterPatternInitializerList(PatternInitializerListContext ctx) {
        checkWhitespaceAroundCommaSeparatedList(ctx);
    }

    @Override
    public void enterParameterList(ParameterListContext ctx) {
        checkWhitespaceAroundCommaSeparatedList(ctx);
    }

    @Override
    public void enterUnionStyleEnumCaseList(UnionStyleEnumCaseListContext ctx) {
        checkWhitespaceAroundCommaSeparatedList(ctx);
    }

    @Override
    public void enterRawValueStyleEnumCaseList(RawValueStyleEnumCaseListContext ctx) {
        checkWhitespaceAroundCommaSeparatedList(ctx);
    }

    private void checkWhitespaceAroundCommaSeparatedList(ParserRuleContext ctx) {
        for (int i = 0; i < ctx.children.size() - 2; i += 2) {
            Token left = ParseTreeUtil.getStopTokenForNode(ctx.getChild(i));
            Token right = ParseTreeUtil.getStartTokenForNode(ctx.getChild(i + 2));
            Token comma = ((TerminalNodeImpl) ctx.getChild(i + 1)).getSymbol();

            verifyCommaLeftAssociation(left, right, comma);
        }
    }

    private void verifyCommaLeftAssociation(Token left, Token right, Token comma) {
        verifier.verifyPunctuationLeftAssociation(left, right, comma, Messages.COMMA);
    }
}