package com.sleekbyte.tailor.listeners;

import com.sleekbyte.tailor.common.Location;
import com.sleekbyte.tailor.common.Messages;
import com.sleekbyte.tailor.common.Rules;
import com.sleekbyte.tailor.output.Printer;
import com.sleekbyte.tailor.utils.ListenerUtil;
import org.antlr.v4.runtime.Token;

import java.util.List;

/**
 * Class to analyze TODO, FIXME, MARK, ??? and !!! comments.
 */
public final class MetaCommentListener extends CommentAnalyzer {

    /**
     * Create instance of MetaCommentListener.
     *
     * @param printer     An instance of Printer
     * @param singleLineComments List of // comments
     * @param multilineComments List of /* comments
     */
    public MetaCommentListener(Printer printer, List<Token> singleLineComments, List<Token> multilineComments) {
        super(printer, singleLineComments, multilineComments);
    }

    @Override
    public void analyze() {
        checkTodoSyntaxInSingleLineComments();
        checkFixmeSyntaxInSingleLineComments();
        checkWarningSyntaxInSingleLineComments();
        checkQuestionSyntaxInSingleLineComments();
        checkMarkSyntaxInSingleLineComments();
    }

    private void checkTodoSyntaxInSingleLineComments() { // `TODO' or `@TODO'
        final String correctTodoRegex = "(?s)// @?TODO(?:\\(\\S+\\))?: \\S.*";
        filterCommentSyntax("todo", correctTodoRegex, singleLineComments);
    }

    private void checkFixmeSyntaxInSingleLineComments() { // `FIXME` or `@FIXME'
        final String correctFixmeRegex = "(?s)// @?FIXME(?:\\(\\S+\\))?: \\S.*";
        filterCommentSyntax("fix\\s?me", correctFixmeRegex, singleLineComments);
    }

    private void checkWarningSyntaxInSingleLineComments() { // `!!!'
        final String correctFixmeRegex = "(?s)// \\!\\!\\!(?:\\(\\S+\\))?: \\S.*";
        filterCommentSyntax("fix\\s?me", correctFixmeRegex, singleLineComments);
    }

    private void checkQuestionSyntaxInSingleLineComments() { // `???'
        final String correctFixmeRegex = "(?s)// @?\\?\\?\\?(?:\\(\\S+\\))?: \\S.*";
        filterCommentSyntax("fix\\s?me", correctFixmeRegex, singleLineComments);
    }

    private void checkMarkSyntaxInSingleLineComments() { // `MARK' or `MARK: -'
        final String correctMarkRegex = "(?s)// MARK:( -)? \\S.*";
        filterCommentSyntax("mark", correctMarkRegex, singleLineComments);
    }

    private void filterCommentSyntax(String occurence, String regex, List<Token> comments) {
        final String containsTodo = ".*\\b"+occurence+"\\b.*";
        comments.stream()
            .filter(token -> token.getText().trim().toLowerCase().matches(containsTodo))
            .filter(token -> !token.getText().trim().matches(regex))
            .forEach(token -> metaContentWarning(token, Messages.META_COMMENTS));
    }

    private void metaContentWarning(Token token, String commentType) {
        Location location = new Location(ListenerUtil.getTokenLocation(token).line);
        printer.warn(Rules.META_COMMENT_SYNTAX, commentType + Messages.META_COMMENT_SYNTAX, location);
    }
}
