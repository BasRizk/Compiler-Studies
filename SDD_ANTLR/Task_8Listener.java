// Generated from Task_8.g4 by ANTLR 4.8

    import java.lang.Math;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Task_8Parser}.
 */
public interface Task_8Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Task_8Parser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(Task_8Parser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link Task_8Parser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(Task_8Parser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link Task_8Parser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(Task_8Parser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Task_8Parser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(Task_8Parser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Task_8Parser#left}.
	 * @param ctx the parse tree
	 */
	void enterLeft(Task_8Parser.LeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link Task_8Parser#left}.
	 * @param ctx the parse tree
	 */
	void exitLeft(Task_8Parser.LeftContext ctx);
	/**
	 * Enter a parse tree produced by {@link Task_8Parser#right}.
	 * @param ctx the parse tree
	 */
	void enterRight(Task_8Parser.RightContext ctx);
	/**
	 * Exit a parse tree produced by {@link Task_8Parser#right}.
	 * @param ctx the parse tree
	 */
	void exitRight(Task_8Parser.RightContext ctx);
	/**
	 * Enter a parse tree produced by {@link Task_8Parser#b_}.
	 * @param ctx the parse tree
	 */
	void enterB_(Task_8Parser.B_Context ctx);
	/**
	 * Exit a parse tree produced by {@link Task_8Parser#b_}.
	 * @param ctx the parse tree
	 */
	void exitB_(Task_8Parser.B_Context ctx);
}