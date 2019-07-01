/* Generated By:JavaCC: Do not edit this line. Parser.java */
package ssfzxc.json.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ssfzxc.json.JSON;
import ssfzxc.json.JSONArray;
import ssfzxc.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;

public class Parser implements ParserConstants {

    public static final Logger log = LoggerFactory.getLogger(Parser.class);

    public static JSONObject parse(String src) throws ParseException {
        Reader reader = new StringReader(src);
        return new Parser(reader).parse();
    }

    public static JSONArray parseArray(String src) throws ParseException {
        Reader reader = new StringReader(src);
        return new Parser(reader).parseArray();
    }

    public static JSONObject parse(InputStream inputStream) throws ParseException {
        return new Parser(new InputStreamReader(inputStream)).parse();
    }

    public static JSONObject parse(Reader reader) throws ParseException {
        return new Parser(reader).parse();
    }

    public static JSONArray parseArray(InputStream inputStream) throws ParseException {
        return new Parser(new InputStreamReader(inputStream)).parseArray();
    }

    public JSONObject parse() throws ParseException {
        JSONObject jsonObject = compilation_jsonobject();
        log.debug(logMsg.toString());
        return jsonObject;
    }

    public JSONArray parseArray() throws ParseException {
        JSONArray jsonArray = compilation_jsonarray();
        log.debug(logMsg.toString());
        return jsonArray;
    }

    private BigDecimal numberValue(String image) {
        return new BigDecimal(image);
    }

    // #@@range/stringValue{
    private String stringValue(String _image) throws ParseException {
        int pos = 0;
        int idx;
        StringBuffer buf = new StringBuffer();
        String image = _image.substring(1, _image.length() - 1);

        while ((idx = image.indexOf("\\", pos)) >= 0) {
            buf.append(image.substring(pos, idx));
            if (image.length() >= idx + 6
                && image.charAt(idx + 1) == 'u'
                && isHexDigits(image.charAt(idx + 2))
                && isHexDigits(image.charAt(idx + 3))
                && isHexDigits(image.charAt(idx + 4))
                && isHexDigits(image.charAt(idx + 5))) {
                buf.append(unescapeHex(image.substring(idx + 2, idx + 6)));
                pos = idx + 6;
            } else {
                buf.append(unescapeSeq(image.charAt(idx + 1)));
                pos = idx + 2;
            }
        }
        if (pos < image.length()) {
            buf.append(image.substring(pos, image.length()));
        }
        return buf.toString();
    }
    // #@@}

    private static final int charMax = Character.MAX_VALUE;
    // #@@range/unescapeHex{

    private char unescapeHex(String digits) throws ParseException {
        int i = Integer.parseInt(digits, 16);
        if (i > charMax) {
            throw new ParseException(
                "octal character sequence too big: \\" + digits);
        }
        return (char) i;
    }
    // #@@}

    // #@@range/unescapeOctal{

    private char unescapeOctal(String digits) throws ParseException {
        int i = Integer.parseInt(digits, 8);
        if (i > charMax) {
            throw new ParseException(
                "octal character sequence too big: \\" + digits);
        }
        return (char) i;
    }
    // #@@}

    // #@@range/unescapeSeq{
    private static final char bell = 7;
    private static final char backspace = 8;
    private static final char escape = 27;
    private static final char vt = 11;

    private char unescapeSeq(char c) throws ParseException {
        switch (c) {
            case '0':
                return '\0';
            case '"':
                return '"';
            case '\'':
                return '\'';
            case 'a':
                return bell;
            case 'b':
                return backspace;
            case 'e':
                return escape;
            case 'f':
                return '\f';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 't':
                return '\t';
            case 'v':
                return vt;
            default:
                throw new ParseException("unknown escape sequence: \"\\" + c);
        }
    }
    // #@@}

    // #@@range/isHexDigits() {
    private boolean isHexDigits(char c) {
        return ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'));
    }

// #@@}


    /*
     *  Grammar
     */
    final public JSONObject compilation_jsonobject() throws ParseException {
        trace_call("compilation_jsonobject");
        try {
            JSONObject jsonobject;
            jsonobject = object();
            {
                if (true) return jsonobject;
            }
            throw new Error("Missing return statement in function");
        } finally {
            trace_return("compilation_jsonobject");
        }
    }

    final public JSONArray compilation_jsonarray() throws ParseException {
        trace_call("compilation_jsonarray");
        try {
            JSONArray jsonarray;
            jsonarray = array();
            {
                if (true) return jsonarray;
            }
            throw new Error("Missing return statement in function");
        } finally {
            trace_return("compilation_jsonarray");
        }
    }

    final public JSON compilation_json() throws ParseException {
        trace_call("compilation_json");
        try {
            JSON json;
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case OBJECT_BEGIN:
                case ARRAY_BEGIN:
                    switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                        case OBJECT_BEGIN:
                            json = object();
                            break;
                        case ARRAY_BEGIN:
                            json = array();
                            break;
                        default:
                            jj_la1[0] = jj_gen;
                            jj_consume_token(-1);
                            throw new ParseException();
                    }
                    break;
                default:
                    jj_la1[1] = jj_gen;
                    throw new ParseException();
            }
            {
                if (true) return json;
            }
            throw new Error("Missing return statement in function");
        } finally {
            trace_return("compilation_json");
        }
    }

    final public JSONObject object() throws ParseException {
        trace_call("object");
        try {
            Token key;
            Object value;
            JSONObject object = new JSONObject();
            jj_consume_token(OBJECT_BEGIN);
            key = jj_consume_token(STRING);
            jj_consume_token(14);
            value = value();
            object.put(stringValue(key.image), value);
            label_1:
            while (true) {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case 15:
                        ;
                        break;
                    default:
                        jj_la1[2] = jj_gen;
                        break label_1;
                }
                jj_consume_token(15);
                key = jj_consume_token(STRING);
                jj_consume_token(14);
                value = value();
                object.put(stringValue(key.image), value);
            }
            jj_consume_token(OBJECT_END);
            {
                if (true) return object;
            }
            throw new Error("Missing return statement in function");
        } finally {
            trace_return("object");
        }
    }

    final public Object value() throws ParseException {
        trace_call("value");
        try {
            Object value;
            Token t;
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case NUMBER:
                case STRING:
                case TRUE:
                case FALSE:
                case NULL:
                case OBJECT_BEGIN:
                case ARRAY_BEGIN:
                    switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                        case OBJECT_BEGIN:
                            value = object();
                            break;
                        case ARRAY_BEGIN:
                            value = array();
                            break;
                        case STRING:
                            t = jj_consume_token(STRING);
                            value = stringValue(t.image);
                            break;
                        case NUMBER:
                            t = jj_consume_token(NUMBER);
                            value = numberValue(t.image);
                            break;
                        case TRUE:
                            jj_consume_token(TRUE);
                            value = Boolean.TRUE;
                            break;
                        case FALSE:
                            jj_consume_token(FALSE);
                            value = Boolean.FALSE;
                            break;
                        case NULL:
                            jj_consume_token(NULL);
                            value = null;
                            break;
                        default:
                            jj_la1[3] = jj_gen;
                            jj_consume_token(-1);
                            throw new ParseException();
                    }
                    break;
                default:
                    jj_la1[4] = jj_gen;
                    throw new ParseException();
            }
            {
                if (true) return value;
            }
            throw new Error("Missing return statement in function");
        } finally {
            trace_return("value");
        }
    }

    final public JSONArray array() throws ParseException {
        trace_call("array");
        try {
            JSONArray objects = new JSONArray();
            Object object;
            jj_consume_token(ARRAY_BEGIN);
            object = value();
            objects.add(object);
            label_2:
            while (true) {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case 15:
                        ;
                        break;
                    default:
                        jj_la1[5] = jj_gen;
                        break label_2;
                }
                jj_consume_token(15);
                object = value();
                objects.add(object);
            }
            jj_consume_token(ARRAY_END);
            {
                if (true) return objects;
            }
            throw new Error("Missing return statement in function");
        } finally {
            trace_return("array");
        }
    }

    /**
     * Generated Token Manager.
     */
    public ParserTokenManager token_source;
    SimpleCharStream jj_input_stream;
    /**
     * Current token.
     */
    public Token token;
    /**
     * Next token.
     */
    public Token jj_nt;
    private int jj_ntk;
    private int jj_gen;
    final private int[] jj_la1 = new int[6];
    static private int[] jj_la1_0;

    static {
        jj_la1_init_0();
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[]{0x1400, 0x1400, 0x8000, 0x17c4, 0x17c4, 0x8000,};
    }

    /**
     * Constructor with InputStream.
     */
    public Parser(java.io.InputStream stream) {
        this(stream, null);
    }

    /**
     * Constructor with InputStream and supplied encoding
     */
    public Parser(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source = new ParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream) {
        ReInit(stream, null);
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream.ReInit(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    }

    /**
     * Constructor.
     */
    public Parser(java.io.Reader stream) {
        jj_input_stream = new SimpleCharStream(stream, 1, 1);
        token_source = new ParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.Reader stream) {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    }

    /**
     * Constructor with generated Token Manager.
     */
    public Parser(ParserTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    }

    /**
     * Reinitialise.
     */
    public void ReInit(ParserTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    }

    private Token jj_consume_token(int kind) throws ParseException {
        Token oldToken;
        if ((oldToken = token).next != null) token = token.next;
        else token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        if (token.kind == kind) {
            jj_gen++;
            trace_token(token, "");
            return token;
        }
        token = oldToken;
        jj_kind = kind;
        throw generateParseException();
    }


    /**
     * Get the next Token.
     */
    final public Token getNextToken() {
        if (token.next != null) token = token.next;
        else token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        jj_gen++;
        trace_token(token, " (in getNextToken)");
        return token;
    }

    /**
     * Get the specific Token.
     */
    final public Token getToken(int index) {
        Token t = token;
        for (int i = 0; i < index; i++) {
            if (t.next != null) t = t.next;
            else t = t.next = token_source.getNextToken();
        }
        return t;
    }

    private int jj_ntk() {
        if ((jj_nt = token.next) == null)
            return (jj_ntk = (token.next = token_source.getNextToken()).kind);
        else
            return (jj_ntk = jj_nt.kind);
    }

    private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
    private int[] jj_expentry;
    private int jj_kind = -1;

    /**
     * Generate ParseException.
     */
    public ParseException generateParseException() {
        jj_expentries.clear();
        boolean[] la1tokens = new boolean[16];
        if (jj_kind >= 0) {
            la1tokens[jj_kind] = true;
            jj_kind = -1;
        }
        for (int i = 0; i < 6; i++) {
            if (jj_la1[i] == jj_gen) {
                for (int j = 0; j < 32; j++) {
                    if ((jj_la1_0[i] & (1 << j)) != 0) {
                        la1tokens[j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 16; i++) {
            if (la1tokens[i]) {
                jj_expentry = new int[1];
                jj_expentry[0] = i;
                jj_expentries.add(jj_expentry);
            }
        }
        int[][] exptokseq = new int[jj_expentries.size()][];
        for (int i = 0; i < jj_expentries.size(); i++) {
            exptokseq[i] = jj_expentries.get(i);
        }
        return new ParseException(token, exptokseq, tokenImage);
    }

    private int trace_indent = 0;
    private boolean trace_enabled = false;
    private StringBuilder logMsg = new StringBuilder("\n");

    /**
     * Enable tracing.
     */
    final public Parser enable_tracing() {
        trace_enabled = true;
        return this;
    }

    /**
     * Disable tracing.
     */
    final public Parser disable_tracing() {
        trace_enabled = false;
        return this;
    }

    private void trace_call(String s) {
        if (trace_enabled) {
            for (int i = 0; i < trace_indent; i++) {
                logMsg.append(' ');
            }
            logMsg.append("Call:   " + s + "\n");
        }
        trace_indent = trace_indent + 2;
    }

    private void trace_return(String s) {
        trace_indent = trace_indent - 2;
        if (trace_enabled) {
            for (int i = 0; i < trace_indent; i++) {
                logMsg.append(' ');
            }
            logMsg.append("Return: " + s + "\n");
        }
    }

    private void trace_token(Token t, String where) {
        if (trace_enabled) {
            for (int i = 0; i < trace_indent; i++) {
                logMsg.append(" ");
            }
            logMsg.append("Consumed token: <" + tokenImage[t.kind]);
            if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
                logMsg.append(": \"" + t.image + "\"");
            }
            logMsg.append(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where + "\n");
        }
    }

    private void trace_scan(Token t1, int t2) {
        if (trace_enabled) {
            for (int i = 0; i < trace_indent; i++) {
                logMsg.append(" ");
            }
            logMsg.append("Visited token: <" + tokenImage[t1.kind]);
            if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
                logMsg.append(": \"" + t1.image + "\"");
            }
            logMsg.append(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">\n");
        }
    }

    // #@@}
}