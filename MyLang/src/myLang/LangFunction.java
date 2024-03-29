package myLang;

import java.util.List;

public class LangFunction implements LangCallable {
	
	private final Stmt.Function declaration;
	private final Environment closure;
	private final boolean isInitializer;
	
	LangFunction(Stmt.Function declaration, Environment closure, 
			boolean isInitializer) {
		this.closure = closure;
		this.declaration = declaration;
		this.isInitializer = isInitializer;
	}
	
	LangFunction bind(LangInstance instance) {
		Environment environment = new Environment(closure);
		environment.define("this", instance);
		return new LangFunction(declaration, environment, isInitializer);
	}
	
	@Override
	public Object call(Compiler interpreter, List<Object> arguments) {
	    Environment environment = new Environment(closure);    
	    for (int i = 0; i < declaration.params.size(); i++) {              
	      environment.define(declaration.params.get(i).lexeme,             
	          arguments.get(i));                                           
	    }
	    
	    try {                                                     
	        interpreter.executeBlock(declaration.body, environment);
	      } catch (Return returnValue) { 	    
	    	if (isInitializer) return closure.getAt(0, "this");
	        return returnValue.value;                               
	      }
	    
	    if (isInitializer) return closure.getAt(0, "this");
	    return null;                                                       
	  }
	
	@Override                          
	  public int arity() {               
	    return declaration.params.size();
	  }
	
	@Override
	public String toString() {
		return "<fn " + declaration.name.lexeme + ">";
	}
	
}
