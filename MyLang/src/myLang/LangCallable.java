package myLang;

import java.util.List;

public interface LangCallable {
	int arity();
	  Object call(Compiler compiler, List<Object> arguments);

}
