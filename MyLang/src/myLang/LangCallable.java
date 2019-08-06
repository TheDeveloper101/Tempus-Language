package myLang;

import java.util.List;

public interface LangCallable {
	int arity();
	  Object call(Transpiler transpiler, List<Object> arguments);

}
