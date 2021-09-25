package com.slc.design.behavioral.interpreter;


import java.util.HashMap;
import java.util.Map;

public interface Expression {
    int interpreter(Context context);
}

abstract class NonTerminalExpression implements Expression {
    Expression e1, e2;

    public NonTerminalExpression(Expression e1, Expression e2) {

        this.e1 = e1;
        this.e2 = e2;
    }
}

class MinusOperation extends NonTerminalExpression {

    public MinusOperation(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public int interpreter(Context context) {
        return this.e1.interpreter(context) - this.e2.interpreter(context);
    }
}

class TerminalExpression implements Expression {

    String variable;

    public TerminalExpression(String variable) {

        this.variable = variable;
    }

    @Override
    public int interpreter(Context context) {
        return context.lookup(this);
    }
}

class PlusOperation extends NonTerminalExpression {
    public PlusOperation(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public int interpreter(Context context) {
        return this.e1.interpreter(context) + this.e2.interpreter(context);
    }
}

class Context {
    private Map<Expression, Integer> map = new HashMap<>();

    public void add(Expression s, Integer value) {
        map.put(s, value);
    }

    public int lookup(Expression s) {
        return map.get(s);
    }
}

class Test {
    public static void main(String[] args) {

        Context context = new Context();
        TerminalExpression a = new TerminalExpression("a");
        TerminalExpression b = new TerminalExpression("b");
        TerminalExpression c = new TerminalExpression("c");
        context.add(a, 4);
        context.add(b, 8);
        context.add(c, 2);

        System.out.println(new MinusOperation(new PlusOperation(a, b), c).interpreter(context));
    }
}
