Related issue on GraalVM bugtracker: [graal#TODO](https://github.com/oracle/graal/issues/TODO).

You can compare bellow the output for regular JVM, GraalVM native with runtime initialization by default
and GraalVM native with build time initialization by default.

```
In regular JVM
interface kotlin.Metadata
fun kotlin.KotlinVersion.isAtLeast(kotlin.Int, kotlin.Int, kotlin.Int): kotlin.Boolean

As GraalVM native image with runtime initialization by default
interface kotlin.Metadata
Exception in thread "main" kotlin.jvm.KotlinReflectionNotSupportedError: Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath
	at kotlin.jvm.internal.ClassReference.error(ClassReference.kt:80)
	at kotlin.jvm.internal.ClassReference.getMembers(ClassReference.kt:18)
	at kotlin.reflect.full.KClasses.getFunctions(KClasses.kt:90)
	at kotlin.reflect.jvm.ReflectJvmMapping.getKotlinFunction(ReflectJvmMapping.kt:134)
	at com.sample.App.main(App.java:16)

As GraalVM native image with build time initialization by default
interface kotlin.Metadata
Exception in thread "main" java.lang.IllegalStateException: @NotNull method kotlin/reflect/jvm/internal/impl/builtins/KotlinBuiltIns.getBuiltInClassByFqName must not return null
	at kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.$$$reportNull$$$0(KotlinBuiltIns.java)
	at kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.getBuiltInClassByFqName(KotlinBuiltIns.java:357)
	at kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap.mapJavaToKotlin(JavaToKotlinClassMap.kt:130)
	at kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap.mapJavaToKotlin$default(JavaToKotlinClassMap.kt:126)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver.mapKotlinClass(JavaTypeResolver.kt:161)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver.computeTypeConstructor(JavaTypeResolver.kt:135)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver.computeSimpleJavaClassifierType(JavaTypeResolver.kt:117)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver.transformJavaClassifierType(JavaTypeResolver.kt:93)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver.transformJavaType(JavaTypeResolver.kt:52)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor$LazyJavaClassTypeConstructor.computeSupertypes(LazyJavaClassDescriptor.kt:191)
	at kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$1.invoke(AbstractTypeConstructor.kt:80)
	at kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$1.invoke(AbstractTypeConstructor.kt:26)
	at kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedLazyValue.invoke(LockBasedStorageManager.java:355)
	at kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedLazyValueWithPostCompute.invoke(LockBasedStorageManager.java:428)
	at kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedNotNullLazyValueWithPostCompute.invoke(LockBasedStorageManager.java:459)
	at kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.getSupertypes(AbstractTypeConstructor.kt:27)
	at kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.getSupertypes(AbstractTypeConstructor.kt:26)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeFunctionNames(LazyJavaClassMemberScope.kt:78)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeFunctionNames(LazyJavaClassMemberScope.kt:67)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope.computeDescriptors(LazyJavaScope.kt:361)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$allDescriptors$1.invoke(LazyJavaScope.kt:68)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$allDescriptors$1.invoke(LazyJavaScope.kt:59)
	at kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedLazyValue.invoke(LockBasedStorageManager.java:355)
	at kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedNotNullLazyValue.invoke(LockBasedStorageManager.java:474)
	at kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope.getContributedDescriptors(LazyJavaScope.kt:342)
	at kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope$DefaultImpls.getContributedDescriptors$default(ResolutionScope.kt:52)
	at kotlin.reflect.jvm.internal.KDeclarationContainerImpl.getMembers(KDeclarationContainerImpl.kt:65)
	at kotlin.reflect.jvm.internal.KClassImpl$Data$declaredNonStaticMembers$2.invoke(KClassImpl.kt:159)
	at kotlin.reflect.jvm.internal.KClassImpl$Data$declaredNonStaticMembers$2.invoke(KClassImpl.kt:44)
	at kotlin.reflect.jvm.internal.ReflectProperties$LazySoftVal.invoke(ReflectProperties.java:92)
	at kotlin.reflect.jvm.internal.ReflectProperties$Val.getValue(ReflectProperties.java:31)
	at kotlin.reflect.jvm.internal.KClassImpl$Data.getDeclaredNonStaticMembers(KClassImpl.kt)
	at kotlin.reflect.jvm.internal.KClassImpl$Data$allNonStaticMembers$2.invoke(KClassImpl.kt:168)
	at kotlin.reflect.jvm.internal.KClassImpl$Data$allNonStaticMembers$2.invoke(KClassImpl.kt:44)
	at kotlin.reflect.jvm.internal.ReflectProperties$LazySoftVal.invoke(ReflectProperties.java:92)
	at kotlin.reflect.jvm.internal.ReflectProperties$Val.getValue(ReflectProperties.java:31)
	at kotlin.reflect.jvm.internal.KClassImpl$Data.getAllNonStaticMembers(KClassImpl.kt)
	at kotlin.reflect.jvm.internal.KClassImpl$Data$allMembers$2.invoke(KClassImpl.kt:174)
	at kotlin.reflect.jvm.internal.KClassImpl$Data$allMembers$2.invoke(KClassImpl.kt:44)
	at kotlin.reflect.jvm.internal.ReflectProperties$LazySoftVal.invoke(ReflectProperties.java:92)
	at kotlin.reflect.jvm.internal.ReflectProperties$Val.getValue(ReflectProperties.java:31)
	at kotlin.reflect.jvm.internal.KClassImpl$Data.getAllMembers(KClassImpl.kt)
	at kotlin.reflect.jvm.internal.KClassImpl.getMembers(KClassImpl.kt:192)
	at kotlin.reflect.full.KClasses.getFunctions(KClasses.kt:90)
	at kotlin.reflect.jvm.ReflectJvmMapping.getKotlinFunction(ReflectJvmMapping.kt:134)
	at com.sample.App.main(App.java:16)

```

Tested with GraalVM 20.0.0.