package tddcycle;

import phases.Phases;
import vk.core.api.*;

import java.util.Collection;

public class TDDCycle extends Phases{

    public TDDCycle(String phase) {
        super(phase);
    }

    private TestResult compile(String code, String test){
        CompilationUnit codeUnit = new CompilationUnit("Code", code, false);
        CompilationUnit testUnit = new CompilationUnit("Test", test, true);
        JavaStringCompiler compiler = CompilerFactory.getCompiler(codeUnit, testUnit);
        compiler.compileAndRunTests();
        CompilerResult compilerResult = compiler.getCompilerResult();
        if(compilerResult.hasCompileErrors()) {
            Collection<CompileError> compileErrors = compilerResult.getCompilerErrorsForCompilationUnit(testUnit);
            compileErrors.addAll(compilerResult.getCompilerErrorsForCompilationUnit(codeUnit));
            compileErrors.forEach(System.out::println);
            return null;
        }
        return compiler.getTestResult();
    }

    public boolean isCompiling(String code, String test){
        TestResult t = compile(code, test);
        return t != null;
    }

    public boolean isTestfailing(String code, String test){
        TestResult t = compile(code, test);
        return t != null && t.getNumberOfFailedTests() > 0;
    }
}
