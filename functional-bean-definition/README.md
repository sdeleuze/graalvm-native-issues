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

During GraalVM native image execution:
```
Exception in thread "main" org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'com.sample.Foo': Instantiation of bean failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.sample.Foo]: No default constructor found; nested exception is java.lang.NoSuchMethodException: com.sample.Foo.<init>()
        at java.lang.Throwable.<init>(Throwable.java:265)
        at java.lang.Exception.<init>(Exception.java:66)
        at java.lang.RuntimeException.<init>(RuntimeException.java:62)
        at org.springframework.core.NestedRuntimeException.<init>(NestedRuntimeException.java:56)
        at org.springframework.beans.BeansException.<init>(BeansException.java:40)
        at org.springframework.beans.FatalBeanException.<init>(FatalBeanException.java:35)
        at org.springframework.beans.factory.BeanCreationException.<init>(BeanCreationException.java:98)
        at org.springframework.beans.factory.BeanCreationException.<init>(BeanCreationException.java:114)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1270)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1164)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:538)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:498)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320)
        at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$2c40051508af481f3e2b011afbd5800cdeae9fd3.getObject(Unknown Source)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:846)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:863)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:546)
        at com.sample.App.main(App.java:12)
        at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:164)
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.sample.Foo]: No default constructor found; nested exception is java.lang.NoSuchMethodException: com.sample.Foo.<init>()
```

Tested with GraalVM 1.0.0 RC12