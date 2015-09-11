package com.sleekbyte.tailor.common;

import com.sleekbyte.tailor.listeners.BlankLineListener;
import com.sleekbyte.tailor.listeners.BraceStyleListener;
import com.sleekbyte.tailor.listeners.CommentAnalyzer;
import com.sleekbyte.tailor.listeners.ConstantNamingListener;
import com.sleekbyte.tailor.listeners.FileListener;
import com.sleekbyte.tailor.listeners.ForceTypeCastListener;
import com.sleekbyte.tailor.listeners.KPrefixListener;
import com.sleekbyte.tailor.listeners.LowerCamelCaseListener;
import com.sleekbyte.tailor.listeners.MultipleImportListener;
import com.sleekbyte.tailor.listeners.RedundantParenthesesListener;
import com.sleekbyte.tailor.listeners.SemicolonTerminatedListener;
import com.sleekbyte.tailor.listeners.UpperCamelCaseListener;
import com.sleekbyte.tailor.listeners.WhitespaceListener;
import com.sleekbyte.tailor.utils.ArgumentParser;

/**
 * Enum for all rules implemented in Tailor.
 */
public enum Rules {
    BRACE_STYLE, COMMENT_WHITESPACE, CONSTANT_K_PREFIX, CONSTANT_NAMING, FORCED_TYPE_CAST, FUNCTION_WHITESPACE,
    LEADING_WHITESPACE, LOWER_CAMEL_CASE, MAX_CLASS_LENGTH, MAX_CLOSURE_LENGTH, MAX_FILE_LENGTH, MAX_FUNCTION_LENGTH,
    MAX_LINE_LENGTH, MAX_NAME_LENGTH, MAX_STRUCT_LENGTH, MULTIPLE_IMPORTS, REDUNDANT_PARENTHESES, TERMINATING_NEWLINE,
    TERMINATING_SEMICOLON, TRAILING_WHITESPACE, UPPER_CAMEL_CASE, WHITESPACE;

    private String name;
    private String className;
    private String description;
    private String link;
    private static final String baseStyleGuideLink = "https://github.com/sleekbyte/tailor/wiki/Rules#";

    public String getName() {
        return this.name;
    }

    public String getClassName() {
        return this.className;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLink() {
        return this.link;
    }

    static {
        BRACE_STYLE.name = "brace-style";
        BRACE_STYLE.description = "Definitions of constructs should follow the One True Brace (OTB) style.";
        BRACE_STYLE.link = baseStyleGuideLink + BRACE_STYLE.getName();
        BRACE_STYLE.className = BraceStyleListener.class.getName();

        COMMENT_WHITESPACE.name = "comment-whitespace";
        COMMENT_WHITESPACE.description = "Ensure at least one whitespace character after a comment opening symbol"
            + " (// or /*) and at least one whitespace character before a comment closing symbol (*/).";
        COMMENT_WHITESPACE.link = baseStyleGuideLink + COMMENT_WHITESPACE.getName();
        COMMENT_WHITESPACE.className = CommentAnalyzer.class.getName();

        CONSTANT_K_PREFIX.name = "constant-k-prefix";
        CONSTANT_K_PREFIX.description = "Flag constants with prefix k.";
        CONSTANT_K_PREFIX.link = baseStyleGuideLink + CONSTANT_K_PREFIX.getName();
        CONSTANT_K_PREFIX.className = KPrefixListener.class.getName();

        CONSTANT_NAMING.name = "constant-naming";
        CONSTANT_NAMING.description = "Global constants should follow either UpperCamelCase or lowerCamelCase naming "
            + "conventions. Local constants should follow lowerCamelCase naming conventions.";
        CONSTANT_NAMING.link = baseStyleGuideLink + CONSTANT_NAMING.getName();
        CONSTANT_NAMING.className = ConstantNamingListener.class.getName();

        FORCED_TYPE_CAST.name = "forced-type-cast";
        FORCED_TYPE_CAST.description = "Flag force cast usages.";
        FORCED_TYPE_CAST.link = baseStyleGuideLink + FORCED_TYPE_CAST.getName();
        FORCED_TYPE_CAST.className = ForceTypeCastListener.class.getName();

        FUNCTION_WHITESPACE.name = "function-whitespace";
        FUNCTION_WHITESPACE.description = "Every function declaration except those at the start and end of file "
            + "should have one blank line before and after itself.";
        FUNCTION_WHITESPACE.link = baseStyleGuideLink + FUNCTION_WHITESPACE.getName();
        FUNCTION_WHITESPACE.className = BlankLineListener.class.getName();

        LEADING_WHITESPACE.name = "leading-whitespace";
        LEADING_WHITESPACE.description = "Verify that source files begins with a non whitespace character.";
        LEADING_WHITESPACE.link = baseStyleGuideLink + LEADING_WHITESPACE.getName();
        LEADING_WHITESPACE.className = FileListener.class.getName();

        LOWER_CAMEL_CASE.name = "lower-camel-case";
        LOWER_CAMEL_CASE.description = "Method and variable names should follow lowerCamelCase naming convention.";
        LOWER_CAMEL_CASE.link = baseStyleGuideLink + LOWER_CAMEL_CASE.getName();
        LOWER_CAMEL_CASE.className = LowerCamelCaseListener.class.getName();

        MAX_CLASS_LENGTH.name = ArgumentParser.MAX_CLASS_LENGTH_OPT;
        MAX_CLASS_LENGTH.description = "Enforce a line limit on the lengths of class bodies.";
        MAX_CLASS_LENGTH.link = baseStyleGuideLink + MAX_CLASS_LENGTH.getName();
        MAX_CLASS_LENGTH.className = FileListener.class.getName();

        MAX_CLOSURE_LENGTH.name = ArgumentParser.MAX_CLOSURE_LENGTH_OPT;
        MAX_CLOSURE_LENGTH.description = "Enforce a line limit on the lengths of closure bodies.";
        MAX_CLOSURE_LENGTH.link = baseStyleGuideLink + MAX_CLOSURE_LENGTH.getName();
        MAX_CLOSURE_LENGTH.className = FileListener.class.getName();

        MAX_FILE_LENGTH.name = ArgumentParser.MAX_FILE_LENGTH_OPT;
        MAX_FILE_LENGTH.description = "Enforce a line limit on a file.";
        MAX_FILE_LENGTH.link = baseStyleGuideLink + MAX_FILE_LENGTH.getName();
        MAX_FILE_LENGTH.className = FileListener.class.getName();

        MAX_FUNCTION_LENGTH.name = ArgumentParser.MAX_FUNCTION_LENGTH_OPT;
        MAX_FUNCTION_LENGTH.description = "Enforce a line limit on the lengths of function bodies.";
        MAX_FUNCTION_LENGTH.link = baseStyleGuideLink + MAX_FUNCTION_LENGTH.getName();
        MAX_FUNCTION_LENGTH.className = FileListener.class.getName();

        MAX_LINE_LENGTH.name = ArgumentParser.MAX_LINE_LENGTH_LONG_OPT;
        MAX_LINE_LENGTH.description = "Enforce a character limit on the length of each line.";
        MAX_LINE_LENGTH.link = baseStyleGuideLink + MAX_LINE_LENGTH.getName();
        MAX_LINE_LENGTH.className = FileListener.class.getName();

        MAX_NAME_LENGTH.name = ArgumentParser.MAX_NAME_LENGTH_OPT;
        MAX_NAME_LENGTH.description = "Enforce a character limit on the length of each construct name.";
        MAX_NAME_LENGTH.link = baseStyleGuideLink + MAX_NAME_LENGTH.getName();
        MAX_NAME_LENGTH.className = FileListener.class.getName();

        MAX_STRUCT_LENGTH.name = ArgumentParser.MAX_STRUCT_LENGTH_OPT;
        MAX_STRUCT_LENGTH.description = "Enforce a line limit on the lengths of struct bodies.";
        MAX_STRUCT_LENGTH.link = baseStyleGuideLink + MAX_STRUCT_LENGTH.getName();
        MAX_STRUCT_LENGTH.className = FileListener.class.getName();

        MULTIPLE_IMPORTS.name = "multiple-imports";
        MULTIPLE_IMPORTS.description = "Multiple import statements should not be defined on a single line.";
        MULTIPLE_IMPORTS.link = baseStyleGuideLink + MULTIPLE_IMPORTS.getName();
        MULTIPLE_IMPORTS.className = MultipleImportListener.class.getName();

        REDUNDANT_PARENTHESES.name = "redundant-parentheses";
        REDUNDANT_PARENTHESES.description = "Control flow constructs, exception handling constructs, and "
            + "initializer(s) should not be enclosed in parentheses.";
        REDUNDANT_PARENTHESES.link = baseStyleGuideLink + REDUNDANT_PARENTHESES.getName();
        REDUNDANT_PARENTHESES.className = RedundantParenthesesListener.class.getName();

        TERMINATING_NEWLINE.name = "terminating-newline";
        TERMINATING_NEWLINE.description = "Verify that source files terminate with a single \\n character.";
        TERMINATING_NEWLINE.link = baseStyleGuideLink + TERMINATING_NEWLINE.getName();
        TERMINATING_NEWLINE.className = FileListener.class.getName();

        TERMINATING_SEMICOLON.name = "terminating-semicolon";
        TERMINATING_SEMICOLON.description = "Statements should not be terminated with semicolons.";
        TERMINATING_SEMICOLON.link = baseStyleGuideLink + TERMINATING_SEMICOLON.getName();
        TERMINATING_SEMICOLON.className = SemicolonTerminatedListener.class.getName();

        TRAILING_WHITESPACE.name = "trailing-whitespace";
        TRAILING_WHITESPACE.description = "Flag spaces or tabs after the last non-whitespace character on the line"
            + " until the newline.";
        TRAILING_WHITESPACE.link = baseStyleGuideLink + TRAILING_WHITESPACE.getName();
        TRAILING_WHITESPACE.className = FileListener.class.getName();

        UPPER_CAMEL_CASE.name = "upper-camel-case";
        UPPER_CAMEL_CASE.description = "class, enum, enum value, struct, and protocol names should follow"
            + " UpperCamelCase naming convention.";
        UPPER_CAMEL_CASE.link = baseStyleGuideLink + UPPER_CAMEL_CASE.getName();
        UPPER_CAMEL_CASE.className = UpperCamelCaseListener.class.getName();

        WHITESPACE.name = "whitespace";
        WHITESPACE.description = "Flag whitespace violations around colon (:), arrow (->), and between construct"
            + " and opening brace ({)";
        WHITESPACE.link = baseStyleGuideLink + WHITESPACE.getName();
        WHITESPACE.className = WhitespaceListener.class.getName();
    }
}
