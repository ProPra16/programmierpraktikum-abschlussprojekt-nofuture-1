package tddcycle;

import phases.Phases;
import vk.core.api.*;

import java.util.Collection;

public class TDDCycle extends Phases{

    private TestResult tr;
    private CompilerResult cr;
    private CompilationUnit codeUnit;
    private CompilationUnit testUnit;
    private CompilationUnit akzeptanzUnit;

    public TDDCycle(String phase) {
        super(phase);
        tr = null;
        cr = null;
    }


    public void compile(String akzeptanz) {
       akzeptanzUnit = new CompilationUnit("AkzeptanztestClass", akzeptanz, true);
       JavaStringCompiler compiler = CompilerFactory.getCompiler(akzeptanzUnit);
       compiler.compileAndRunTests();
       cr = compiler.getCompilerResult();
       tr = compiler.getTestResult();
    }

    public void compile(String code, String test){
        codeUnit = new CompilationUnit("Class", code, false);
        testUnit = new CompilationUnit("TestClass", test, true);
        JavaStringCompiler compiler = CompilerFactory.getCompiler(codeUnit, testUnit);
        compiler.compileAndRunTests();
        cr = compiler.getCompilerResult();
        tr = compiler.getTestResult();
    }

    public void compile(String akzeptanz, String code, String test){

        akzeptanzUnit = new CompilationUnit("AkzeptanztestClass", akzeptanz, true);
        codeUnit = new CompilationUnit("Class", code, false);
        testUnit = new CompilationUnit("TestClass", test, true);
        JavaStringCompiler compiler = CompilerFactory.getCompiler(akzeptanzUnit, codeUnit, testUnit);
        compiler.compileAndRunTests();
        cr = compiler.getCompilerResult();
        tr = compiler.getTestResult();
    }

    public Collection<CompileError> getCompileErrorsTest(){
        return  cr.getCompilerErrorsForCompilationUnit(testUnit);
    }

    public Collection<CompileError> getCompileErrorsAkzeptanz(){
        return  cr.getCompilerErrorsForCompilationUnit(akzeptanzUnit);
    }

    public Collection<CompileError> getCompileErrorsCode(){
        return  cr.getCompilerErrorsForCompilationUnit(codeUnit);
    }

    public boolean hasCompileErrors(){
        return cr != null && cr.hasCompileErrors();
    }

    public boolean hasFailingTest(){
        return tr != null && tr.getNumberOfFailedTests() == 1;
    }
}
