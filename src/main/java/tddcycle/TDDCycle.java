package tddcycle;

import phases.Phases;
import vk.core.api.*;

import java.util.Collection;
import java.util.Objects;

public class TDDCycle extends Phases{

    public TDDCycle(String phase) {
        super(phase);
    }

    private TestResult compile(String code, String test){
        CompilationUnit codeUnit = new CompilationUnit("Class", code, false);
        CompilationUnit testUnit = new CompilationUnit("TestClass", test, true);
        JavaStringCompiler compiler = CompilerFactory.getCompiler(codeUnit, testUnit);
        compiler.compileAndRunTests();
        CompilerResult compilerResult = compiler.getCompilerResult();
        if(compilerResult.hasCompileErrors()) {
            Collection<CompileError> compileErrors = compilerResult.getCompilerErrorsForCompilationUnit(testUnit);
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

    public void next(String code, String test){
        if(Objects.equals(getPhase(), "red")){
            if(isCompiling(code, test) && isTestfailing(code, test)){
                setPhase("green");
            }
        }else if(Objects.equals(getPhase(), "green")){
            if(!isTestfailing(code, test)){
                setPhase("refactor");
            }
        }else if(Objects.equals(getPhase(), "refactor")){
            if(!isTestfailing(code, test)){
                setPhase("red");
            }
        }
    }
}
