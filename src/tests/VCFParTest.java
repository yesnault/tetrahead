package tests;

import gui.ConcreteFactory;

import java.lang.reflect.Method;
import java.util.*;
import core.*;
import core.modules.*;
import junit.framework.*;

public class VCFParTest extends TestCase implements ITestModule {

	// les modules utilise dans les tests
	LFO vco ;
	VCFPar vcf ;
	
	// pont vers le test Oscillo
	
	private ModuleOscillo mo ;
	public Module getTestedModule() { return vcf ; }
	public void init() { 
		try  {
			setUp() ;
		} catch(Exception e) {
			e.printStackTrace() ;
		}
	}
	public Collection<Method> getTestedMethods() {
		Collection<Method> methods = new ArrayList<Method>() ;
		try {
			// Ajouter les methodes a tester
			methods.add(this.getClass().getMethod(
					"OscilloTest_ComputeLowPass")) ;
			methods.add(this.getClass().getMethod(
					"OscilloTest_ComputeHighPass")) ;
			methods.add(this.getClass().getMethod(
					"OscilloTest_ComputeLowPassResonance")) ;
			methods.add(this.getClass().getMethod(
					"OscilloTest_ComputeHighPassResonance")) ;
			methods.add(this.getClass().getMethod(
					"OscilloTest_VCO_SawTooth_1kHz_CuteOff_3kHz")) ;
		} catch(Exception e) {}
		
		return methods ;
	}
	public void setModuleOscillo(ModuleOscillo mo) { this.mo = mo ; }
	
	protected void setUp() throws Exception {
		super.setUp();
		ConcreteFactory.setAFactory(AFactory.getInstance());
		vco = new VCO();
		vco.setFreqEch(44000) ;
		vcf = new VCFPar();
		vcf.setFreqEch(44000) ;
		
		/* On branche le VCO au VCF */
		vco.getPortOut().addPortIn(vcf.getPortIn(VCF.PORT_SIGNAL_IN));
		
		/* On branche l'oscillo à la sortie du VCF */
		vcf.getPortOut().addPortIn(mo.getPortIn(ModuleOscillo.PORT_IN)) ;
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'core.modules.VCF.compute()'
	 */
	public void testCompute() {
		
	}
	
	public void OscilloTest_ComputeLowPass() {
		/* On passe un signal carré */
		vco.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_SQUARE)
			);
		vco.getContinuousParameter(LFO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(440)) ;

		vcf.getDiscreteParameter(VCF.PARAM_BY_PASS).setCurrentValue(
				new ParameterValue(VCF.PVALUE_BYPASS_LOWPASS)	
			);
		
		// Signal carré à 440 Hz => LP à 600 doit laisser seulement
		// la sinusoïde, éventuellement atténuée 
		vcf.getContinuousParameter(VCFPar.PARAM_CUT_OFF).setCurrentValue(
				new ParameterValue(600)
			);
		vcf.getContinuousParameter(VCF.PARAM_RESONANCE).setCurrentValue(
				new ParameterValue(0.)
			);
	}
	
	public void OscilloTest_ComputeHighPass() {
		/* On passe un signal carré */
		vco.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_SQUARE)
			);
		vco.getContinuousParameter(LFO.PARAM_PITCH)
		.setCurrentValue(new ParameterValue(440)) ;

		vcf.getDiscreteParameter(VCF.PARAM_BY_PASS).setCurrentValue(
			new ParameterValue(VCF.PVALUE_BYPASS_HIGHPASS)	
		);
		
		// signal carré à 440 Hz => HP à 600 doit rendre un signal carré à 880 Hz
		vcf.getContinuousParameter(VCFPar.PARAM_CUT_OFF).setCurrentValue(
				new ParameterValue(600)
				);
		vcf.getContinuousParameter(VCF.PARAM_RESONANCE).setCurrentValue(
				new ParameterValue(0.)
				);
	}

	public void OscilloTest_ComputeLowPassResonance() {
		/* On passe un signal carré */
		vco.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_SQUARE)
			);
		vco.getContinuousParameter(LFO.PARAM_PITCH)
		.setCurrentValue(new ParameterValue(440)) ;

		vcf.getDiscreteParameter(VCF.PARAM_BY_PASS).setCurrentValue(
				new ParameterValue(VCF.PVALUE_BYPASS_LOWPASS)	
			);
		
		vcf.getContinuousParameter(VCFPar.PARAM_CUT_OFF).setCurrentValue(
				new ParameterValue(600)
				);
		vcf.getContinuousParameter(VCF.PARAM_RESONANCE).setCurrentValue(
				new ParameterValue(0.5)
				);
	}
	
	public void OscilloTest_ComputeHighPassResonance() {
		/* On passe un signal carré */
		vco.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_SQUARE)
			);
		vco.getContinuousParameter(LFO.PARAM_PITCH)
		.setCurrentValue(new ParameterValue(440)) ;

		vcf.getDiscreteParameter(VCF.PARAM_BY_PASS).setCurrentValue(
			new ParameterValue(VCF.PVALUE_BYPASS_HIGHPASS)	
		);
		
		vcf.getContinuousParameter(VCFPar.PARAM_CUT_OFF).setCurrentValue(
				new ParameterValue(600)
				);
		vcf.getContinuousParameter(VCF.PARAM_RESONANCE).setCurrentValue(
				new ParameterValue(0.0)
				);
	}

	public void OscilloTest_VCO_SawTooth_1kHz_CuteOff_3kHz() {
		/* On passe un signal carré */
		vco.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_SAWTOOTH)
			);
		vco.getContinuousParameter(LFO.PARAM_PITCH)
		.setCurrentValue(new ParameterValue(1000)) ;

		vcf.getDiscreteParameter(VCF.PARAM_BY_PASS).setCurrentValue(
			new ParameterValue(VCF.PVALUE_BYPASS_LOWPASS)	
		);
		
		vcf.getContinuousParameter(VCFPar.PARAM_CUT_OFF).setCurrentValue(
				new ParameterValue(3000)
				);
		vcf.getContinuousParameter(VCF.PARAM_RESONANCE).setCurrentValue(
				new ParameterValue(0.)
				);
	}

	/*
	 * Test method for 'core.modules.VCF.reset()'
	 */
	public void testReset() {

	}

	/*
	 * Test method for 'core.modules.VCF.VCF(IFactory)'
	 */
	public void testVCF() {
	
	}

	public void execute() {
		try {
			vco.compute();
			vcf.compute();
			mo.compute();
		}
		catch(SyntheCoreException sce) {
			System.err.println(sce.getMessage());
		}
	}

}