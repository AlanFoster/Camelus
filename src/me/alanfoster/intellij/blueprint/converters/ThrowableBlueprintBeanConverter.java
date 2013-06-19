package me.alanfoster.intellij.blueprint.converters;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.ConvertContext;
import me.alanfoster.intellij.blueprint.dom.BlueprintBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Converter for Blueprint beans which only extend Throwable.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see BlueprintBean
 */
public class ThrowableBlueprintBeanConverter extends BlueprintBeanConverter {
    private static final String THROWABLE_QUALIFIED_NAME = "java.lang.Throwable";

    @NotNull
    @Override
    public Collection<? extends BlueprintBean> getVariants(final ConvertContext context) {
        // TODO Research if intellij uses LRU cache or such, or should i be maintaining such things myself
        final Project project = context.getProject();
        final PsiClass throwablePsiClass = getPsiClass(THROWABLE_QUALIFIED_NAME, project);
        return ContainerUtil.filter(super.getVariants(context), new Condition<BlueprintBean>() {
            @Override
            public boolean value(BlueprintBean blueprintBean) {
                final PsiClass psiClass = blueprintBean.getClassAttribute().getValue();

                return hasSuper(psiClass, throwablePsiClass);
            }
        });
    }

    /**
     * Helper class that tests if a child is an instance of a parent
     *
     * @param child The child to test for
     * @param parent The parent to test for
     * @return true if the child extends the parent class
     */
    public static boolean hasSuper(@Nullable PsiClass child, PsiClass parent) {
        if(child == null) {
            return false;
        }

        return child.equals(parent) || hasSuper(child.getSuperClass(), parent);
    }

    public static PsiClass getPsiClass(@NotNull Class<?> clazz, Project project) {
        return getPsiClass(clazz.getName(), project);
    }

    public static PsiClass getPsiClass(String qualifiedName, Project project) {
        final PsiClass psiClass = JavaPsiFacade.getInstance(project)
                .findClass(THROWABLE_QUALIFIED_NAME, GlobalSearchScope.allScope(project));

        return psiClass;
    }

}