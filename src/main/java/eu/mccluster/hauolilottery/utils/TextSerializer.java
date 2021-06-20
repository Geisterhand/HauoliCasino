package eu.mccluster.hauolilottery.utils;

import com.google.common.collect.Lists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

//https://gist.github.com/TheKnightKarim/ac27f8fd29ec28f9100d0d6428044a2f
public class TextSerializer {

    private static final int FORMATTING_CODE_LENGTH = 2;

    private static final String LOOKUP = "0123456789abcdefklmnor";
    private static final char code = '&';

    private static int findFormat(char format) {
        int pos = LOOKUP.indexOf(format);
        if (pos == -1) {
            pos = LOOKUP.indexOf(Character.toLowerCase(format));
        }

        return pos;
    }

    @Nullable
    private static TextFormatting parseFormat(char format) {
        int pos = findFormat(format);
        return pos != -1 ? TextFormatting.values()[pos] : null;
    }

    public static ITextComponent parse(String input) {
        int pos = input.length();
        if (pos < FORMATTING_CODE_LENGTH) {
            // Not enough characters to form a formatting code => plain text
            return new TextComponentString(input);
        }

        // Find the first (potential) formatting code
        int next = input.lastIndexOf(code, pos - FORMATTING_CODE_LENGTH);
        if (next == -1) {
            // No potential formatting code found => plain text
            return new TextComponentString(input);
        }

        ITextComponent current = null;
        boolean reset = false;
        List<ITextComponent> parts = Lists.newArrayList();

        do {
            // Parse the formatting code
            TextFormatting format = parseFormat(input.charAt(next + 1));
            if (format != null) {
                int from = next + FORMATTING_CODE_LENGTH;
                if (from != pos) {
                    // The plain text between the current and last formatting code
                    String content = input.substring(from, pos);

                    if (current != null) {
                        if (reset) {
                            // Color codes reset the text style so we avoid inheritance
                            // by adding directly to the root text
                            parts.add(current);
                            reset = false;
                            current = new TextComponentString(content);
                        } else {
                            // Inherit color/style
                            current = new TextComponentString(content).appendSibling(current);
                        }
                    } else {
                        current = new TextComponentString(content);
                    }
                }

                // current == null => style does not apply to any content
                if (current != null) {
                    reset |= applyStyle(current.getStyle(), format);
                }

                // Mark the current position
                pos = next;
            }

            // Search for next formatting code
            next = input.lastIndexOf(code, next - 1);
        } while (next != -1);

        if (current == null) {
            // No formatted text found
            if (pos == 0) {
                // Text contains only (redundant) formatting codes => empty text
                return new TextComponentString("");
            } else {
                // No valid formatting code found => plain text
                return new TextComponentString(input);
            }
        }

        // Return simple text if there is only one text style in the input string
        if (pos == 0 && parts.isEmpty()) {
            return current;
        }

        // Build the resulting text
        parts.add(current);
        Collections.reverse(parts);
        TextComponentString result = new TextComponentString(pos > 0 ? input.substring(0, pos) : "");
        for (ITextComponent textComponent : parts) {
            result.appendSibling(textComponent);
        }
        return result;
    }

    private static boolean applyStyle(Style textStyle, TextFormatting formatting) {
        switch (formatting) {
            case BOLD:
                textStyle.setBold(true);
                break;
            case ITALIC:
                textStyle.setItalic(true);
                break;
            case UNDERLINE:
                textStyle.setUnderlined(true);
                break;
            case STRIKETHROUGH:
                textStyle.setStrikethrough(true);
                break;
            case OBFUSCATED:
                textStyle.setObfuscated(true);
                break;
            default:
                if (textStyle.getColor() == null) {
                    textStyle.setColor(formatting);
                }
                return true;
        }

        return false;
    }

}
