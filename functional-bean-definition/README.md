Related issue on GraalVM bugtracker: [graal#TODO](https://github.com/oracle/graal/issues/TODO).

In a normal JVM, no error.

During GraalVM native image compilation

```
error: Error encountered while parsing com.oracle.svm.reflect.ClassLoader_findLoadedClass_e808259a373ab881b63621d947f9331965ab1a9e.invoke(java.lang.Object, java.lang.Object[]) 
Parsing context:
        parsing java.lang.reflect.Method.invoke(Method.java:498)
        parsing org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:154)
        parsing org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:622)
        parsing org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:607)
        parsing org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1288)
        parsing org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1127)
        parsing org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getSingletonFactoryBeanForTypeCheck(AbstractAutowireCapableBeanFactory.java:974)
        parsing org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getTypeForFactoryBean(AbstractAutowireCapableBeanFactory.java:848)
        parsing org.springframework.beans.factory.support.AbstractBeanFactory.isTypeMatch(AbstractBeanFactory.java:574)
        parsing org.springframework.beans.factory.support.AbstractBeanFactory.isTypeMatch(AbstractBeanFactory.java:602)
        parsing org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:838)
        parsing org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:546)
        parsing com.sample.App.main(App.java:12)
        parsing com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:164)
        parsing com.oracle.svm.core.code.IsolateEnterStub.JavaMainWrapper_run_5087f5482cc9a6abc971913ece43acb471d2631b(generated:0)
Original error: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: Unsupported method java.lang.ClassLoader.findLoadedClass(String) is reachable: The declaring class of this element has been substituted, but this element is not present in the substitution class
```

Tested with GraalVM 1.0.0 RC10