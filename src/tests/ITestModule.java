package tests;

import core.modules.Module;
import java.lang.reflect.*;
import java.util.*;

/**
 * @author  Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 */
public interface ITestModule {
	public Collection<Method> getTestedMethods() ;
	/**
	 * @uml.property  name="testedModule"
	 * @uml.associationEnd  
	 */
	public Module getTestedModule() ;
	public void init();
	public void setModuleOscillo(ModuleOscillo mo) ;
	public void execute();
}
