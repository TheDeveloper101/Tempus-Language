package myLang;

import java.util.List;
import java.util.Map;

public class LangClass implements LangCallable{
	final String name;                 
	final LangClass superclass;
	private final Map<String, LangFunction> methods;

	  LangClass(String name, LangClass superclass, 
			  Map<String, LangFunction> methods) {
		this.superclass = superclass;
	    this.name = name;                                      
	    this.methods = methods;                                
	  }
	  
	  LangFunction findMethod(String name) {
		  if (methods.containsKey(name)) {   
		      return methods.get(name);        
		    }
		  
		  if (superclass != null) {
			  return superclass.findMethod(name);
		  }

		    return null;  
	  }

	  @Override                          
	  public String toString() {         
	    return name;                     
	  }
	  
	  @Override                                                            
	  public Object call(Compiler compiler, List<Object> arguments) {
	    LangInstance instance = new LangInstance(this); 
	    LangFunction initializer = findMethod("init");
	    if (initializer != null) {
	    	initializer.bind(instance).call(compiler, arguments);
	    }
	    return instance;                                                   
	  }

	  @Override                                                            
	  public int arity() {                                                 
	    LangFunction initializer = findMethod("init");
	    if (initializer == null) return 0;
	    return initializer.arity();
	  }                                                                    
 
	  
	}                                    

